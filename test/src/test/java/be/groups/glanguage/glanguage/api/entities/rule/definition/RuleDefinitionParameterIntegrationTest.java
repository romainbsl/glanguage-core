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
}
