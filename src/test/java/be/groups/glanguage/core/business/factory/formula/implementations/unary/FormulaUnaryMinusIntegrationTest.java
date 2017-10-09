package be.groups.glanguage.core.business.factory.formula.implementations.unary;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.unary.FormulaUnaryMinus;
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
 * Test class for {@link FormulaUnaryMinus}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaUnaryMinusIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

	/*
   * Tests
	 */

  /**
   * Tests {@link FormulaUnaryMinus#isValid(Evaluator)} when operand is integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidInteger() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS)).when(formula)
                                                                                  .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaUnaryMinus#isValid(Evaluator)} when operand is numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNumeric() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS)).when(formula)
                                                                                  .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaUnaryMinus#isValid(Evaluator)} when operand is not integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotIntegerOrNumeric() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS)).when(formula)
                                                                                  .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaUnaryMinus#getReturnType()} when operand is integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeInteger() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS)).when(formula)
                                                                                  .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaUnaryMinus#getReturnType()} when operand is numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeNumeric() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS)).when(formula)
                                                                                  .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaUnaryMinus#getReturnType()} when operand is not integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeNotIntegerOrNumeric() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS)).when(formula)
                                                                                  .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaUnaryMinus#asText()}
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testAsText() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(operand.asText()).thenReturn("some_rule");

    FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS)).when(formula)
                                                                                  .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertEquals("- some_rule", formula.asText());
  }
}
