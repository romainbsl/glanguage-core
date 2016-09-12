package be.groups.glanguage.glanguage.api;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import be.groups.glanguage.glanguage.api.test.categories.DatabaseTest;

@RunWith(Categories.class)
@Categories.ExcludeCategory(DatabaseTest.class)
@SuiteClasses({ ProjectTestSuite.class })
public class ProjectWithoutDatabaseTestSuite {

}
