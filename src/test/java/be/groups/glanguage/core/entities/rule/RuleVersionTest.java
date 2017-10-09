package be.groups.glanguage.core.entities.rule;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for {@link RuleVersion}
 * 
 * @author DUPIREFR
 */
public class RuleVersionTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link RuleVersion#isEffective(LocalDate)} when effective
	 * period is opened and effective date falls after start date
	 */
	@Test
	public void testIsEffectiveOpenedPeriodEffective() {
		RuleVersion ruleVersion = new RuleVersion();
		ruleVersion.setEffectiveStartDate(LocalDate.of(2015, 1, 1));

		assertTrue(ruleVersion.isEffective(LocalDate.of(2015, 1, 1)));
	}

	/**
	 * Tests {@link RuleVersion#isEffective(LocalDate)} when effective
	 * period is opened and effective date falls before start date
	 */
	@Test
	public void testIsEffectiveOpenedPeriodNotEffective() {
		RuleVersion ruleVersion = new RuleVersion();
		ruleVersion.setEffectiveStartDate(LocalDate.of(2015, 1, 1));

		assertFalse(ruleVersion.isEffective(LocalDate.of(2014, 12, 31)));
	}

	/**
	 * Tests {@link RuleVersion#isEffective(LocalDate)} when effective
	 * period is closed and effective date falls between start and end date
	 */
	@Test
	public void testIsEffectiveClosedPeriodEffective() {
		RuleVersion ruleVersion = new RuleVersion();
		ruleVersion.setEffectiveStartDate(LocalDate.of(2015, 1, 1));
		ruleVersion.setEffectiveEndDate(LocalDate.of(2015, 12, 31));

		assertTrue(ruleVersion.isEffective(LocalDate.of(2015, 2, 1)));
	}

	/**
	 * Tests {@link RuleVersion#isEffective(LocalDate)} when effective
	 * period is closed and effective date falls before start date
	 */
	@Test
	public void testIsEffectiveClosedPeriodNotEffectiveBefore() {
		RuleVersion ruleVersion = new RuleVersion();
		ruleVersion.setEffectiveStartDate(LocalDate.of(2015, 1, 1));
		ruleVersion.setEffectiveEndDate(LocalDate.of(2015, 12, 31));

		assertFalse(ruleVersion.isEffective(LocalDate.of(2014, 12, 31)));
	}

	/**
	 * Tests {@link RuleVersion#isEffective(LocalDate)} when effective
	 * period is closed and effective date falls after end date
	 */
	@Test
	public void testIsEffectiveClosedPeriodNotEffectiveAfter() {
		RuleVersion ruleVersion = new RuleVersion();
		ruleVersion.setEffectiveStartDate(LocalDate.of(2015, 1, 1));
		ruleVersion.setEffectiveEndDate(LocalDate.of(2015, 12, 31));

		assertFalse(ruleVersion.isEffective(LocalDate.of(2016, 1, 1)));
	}

}
