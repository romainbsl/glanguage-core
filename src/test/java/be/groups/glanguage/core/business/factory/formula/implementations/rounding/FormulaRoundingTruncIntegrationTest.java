package be.groups.glanguage.core.business.factory.formula.implementations.rounding;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.rounding.FormulaRoundingTrunc;
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
 * Test class for {@link FormulaRoundingTrunc}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaRoundingTruncIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;
    
	/*
     * Tests
	 */

  /**
   * Tests {@link FormulaRoundingTrunc#isValid(Evaluator)} when parameter is integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidInteger() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaRoundingTrunc formula = spy(FormulaRoundingTrunc.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_TRUNC)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaRoundingTrunc#isValid(Evaluator)} when parameter is numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNumeric() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaRoundingTrunc formula = spy(FormulaRoundingTrunc.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_TRUNC)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaRoundingTrunc#isValid(Evaluator)} when parameter is not integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotIntegerOrNumeric() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaRoundingTrunc formula = spy(FormulaRoundingTrunc.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_TRUNC)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaRoundingTrunc#isValid(Evaluator)} when parameter is integer and precision
   * is integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidIntegerInteger() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula precision = mock(AbstractFormula.class);
    when(precision.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaRoundingTrunc formula = spy(FormulaRoundingTrunc.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_TRUNC)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaRoundingTrunc#isValid(Evaluator)} when parameter is numeric and precision
   * is integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNumericInteger() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula precision = mock(AbstractFormula.class);
    when(precision.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaRoundingTrunc formula = spy(FormulaRoundingTrunc.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_TRUNC)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaRoundingTrunc#isValid(Evaluator)} when parameter is numeric and precision
   * is numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNumericNumeric() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula precision = mock(AbstractFormula.class);
    when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaRoundingTrunc formula = spy(FormulaRoundingTrunc.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_TRUNC)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaRoundingTrunc#getReturnType()} when parameter is integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeInteger() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaRoundingTrunc formula = spy(FormulaRoundingTrunc.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_TRUNC)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaRoundingTrunc#getReturnType()} when parameter is numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeNumeric() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaRoundingTrunc formula = spy(FormulaRoundingTrunc.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_TRUNC)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaRoundingTrunc#getReturnType()} when parameter is not integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeNotIntegerOrNumeric() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaRoundingTrunc formula = spy(FormulaRoundingTrunc.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_TRUNC)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaRoundingTrunc#getReturnType()} when parameter is integer and precision is
   * integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeIntegerInteger() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula precision = mock(AbstractFormula.class);
    when(precision.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaRoundingTrunc formula = spy(FormulaRoundingTrunc.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_TRUNC)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

    assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaRoundingTrunc#getReturnType()} when parameter is numeric and precision is
   * integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeNumericInteger() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula precision = mock(AbstractFormula.class);
    when(precision.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaRoundingTrunc formula = spy(FormulaRoundingTrunc.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_TRUNC)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

    assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaRoundingTrunc#getReturnType()} when parameter is numeric and precision is
   * numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeNumericNumeric() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula precision = mock(AbstractFormula.class);
    when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaRoundingTrunc formula = spy(FormulaRoundingTrunc.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_TRUNC)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaRoundingTrunc#asText()}
   */
  @Test
  public void testAsText() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
    when(parameter.asText()).thenReturn("some_rule");

    AbstractFormula precision = mock(AbstractFormula.class);
    when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
    when(precision.asText()).thenReturn("2");

    FormulaRoundingTrunc formula = spy(FormulaRoundingTrunc.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_TRUNC)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

    assertEquals("trunc(some_rule; 2)", formula.asText());
  }
}
