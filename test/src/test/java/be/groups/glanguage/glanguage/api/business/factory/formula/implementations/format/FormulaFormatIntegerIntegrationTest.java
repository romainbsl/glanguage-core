package be.groups.glanguage.glanguage.api.business.factory.formula.implementations.format;

import be.groups.glanguage.glanguage.api.business.factory.*;
import be.groups.glanguage.glanguage.api.dao.*;
import be.groups.glanguage.glanguage.api.entities.evaluation.*;
import be.groups.glanguage.glanguage.api.entities.formula.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.*;
import be.groups.glanguage.glanguage.api.entities.utils.format.*;
import be.groups.glanguage.glanguage.api.error.exception.*;
import be.groups.glanguage.glanguage.api.test.categories.*;
import java.util.*;
import org.junit.*;
import org.junit.experimental.categories.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaFormatInteger}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaFormatIntegerIntegrationTest extends DaoIntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;
  /*
   * Tests
	 */

  /**
   * Tests {@link FormulaFormatInteger#isValid(Evaluator)} when parameters match
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidMatching() throws GLanguageException {
    AbstractFormula number = mock(AbstractFormula.class);
    when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula width = mock(AbstractFormula.class);
    when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula alignment = mock(AbstractFormula.class);
    when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    doReturn("none").when(alignment).getStringValue(null);

    AbstractFormula fill = mock(AbstractFormula.class);
    when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula sign = mock(AbstractFormula.class);
    when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    doReturn("none").when(sign).getStringValue(null);

    FormulaFormatInteger formula = spy(FormulaFormatInteger.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_INTEGER)).when(formula)
                                                                                    .getDescription();
    doReturn(Arrays.asList(number, width, alignment, fill, sign)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaFormatInteger#isValid(Evaluator)} when parameters match and without
   * optional parameters
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidMatchingWithoutOptionalParameters() throws GLanguageException {
    AbstractFormula number = mock(AbstractFormula.class);
    when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaFormatInteger formula = spy(FormulaFormatInteger.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_INTEGER)).when(formula)
                                                                                    .getDescription();
    doReturn(Arrays.asList(number)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaFormatInteger#isValid(Evaluator)} when parameters don't match
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotMatching() throws GLanguageException {
    AbstractFormula number = mock(AbstractFormula.class);
    when(number.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula width = mock(AbstractFormula.class);
    when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula alignment = mock(AbstractFormula.class);
    when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula fill = mock(AbstractFormula.class);
    when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula sign = mock(AbstractFormula.class);
    when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaFormatInteger formula = spy(FormulaFormatInteger.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_INTEGER)).when(formula)
                                                                                    .getDescription();
    doReturn(Arrays.asList(number, width, alignment, fill, sign)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaFormatInteger#getReturnType(Evaluator)} when parameters match
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeMatching() throws GLanguageException {
    FormulaFormatInteger formula = spy(FormulaFormatInteger.class);

    assertEquals(FormulaReturnType.STRING, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaFormatInteger#asText()}
   */
  @Test
  public void testAsText() throws GLanguageException {
    AbstractFormula number = mock(AbstractFormula.class);
    when(number.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(number.asText()).thenReturn("10");

    AbstractFormula width = mock(AbstractFormula.class);
    when(width.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(width.asText()).thenReturn("5");

    AbstractFormula alignment = mock(AbstractFormula.class);
    when(alignment.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    when(alignment.asText()).thenReturn(FormatAlignment.Values.LEFT_JUSTIFY);

    AbstractFormula fill = mock(AbstractFormula.class);
    when(fill.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    when(fill.asText()).thenReturn("*");

    AbstractFormula sign = mock(AbstractFormula.class);
    when(sign.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    when(sign.asText()).thenReturn(FormatSign.Values.NONE);

    FormulaFormatInteger formula = spy(FormulaFormatInteger.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_INTEGER)).when(formula)
                                                                                    .getDescription();
    doReturn(Arrays.asList(number, width, alignment, fill, sign)).when(formula).getParameters();

    assertEquals("formatInteger(10; 5; "
            + FormatAlignment.Values.LEFT_JUSTIFY
            + "; *; "
            + FormatSign.Values.NONE
            + ")",
        formula.asText());
  }
}
