package be.groups.glanguage.glanguage.api.entities.formula.description.combination;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

/**
 * Test class for {@link FormulaParameterCombinationItem}
 *
 * @author DUPIREFR
 */
public class FormulaParameterCombinationItemIntegrationTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */

    /**
     * Tests {@link FormulaParameterCombinationItem} JPA mapping
     */
    @Test
    @Category(JpaMappingTestsCategory.class)
    public void testJpaMapping() {
        FormulaParameterCombinationItem parameter = getEntityManager().find(FormulaParameterCombinationItem.class, 1);
		
		/* Checking entity */
        assertNotNull(parameter);

        assertEquals(Integer.valueOf(1), parameter.getId());

        assertEquals("integer", parameter.getName(Language.EN));
        assertEquals("expression de type INTEGER", parameter.getDescription(Language.FR));
        assertEquals("expressie van type INTEGER", parameter.getDescription(Language.NL));
        assertEquals("expression of type INTEGER", parameter.getDescription(Language.EN));
        assertEquals("", parameter.getDescription(Language.X));

        assertEquals(1, parameter.getTypes().size());
        assertTrue(parameter.getReturnTypes().contains(FormulaReturnType.INTEGER));
		
		/* Checking relationships */
        assertNotNull(parameter.getCombination());
        assertEquals(Integer.valueOf(1), parameter.getCombination().getId());
    }

}
