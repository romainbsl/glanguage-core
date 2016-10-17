package be.groups.glanguage.glanguage.api.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;

public class FormulaDaoTest extends DaoTest {

	@Test
	public void testFindById() {
		AbstractFormula formula = new FormulaDao().findById(900000);
		assertNotNull(formula);
		assertEquals(900000, formula.getId());
	}
	
}
