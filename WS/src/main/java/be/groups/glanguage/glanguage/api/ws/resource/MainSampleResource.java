package be.groups.glanguage.glanguage.api.ws.resource;

import java.time.LocalDateTime;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import be.groups.glanguage.glanguage.api.business.action.SemanticalAction;
import be.groups.glanguage.glanguage.api.business.action.standard.AsStandard;
import be.groups.glanguage.glanguage.api.business.analysis.byaccj.SlangTab;
import be.groups.glanguage.glanguage.api.business.plan.Plan;
import be.groups.glanguage.glanguage.api.business.universe.Universe;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;

/**
 * Created by boissero on 3/24/2016.
 */
@Path(value = "/glanguage")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "validation", description = "Validation interface")
public class MainSampleResource {
	protected final Logger LOG;
	
	public MainSampleResource() {
		LOG = LoggerFactory.getLogger(MainSampleResource.class);
	}
	
	@POST
	@Path("/parse")
	@ApiOperation(value = "parse formula string", response = Response.class)
	public Response getFormulaFromString(@Context ContainerRequestContext request,
								@ApiParam(value = "formulaString", required = true, defaultValue = "") 
								String formulaString) {
		initializePersistence();
		try {
			return Response.status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(
                    		parse(formulaString)
                    ).build();			
		} catch (Exception e) {
			LOG.info(e.getLocalizedMessage());
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}
	}

	private AbstractFormula parse(String formulaString) {
		SemanticalAction semanticalAction = new AsStandard();
		SlangTab parser = new SlangTab(true);
		parser.setSemanticalAction(semanticalAction);
		parser.setFormulaString(formulaString);
		parser.analyze();
		return semanticalAction.getFormula();
	}
	
	@GET
	@Path("/formulaString/{formulaId}/{ruleSetVersionId}")
	@ApiOperation(value = "get the string representation of a formula identified by its id", response = Response.class)
	public Response getStringFromFormula(@Context ContainerRequestContext request,
								@ApiParam(value = "formulaId", required = true) 
								@PathParam("formulaId") Integer formulaId,
								@ApiParam(value = "ruleSetVersionId", required = true) 
								@PathParam("ruleSetVersionId") Integer ruleSetVersionId,
								@QueryParam("effectivityDate") LocalDateTime effectivityDate) {
		initializePersistence();
		try {
			return Response.status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(
                    		asText(formulaId, ruleSetVersionId, effectivityDate)
                    ).build();			
		} catch (Exception e) {
			LOG.error("Unable to get the string representation of the formula identified by " + formulaId, e);
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}
	}
	
	private void initializePersistence() {
		JpaUtil.setEntityManager(JpaUtil.createDataSource(DatabaseIdentifier.DEVELOPMENT_DB));
	}

	private String asText(Integer formulaId, Integer ruleSetVersionId, LocalDateTime effectivityDate) {
		AbstractFormula formula = Universe.getFormula(formulaId);
		if (formula != null) {
			Plan plan = Universe.getPlan(ruleSetVersionId, effectivityDate);
			plan.branch(formula, null);
			return formula.asText();
		} else {
			return "";
		}
	}
	
}
