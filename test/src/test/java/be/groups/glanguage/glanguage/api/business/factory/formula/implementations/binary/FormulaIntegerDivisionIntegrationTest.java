package be.groups.glanguage.glanguage.api.business.factory.formula.implementations.binary;

import be.groups.glanguage.glanguage.api.*;
import be.groups.glanguage.glanguage.api.business.factory.*;
import be.groups.glanguage.glanguage.api.entities.evaluation.*;
import be.groups.glanguage.glanguage.api.entities.formula.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.binary.*;
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
 * Test class for {@link FormulaIntegerDivision}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaIntegerDivisionIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

	/*
   * Tests
	 */

  /**
   * Tests {@link FormulaIntegerDivision#isValid(Evaluator)} when operands are integers
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidMatching() throws GLanguageException {
    AbstractFormula numerator = mock(AbstractFormula.class);
    when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula denominator = mock(AbstractFormula.class);
    when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaIntegerDivision formula = spy(FormulaIntegerDivision.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_INTEGER_DIVISION)).when(
        formula).getDescription();
    doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaIntegerDivision#isValid(Evaluator)} when operands are not integers
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotMatching() throws GLanguageException {
    AbstractFormula numerator = mock(AbstractFormula.class);
    when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula denominator = mock(AbstractFormula.class);
    when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaIntegerDivision formula = spy(FormulaIntegerDivision.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_INTEGER_DIVISION)).when(
        formula).getDescription();
    doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaIntegerDivision#getReturnType()} when operands are integers
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeMatching() throws GLanguageException {
    AbstractFormula numerator = mock(AbstractFormula.class);
    when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula denominator = mock(AbstractFormula.class);
    when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaIntegerDivision formula = spy(FormulaIntegerDivision.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_INTEGER_DIVISION)).when(
        formula).getDescription();
    doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

    assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaIntegerDivision#getReturnType()} when operands are not integers
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeNotMatching() throws GLanguageException {
    AbstractFormula numerator = mock(AbstractFormula.class);
    when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula denominator = mock(AbstractFormula.class);
    when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaIntegerDivision formula = spy(FormulaIntegerDivision.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_INTEGER_DIVISION)).when(
        formula).getDescription();
    doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaIntegerDivision#asText()}
   */
  @Test
  @Category(DatabaseTestCategory.class)
  public void testAsText() throws GLanguageException {
    AbstractFormula numerator = mock(AbstractFormula.class);
    when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(numerator.asText()).thenReturn("some_rule1");

    AbstractFormula denominator = mock(AbstractFormula.class);
    when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(denominator.asText()).thenReturn("some_rule2");

    FormulaIntegerDivision formula = spy(FormulaIntegerDivision.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_INTEGER_DIVISION)).when(
        formula).getDescription();
    doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

    assertEquals("some_rule1 / some_rule2", formula.asText());
  }
}
