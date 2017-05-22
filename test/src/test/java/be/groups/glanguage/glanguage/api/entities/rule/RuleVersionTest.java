package be.groups.glanguage.glanguage.api.entities.rule;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.entities.utils.rounding.RoundingType;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Test class for {@link RuleVersion}
 * 
 * @author DUPIREFR
 */
public class RuleVersionTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link RuleVersion} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
	public void testJpaMapping() {
		RuleVersion ruleVersion = getEntityManager().find(RuleVersion.class, -900003);

		/* Checking entity */
		assertNotNull(ruleVersion);

		assertEquals(-900003, ruleVersion.getId());

		assertEquals("1.0.0", ruleVersion.getVersion());

		assertEquals(RuleType.OR, ruleVersion.getRuleType());

		assertEquals(LocalDate.of(2014, 1, 1), ruleVersion.getEffectivityStartDate());
		assertEquals(LocalDate.of(2014, 12, 31), ruleVersion.getEffectivityEndDate());

		assertEquals(Double.valueOf(0.01), ruleVersion.getRoundingPrecision());
		assertEquals(RoundingType.ARITHMETIC, ruleVersion.getRoundingType());

		/* Checking relationships */
		assertNotNull(ruleVersion.getRuleDefinition());
		assertEquals(-900001, ruleVersion.getRuleDefinition().getId());

		assertNotNull(ruleVersion.getRuleDescription());
		assertEquals(-900000, ruleVersion.getRuleDescription().getId());

		assertNotNull(ruleVersion.getGroupItems());
		assertEquals(1, ruleVersion.getGroupItems().size());

		RuleGroupItemId ruleGroupItemId = new RuleGroupItemId();
		ruleGroupItemId.setRuleId(-900001);
		ruleGroupItemId.setRuleVersionId(-900003);
		assertEquals(ruleGroupItemId, ruleVersion.getGroupItems().first().getId());

		assertNotNull(ruleVersion.getApplicabilityCondition());
		assertEquals(-900003, ruleVersion.getApplicabilityCondition().getId());

		assertNotNull(ruleVersion.getFormula());
		assertEquals(-900004, ruleVersion.getFormula().getId());
	}

	/**
	 * Tests {@link RuleVersion#isEffective(LocalDate)} when effectivity
	 * period is opened and effectivity date falls after start date
	 */
	@Test
	public void testIsEffectiveOpenedPeriodEffective() {
		RuleVersion ruleVersion = new RuleVersion();
		ruleVersion.setEffectivityStartDate(LocalDate.of(2015, 1, 1));

		assertTrue(ruleVersion.isEffective(LocalDate.of(2015, 1, 1)));
	}

	/**
	 * Tests {@link RuleVersion#isEffective(LocalDate)} when effectivity
	 * period is opened and effectivity date falls before start date
	 */
	@Test
	public void testIsEffectiveOpenedPeriodNotEffective() {
		RuleVersion ruleVersion = new RuleVersion();
		ruleVersion.setEffectivityStartDate(LocalDate.of(2015, 1, 1));

		assertFalse(ruleVersion.isEffective(LocalDate.of(2014, 12, 31)));
	}

	/**
	 * Tests {@link RuleVersion#isEffective(LocalDate)} when effectivity
	 * period is closed and effectivity date falls between start and end date
	 */
	@Test
	public void testIsEffectiveClosedPeriodEffective() {
		RuleVersion ruleVersion = new RuleVersion();
		ruleVersion.setEffectivityStartDate(LocalDate.of(2015, 1, 1));
		ruleVersion.setEffectivityEndDate(LocalDate.of(2015, 12, 31));

		assertTrue(ruleVersion.isEffective(LocalDate.of(2015, 2, 1)));
	}

	/**
	 * Tests {@link RuleVersion#isEffective(LocalDate)} when effectivity
	 * period is closed and effectivity date falls before start date
	 */
	@Test
	public void testIsEffectiveClosedPeriodNotEffectiveBefore() {
		RuleVersion ruleVersion = new RuleVersion();
		ruleVersion.setEffectivityStartDate(LocalDate.of(2015, 1, 1));
		ruleVersion.setEffectivityEndDate(LocalDate.of(2015, 12, 31));

		assertFalse(ruleVersion.isEffective(LocalDate.of(2014, 12, 31)));
	}

	/**
	 * Tests {@link RuleVersion#isEffective(LocalDate)} when effectivity
	 * period is closed and effectivity date falls after end date
	 */
	@Test
	public void testIsEffectiveClosedPeriodNotEffectiveAfter() {
		RuleVersion ruleVersion = new RuleVersion();
		ruleVersion.setEffectivityStartDate(LocalDate.of(2015, 1, 1));
		ruleVersion.setEffectivityEndDate(LocalDate.of(2015, 12, 31));

		assertFalse(ruleVersion.isEffective(LocalDate.of(2016, 1, 1)));
	}

}