package be.groups.glanguage.core.business.evaluation;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.plan.Plan;
import be.groups.glanguage.core.business.universe.Universe;
import be.groups.glanguage.core.entities.rule.RuleVersion;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.Assert.*;
/**
 * @author michotte
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class PlanEvaluatorIntegrationTest extends IntegrationTest {

  @Autowired
  private Universe universe;
    
    /**
     * Tests {@link PlanEvaluator#evaluatePlan()}
     */
    @Test
    @Transactional
    public void evaluatePlanTest() throws GLanguageException {
      Plan plan = universe.getPlan(-900003L, LocalDate.now());
        Object context = new Object();
        PlanEvaluator evaluator = new PlanEvaluator(plan, context);
        evaluator.evaluatePlan();
        assertFalse(plan.isBranched());
        assertNotNull(evaluator.getEvaluatedRuleVersions());
        assertFalse(evaluator.getEvaluatedRuleVersions().isEmpty());
        assertEquals(4, evaluator.getEvaluatedRuleVersions().size());
        for (RuleVersion ruleVersion : evaluator.getEvaluatedRuleVersions().keySet()) {
            if (ruleVersion.getId() ==  -900005L) {
                Assert.assertEquals(1500, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else if (ruleVersion.getId() ==  -900006L) {
                Assert.assertEquals(2500, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else if (ruleVersion.getId() ==  -900007L) {
                Assert.assertEquals(1000, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else if (ruleVersion.getId() ==  -900008L) {
                Assert.assertEquals(1500, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else {
                fail("Unexepected evaluated rule version : " + ruleVersion.getId());
            }
        }
    }

    /**
     * Tests {@link PlanEvaluator#evaluatePlan()}
     */
    @Test
    @Transactional
    public void evaluateEvaluatedPlanTest() throws GLanguageException {
      Plan plan = universe.getPlan(-900003L, LocalDate.now());
        Object context = new Object();
        plan.evaluateWithContext(context);
        assertTrue(plan.isBranched());
        PlanEvaluator evaluator = new PlanEvaluator(plan, context);
        evaluator.evaluatePlan();
        assertNotNull(evaluator.getEvaluatedRuleVersions());
        assertFalse(evaluator.getEvaluatedRuleVersions().isEmpty());
        assertEquals(4, evaluator.getEvaluatedRuleVersions().size());
        for (RuleVersion ruleVersion : evaluator.getEvaluatedRuleVersions().keySet()) {
            if (ruleVersion.getId() ==  -900005L) {
                Assert.assertEquals(1500, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else if (ruleVersion.getId() ==  -900006L) {
                Assert.assertEquals(2500, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else if (ruleVersion.getId() ==  -900007L) {
                Assert.assertEquals(1000, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else if (ruleVersion.getId() ==  -900008L) {
                Assert.assertEquals(1500, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else {
                fail("Unexepected evaluated rule version : " + ruleVersion.getId());
            }
        }
    }

    /**
     * Tests {@link PlanEvaluator#evaluateRuleVersion(RuleVersion)}
     */
    @Test
    @Transactional
    public void evaluateRuleVersionTest() throws GLanguageException {
      Plan plan = universe.getPlan(-900003L, LocalDate.now());
        Object context = new Object();
        PlanEvaluator evaluator = new PlanEvaluator(plan, context);
        evaluator.evaluateRuleVersion(plan.getEffectiveRuleVersionByRuleIdentityId("-900003"));
        assertFalse(plan.isBranched());
        assertNotNull(evaluator.getEvaluatedRuleVersions());
        assertFalse(evaluator.getEvaluatedRuleVersions().isEmpty());
        assertEquals(3, evaluator.getEvaluatedRuleVersions().size());
        for (RuleVersion ruleVersion : evaluator.getEvaluatedRuleVersions().keySet()) {
            if (ruleVersion.getId() ==  -900006L) {
                Assert.assertEquals(2500, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else if (ruleVersion.getId() ==  -900007L) {
                Assert.assertEquals(1000, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else if (ruleVersion.getId() ==  -900008L) {
                Assert.assertEquals(1500, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else {
                fail("Unexepected evaluated rule version : " + ruleVersion.getId());
            }
        }
    }

    /**
     * Tests {@link PlanEvaluator#evaluateRuleVersion(RuleVersion)}
     */
    @Test
    @Transactional
    public void evaluateEvaluatedRuleVersionTest() throws GLanguageException {
      Plan plan = universe.getPlan(-900003L, LocalDate.now());
        Object context = new Object();
        plan.evaluateWithContext(context, "-900003", false);
        PlanEvaluator evaluator = new PlanEvaluator(plan, context);
        evaluator.evaluateRuleVersion(plan.getEffectiveRuleVersionByRuleIdentityId("-900003"));
        assertNotNull(evaluator.getEvaluatedRuleVersions());
        assertFalse(evaluator.getEvaluatedRuleVersions().isEmpty());
        assertEquals(3, evaluator.getEvaluatedRuleVersions().size());
        for (RuleVersion ruleVersion : evaluator.getEvaluatedRuleVersions().keySet()) {
            if (ruleVersion.getId() ==  -900006L) {
                Assert.assertEquals(2500, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else if (ruleVersion.getId() ==  -900007L) {
                Assert.assertEquals(1000, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else if (ruleVersion.getId() ==  -900008L) {
                Assert.assertEquals(1500, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else {
                fail("Unexepected evaluated rule version : " + ruleVersion.getId());
            }
        }
    }

    /**
     * Tests {@link PlanEvaluator#evaluateRuleVersion(String, boolean)}
     */
    @Test
    @Transactional
    public void evaluateRuleVersionByRuleIdentifierTest() throws GLanguageException {
      Plan plan = universe.getPlan(-900003L, LocalDate.now());
        Object context = new Object();
        PlanEvaluator evaluator = new PlanEvaluator(plan, context);
        evaluator.evaluateRuleVersion("-900003", false);
        assertFalse(plan.isBranched());
        assertNotNull(evaluator.getEvaluatedRuleVersions());
        assertFalse(evaluator.getEvaluatedRuleVersions().isEmpty());
        assertEquals(3, evaluator.getEvaluatedRuleVersions().size());
        for (RuleVersion ruleVersion : evaluator.getEvaluatedRuleVersions().keySet()) {
            if (ruleVersion.getId() ==  -900006L) {
                Assert.assertEquals(2500, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else if (ruleVersion.getId() ==  -900007L) {
                Assert.assertEquals(1000, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else if (ruleVersion.getId() ==  -900008L) {
                Assert.assertEquals(1500, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else {
                fail("Unexepected evaluated rule version : " + ruleVersion.getId());
            }
        }
    }

    /**
     * Tests {@link PlanEvaluator#evaluateRuleVersion(String, boolean)}
     */
    @Test
    @Transactional
    public void evaluateEvaluatedRuleVersionByRuleIdentifierTest() throws GLanguageException {
      Plan plan = universe.getPlan(-900003L, LocalDate.now());
        Object context = new Object();
        plan.evaluateWithContext(context, "-900003", false);
        PlanEvaluator evaluator = new PlanEvaluator(plan, context);
        evaluator.evaluateRuleVersion("-900003", false);
        assertFalse(plan.isBranched());
        assertNotNull(evaluator.getEvaluatedRuleVersions());
        assertFalse(evaluator.getEvaluatedRuleVersions().isEmpty());
        assertEquals(3, evaluator.getEvaluatedRuleVersions().size());
        for (RuleVersion ruleVersion : evaluator.getEvaluatedRuleVersions().keySet()) {
            if (ruleVersion.getId() ==  -900006L) {
                Assert.assertEquals(2500, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else if (ruleVersion.getId() ==  -900007L) {
                Assert.assertEquals(1000, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else if (ruleVersion.getId() ==  -900008L) {
                Assert.assertEquals(1500, evaluator.getRuleVersionValue(ruleVersion));
                break;
            } else {
                fail("Unexepected evaluated rule version : " + ruleVersion.getId());
            }
        }
    }

}