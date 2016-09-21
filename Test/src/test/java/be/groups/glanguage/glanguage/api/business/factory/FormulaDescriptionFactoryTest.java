package be.groups.glanguage.glanguage.api.business.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;

public class FormulaDescriptionFactoryTest {
	
	@Test
	public void test() {
		Environment.setUp();
		TNSNames.setUp();
		
		JpaUtil.setEntityManager(JpaUtil.createDataSource(DatabaseIdentifier.DEVELOPMENT_DB));
		
		FormulaDescription undefined = FormulaDescriptionFactory.getDescription(FormulaType.UNDEFINED);
		assertNotNull(undefined);
		assertEquals(FormulaType.UNDEFINED, undefined.getType());
		
		FormulaDescription terminalInteger = FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER);
		assertNotNull(terminalInteger);
		assertEquals(FormulaType.TERMINAL_INTEGER, terminalInteger.getType());
	}
	
}
