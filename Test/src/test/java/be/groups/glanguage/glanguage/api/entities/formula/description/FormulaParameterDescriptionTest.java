package be.groups.glanguage.glanguage.api.entities.formula.description;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

/**
 * Test class for {@link FormulaParameterDescription}
 * 
 * @author DUPIREFR
 */
public class FormulaParameterDescriptionTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaParameterDescription} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
	public void testJpaMapping() {
		FormulaParameterDescription parameterDescription = getEntityManager().find(FormulaParameterDescription.class, 1);
		
		/* Checking entity */
		assertNotNull(parameterDescription);
		
		assertEquals(Integer.valueOf(1), parameterDescription.getId());
		
		assertEquals("operand", parameterDescription.getName());
		assertEquals("Objet à nier", parameterDescription.getDescriptionFr());
		assertEquals("Object te ontkennen", parameterDescription.getDescriptionNl());
		assertEquals("Item to negate", parameterDescription.getDescriptionX());
		assertNull(parameterDescription.getDescriptionDe());
		
		assertEquals(FormulaReturnType.BOOLEAN, parameterDescription.getReturnType());
		
		/* Checking relationships */
		assertNotNull(parameterDescription.getParametersCombination());
		assertEquals(Integer.valueOf(1), parameterDescription.getParametersCombination().getId());
	}
	
}
