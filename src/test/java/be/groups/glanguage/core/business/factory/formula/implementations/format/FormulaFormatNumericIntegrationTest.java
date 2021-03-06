package be.groups.glanguage.core.business.factory.formula.implementations.format;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.format.FormulaFormatNumeric;
import be.groups.glanguage.core.entities.utils.format.FormatAlignment;
import be.groups.glanguage.core.entities.utils.format.FormatSign;
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
 * Test class for {@link FormulaFormatNumeric}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaFormatNumericIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;
  /*
   * Tests
	 */

  /**
   * Tests {@link FormulaFormatNumeric#isValid(Evaluator)} when parameters match
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidMatching() throws GLanguageException {
    AbstractFormula number = mock(AbstractFormula.class);
    when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula width = mock(AbstractFormula.class);
    when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula decimals = mock(AbstractFormula.class);
    when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula alignment = mock(AbstractFormula.class);
    when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    doReturn("none").when(alignment).getStringValue(null);

    AbstractFormula fill = mock(AbstractFormula.class);
    when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula sign = mock(AbstractFormula.class);
    when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    doReturn("none").when(sign).getStringValue(null);

    AbstractFormula mark = mock(AbstractFormula.class);
    when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_NUMERIC)).when(formula)
                                                                                    .getDescription();
    doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula)
                                                                                 .getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaFormatNumeric#isValid(Evaluator)} when parameters match and without
   * optional parameters
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidMatchingWithoutOptionalParameters() throws GLanguageException {
    AbstractFormula number = mock(AbstractFormula.class);
    when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_NUMERIC)).when(formula)
                                                                                    .getDescription();
    doReturn(Arrays.asList(number)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaFormatNumeric#isValid(Evaluator)} when parameters don't match
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotMatching() throws GLanguageException {
    AbstractFormula number = mock(AbstractFormula.class);
    when(number.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula width = mock(AbstractFormula.class);
    when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula decimals = mock(AbstractFormula.class);
    when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula alignment = mock(AbstractFormula.class);
    when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula fill = mock(AbstractFormula.class);
    when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula sign = mock(AbstractFormula.class);
    when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula mark = mock(AbstractFormula.class);
    when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_NUMERIC)).when(formula)
                                                                                    .getDescription();
    doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula)
                                                                                 .getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaFormatNumeric#getReturnType()} when parameters match
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeMatching() throws GLanguageException {
    FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);

    assertEquals(FormulaReturnType.STRING, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaFormatNumeric#asText()}
   */
  @Test
  public void testAsText() throws GLanguageException {
    AbstractFormula number = mock(AbstractFormula.class);
    when(number.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
    when(number.asText()).thenReturn("11.57");

    AbstractFormula width = mock(AbstractFormula.class);
    when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(width.asText()).thenReturn("5");

    AbstractFormula decimals = mock(AbstractFormula.class);
    when(decimals.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(decimals.asText()).thenReturn("1");

    AbstractFormula alignment = mock(AbstractFormula.class);
    when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    when(alignment.asText()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);

    AbstractFormula fill = mock(AbstractFormula.class);
    when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    when(fill.asText()).thenReturn("*");

    AbstractFormula sign = mock(AbstractFormula.class);
    when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    when(sign.asText()).thenReturn(FormatSign.Values.NONE);

    AbstractFormula mark = mock(AbstractFormula.class);
    when(mark.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    when(mark.asText()).thenReturn(",");

    FormulaFormatNumeric formula = spy(FormulaFormatNumeric.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_NUMERIC)).when(formula)
                                                                                    .getDescription();
    doReturn(Arrays.asList(number, width, decimals, alignment, fill, sign, mark)).when(formula)
                                                                                 .getParameters();

    assertEquals("formatNumeric(11.57; 5; 1; "
            + FormatAlignment.Values.LEFT_JUSTIFY
            + "; *; "
            + FormatSign.Values.NONE
            + "; ,)",
        formula.asText());
  }
}
