package be.groups.glanguage.glanguage.api;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import be.groups.glanguage.glanguage.api.test.categories.IntegrationTestCategory;

@RunWith(Categories.class)
@Categories.ExcludeCategory({IntegrationTestCategory.class})
@SuiteClasses({ProjectIntegrationTestSuite.class})
public class ProjectWithoutExternalResourcesTestSuite {

}
