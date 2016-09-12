package be.groups.glanguage.glanguage.api.entities.ruleset;

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
	@Category(JpaMappingTest.class)
	public void testJpaMapping() {
		RuleSet ruleSet = em.find(RuleSet.class, 900000);

		/* Checking entity */
		assertNotNull(ruleSet);

		assertEquals(900000, ruleSet.getId());

		assertEquals("rs1_fr", ruleSet.getAliasFr());
		assertEquals("rs1_nl", ruleSet.getAliasNl());
		assertEquals("rs1_de", ruleSet.getAliasDe());
		assertEquals("rs1_x", ruleSet.getAliasX());

		assertEquals("rs1_descr_fr", ruleSet.getDescriptionFr());
		assertEquals("rs1_descr_nl", ruleSet.getDescriptionNl());
		assertEquals("rs1_descr_de", ruleSet.getDescriptionDe());
		assertEquals("rs1_descr_x", ruleSet.getDescriptionX());

		/* Checking relationships */
		assertNotNull(ruleSet.getVersions());
		assertEquals(3, ruleSet.getVersions().size());
		assertEquals(3, ruleSet.getVersions().stream().map(v -> v.getId()).distinct().count());

		List<Integer> ruleSetVersionIds = Arrays.asList(900000, 900001, 900002);
		ruleSet.getVersions().forEach(v -> {
			assertTrue(ruleSetVersionIds.contains(v.getId()));
		});
	}

}
