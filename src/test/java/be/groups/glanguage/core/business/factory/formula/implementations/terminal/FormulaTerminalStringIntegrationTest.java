package be.groups.glanguage.core.business.factory.formula.implementations.terminal;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.terminal.FormulaTerminalString;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Test class for {@link FormulaTerminalString}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaTerminalStringIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;
  /*
   * Tests
	 */

  /**
   * Tests {@link FormulaTerminalString#isValid(Evaluator)} when {@link
   * FormulaTerminalString#getConstantValue()} is not null and well formatted
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValid() throws GLanguageException {
    FormulaTerminalString formula = spy(FormulaTerminalString.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_STRING)).when(formula)
                                                                                   .getDescription();
    doReturn("string").when(formula).getConstantValue();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalString#isValid(Evaluator)} when {@link
   * FormulaTerminalString#getConstantValue()} is null
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNull() throws GLanguageException {
    FormulaTerminalString formula = spy(FormulaTerminalString.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_STRING)).when(formula)
                                                                                   .getDescription();
    doReturn(null).when(formula).getConstantValue();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalString#asText()}
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testAsText() throws GLanguageException {
    FormulaTerminalString formula = spy(FormulaTerminalString.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_STRING)).when(formula)
                                                                                   .getDescription();
    doReturn("string").when(formula).getConstantValue();

    assertEquals("\"string\"", formula.asText());
  }
}
