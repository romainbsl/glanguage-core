package be.groups.glanguage.core.business.factory;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaDescriptionFactoryIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

	@Test
	public void test() {
    FormulaDescription undefined = formulaDescriptionFactory.getDescription(FormulaType.UNDEFINED);
    assertNotNull(undefined);
		assertEquals(FormulaType.UNDEFINED, undefined.getType());

    FormulaDescription terminalInteger =
        formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER);
    assertNotNull(terminalInteger);
		assertEquals(FormulaType.TERMINAL_INTEGER, terminalInteger.getType());
	}
	
}
