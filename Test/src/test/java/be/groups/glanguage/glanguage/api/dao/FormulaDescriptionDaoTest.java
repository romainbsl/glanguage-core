package be.groups.glanguage.glanguage.api.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;

public class FormulaDescriptionDaoTest {

	@Test
	public void testFindAll() {
		Environment.setUp();
		TNSNames.setUp();

		JpaUtil.setEntityManager(JpaUtil.createDataSource(DatabaseIdentifier.DEVELOPMENT_DB));

		List<FormulaDescription> formulaDescriptions = new FormulaDescriptionDao().findAll();
		assertNotNull(formulaDescriptions);
		assertFalse(formulaDescriptions.isEmpty());
		System.out.println(formulaDescriptions.size());
		System.out.println(formulaDescriptions);
	}
	
}
