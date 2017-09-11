package be.groups.glanguage.glanguage.api;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;

@RunWith(Categories.class)
@Categories.IncludeCategory(JpaMappingTestsCategory.class)
@SuiteClasses({ProjectIntegrationTestSuite.class})
public class ProjectMappingTestSuite {

}
