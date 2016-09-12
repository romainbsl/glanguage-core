package be.groups.glanguage.glanguage.api.entities.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
 * Test class for {@link RuleVersion}
 * 
 * @author DUPIREFR
 */
public class RuleVersionTest {

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
	 * Tests {@link RuleVersion} JPA mapping
	 */
	@Test
	public void testJpaMapping() {
		RuleVersion ruleVersion = em.find(RuleVersion.class, 900003);

		/* Checking entity */
		assertNotNull(ruleVersion);

		assertEquals(900003, ruleVersion.getId());

		assertEquals("1.0.0", ruleVersion.getVersion());

		assertEquals(RuleType.OR, ruleVersion.getRuleType());

		assertEquals(LocalDateTime.of(2014, 1, 1, 0, 0), ruleVersion.getEffectivityStartDate());
		assertEquals(LocalDateTime.of(2014, 12, 31, 0, 0), ruleVersion.getEffectivityEndDate());

		assertEquals(Double.valueOf(0.01), ruleVersion.getRoundingPrecision());
		assertEquals(RoundingType.ARITHMETIC, ruleVersion.getRoundingType());

		/* Checking relationships */
		assertNotNull(ruleVersion.getRuleDefinition());
		assertEquals(900001, ruleVersion.getRuleDefinition().getId());

		assertNotNull(ruleVersion.getRuleDescription());
		assertEquals(900000, ruleVersion.getRuleDescription().getId());

		assertNotNull(ruleVersion.getGroupItems());
		assertEquals(1, ruleVersion.getGroupItems().size());

		RuleGroupItemId ruleGroupItemId = new RuleGroupItemId();
		ruleGroupItemId.setRuleId(900001);
		ruleGroupItemId.setRuleVersionId(900003);
		assertEquals(ruleGroupItemId, ruleVersion.getGroupItems().first().getId());

		assertNotNull(ruleVersion.getApplicabilityCondition());
		assertEquals(900003, ruleVersion.getApplicabilityCondition().getId());

		assertNotNull(ruleVersion.getFormula());
		assertEquals(900004, ruleVersion.getFormula().getId());
	}

	/**
	 * Tests {@link RuleVersion#isEffective(LocalDateTime)} when effectivity
	 * period is opened and effectivity date falls after start date
	 */
	@Test
	public void testIsEffectiveOpenedPeriodEffective() {
		RuleVersion ruleVersion = new RuleVersion();
		ruleVersion.setEffectivityStartDate(LocalDateTime.of(2015, 1, 1, 0, 0));

		assertTrue(ruleVersion.isEffective(LocalDateTime.of(2015, 1, 1, 0, 0)));
	}

	/**
	 * Tests {@link RuleVersion#isEffective(LocalDateTime)} when effectivity
	 * period is opened and effectivity date falls before start date
	 */
	@Test
	public void testIsEffectiveOpenedPeriodNotEffective() {
		RuleVersion ruleVersion = new RuleVersion();
		ruleVersion.setEffectivityStartDate(LocalDateTime.of(2015, 1, 1, 0, 0));

		assertFalse(ruleVersion.isEffective(LocalDateTime.of(2014, 12, 31, 0, 0)));
	}

	/**
	 * Tests {@link RuleVersion#isEffective(LocalDateTime)} when effectivity
	 * period is closed and effectivity date falls between start and end date
	 */
	@Test
	public void testIsEffectiveClosedPeriodEffective() {
		RuleVersion ruleVersion = new RuleVersion();
		ruleVersion.setEffectivityStartDate(LocalDateTime.of(2015, 1, 1, 0, 0));
		ruleVersion.setEffectivityEndDate(LocalDateTime.of(2015, 12, 31, 0, 0));

		assertTrue(ruleVersion.isEffective(LocalDateTime.of(2015, 2, 1, 0, 0)));
	}

	/**
	 * Tests {@link RuleVersion#isEffective(LocalDateTime)} when effectivity
	 * period is closed and effectivity date falls before start date
	 */
	@Test
	public void testIsEffectiveClosedPeriodNotEffectiveBefore() {
		RuleVersion ruleVersion = new RuleVersion();
		ruleVersion.setEffectivityStartDate(LocalDateTime.of(2015, 1, 1, 0, 0));
		ruleVersion.setEffectivityEndDate(LocalDateTime.of(2015, 12, 31, 0, 0));

		assertFalse(ruleVersion.isEffective(LocalDateTime.of(2014, 12, 31, 0, 0)));
	}

	/**
	 * Tests {@link RuleVersion#isEffective(LocalDateTime)} when effectivity
	 * period is closed and effectivity date falls after end date
	 */
	@Test
	public void testIsEffectiveClosedPeriodNotEffectiveAfter() {
		RuleVersion ruleVersion = new RuleVersion();
		ruleVersion.setEffectivityStartDate(LocalDateTime.of(2015, 1, 1, 0, 0));
		ruleVersion.setEffectivityEndDate(LocalDateTime.of(2015, 12, 31, 0, 0));

		assertFalse(ruleVersion.isEffective(LocalDateTime.of(2016, 1, 1, 0, 0)));
	}

}
