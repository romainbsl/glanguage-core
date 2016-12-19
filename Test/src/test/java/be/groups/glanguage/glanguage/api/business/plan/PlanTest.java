package be.groups.glanguage.glanguage.api.business.plan;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.universe.Universe;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.Assert.*;

public class PlanTest extends BaseDatabaseTest {

    /**
     * Tests {@link Plan#evaluate()}}
     */
    @Test
    public void testEvaluate() {
        LocalDate effectivityDate = LocalDate.now();
        Plan plan = Universe.getPlan(-900003, effectivityDate);
        plan.initEvaluation();
        assertNotNull(plan);
        assertNotNull(plan.getRuleVersions());
        assertFalse(plan.getRuleVersions().isEmpty());
        assertFalse(plan.isBranched());
        Object object = null;
        Map<RuleVersion, Object> evaluatedRuleVersions = plan.evaluateWithContext(object);
        assertTrue(plan.isBranched());
        assertNotNull(evaluatedRuleVersions);
        assertFalse(evaluatedRuleVersions.isEmpty());
        assertEquals(4, evaluatedRuleVersions.size());
        for (RuleVersion ruleVersion : evaluatedRuleVersions.keySet()) {
            switch (ruleVersion.getId()) {
                case -900005:
                    assertEquals(1500, ruleVersion.getValue());
                    break;
                case -900006:
                    assertEquals(2500, ruleVersion.getValue());
                    break;
                case -900007:
                    assertEquals(1000, ruleVersion.getValue());
                    break;
                case -900008:
                    assertEquals(1500, ruleVersion.getValue());
                    break;
                default:
                    fail("Unexepected evaluated rule version : " + ruleVersion.getId());
            }
        }
    }

    /**
     * Tests {@link Plan#evaluateWithContext(Object, String, boolean)}
     */
    @Test
    public void testEvaluateRule900002() {
        LocalDate effectivityDate = LocalDate.now();
        Plan plan = Universe.getPlan(-900003, effectivityDate);
        plan.initEvaluation();
        assertNotNull(plan);
        assertNotNull(plan.getRuleVersions());
        assertFalse(plan.getRuleVersions().isEmpty());
        assertFalse(plan.isBranched());
        Map<RuleVersion, Object> evaluatedRuleVersions = plan.evaluateWithContext(null, "-900002", false);
        assertFalse(plan.isBranched());
        assertNotNull(evaluatedRuleVersions);
        assertFalse(evaluatedRuleVersions.isEmpty());
        assertEquals(1, evaluatedRuleVersions.size());
        for (RuleVersion ruleVersion : evaluatedRuleVersions.keySet()) {
            switch (ruleVersion.getId()) {
                case -900005:
                    assertEquals(1500, ruleVersion.getValue());
                    break;
                default:
                    fail("Unexepected evaluated rule version : " + ruleVersion.getId());
            }
        }
    }

    /**
     * Tests {@link Plan#evaluateWithContext(Object, String, boolean)}
     */
    @Test
    public void testEvaluateRule900003() {
        LocalDate effectivityDate = LocalDate.now();
        Plan plan = Universe.getPlan(-900003, effectivityDate);
        plan.initEvaluation();
        assertNotNull(plan);
        assertNotNull(plan.getRuleVersions());
        assertFalse(plan.getRuleVersions().isEmpty());
        assertFalse(plan.isBranched());
        Map<RuleVersion, Object> evaluatedRuleVersions = plan.evaluateWithContext(null, "-900003", false);
        assertFalse(plan.isBranched());
        assertNotNull(evaluatedRuleVersions);
        assertFalse(evaluatedRuleVersions.isEmpty());
        assertEquals(3, evaluatedRuleVersions.size());
        for (RuleVersion ruleVersion : evaluatedRuleVersions.keySet()) {
            switch (ruleVersion.getId()) {
                case -900006:
                    assertEquals(2500, ruleVersion.getValue());
                    break;
                case -900007:
                    assertEquals(1000, ruleVersion.getValue());
                    break;
                case -900008:
                    assertEquals(1500, ruleVersion.getValue());
                    break;
                default:
                    fail("Unexepected evaluated rule version : " + ruleVersion.getId());
            }
        }
    }

}
