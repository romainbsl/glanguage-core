package be.groups.glanguage.core;

import be.groups.glanguage.core.test.categories.JpaMappingTestsCategory;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@Categories.IncludeCategory(JpaMappingTestsCategory.class)
@SuiteClasses({ProjectIntegrationTestSuite.class})
public class ProjectMappingTestSuite {

}
