package be.groups.glanguage.glanguage.api.entities.formula.description.combination;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author michotte
 */
public class FormulaParameterCombinationIntegrationTest extends BaseDatabaseTest {

    /*
     * Tests
	 */

    /**
     * Tests {@link FormulaParameterCombination} JPA mapping
     */
    @Test
    @Category(JpaMappingTestsCategory.class)
    public void testJpaMapping() {
        FormulaParameterCombination parameterCombination = getEntityManager().find(FormulaParameterCombination.class,
                                                                                   9);

		/* Checking entity */
        assertNotNull(parameterCombination);
        assertEquals(Integer.valueOf(9), parameterCombination.getId());

		/* Checking relationships */
        assertNotNull(parameterCombination.getCombinationParameters());
        assertEquals(2, parameterCombination.getCombinationParameters().size());
        assertEquals(2, parameterCombination.getCombinationParameters().stream().map(d -> d.getId()).distinct().count());
    }

}
