package be.groups.glanguage.glanguage.api;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(JpaMappingTest.class)
@SuiteClasses({ProjectTestSuite.class})
public class ProjectMappingTestSuite {

}
