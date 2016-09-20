package be.groups.glanguage.glanguage.api;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import be.groups.glanguage.glanguage.api.test.categories.DatabaseTest;
import be.groups.glanguage.glanguage.api.test.categories.WsTest;

@RunWith(Categories.class)
@Categories.ExcludeCategory({DatabaseTest.class, WsTest.class})
@SuiteClasses({ProjectTestSuite.class})
public class ProjectWithoutExternalResourcesTestSuite {

}
