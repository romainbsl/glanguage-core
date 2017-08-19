package be.groups.glanguage.glanguage.api.business.plan;

import be.groups.glanguage.glanguage.api.business.universe.*;
import be.groups.glanguage.glanguage.api.dao.*;
import be.groups.glanguage.glanguage.api.entities.rule.*;
import be.groups.glanguage.glanguage.api.error.exception.*;
import java.time.*;
import java.util.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlanIntegrationTest extends DaoIntegrationTest {

  @Autowired
  private Universe universe;
    
    /**
     * Tests {@link Plan#evaluate()}}
     */
    @Test
    @Ignore
    // TODO Lazy-init Exception : must find a way to use @Transactional without losing sharding
    public void testEvaluate() throws GLanguageException {
        LocalDate effectiveDate = LocalDate.now();
      Plan plan = universe.getPlan(-900003, effectiveDate);
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
    @Ignore
    // TODO Lazy-init Exception : must find a way to use @Transactional without losing sharding
    public void testEvaluateRule900002() throws GLanguageException {
        LocalDate effectiveDate = LocalDate.now();
      Plan plan = universe.getPlan(-900003, effectiveDate);
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
    @Ignore
    // TODO Lazy-init Exception : must find a way to use @Transactional without losing sharding
    public void testEvaluateRule900003() throws GLanguageException {
        LocalDate effectiveDate = LocalDate.now();
      Plan plan = universe.getPlan(-900003, effectiveDate);
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
