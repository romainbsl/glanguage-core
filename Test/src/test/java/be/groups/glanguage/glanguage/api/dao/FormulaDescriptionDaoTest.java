package be.groups.glanguage.glanguage.api.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;

public class FormulaDescriptionDaoTest extends DaoTest {

	@Test
	public void testFindAll() {
		List<FormulaDescription> formulaDescriptions = new FormulaDescriptionDao().findAll();
		assertNotNull(formulaDescriptions);
		assertFalse(formulaDescriptions.isEmpty());
		System.out.println(formulaDescriptions.size());
		System.out.println(formulaDescriptions);
	}
	
}
