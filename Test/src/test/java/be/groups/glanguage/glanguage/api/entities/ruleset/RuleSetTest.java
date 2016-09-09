package be.groups.glanguage.glanguage.api.entities.ruleset;

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
 * Test class for {@link RuleSet}
 * 
 * @author DUPIREFR
 */
public class RuleSetTest {

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
	 * Tests {@link RuleSet} JPA mapping
	 */
	@Test
	public void testJpaMapping() {
		RuleSet ruleSet = em.find(RuleSet.class, 900000);
		
		assertNotNull(ruleSet);
		
		assertEquals(900000, ruleSet.getId());
		
		assertEquals("test_fr", ruleSet.getAliasFr());
		assertEquals("test_nl", ruleSet.getAliasNl());
		assertEquals("test_de", ruleSet.getAliasDe());
		assertEquals("test_x", ruleSet.getAliasX());
		
		assertEquals("descr_test_fr", ruleSet.getDescriptionFr());
		assertEquals("descr_test_nl", ruleSet.getDescriptionNl());
		assertEquals("descr_test_de", ruleSet.getDescriptionDe());
		assertEquals("descr_test_x", ruleSet.getDescriptionX());
	}

}
