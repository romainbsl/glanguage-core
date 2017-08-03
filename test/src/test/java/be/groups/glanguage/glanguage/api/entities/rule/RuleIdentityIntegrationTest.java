package be.groups.glanguage.glanguage.api.entities.rule;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.entities.rule.definition.DefinitionLevel;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameter;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test class for {@link RuleIdentity}
 * 
 * @author DUPIREFR
 */
public class RuleIdentityIntegrationTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link RuleIdentity} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
	public void testJpaMapping() {
		RuleIdentity ruleIdentity = getEntityManager().find(RuleIdentity.class, -900000);

		/* Checking entity */
		assertNotNull(ruleIdentity);

		assertEquals(-900000, ruleIdentity.getId());

		/* Checking relationships */
		assertNotNull(ruleIdentity.getRuleDefinitions());
		assertEquals(2, ruleIdentity.getRuleDefinitions().size());
		assertEquals(2, ruleIdentity.getRuleDefinitions().stream().map(RuleDefinition::getId).distinct().count());

		List<Integer> ruleDefinitionIds = Arrays.asList(-900000, -900001);
		ruleIdentity.getRuleDefinitions().forEach(d -> assertTrue(ruleDefinitionIds.contains(d.getId())));
	}

	/**
	 * Tests {@link RuleIdentity#getRuleDefinition(Collection)} when no specific
	 * definition is defined
	 */
	@Test
	public void testGetDefinitionNoSpecificDefinition() {
		RuleIdentity ruleIdentity = new RuleIdentity();

		List<RuleDefinitionParameter> ruleDefinitionParameters = new ArrayList<>();
		ruleDefinitionParameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "100000"));

		RuleDefinition ruleDefinition = new RuleDefinition();

		ruleIdentity.getRuleDefinitions().add(ruleDefinition);

		assertNotNull(ruleIdentity.getRuleDefinition(ruleDefinitionParameters));
		assertEquals(ruleDefinition, ruleIdentity.getRuleDefinition(ruleDefinitionParameters));
	}

	/**
	 * Tests {@link RuleIdentity#getRuleDefinition(Collection)} when specific
	 * definitions are defined, but none is matching criteria
	 */
	@Test
	public void testGetDefinitionNoMatchingDefinition() {
		RuleIdentity ruleIdentity = new RuleIdentity();

		List<RuleDefinitionParameter> ruleDefinitionParameters = new ArrayList<>();
		ruleDefinitionParameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "100000"));

		RuleDefinition defaultRuleDefinition = new RuleDefinition();
		defaultRuleDefinition.setId(0);

		RuleDefinition employerRuleDefinition = new RuleDefinition();
		employerRuleDefinition.setId(1);
		employerRuleDefinition.getDefinitionParameters().add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "200000"));

		ruleIdentity.getRuleDefinitions().add(defaultRuleDefinition);
		ruleIdentity.getRuleDefinitions().add(employerRuleDefinition);

		assertNotNull(ruleIdentity.getRuleDefinition(ruleDefinitionParameters));
		assertEquals(defaultRuleDefinition, ruleIdentity.getRuleDefinition(ruleDefinitionParameters));
	}

	/**
	 * Tests {@link RuleIdentity#getRuleDefinition(Collection)} when specific
	 * definitions are defined and one is matching criteria
	 */
	@Test
	public void testGetDefinitionMatching() {
		RuleIdentity ruleIdentity = new RuleIdentity();

		List<RuleDefinitionParameter> ruleDefinitionParameters = new ArrayList<>();
		ruleDefinitionParameters.add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "100000"));

		RuleDefinition defaultRuleDefinition = new RuleDefinition();
		defaultRuleDefinition.setId(0);

		RuleDefinition employerRuleDefinition = new RuleDefinition();
		employerRuleDefinition.setId(1);
		employerRuleDefinition.getDefinitionParameters().add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "100000"));

		ruleIdentity.getRuleDefinitions().add(defaultRuleDefinition);
		ruleIdentity.getRuleDefinitions().add(employerRuleDefinition);

		assertNotNull(ruleIdentity.getRuleDefinition(ruleDefinitionParameters));
		assertEquals(employerRuleDefinition, ruleIdentity.getRuleDefinition(ruleDefinitionParameters));
	}

}
