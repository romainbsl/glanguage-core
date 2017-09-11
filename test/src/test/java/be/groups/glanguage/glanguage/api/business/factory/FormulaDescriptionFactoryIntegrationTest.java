package be.groups.glanguage.glanguage.api.business.factory;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FormulaDescriptionFactoryIntegrationTest extends BaseDatabaseTest {
	
	@Test
	public void test() {
		FormulaDescription undefined = FormulaDescriptionFactory.getDescription(FormulaType.UNDEFINED);
		assertNotNull(undefined);
		assertEquals(FormulaType.UNDEFINED, undefined.getType());
		
		FormulaDescription terminalInteger = FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER);
		assertNotNull(terminalInteger);
		assertEquals(FormulaType.TERMINAL_INTEGER, terminalInteger.getType());
	}
	
}
