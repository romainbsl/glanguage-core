package be.groups.glanguage.glanguage.api.business;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactoryTest;
import be.groups.glanguage.glanguage.api.business.parser.ParserTest;
import be.groups.glanguage.glanguage.api.business.plan.PlanTest;
import be.groups.glanguage.glanguage.api.business.universe.UniverseTest;

/**
 * Test suite for Business module
 * 
 * @author DUPIREFR
 */
@RunWith(Suite.class)
@SuiteClasses({FormulaDescriptionFactoryTest.class, ParserTest.class, PlanTest.class, UniverseTest.class})
public class BusinessTestSuite {

}
