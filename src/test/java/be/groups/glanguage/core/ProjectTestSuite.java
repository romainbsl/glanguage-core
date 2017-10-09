package be.groups.glanguage.core;

import be.groups.glanguage.core.business.BusinessIntegrationTestSuite;
import be.groups.glanguage.core.dao.DaoIntegrationTestSuite;
import be.groups.glanguage.core.entities.EntitiesUnitTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by michotte on 9/10/2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({EntitiesUnitTestSuite.class, DaoIntegrationTestSuite.class, BusinessIntegrationTestSuite.class})
public class ProjectTestSuite {
}
