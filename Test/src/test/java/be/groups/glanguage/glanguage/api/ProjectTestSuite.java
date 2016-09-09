package be.groups.glanguage.glanguage.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import be.groups.glanguage.glanguage.api.entities.EntitiesTestSuite;

/**
 * Test suite for the whole project
 * 
 * @author DUPIREFR
 */
@RunWith(Suite.class)
@SuiteClasses({ EntitiesTestSuite.class })
public class ProjectTestSuite {

}
