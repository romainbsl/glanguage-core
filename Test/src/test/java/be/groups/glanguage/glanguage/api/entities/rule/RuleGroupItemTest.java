package be.groups.glanguage.glanguage.api.entities.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.common.persistence.util.TransactionHelper;
import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;

/**
 * Test class for {@link RuleGroupItem}
 * 
 * @author DUPIREFR
 */
public class RuleGroupItemTest {

	/*
	 * Static fields
	 */
	private static EntityManager em;

	/*
	 * Setups
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		Environment.setUp();
		TNSNames.setUp();

		JpaUtil.setEntityManager(JpaUtil.createDataSource(DatabaseIdentifier.DEVELOPMENT_DB));

		if (!TransactionHelper.isActive()) {
			TransactionHelper.begin();
		}

		em = JpaUtil.getEntityManager();
	}

	@AfterClass
	public static void close() {
		if (TransactionHelper.isActive()) {
			TransactionHelper.rollback();
		}
	}

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
		ruleGroupItemId.setRuleVersionId(900003);
		ruleGroupItemId.setRuleId(900001);

		RuleGroupItem ruleGroupItem = em.find(RuleGroupItem.class, ruleGroupItemId);

		/* Checking entity */
		assertNotNull(ruleGroupItem);

		assertEquals(ruleGroupItemId, ruleGroupItem.getId());

		assertEquals(1, ruleGroupItem.getSequenceNumber());
		
		/* Checking relationships */
		assertNotNull(ruleGroupItem.getGroupRule());
		assertEquals(900003, ruleGroupItem.getGroupRule().getId());
		
		assertNotNull(ruleGroupItem.getItemRule());
		assertEquals(900001, ruleGroupItem.getItemRule().getId());
	}

}
