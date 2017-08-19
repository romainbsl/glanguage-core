package be.groups.glanguage.glanguage.api.business.factory;

import be.groups.glanguage.glanguage.api.dao.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaDescriptionFactoryIntegrationTest extends DaoIntegrationTest {

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
