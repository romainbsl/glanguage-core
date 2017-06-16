package be.groups.glanguage.glanguage.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import be.groups.glanguage.glanguage.api.business.BusinessTestSuite;
import be.groups.glanguage.glanguage.api.dao.DaoTestSuite;
import be.groups.glanguage.glanguage.api.entities.EntitiesTestSuite;
import be.groups.glanguage.glanguage.api.ws.WsTestSuite;

/**
 * Test suite for the whole project
 * 
 * @author DUPIREFR
 */
@RunWith(Suite.class)
@SuiteClasses({EntitiesTestSuite.class, DaoTestSuite.class, BusinessTestSuite.class, WsTestSuite.class})
public class ProjectTestSuite {

}
