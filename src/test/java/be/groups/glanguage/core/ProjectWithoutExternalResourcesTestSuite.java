package be.groups.glanguage.core;

import be.groups.glanguage.core.test.categories.IntegrationTestCategory;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@Categories.ExcludeCategory({IntegrationTestCategory.class})
@SuiteClasses({ProjectIntegrationTestSuite.class})
public class ProjectWithoutExternalResourcesTestSuite {

}
