package be.groups.glanguage.core.business.factory.formula.implementations.terminal;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.test.categories.DatabaseTestCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Test class for {@link FormulaTerminalInteger}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaTerminalIntegerIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

	/*
   * Tests
	 */

  /**
   * Tests {@link FormulaTerminalInteger#isValid(Evaluator)} when {@link
   * FormulaTerminalInteger#getConstantValue()} is not null and well formatted
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValid() throws GLanguageException {
    FormulaTerminalInteger formula = spy(FormulaTerminalInteger.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER)).when(formula)
                                                                                    .getDescription();
    doReturn("1").when(formula).getConstantValue();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalInteger#isValid(Evaluator)} when {@link
   * FormulaTerminalInteger#getConstantValue()} is not null but not well formatted
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotWellFormatted() throws GLanguageException {
    FormulaTerminalInteger formula = spy(FormulaTerminalInteger.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER)).when(formula)
                                                                                    .getDescription();
    doReturn("x").when(formula).getConstantValue();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalInteger#isValid(Evaluator)} when {@link
   * FormulaTerminalInteger#getConstantValue()} is null
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNull() throws GLanguageException {
    FormulaTerminalInteger formula = spy(FormulaTerminalInteger.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER)).when(formula)
                                                                                    .getDescription();
    doReturn(null).when(formula).getConstantValue();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalInteger#asText()}
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testAsText() throws GLanguageException {
    FormulaTerminalInteger formula = spy(FormulaTerminalInteger.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER)).when(formula)
                                                                                    .getDescription();
    doReturn("1").when(formula).getConstantValue();

    Assert.assertEquals("1", formula.asText());
  }
}
