package be.groups.glanguage.glanguage.api.dao.rule;

import be.groups.glanguage.glanguage.api.*;
import be.groups.glanguage.glanguage.api.entities.rule.*;
import be.groups.glanguage.glanguage.api.entities.utils.rounding.*;
import be.groups.glanguage.glanguage.api.test.categories.*;
import java.time.*;
import java.util.*;
import org.junit.*;
import org.junit.experimental.categories.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;

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
    Optional<RuleVersion> optRuleVersion = ruleVersionDao.findById(-900003);

		/* Checking entity */
    assertNotNull(optRuleVersion);
    assertTrue(optRuleVersion.isPresent());

    RuleVersion ruleVersion = optRuleVersion.get();

    assertEquals(-900003, ruleVersion.getId());

    assertEquals("1.0.0", ruleVersion.getVersion());

    assertEquals(RuleType.OR, ruleVersion.getRuleType());

    assertEquals(LocalDate.of(2014, 1, 1), ruleVersion.getEffectiveStartDate());
    assertEquals(LocalDate.of(2014, 12, 31), ruleVersion.getEffectiveEndDate());

    assertEquals(Double.valueOf(0.01), ruleVersion.getRoundingPrecision());
    assertEquals(RoundingType.ARITHMETIC, ruleVersion.getRoundingType());

		/* Checking relationships */
    assertNotNull(ruleVersion.getRuleDefinition());
    assertEquals(-900001, ruleVersion.getRuleDefinition().getId());

    assertNotNull(ruleVersion.getRuleDescription());
    assertEquals(-900000, ruleVersion.getRuleDescription().getId());

    assertNotNull(ruleVersion.getGroupItems());
    assertEquals(1, ruleVersion.getGroupItems().size());

    RuleGroupItemId ruleGroupItemId = new RuleGroupItemId();
    ruleGroupItemId.setRuleId(-900001);
    ruleGroupItemId.setRuleVersionId(-900003);
    assertEquals(ruleGroupItemId, ruleVersion.getGroupItems().first().getId());

    assertNotNull(ruleVersion.getApplicabilityCondition());
    assertEquals(-900003, ruleVersion.getApplicabilityCondition().getId());

    assertNotNull(ruleVersion.getFormula());
    assertEquals(-900004, ruleVersion.getFormula().getId());
  }
}
