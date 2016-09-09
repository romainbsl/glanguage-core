package be.groups.glanguage.glanguage.api.entities.ruleset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

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
 * Test class for {@link RuleSetVersion}
 * 
 * @author DUPIREFR
 */
public class RuleSetVersionTest {

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
	 * Tests {@link RuleSetVersion} JPA mapping
	 */
	@Test
	public void testJpaMapping() {
		RuleSetVersion ruleSetVersion = em.find(RuleSetVersion.class, 900003);

		assertNotNull(ruleSetVersion);

		assertEquals(900003, ruleSetVersion.getId());

		assertEquals(LocalDateTime.of(2016, 9, 6, 9, 0), ruleSetVersion.getExploitationStartDate());
		assertEquals("1.0.1", ruleSetVersion.getVersion());

		assertEquals("micmax", ruleSetVersion.getAuthor());
		assertEquals("hotfix", ruleSetVersion.getLabel());
		assertEquals(RuleSetVersionStatus.PROD, ruleSetVersion.getStatus());

		assertNotNull(ruleSetVersion.getRuleSet());
		assertEquals(900001, ruleSetVersion.getRuleSet().getId());

		assertNotNull(ruleSetVersion.getParent());
		assertEquals(900002, ruleSetVersion.getParent().getId());
	}

}
