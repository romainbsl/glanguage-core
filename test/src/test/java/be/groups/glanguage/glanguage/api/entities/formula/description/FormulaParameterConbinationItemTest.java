package be.groups.glanguage.glanguage.api.entities.formula.description;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbinationItem;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

/**
 * Test class for {@link FormulaParameterConbinationItem}
 * 
 * @author DUPIREFR
 */
public class FormulaParameterConbinationItemTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaParameterConbinationItem} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
	public void testJpaMapping() {
		FormulaParameterConbinationItem parameter = getEntityManager().find(FormulaParameterConbinationItem.class, 1);
		
		/* Checking entity */
		assertNotNull(parameter);
		
		assertEquals(Integer.valueOf(1), parameter.getId());
		
		assertEquals("operand", parameter.getName());
		assertEquals("Objet à nier", parameter.getDescription(Language.FR));
		assertEquals("Object te ontkennen", parameter.getDescription(Language.NL));
		assertEquals("Item to negate", parameter.getDescription(Language.EN));
		assertNull(parameter.getDescription(Language.X));
		
		assertEquals(FormulaReturnType.BOOLEAN, parameter.getReturnType());
		
		/* Checking relationships */
		assertNotNull(parameter.getParametersCombination());
		assertEquals(Integer.valueOf(1), parameter.getParametersCombination().getId());
	}
	
}
