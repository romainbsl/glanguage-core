package be.groups.glanguage.core;

import be.groups.glanguage.core.business.BusinessIntegrationTestSuite;
import be.groups.glanguage.core.dao.DaoIntegrationTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite for the whole project
 * 
 * @author DUPIREFR
 */
@RunWith(Suite.class)
@SuiteClasses({DaoIntegrationTestSuite.class, BusinessIntegrationTestSuite.class})
public class ProjectIntegrationTestSuite {

}
