package be.groups.glanguage.glanguage.api.business;

import be.groups.glanguage.glanguage.api.business.evaluation.PlanEvaluatorIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactoryIntegrationTest;
import be.groups.glanguage.glanguage.api.business.parser.ParserTest;
import be.groups.glanguage.glanguage.api.business.plan.PlanIntegrationTest;
import be.groups.glanguage.glanguage.api.business.universe.UniverseIntegrationTest;

/**
 * Test suite for Business module
 * 
 * @author DUPIREFR
 */
@RunWith(Suite.class)
@SuiteClasses({FormulaDescriptionFactoryIntegrationTest.class, ParserTest.class, PlanEvaluatorIntegrationTest.class, PlanIntegrationTest.class,
        UniverseIntegrationTest.class})
public class BusinessIntegrationTestSuite {

}
