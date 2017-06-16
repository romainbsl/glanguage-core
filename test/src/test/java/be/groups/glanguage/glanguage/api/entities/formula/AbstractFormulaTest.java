package be.groups.glanguage.glanguage.api.entities.formula;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.test.categories.JpaMappingTestsCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class for {@link AbstractFormula}
 * 
 * @author DUPIREFR
 */
public class AbstractFormulaTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link AbstractFormula} JPA mapping
	 */
	@Test
	@Category(JpaMappingTestsCategory.class)
	public void testJpaMapping() {
		AbstractFormula formula = getEntityManager().find(AbstractFormula.class, -900003);

		/* Checking entity */
		assertNotNull(formula);

		assertEquals(-900003, formula.getId());

		assertEquals("TRUE", formula.getConstantValue());
		assertEquals(Integer.valueOf(4), formula.getSequenceNumber());
		
		/* Checking relationships */
		assertNotNull(formula.getDescription());
		assertEquals(Integer.valueOf(1004), formula.getDescription().getId());
	}

}
