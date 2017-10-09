package be.groups.glanguage.core.dao.rule;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.entities.rule.RuleGroupItemId;
import be.groups.glanguage.core.entities.rule.RuleType;
import be.groups.glanguage.core.entities.rule.RuleVersion;
import be.groups.glanguage.core.entities.utils.rounding.RoundingType;
import be.groups.glanguage.core.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Test class for {@link RuleVersion}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RuleVersionDaoIntegrationTest extends IntegrationTest {

  @Autowired
  private RuleVersionDao ruleVersionDao;
  /*
   * Tests
	 */

  /**
   * Tests {@link RuleVersion} JPA mapping
   */
  @Test
  @Category(JpaMappingTestsCategory.class)
  public void testJpaMapping() {
    Optional<RuleVersion> optRuleVersion = ruleVersionDao.findById(-900003L);

		/* Checking entity */
    assertNotNull(optRuleVersion);
    assertTrue(optRuleVersion.isPresent());

    RuleVersion ruleVersion = optRuleVersion.get();

    assertEquals(Long.valueOf(-900003L), ruleVersion.getId());

    assertEquals("1.0.0", ruleVersion.getVersion());

    assertEquals(RuleType.OR, ruleVersion.getRuleType());

    assertEquals(LocalDate.of(2014, 1, 1), ruleVersion.getEffectiveStartDate());
    assertEquals(LocalDate.of(2014, 12, 31), ruleVersion.getEffectiveEndDate());

    assertEquals(Double.valueOf(0.01), ruleVersion.getRoundingPrecision());
    assertEquals(RoundingType.ARITHMETIC, ruleVersion.getRoundingType());

		/* Checking relationships */
    assertNotNull(ruleVersion.getRuleDefinition());
    assertEquals(Long.valueOf(-900001L), ruleVersion.getRuleDefinition().getId());

    assertNotNull(ruleVersion.getRuleDescription());
    assertEquals(Long.valueOf(-900000L), ruleVersion.getRuleDescription().getId());

    assertNotNull(ruleVersion.getGroupItems());
    assertEquals(1, ruleVersion.getGroupItems().size());

    RuleGroupItemId ruleGroupItemId = new RuleGroupItemId();
    ruleGroupItemId.setRuleId(-900001L);
    ruleGroupItemId.setRuleVersionId(-900003L);
    assertEquals(ruleGroupItemId, ruleVersion.getGroupItems().first().getId());

    assertNotNull(ruleVersion.getApplicabilityCondition());
    assertEquals(Long.valueOf(-900003L), ruleVersion.getApplicabilityCondition().getId());

    assertNotNull(ruleVersion.getFormula());
    assertEquals(Long.valueOf(-900004L), ruleVersion.getFormula().getId());
  }
}
