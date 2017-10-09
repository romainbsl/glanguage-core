package be.groups.glanguage.core.dao.rule;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.entities.rule.RuleGroupItem;
import be.groups.glanguage.core.entities.rule.RuleGroupItemId;
import be.groups.glanguage.core.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Test class for {@link RuleGroupItem}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RuleGroupItemDaoIntegrationTest extends IntegrationTest {

  @Autowired
  private RuleGroupItemDao ruleGroupItemDao;

	/*
   * Tests
	 */

  /**
   * Tests {@link RuleGroupItem} JPA mapping
   */
  @Test
  @Category(JpaMappingTestsCategory.class)
  public void testJpaMapping() {
    RuleGroupItemId ruleGroupItemId = new RuleGroupItemId();
    ruleGroupItemId.setRuleVersionId(-900003L);
    ruleGroupItemId.setRuleId(-900001L);

    Optional<RuleGroupItem> optRuleGroupItem = ruleGroupItemDao.findById(ruleGroupItemId);

		/* Checking entity */
    assertNotNull(optRuleGroupItem);
    assertTrue(optRuleGroupItem.isPresent());

    RuleGroupItem ruleGroupItem = optRuleGroupItem.get();

    assertEquals(ruleGroupItemId, ruleGroupItem.getId());

    assertEquals(1, ruleGroupItem.getSequenceNumber());

		/* Checking relationships */
    assertNotNull(ruleGroupItem.getGroupRule());
    assertEquals(Long.valueOf(-900003L), ruleGroupItem.getGroupRule().getId());

    assertNotNull(ruleGroupItem.getItemRule());
    assertEquals(Long.valueOf(-900001L), ruleGroupItem.getItemRule().getId());
  }
}
