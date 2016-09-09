package be.groups.glanguage.glanguage.api.entities.rule.definition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import be.groups.common.persistence.util.TransactionHelper;
import be.groups.common.test.utils.Environment;
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
	public void testJpaMapping() {
		RuleDefintionParameterId ruleDefintionParameterId = new RuleDefintionParameterId();
		ruleDefintionParameterId.setLevelId(2);
		ruleDefintionParameterId.setRuleDefinitionId(900003);
		ruleDefintionParameterId.setValue("100000");

		RuleDefinitionParameter ruleDefinitionParameter = em.find(RuleDefinitionParameter.class,
				ruleDefintionParameterId);

		/* Checking entity */
		assertNotNull(ruleDefinitionParameter);

		assertEquals(ruleDefintionParameterId, ruleDefinitionParameter.getId());

		/* Checking relationships */
		assertNotNull(ruleDefinitionParameter.getRuleDefinition());
		assertEquals(900003, ruleDefinitionParameter.getRuleDefinition().getId());
	}

}
