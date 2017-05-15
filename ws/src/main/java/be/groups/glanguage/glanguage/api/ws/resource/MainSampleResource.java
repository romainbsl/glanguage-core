package be.groups.glanguage.glanguage.api.ws.resource;

import be.groups.glanguage.glanguage.api.business.action.SemanticalAction;
import be.groups.glanguage.glanguage.api.business.action.standard.AsStandard;
import be.groups.glanguage.glanguage.api.business.analysis.byaccj.SlangTab;
import be.groups.glanguage.glanguage.api.business.plan.Plan;
import be.groups.glanguage.glanguage.api.business.universe.Universe;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                                                 String formulaString) throws GLanguageException {
        initializePersistence();
        return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(parse(formulaString))
                .build();
    }

    private AbstractFormula parse(String formulaString) throws GLanguageException {
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
                                         @ApiParam(value = "formulaId", required = true) @PathParam("formulaId")
                                                 Integer formulaId,
                                         @ApiParam(value = "ruleSetVersionId", required = true) @PathParam
                                                 ("ruleSetVersionId") Integer ruleSetVersionId,
                                         @QueryParam("effectivityDate") LocalDate effectivityDate) {

        LOG.error("Enter : " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        initializePersistence();
        try {
            return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(asText(formulaId,
                    ruleSetVersionId,
                    effectivityDate
                            == null
                            ?
                            LocalDate
                                    .now()
                            :
                            effectivityDate))
                    .build();
        } catch (Exception e) {
            LOG.error("Exception : "+
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            LOG.error("Unable to get the string representation of the formula identified by " + formulaId, e);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        } finally {
            LOG.error("Exit : " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        }
    }

    private void initializePersistence() {
        LOG.error("Enter Init Persistence : " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS")));

        JpaUtil.setCentralEntityManager(JpaUtil.createDataSource(DatabaseIdentifier.DEVELOPMENT_DB));

        LOG.error("Exit Init Persistence : " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS")));
    }

    private String asText(Integer formulaId, Integer ruleSetVersionId, LocalDate effectivityDate) {
        AbstractFormula formula = Universe.getFormula(formulaId);
        if (formula != null) {
            LOG.error("Enter Plan : " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS")));

            Plan plan = Universe.getPlan(ruleSetVersionId, effectivityDate);

//            plan.setRuleVersions(RuleVersionFactory
//                    .getRuleVersions(ruleSetVersionId, effectivityDate != null ? effectivityDate : LocalDate.now()));

            LOG.error("Exit Plan : " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS")));


            LOG.error("Enter Branch : " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS")));

            plan.branch(null, formula, null);

            LOG.error("Exit Branch : " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS")));

            return formula.asText();
        } else {
            return "";
        }
    }

}
