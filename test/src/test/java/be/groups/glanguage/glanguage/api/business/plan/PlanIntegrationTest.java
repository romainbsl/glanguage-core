package be.groups.glanguage.glanguage.api.business.plan;

import be.groups.glanguage.glanguage.api.*;
import be.groups.glanguage.glanguage.api.business.universe.*;
import be.groups.glanguage.glanguage.api.entities.rule.*;
import be.groups.glanguage.glanguage.api.error.exception.*;
import java.time.*;
import java.util.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;
import org.springframework.transaction.annotation.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlanIntegrationTest extends IntegrationTest {

  @Autowired
  private Universe universe;
    
    /**
     * Tests {@link Plan#evaluate()}}
     */
    @Test
    @Transactional
    public void testEvaluate() throws GLanguageException {
        LocalDate effectiveDate = LocalDate.now();
      Plan plan = universe.getPlan(-900003L, effectiveDate);
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
            if (ruleVersion.getId() ==  -900005L) {
                assertEquals(1500, ruleVersion.getValue());
                break;
            } else if (ruleVersion.getId() ==  -900006L) {
                assertEquals(2500, ruleVersion.getValue());
                break;
            } else if (ruleVersion.getId() ==  -900007L) {
                assertEquals(1000, ruleVersion.getValue());
                break;
            } else if (ruleVersion.getId() ==  -900008L) {
                assertEquals(1500, ruleVersion.getValue());
                break;
            } else {
                    fail("Unexepected evaluated rule version : " + ruleVersion.getId());
            }
        }
    }

    /**
     * Tests {@link Plan#evaluateWithContext(Object, String, boolean)}
     */
    @Test
    @Transactional
    public void testEvaluateRule900002() throws GLanguageException {
        LocalDate effectiveDate = LocalDate.now();
      Plan plan = universe.getPlan(-900003L, effectiveDate);
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
            if (ruleVersion.getId() ==  -900005L) {
                assertEquals(1500, ruleVersion.getValue());
                break;
            } else {
                fail("Unexepected evaluated rule version : " + ruleVersion.getId());
            }
        }
    }

    /**
     * Tests {@link Plan#evaluateWithContext(Object, String, boolean)}
     */
    @Test
    @Transactional
    public void testEvaluateRule900003() throws GLanguageException {
        LocalDate effectiveDate = LocalDate.now();
      Plan plan = universe.getPlan(-900003L, effectiveDate);
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
            if (ruleVersion.getId() ==  -900006L) {
                assertEquals(2500, ruleVersion.getValue());
                break;
            } else if (ruleVersion.getId() ==  -900007L) {
                assertEquals(1000, ruleVersion.getValue());
                break;
            } else if (ruleVersion.getId() ==  -900008L) {
                assertEquals(1500, ruleVersion.getValue());
                break;
            } else {
                fail("Unexepected evaluated rule version : " + ruleVersion.getId());
            }
        }
    }

}
