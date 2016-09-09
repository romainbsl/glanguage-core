package be.groups.glanguage.glanguage.api.entities.ruleset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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
		RuleSetVersion ruleSetVersion = em.find(RuleSetVersion.class, 900001);

		/* Checking entity */
		assertNotNull(ruleSetVersion);

		assertEquals(900001, ruleSetVersion.getId());

		assertEquals(LocalDateTime.of(2016, 9, 7, 9, 0), ruleSetVersion.getExploitationStartDate());
		assertEquals("1.0.1", ruleSetVersion.getVersion());

		assertEquals("dupirefr", ruleSetVersion.getAuthor());
		assertEquals("hotfix", ruleSetVersion.getLabel());
		assertEquals(RuleSetVersionStatus.PROD, ruleSetVersion.getStatus());

		/* Checking relationships */
		assertNotNull(ruleSetVersion.getRuleSet());
		assertEquals(900000, ruleSetVersion.getRuleSet().getId());

		assertNotNull(ruleSetVersion.getParent());
		assertEquals(900000, ruleSetVersion.getParent().getId());

		assertNotNull(ruleSetVersion.getIncludes());
		assertEquals(1, ruleSetVersion.getIncludes().size());
		assertEquals(900003, ((RuleSetVersion) ruleSetVersion.getIncludes().toArray()[0]).getId());

		assertNotNull(ruleSetVersion.getIncludedIn());
		assertEquals(1, ruleSetVersion.getIncludedIn().size());
		assertEquals(900004, ((RuleSetVersion) ruleSetVersion.getIncludedIn().toArray()[0]).getId());

		assertNotNull(ruleSetVersion.getRuleVersions());
		assertEquals(4, ruleSetVersion.getRuleVersions().size());
		assertEquals(4, ruleSetVersion.getRuleVersions().stream().map(v -> v.getId()).distinct().count());

		List<Integer> ruleVersionIds = Arrays.asList(900000, 900002, 900003, 900004);
		ruleSetVersion.getRuleVersions().forEach(v -> {
			assertTrue(ruleVersionIds.contains(v.getId()));
		});
	}

}
