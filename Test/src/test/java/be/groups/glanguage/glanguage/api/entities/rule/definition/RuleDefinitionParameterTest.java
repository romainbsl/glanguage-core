package be.groups.glanguage.glanguage.api.entities.rule.definition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.common.persistence.util.TransactionHelper;
import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTest;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;

/**
 * Test class for {@link RuleDefinitionParameter}
 * 
 * @author DUPIREFR
 */
public class RuleDefinitionParameterTest {

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
	 * Tests {@link RuleDefinitionParameter} JPA mapping
	 */
	@Test
	@Category(JpaMappingTest.class)
	public void testJpaMapping() {
		RuleDefinitionParameterId ruleDefintionParameterId = new RuleDefinitionParameterId();
		ruleDefintionParameterId.setLevelId(2);
		ruleDefintionParameterId.setRuleDefinitionId(900001);
		ruleDefintionParameterId.setValue("100000");

		RuleDefinitionParameter ruleDefinitionParameter = em.find(RuleDefinitionParameter.class,
				ruleDefintionParameterId);

		/* Checking entity */
		assertNotNull(ruleDefinitionParameter);

		assertEquals(ruleDefintionParameterId, ruleDefinitionParameter.getId());

		/* Checking relationships */
		assertNotNull(ruleDefinitionParameter.getRuleDefinition());
		assertEquals(900001, ruleDefinitionParameter.getRuleDefinition().getId());
	}

	/**
	 * Tests {@link RuleDefinitionParameter#match(RuleDefinitionParameter)} when
	 * levels are not matching
	 */
	@Test
	public void testMatchLevelsNotMatching() {
		RuleDefinitionParameter firstRuleDefinitionParameter = new RuleDefinitionParameter(DefinitionLevel.EMPLOYER,
				"100000");
		RuleDefinitionParameter secondRuleDefinitionParameter = new RuleDefinitionParameter(
				DefinitionLevel.JOINT_COMMITTEE, "355");

		assertFalse(firstRuleDefinitionParameter.match(secondRuleDefinitionParameter));
	}

	/**
	 * Tests {@link RuleDefinitionParameter#match(RuleDefinitionParameter)} when
	 * levels are matching, but not values
	 */
	@Test
	public void testMatchLevelsMatchingValuesNotMatching() {
		RuleDefinitionParameter firstRuleDefinitionParameter = new RuleDefinitionParameter(DefinitionLevel.EMPLOYER,
				"100000");
		RuleDefinitionParameter secondRuleDefinitionParameter = new RuleDefinitionParameter(DefinitionLevel.EMPLOYER,
				"120000");

		assertFalse(firstRuleDefinitionParameter.match(secondRuleDefinitionParameter));
	}

	/**
	 * Tests {@link RuleDefinitionParameter#match(RuleDefinitionParameter)} when
	 * levels and values are matching
	 */
	@Test
	public void testMatchMatching() {
		RuleDefinitionParameter firstRuleDefinitionParameter = new RuleDefinitionParameter(DefinitionLevel.EMPLOYER,
				"100000");
		RuleDefinitionParameter secondRuleDefinitionParameter = new RuleDefinitionParameter(DefinitionLevel.EMPLOYER,
				"100000");

		assertTrue(firstRuleDefinitionParameter.match(secondRuleDefinitionParameter));
	}

}
