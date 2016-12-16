package be.groups.glanguage.glanguage.api.business.evaluation;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.plan.Plan;
import be.groups.glanguage.glanguage.api.business.universe.Universe;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;
/**
 * Created by michotte on 16/12/2016.
 */
public class PlanEvaluatorTest extends BaseDatabaseTest {

    /**
     * Tests {@link PlanEvaluator#evaluatePlan()}
     */
    @Test
    public void evaluatePlanTest() {
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
    public void evaluateEvaluatedPlanTest() {
        Plan plan = Universe.getPlan(-900003, LocalDate.now());
        Object context = new Object();
        plan.evaluate(context);
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
    public void evaluateRuleVersionTest() {
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
    public void evaluateEvaluatedRuleVersionTest() {
        Plan plan = Universe.getPlan(-900003, LocalDate.now());
        Object context = new Object();
        plan.evaluate(context, "-900003", false);
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
    public void evaluateRuleVersionByRuleIdentifierTest() {
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
    public void evaluateEvaluatedRuleVersionByRuleIdentifierTest() {
        Plan plan = Universe.getPlan(-900003, LocalDate.now());
        Object context = new Object();
        plan.evaluate(context, "-900003", false);
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