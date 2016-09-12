package be.groups.glanguage.glanguage.api.entities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import be.groups.glanguage.glanguage.api.entities.rule.RuleDefinitionTest;
import be.groups.glanguage.glanguage.api.entities.rule.RuleIdentityTest;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersionTest;
import be.groups.glanguage.glanguage.api.entities.rule.definition.RuleDefinitionParameterTest;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetTest;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersionTest;

/**
 * Test suite for entities package
 * 
 * @author DUPIREFR
 */
@RunWith(Suite.class)
@SuiteClasses({ RuleSetTest.class, RuleSetVersionTest.class, RuleIdentityTest.class, RuleDefinitionTest.class,
		RuleDefinitionParameterTest.class, RuleVersionTest.class })
public class EntitiesTestSuite {

}
