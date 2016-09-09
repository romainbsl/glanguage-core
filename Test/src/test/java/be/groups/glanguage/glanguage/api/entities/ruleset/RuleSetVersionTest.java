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
		RuleSetVersion ruleSetVersion = em.find(RuleSetVersion.class, 900000);
		
		assertNotNull(ruleSetVersion);
		
		assertEquals(900000, ruleSetVersion.getId());
		
		assertEquals(LocalDateTime.of(2016, 9, 5, 9, 0), ruleSetVersion.getExploitationStartDate());
		assertEquals("1.0.0", ruleSetVersion.getVersion());
		
		assertEquals("dupirefr", ruleSetVersion.getAuthor());
		assertEquals("init", ruleSetVersion.getLabel());
		assertEquals(RuleSetVersionStatus.PROD, ruleSetVersion.getStatus());
	}

}
