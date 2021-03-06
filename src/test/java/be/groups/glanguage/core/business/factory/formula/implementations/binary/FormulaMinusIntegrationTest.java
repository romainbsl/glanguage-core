package be.groups.glanguage.core.business.factory.formula.implementations.binary;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.binary.FormulaMinus;
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
 * Test class for {@link FormulaMinus}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaMinusIntegrationTest extends IntegrationTest {

  /*
   * Constants
   */
  private static final double DELTA = 1e-15;
  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

	/*
   * Tests
	 */

  /**
   * Tests {@link FormulaMinus#isValid(Evaluator)} when both operands are integers
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothIntegers() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMinus#isValid(Evaluator)} when first operand is integer and second numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstIntSecondNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMinus#isValid(Evaluator)} when first operand is integer and second is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstIntSecondNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMinus#isValid(Evaluator)} when second operand is integer and first is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidSecondIntFirstNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMinus#isValid(Evaluator)} when first operand is numeric and second integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstNumSecondInt() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMinus#isValid(Evaluator)} when first operand is numeric and second is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstNumSecondNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMinus#isValid(Evaluator)} when second operand is numeric and first is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidSecondNumFirstNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMinus#isValid(Evaluator)} when both operands are numerics
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothNumerics() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMinus#isValid(Evaluator)} when first operand is a date and second is a
   * duration
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstDateSecondDuration() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMinus#isValid(Evaluator)} when both operands are durations
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothDurations() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMinus#isValid(Evaluator)} when first operand is a duration and second is a
   * date
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstDurationSecondDate() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMinus#isValid(Evaluator)} when both operands are dates
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothDate() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMinus#getReturnType()} when both operands are integers
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothIntegers() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaMinus#getReturnType()} when first operand is integer and second numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeFirstIntSecondNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaMinus#getReturnType()} when first operand is integer and second is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeFirstIntSecondNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaMinus#getReturnType()} when second operand is integer and first is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeSecondIntFirstNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaMinus#getReturnType()} when first operand is numeric and second integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeFirstNumSecondInt() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaMinus#getReturnType()} when first operand is numeric and second is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeFirstNumSecondNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaMinus#getReturnType()} when second operand is numeric and first is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeSecondNumFirstNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaMinus#getReturnType()} when both operands are numerics
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothNumerics() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaMinus#getReturnType()} when both operands are durations
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothDurations() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.DURATION, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaMinus#getReturnType()} when first operand is a date and second is a
   * duration
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeFirstDateSecondDuration() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.DATE, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaMinus#getReturnType()} when first operand is a duration and second is a
   * date
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeFirstDurationSecondDate() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaMinus#asText()}
   */
  @Test
  @Category(DatabaseTestCategory.class)
  public void testAsText() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(operand1.asText()).thenReturn("some_rule1");

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(operand2.asText()).thenReturn("some_rule2");

    FormulaMinus formula = spy(FormulaMinus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals("some_rule1 - some_rule2", formula.asText());
  }
}
