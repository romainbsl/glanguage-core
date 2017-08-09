package be.groups.glanguage.glanguage.api.entities.rule.definition;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for {@link RuleDefinitionParameter}
 * 
 * @author DUPIREFR
 */
public class RuleDefinitionParameterTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link RuleDefinitionParameter#matches(RuleDefinitionParameter)} when
	 * levels are not matching
	 */
	@Test
	public void testMatchLevelsNotMatching() {
		RuleDefinitionParameter firstRuleDefinitionParameter = new RuleDefinitionParameter(DefinitionLevel.EMPLOYER,
				"100000");
		RuleDefinitionParameter secondRuleDefinitionParameter = new RuleDefinitionParameter(
				DefinitionLevel.JOINT_COMMITTEE, "355");

		assertFalse(firstRuleDefinitionParameter.matches(secondRuleDefinitionParameter));
	}

	/**
	 * Tests {@link RuleDefinitionParameter#matches(RuleDefinitionParameter)} when
	 * levels are matching, but not values
	 */
	@Test
	public void testMatchLevelsMatchingValuesNotMatching() {
		RuleDefinitionParameter firstRuleDefinitionParameter = new RuleDefinitionParameter(DefinitionLevel.EMPLOYER,
				"100000");
		RuleDefinitionParameter secondRuleDefinitionParameter = new RuleDefinitionParameter(DefinitionLevel.EMPLOYER,
				"120000");

		assertFalse(firstRuleDefinitionParameter.matches(secondRuleDefinitionParameter));
	}

	/**
	 * Tests {@link RuleDefinitionParameter#matches(RuleDefinitionParameter)} when
	 * levels and values are matching
	 */
	@Test
	public void testMatchMatching() {
		RuleDefinitionParameter firstRuleDefinitionParameter = new RuleDefinitionParameter(DefinitionLevel.EMPLOYER,
				"100000");
		RuleDefinitionParameter secondRuleDefinitionParameter = new RuleDefinitionParameter(DefinitionLevel.EMPLOYER,
				"100000");

		assertTrue(firstRuleDefinitionParameter.matches(secondRuleDefinitionParameter));
	}

}
