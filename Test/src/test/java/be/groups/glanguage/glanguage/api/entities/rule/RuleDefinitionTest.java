package be.groups.glanguage.glanguage.api.entities.rule;

import static org.junit.Assert.*;

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
 * Test class for {@link RuleDefinition}
 * 
 * @author DUPIREFR
 */
public class RuleDefinitionTest {

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
	 * Tests {@link RuleDefinition} JPA mapping
	 */
	@Test
	public void testJpaMapping() {
		RuleDefinition ruleDefinition = em.find(RuleDefinition.class, 900002);

		/* Checking entity */
		assertNotNull(ruleDefinition);

		assertEquals(900002, ruleDefinition.getId());

		/* Checking relationships */
		assertEquals(900001, ruleDefinition.getRuleIdentity().getId());

		assertNotNull(ruleDefinition.getDefinitionParameters());
		assertEquals(2, ruleDefinition.getDefinitionParameters().size());

		assertNotNull(ruleDefinition.getVersions());
		assertEquals(2, ruleDefinition.getVersions().size());
	}

}
