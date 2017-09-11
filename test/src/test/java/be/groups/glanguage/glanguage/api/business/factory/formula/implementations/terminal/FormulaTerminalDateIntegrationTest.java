package be.groups.glanguage.glanguage.api.business.factory.formula.implementations.terminal;

import be.groups.glanguage.glanguage.api.*;
import be.groups.glanguage.glanguage.api.business.factory.*;
import be.groups.glanguage.glanguage.api.entities.evaluation.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.*;
import be.groups.glanguage.glanguage.api.error.exception.*;
import be.groups.glanguage.glanguage.api.test.categories.*;
import org.junit.*;
import org.junit.experimental.categories.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaTerminalDate}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaTerminalDateIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;
  /*
   * Tests
	 */

  /**
   * Tests {@link FormulaTerminalDate#isValid(Evaluator)} when {@link
   * FormulaTerminalDate#getConstantValue()} is not null and well formatted
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValid() throws GLanguageException {
    FormulaTerminalDate formula = spy(FormulaTerminalDate.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DATE)).when(formula)
                                                                                 .getDescription();
    doReturn("01/01/2015").when(formula).getConstantValue();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalDate#isValid(Evaluator)} when {@link
   * FormulaTerminalDate#getConstantValue()} is not null but not well formatted
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotWellFormatted() throws GLanguageException {
    FormulaTerminalDate formula = spy(FormulaTerminalDate.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DATE)).when(formula)
                                                                                 .getDescription();
    doReturn("2015/01/01").when(formula).getConstantValue();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalDate#isValid(Evaluator)} when {@link
   * FormulaTerminalDate#getConstantValue()} is null
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNull() throws GLanguageException {
    FormulaTerminalDate formula = spy(FormulaTerminalDate.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DATE)).when(formula)
                                                                                 .getDescription();
    doReturn(null).when(formula).getConstantValue();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalDate#asText()}
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testAsText() throws GLanguageException {
    FormulaTerminalDate formula = spy(FormulaTerminalDate.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DATE)).when(formula)
                                                                                 .getDescription();
    doReturn("01/01/2015").when(formula).getConstantValue();

    assertEquals("01/01/2015", formula.asText());
  }
}
