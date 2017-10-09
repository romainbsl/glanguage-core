package be.groups.glanguage.core.business.factory.formula.implementations.format;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.format.FormulaFormatString;
import be.groups.glanguage.core.entities.utils.format.FormatAlignment;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaFormatString}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaFormatStringIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;
  /*
   * Tests
	 */

  /**
   * Tests {@link FormulaFormatString#isValid(Evaluator)} when parameters match
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidMatching() throws GLanguageException {
    AbstractFormula string = mock(AbstractFormula.class);
    when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula width = mock(AbstractFormula.class);
    when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula alignment = mock(AbstractFormula.class);
    when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    when(alignment.getStringValue(null)).thenReturn(FormatAlignment.Values.NO_JUSTIFY);

    AbstractFormula fill = mock(AbstractFormula.class);
    when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaFormatString formula = spy(FormulaFormatString.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_STRING)).when(formula)
                                                                                   .getDescription();
    doReturn(Arrays.asList(string, width, alignment, fill)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaFormatString#isValid(Evaluator)} when parameters match and without optional
   * parameters
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidMatchingWithoutOptionalParameters() throws GLanguageException {
    AbstractFormula string = mock(AbstractFormula.class);
    when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaFormatString formula = spy(FormulaFormatString.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_STRING)).when(formula)
                                                                                   .getDescription();
    doReturn(Arrays.asList(string)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaFormatString#isValid(Evaluator)} when parameters don't match
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotMatching() throws GLanguageException {
    AbstractFormula string = mock(AbstractFormula.class);
    when(string.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula width = mock(AbstractFormula.class);
    when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula alignment = mock(AbstractFormula.class);
    when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula fill = mock(AbstractFormula.class);
    when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaFormatString formula = spy(FormulaFormatString.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_STRING)).when(formula)
                                                                                   .getDescription();
    doReturn(Arrays.asList(string, width, alignment, fill)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaFormatString#getReturnType(Evaluator)} when parameters match
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeMatching() throws GLanguageException {
    FormulaFormatString formula = spy(FormulaFormatString.class);

    assertEquals(FormulaReturnType.STRING, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaFormatString#asText()}
   */
  @Test
  public void testAsText() throws GLanguageException {
    AbstractFormula string = mock(AbstractFormula.class);
    when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    when(string.asText()).thenReturn("some_rule1");

    AbstractFormula width = mock(AbstractFormula.class);
    when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(width.asText()).thenReturn("10");

    AbstractFormula alignment = mock(AbstractFormula.class);
    when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    when(alignment.asText()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);

    AbstractFormula fill = mock(AbstractFormula.class);
    when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    when(fill.asText()).thenReturn("0");

    FormulaFormatString formula = spy(FormulaFormatString.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_STRING)).when(formula)
                                                                                   .getDescription();
    doReturn(Arrays.asList(string, width, alignment, fill)).when(formula).getParameters();

    assertEquals("formatString(some_rule1; 10; " + FormatAlignment.Values.LEFT_JUSTIFY + "; 0)",
        formula.asText());
  }
}
