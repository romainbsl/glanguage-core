package be.groups.glanguage.glanguage.api.entities.rule.definition;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

/**
 * Test class for {@link RuleDefinitionParameter}
 * 
 * @author DUPIREFR
 */
public class RuleDefinitionParameterIntegrationTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link RuleDefinitionParameter} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
	public void testJpaMapping() {
		RuleDefinitionParameterId ruleDefintionParameterId = new RuleDefinitionParameterId();
		ruleDefintionParameterId.setLevelId(2);
		ruleDefintionParameterId.setRuleDefinitionId(-900001);
		ruleDefintionParameterId.setValue("100000");

		RuleDefinitionParameter ruleDefinitionParameter = getEntityManager().find(RuleDefinitionParameter.class,
				ruleDefintionParameterId);

		/* Checking entity */
		assertNotNull(ruleDefinitionParameter);

		assertEquals(ruleDefintionParameterId, ruleDefinitionParameter.getId());

		/* Checking relationships */
		assertNotNull(ruleDefinitionParameter.getRuleDefinition());
		assertEquals(-900001, ruleDefinitionParameter.getRuleDefinition().getId());
	}

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
