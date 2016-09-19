package be.groups.glanguage.glanguage.api.ws.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;

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
	
	@GET
	@Path("/parse/{formulaString}")
	@ApiOperation(value = "parse formula string", response = Response.class)
	public Response getAndTest(@Context ContainerRequestContext request,
								@ApiParam(value = "formulaString", required = true, defaultValue = "") 
								@PathParam("formulaString") String formulaString) {
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
		return semanticalAction.getFormulaList().get(0);
	}
	
}
