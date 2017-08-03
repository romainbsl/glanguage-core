package be.groups.glanguage.glanguage.api.entities.formula.description;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class for {@link FormulaDescription}
 *
 * @author DUPIREFR
 * @author MMI
 */
public class FormulaDescriptionIntegrationTest extends BaseDatabaseTest {

	/*
     * Tests
	 */

    /**
     * Tests {@link FormulaDescription} JPA mapping
     */
    @Test
    @Category(JpaMappingTestsCategory.class)
    public void testJpaMapping() {
        FormulaDescription formulaDescription = getEntityManager().find(FormulaDescription.class, 214);
		
		/* Checking entity */
        assertNotNull(formulaDescription);

        assertEquals(Integer.valueOf(214), formulaDescription.getId());

        assertEquals(FormulaType.OP_OR, formulaDescription.getType());

        assertEquals("OR", formulaDescription.getName());

        assertEquals("'OU' logique", formulaDescription.getDescription(Language.FR));
        assertEquals("Logische 'OF'", formulaDescription.getDescription(Language.NL));
        assertEquals("", formulaDescription.getDescription(Language.X));
        assertEquals("Logical 'OR'", formulaDescription.getDescription(Language.EN));

        assertEquals(FormulaPriority.OR, formulaDescription.getPriority());
		
		/* Checking relationships */
        assertNotNull(formulaDescription.getUsages());
        assertEquals(1, formulaDescription.getUsages().size());
        assertEquals(Integer.valueOf(96), formulaDescription.getUsages().iterator().next().getId());
    }

}
