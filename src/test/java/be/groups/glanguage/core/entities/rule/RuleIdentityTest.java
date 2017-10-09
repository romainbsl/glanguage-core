package be.groups.glanguage.core.entities.rule;

import be.groups.glanguage.core.entities.rule.definition.DefinitionLevel;
import be.groups.glanguage.core.entities.rule.definition.RuleDefinitionParameter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class for {@link RuleIdentity}
 * 
 * @author DUPIREFR
 */
public class RuleIdentityTest {

	/*
	 * Tests
	 */
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
		ruleDefinition.setId(0L);

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
		defaultRuleDefinition.setId(0L);

		RuleDefinition employerRuleDefinition = new RuleDefinition();
		employerRuleDefinition.setId(1L);
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
		defaultRuleDefinition.setId(0L);

		RuleDefinition employerRuleDefinition = new RuleDefinition();
		employerRuleDefinition.setId(1L);
		employerRuleDefinition.getDefinitionParameters().add(new RuleDefinitionParameter(DefinitionLevel.EMPLOYER, "100000"));

		ruleIdentity.getRuleDefinitions().add(defaultRuleDefinition);
		ruleIdentity.getRuleDefinitions().add(employerRuleDefinition);

		assertNotNull(ruleIdentity.getRuleDefinition(ruleDefinitionParameters));
		assertEquals(employerRuleDefinition, ruleIdentity.getRuleDefinition(ruleDefinitionParameters));
	}

}
