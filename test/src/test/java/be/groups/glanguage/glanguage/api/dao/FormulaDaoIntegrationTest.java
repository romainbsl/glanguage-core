package be.groups.glanguage.glanguage.api.dao;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FormulaDaoIntegrationTest extends DaoIntegrationTest {

	@Test
	public void testFindById() {
		AbstractFormula formula = new FormulaDao().findById(-900000);
		assertNotNull(formula);
		assertEquals(-900000, formula.getId());
	}
	
}
