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
 * Test class for {@link FormulaTerminalNumeric}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaTerminalNumericIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;
    
	/*
   * Tests
	 */

  /**
   * Tests {@link FormulaTerminalNumeric#isValid(Evaluator)} when {@link
   * FormulaTerminalNumeric#getConstantValue()} is not null and well formatted
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValid() throws GLanguageException {
    FormulaTerminalNumeric formula = spy(FormulaTerminalNumeric.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC)).when(formula)
                                                                                    .getDescription();
    doReturn("1.5").when(formula).getConstantValue();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalNumeric#isValid(Evaluator)} when {@link
   * FormulaTerminalNumeric#getConstantValue()} is not null but not well formatted
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotWellFormatted() throws GLanguageException {
    FormulaTerminalNumeric formula = spy(FormulaTerminalNumeric.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC)).when(formula)
                                                                                    .getDescription();
    doReturn("1,5").when(formula).getConstantValue();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalNumeric#isValid(Evaluator)} when {@link
   * FormulaTerminalNumeric#getConstantValue()} is null
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNull() throws GLanguageException {
    FormulaTerminalNumeric formula = spy(FormulaTerminalNumeric.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC)).when(formula)
                                                                                    .getDescription();
    doReturn(null).when(formula).getConstantValue();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalNumeric#asText()}
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testAsText() throws GLanguageException {
    FormulaTerminalNumeric formula = spy(FormulaTerminalNumeric.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC)).when(formula)
                                                                                    .getDescription();
    doReturn("1.5").when(formula).getConstantValue();

    assertEquals("1.5", formula.asText());
  }
}
