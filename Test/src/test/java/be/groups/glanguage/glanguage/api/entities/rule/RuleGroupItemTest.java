package be.groups.glanguage.glanguage.api.entities.rule;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class for {@link RuleGroupItem}
 * 
 * @author DUPIREFR
 */
public class RuleGroupItemTest extends BaseDatabaseTest {

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
		ruleGroupItemId.setRuleVersionId(-900003);
		ruleGroupItemId.setRuleId(-900001);

		RuleGroupItem ruleGroupItem = getEntityManager().find(RuleGroupItem.class, ruleGroupItemId);

		/* Checking entity */
		assertNotNull(ruleGroupItem);

		assertEquals(ruleGroupItemId, ruleGroupItem.getId());

		assertEquals(1, ruleGroupItem.getSequenceNumber());
		
		/* Checking relationships */
		assertNotNull(ruleGroupItem.getGroupRule());
		assertEquals(-900003, ruleGroupItem.getGroupRule().getId());
		
		assertNotNull(ruleGroupItem.getItemRule());
		assertEquals(-900001, ruleGroupItem.getItemRule().getId());
	}

}
