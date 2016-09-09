package be.groups.glanguage.glanguage.api.entities.rule;

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
 * Test class for {@link RuleIdentity}
 * 
 * @author DUPIREFR
 */
public class RuleIdentityTest {

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
	 * Tests {@link RuleIdentity} JPA mapping
	 */
	@Test
	public void testJpaMapping() {
		RuleIdentity ruleIdentity = em.find(RuleIdentity.class, 900000);

		/* Checking entity */
		assertNotNull(ruleIdentity);

		assertEquals(900000, ruleIdentity.getId());

		/* Checking relationships */
		assertNotNull(ruleIdentity.getRuleDefinitions());
		assertEquals(2, ruleIdentity.getRuleDefinitions().size());
	}

}
