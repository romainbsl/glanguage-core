package be.groups.glanguage.glanguage.api.entities.formula.description;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test class for {@link FormulaParametersCombination}
 * 
 * @author DUPIREFR
 */
public class FormulaParametersCombinationTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaParametersCombination} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
	public void testJpaMapping() {
		FormulaParametersCombination parametersCombination = getEntityManager().find(FormulaParametersCombination.class, 2);
		
		/* Checking entity */
		assertNotNull(parametersCombination);
		
		assertEquals(Integer.valueOf(2), parametersCombination.getId());
		
		assertEquals(FormulaReturnType.BOOLEAN, parametersCombination.getReturnType());
		
		/* Checking relationships */
		assertNotNull(parametersCombination.getDescription());
		assertEquals(Integer.valueOf(213), parametersCombination.getDescription().getId());
		
		assertNotNull(parametersCombination.getParametersDescriptions());
		assertEquals(2, parametersCombination.getParametersDescriptions().size());
		assertEquals(2, parametersCombination.getParametersDescriptions().stream().map(d -> d.getId()).distinct().count());
		
		List<Integer> parametersDescriptionsIds = Arrays.asList(2, 3);
		parametersCombination.getParametersDescriptions().forEach(d -> {
			assertTrue(parametersDescriptionsIds.contains(d.getId()));
		});
	}
	
}
