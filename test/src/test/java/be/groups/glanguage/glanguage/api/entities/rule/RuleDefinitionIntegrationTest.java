package be.groups.glanguage.glanguage.api.entities.rule;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameterId;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test class for {@link RuleDefinition}
 * 
 * @author DUPIREFR
 */
public class RuleDefinitionIntegrationTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link RuleDefinition} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
	public void testJpaMapping() {
		RuleDefinition ruleDefinition = getEntityManager().find(RuleDefinition.class, -900001);

		/* Checking entity */
		assertNotNull(ruleDefinition);

		assertEquals(-900001, ruleDefinition.getId());

		/* Checking relationships */
		assertEquals(-900000, ruleDefinition.getRuleIdentity().getId());

		assertNotNull(ruleDefinition.getDefinitionParameters());
		assertEquals(2, ruleDefinition.getDefinitionParameters().size());
		assertEquals(2, ruleDefinition.getDefinitionParameters().stream().map(p -> p.getId()).distinct().count());

		RuleDefinitionParameterId firstRuleDefinitionParameterId = new RuleDefinitionParameterId();
		firstRuleDefinitionParameterId.setLevelId(2);
		firstRuleDefinitionParameterId.setRuleDefinitionId(-900001);
		firstRuleDefinitionParameterId.setValue("100000");

		RuleDefinitionParameterId secondtRuleDefinitionParameterId = new RuleDefinitionParameterId();
		secondtRuleDefinitionParameterId.setLevelId(3);
		secondtRuleDefinitionParameterId.setRuleDefinitionId(-900001);
		secondtRuleDefinitionParameterId.setValue("355");

		List<RuleDefinitionParameterId> ruleDefinitionParameterIds = Arrays.asList(firstRuleDefinitionParameterId,
				secondtRuleDefinitionParameterId);
		ruleDefinition.getDefinitionParameters().forEach(p -> {
			assertTrue(ruleDefinitionParameterIds.contains(p.getId()));
		});

		assertNotNull(ruleDefinition.getVersions());
		assertEquals(1, ruleDefinition.getVersions().size());
		assertEquals(-900003, ruleDefinition.getVersions().get(0).getId());
	}

}
