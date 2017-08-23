package be.groups.glanguage.glanguage.api.entities.formula.description.usage;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author michotte
 */
public class FormulaUsageIntegrationTest extends BaseDatabaseTest {

	/*
     * Tests
	 */

    /**
     * Tests {@link FormulaDescription} JPA mapping
     */
    @Test
    @Category(JpaMappingTestsCategory.class)
    public void testJpaMapping() {
        FormulaUsage formulaUsage = getEntityManager().find(FormulaUsage.class, 1);

		/* Checking entity */
        assertNotNull(formulaUsage);
        assertEquals(Integer.valueOf(1), formulaUsage.getId());

		/* Checking relationships */
        assertNotNull(formulaUsage.getFormulaDescription());
        assertNotNull(formulaUsage.getParameterCombination());
        assertEquals(Integer.valueOf(1), formulaUsage.getFormulaDescription().getId());
        assertEquals(Integer.valueOf(1), formulaUsage.getParameterCombination().getId());
    }

}