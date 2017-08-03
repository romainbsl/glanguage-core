package be.groups.glanguage.glanguage.api.business.evaluation;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.plan.Plan;
import be.groups.glanguage.glanguage.api.business.universe.Universe;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;
/**
 * @author michotte
 */
public class PlanEvaluatorIntegrationTest extends BaseDatabaseTest {

    /**
     * Tests {@link PlanEvaluator#evaluatePlan()}
     */
    @Test
    public void evaluatePlanTest() throws GLanguageException {
        Plan plan = Universe.getPlan(-900003, LocalDate.now());
        Object context = new Object();
        PlanEvaluator evaluator = new PlanEvaluator(plan, context);
        evaluator.evaluatePlan();
        assertFalse(plan.isBranched());
        assertNotNull(evaluator.getEvaluatedRuleVersions());
        assertFalse(evaluator.getEvaluatedRuleVersions().isEmpty());
        assertEquals(4, evaluator.getEvaluatedRuleVersions().size());
        for (RuleVersion ruleVersion : evaluator.getEvaluatedRuleVersions().keySet()) {
            switch (ruleVersion.getId()) {
                case -900005:
                    assertEquals(1500, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                case -900006:
                    assertEquals(2500, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                case -900007:
                    assertEquals(1000, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                case -900008:
                    assertEquals(1500, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                default:
                    fail("Unexepected evaluated rule version : " + ruleVersion.getId());
            }
        }
    }

    /**
     * Tests {@link PlanEvaluator#evaluatePlan()}
     */
    @Test
    public void evaluateEvaluatedPlanTest() throws GLanguageException {
        Plan plan = Universe.getPlan(-900003, LocalDate.now());
        Object context = new Object();
        plan.evaluateWithContext(context);
        assertTrue(plan.isBranched());
        PlanEvaluator evaluator = new PlanEvaluator(plan, context);
        evaluator.evaluatePlan();
        assertNotNull(evaluator.getEvaluatedRuleVersions());
        assertFalse(evaluator.getEvaluatedRuleVersions().isEmpty());
        assertEquals(4, evaluator.getEvaluatedRuleVersions().size());
        for (RuleVersion ruleVersion : evaluator.getEvaluatedRuleVersions().keySet()) {
            switch (ruleVersion.getId()) {
                case -900005:
                    assertEquals(1500, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                case -900006:
                    assertEquals(2500, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                case -900007:
                    assertEquals(1000, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                case -900008:
                    assertEquals(1500, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                default:
                    fail("Unexepected evaluated rule version : " + ruleVersion.getId());
            }
        }
    }

    /**
     * Tests {@link PlanEvaluator#evaluateRuleVersion(RuleVersion)}
     */
    @Test
    public void evaluateRuleVersionTest() throws GLanguageException {
        Plan plan = Universe.getPlan(-900003, LocalDate.now());
        Object context = new Object();
        PlanEvaluator evaluator = new PlanEvaluator(plan, context);
        evaluator.evaluateRuleVersion(plan.getEffectiveRuleVersionByRuleIdentityId("-900003"));
        assertFalse(plan.isBranched());
        assertNotNull(evaluator.getEvaluatedRuleVersions());
        assertFalse(evaluator.getEvaluatedRuleVersions().isEmpty());
        assertEquals(3, evaluator.getEvaluatedRuleVersions().size());
        for (RuleVersion ruleVersion : evaluator.getEvaluatedRuleVersions().keySet()) {
            switch (ruleVersion.getId()) {
                case -900006:
                    assertEquals(2500, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                case -900007:
                    assertEquals(1000, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                case -900008:
                    assertEquals(1500, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                default:
                    fail("Unexepected evaluated rule version : " + ruleVersion.getId());
            }
        }
    }

    /**
     * Tests {@link PlanEvaluator#evaluateRuleVersion(RuleVersion)}
     */
    @Test
    public void evaluateEvaluatedRuleVersionTest() throws GLanguageException {
        Plan plan = Universe.getPlan(-900003, LocalDate.now());
        Object context = new Object();
        plan.evaluateWithContext(context, "-900003", false);
        PlanEvaluator evaluator = new PlanEvaluator(plan, context);
        evaluator.evaluateRuleVersion(plan.getEffectiveRuleVersionByRuleIdentityId("-900003"));
        assertNotNull(evaluator.getEvaluatedRuleVersions());
        assertFalse(evaluator.getEvaluatedRuleVersions().isEmpty());
        assertEquals(3, evaluator.getEvaluatedRuleVersions().size());
        for (RuleVersion ruleVersion : evaluator.getEvaluatedRuleVersions().keySet()) {
            switch (ruleVersion.getId()) {
                case -900006:
                    assertEquals(2500, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                case -900007:
                    assertEquals(1000, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                case -900008:
                    assertEquals(1500, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                default:
                    fail("Unexepected evaluated rule version : " + ruleVersion.getId());
            }
        }
    }

    /**
     * Tests {@link PlanEvaluator#evaluateRuleVersion(String, boolean)}
     */
    @Test
    public void evaluateRuleVersionByRuleIdentifierTest() throws GLanguageException {
        Plan plan = Universe.getPlan(-900003, LocalDate.now());
        Object context = new Object();
        PlanEvaluator evaluator = new PlanEvaluator(plan, context);
        evaluator.evaluateRuleVersion("-900003", false);
        assertFalse(plan.isBranched());
        assertNotNull(evaluator.getEvaluatedRuleVersions());
        assertFalse(evaluator.getEvaluatedRuleVersions().isEmpty());
        assertEquals(3, evaluator.getEvaluatedRuleVersions().size());
        for (RuleVersion ruleVersion : evaluator.getEvaluatedRuleVersions().keySet()) {
            switch (ruleVersion.getId()) {
                case -900006:
                    assertEquals(2500, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                case -900007:
                    assertEquals(1000, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                case -900008:
                    assertEquals(1500, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                default:
                    fail("Unexepected evaluated rule version : " + ruleVersion.getId());
            }
        }
    }

    /**
     * Tests {@link PlanEvaluator#evaluateRuleVersion(String, boolean)}
     */
    @Test
    public void evaluateEvaluatedRuleVersionByRuleIdentifierTest() throws GLanguageException {
        Plan plan = Universe.getPlan(-900003, LocalDate.now());
        Object context = new Object();
        plan.evaluateWithContext(context, "-900003", false);
        PlanEvaluator evaluator = new PlanEvaluator(plan, context);
        evaluator.evaluateRuleVersion("-900003", false);
        assertFalse(plan.isBranched());
        assertNotNull(evaluator.getEvaluatedRuleVersions());
        assertFalse(evaluator.getEvaluatedRuleVersions().isEmpty());
        assertEquals(3, evaluator.getEvaluatedRuleVersions().size());
        for (RuleVersion ruleVersion : evaluator.getEvaluatedRuleVersions().keySet()) {
            switch (ruleVersion.getId()) {
                case -900006:
                    assertEquals(2500, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                case -900007:
                    assertEquals(1000, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                case -900008:
                    assertEquals(1500, evaluator.getRuleVersionValue(ruleVersion));
                    break;
                default:
                    fail("Unexepected evaluated rule version : " + ruleVersion.getId());
            }
        }
    }

}