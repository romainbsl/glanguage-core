package be.groups.glanguage.glanguage.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import be.groups.glanguage.glanguage.api.business.BusinessIntegrationTestSuite;
import be.groups.glanguage.glanguage.api.dao.DaoIntegrationTestSuite;
import be.groups.glanguage.glanguage.api.entities.EntitiesIntegrationTestSuite;
import be.groups.glanguage.glanguage.api.ws.WsIntegrationTestSuite;

/**
 * Test suite for the whole project
 * 
 * @author DUPIREFR
 */
@RunWith(Suite.class)
@SuiteClasses({EntitiesIntegrationTestSuite.class, DaoIntegrationTestSuite.class, BusinessIntegrationTestSuite.class, WsIntegrationTestSuite.class})
public class ProjectIntegrationTestSuite {

}
