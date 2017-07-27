package be.groups.glanguage.glanguage.api.ws.resource;

import be.groups.glanguage.glanguage.api.business.action.SemanticalAction;
import be.groups.glanguage.glanguage.api.business.action.standard.AsStandard;
import be.groups.glanguage.glanguage.api.business.analysis.byaccj.SlangTab;
import be.groups.glanguage.glanguage.api.business.evaluation.PlanEvaluator;
import be.groups.glanguage.glanguage.api.business.plan.Plan;
import be.groups.glanguage.glanguage.api.business.universe.Universe;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
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
 * Web service entry points
 * <p>
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

    /**
     * Parse a {@link String} representing a {@link AbstractFormula formula} in the context of the
     * {@link be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion RuleSetVersion} identified by {@code
     * ruleSetVerionId}
     *
     * @param request          the request
     * @param formulaString    the {@link String} representing the {@link AbstractFormula formula} to be parsed
     * @param ruleSetVersionId {@link PathParam} the identifier of the
     *                         {@link be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion RuleSetVersion}
     *                         to be used to resolve rule references
     * @return a {@link Response} with the parsed {@link AbstractFormula} in the body of the message
     * @throws GLanguageException if there is a problem during parsing process
     */
    @POST
    @Path("/parse/{ruleSetVersionId}")
    @ApiOperation(value = "parse formula string", response = Response.class)
    public Response getFormulaFromString(@Context ContainerRequestContext request,
                                         @ApiParam(value = "formulaString", required = true, defaultValue = "")
                                                 String formulaString,
                                         @ApiParam(value = "ruleSetVersionId", required = true) @PathParam
                                                 ("ruleSetVersionId") Integer ruleSetVersionId) throws
                                                                                                GLanguageException {

        return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(parse(formulaString,
                                                                                                 ruleSetVersionId))
                .build();
    }

    private AbstractFormula parse(String formulaString, Integer ruleSetVersionId) throws GLanguageException {
        SemanticalAction semanticalAction = new AsStandard();
        SlangTab parser = new SlangTab(true);
        parser.setSemanticalAction(semanticalAction);
        parser.setFormulaString(formulaString);
        parser.setEvaluator(new PlanEvaluator(Universe.getPlan(ruleSetVersionId, LocalDate.now()), LocalDate.now()));
        parser.analyze();
        return semanticalAction.getFormula();
    }

    /**
     * Get the string representation of a {@link AbstractFormula formula} identified by {@code formulaId} in the
     * context of the {@link be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion RuleSetVersion}
     * identified by {@code ruleSetVerionId}
     *
     * @param request          the request
     * @param formulaId        {@link PathParam} the identifier of the the {@link AbstractFormula formula} for which
     *                         the string representation has to be returned
     * @param ruleSetVersionId {@link PathParam} the identifier of the
     *                         {@link be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion RuleSetVersion}
     *                         to be used to resolve rule references
     * @return a {@link Response} with the string representation of the {@link AbstractFormula formula}
     */
    @GET
    @Path("/formulaString/{formulaId}/{ruleSetVersionId}")
    @ApiOperation(value = "get the string representation of a formula identified by its id", response = Response.class)
    public Response getStringFromFormula(@Context ContainerRequestContext request,
                                         @ApiParam(value = "formulaId", required = true) @PathParam("formulaId")
                                                 Integer formulaId,
                                         @ApiParam(value = "ruleSetVersionId", required = true) @PathParam
                                                 ("ruleSetVersionId") Integer ruleSetVersionId,
                                         @QueryParam("effectiveDate") LocalDate effectiveDate) {

        LOG.error("Enter : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        try {
            return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(asText(formulaId,
                                                                                                      ruleSetVersionId,
                                                                                                      effectiveDate
                                                                                                              == null
                                                                                                              ?
                                                                                                              LocalDate
                                                                                                              .now()
                                                                                                              :
                                                                                                              effectiveDate))
                    .build();
        } catch (Exception e) {
            LOG.error("Exception : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            LOG.error("Unable to get the string representation of the formula identified by " + formulaId, e);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        } finally {
            LOG.error("Exit : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        }
    }

    private String asText(Integer formulaId, Integer ruleSetVersionId, LocalDate effectiveDate) {
        AbstractFormula formula = Universe.getFormula(formulaId);
        if (formula != null) {
            LOG.error("Enter Plan : " + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS")));

            Plan plan = Universe.getPlan(ruleSetVersionId, effectiveDate);

            LOG.error("Exit Plan : " + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS")));


            LOG.error("Enter Branch : " + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS")));

            plan.branch(null, formula, null);

            LOG.error("Exit Branch : " + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS")));

            return formula.asText();
        } else {
            return "";
        }
    }

}
