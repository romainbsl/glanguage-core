package be.groups.glanguage.glanguage.api.entities.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

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
 * Test class for {@link RuleDescription}
 * 
 * @author DUPIREFR
 */
public class RuleDescriptionTest {

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
	 * Tests {@link RuleDescription} JPA mapping
	 */
	@Test
	@Category(JpaMappingTest.class)
	public void testJpaMapping() {
		RuleDescription ruleDescription = em.find(RuleDescription.class, 900000);

		/* Checking entity */
		assertNotNull(ruleDescription);
		
		assertEquals(900000, ruleDescription.getId());

		assertEquals("r1", ruleDescription.getCode());

		assertEquals("r1_fr", ruleDescription.getAliasFr());
		assertEquals("r1_nl", ruleDescription.getAliasNl());
		assertEquals("r1_de", ruleDescription.getAliasDe());
		assertEquals("r1_x", ruleDescription.getAliasX());

		assertEquals("descr_r1_fr", ruleDescription.getDescriptionFr());
		assertEquals("descr_r1_nl", ruleDescription.getDescriptionNl());
		assertEquals("descr_r1_de", ruleDescription.getDescriptionDe());
		assertEquals("descr_r1_x", ruleDescription.getDescriptionX());

		/* Checking relationships */
		assertNotNull(ruleDescription.getRuleVersions());
		assertEquals(4, ruleDescription.getRuleVersions().size());
		assertEquals(4, ruleDescription.getRuleVersions().stream().map(rv -> rv.getId()).distinct().count());

		List<Integer> ruleVersionIds = Arrays.asList(900000, 900001, 900002, 900003);
		ruleDescription.getRuleVersions().forEach(rv -> {
			assertTrue(ruleVersionIds.contains(rv.getId()));
		});
	}

}
