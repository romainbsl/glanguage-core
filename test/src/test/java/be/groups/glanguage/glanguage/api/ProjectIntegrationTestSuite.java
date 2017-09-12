package be.groups.glanguage.glanguage.api;

import be.groups.glanguage.glanguage.api.business.*;
import be.groups.glanguage.glanguage.api.dao.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

/**
 * Test suite for the whole project
 * 
 * @author DUPIREFR
 */
@RunWith(Suite.class)
@SuiteClasses({DaoIntegrationTestSuite.class, BusinessIntegrationTestSuite.class})
public class ProjectIntegrationTestSuite {

}
