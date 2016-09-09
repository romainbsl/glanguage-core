package be.groups.glanguage.glanguage.api.entities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetTest;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersionTest;

/**
 * Test suite for entities package
 * 
 * @author DUPIREFR
 */
@RunWith(Suite.class)
@SuiteClasses({ RuleSetTest.class, RuleSetVersionTest.class })
public class EntitiesTestSuite {

}
