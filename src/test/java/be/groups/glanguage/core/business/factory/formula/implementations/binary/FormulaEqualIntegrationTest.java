package be.groups.glanguage.core.business.factory.formula.implementations.binary;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.binary.FormulaEqual;
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
 * Test class for {@link FormulaEqual}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaEqualIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;
  /*
   * Tests
	 */


  /**
   * Tests {@link FormulaEqual#isValid(Evaluator)} when both operands are integers
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothIntegers() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaEqual#isValid(Evaluator)} when first operand is integer and second numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstIntSecondNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaEqual#isValid(Evaluator)} when first operand is integer and second is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstIntSecondNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaEqual#isValid(Evaluator)} when second operand is integer and first is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidSecondIntFirstNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaEqual#isValid(Evaluator)} when first operand is numeric and second integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstNumSecondInt() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaEqual#isValid(Evaluator)} when first operand is numeric and second is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstNumSecondNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaEqual#isValid(Evaluator)} when second operand is numeric and first is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidSecondNumFirstNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaEqual#isValid(Evaluator)} when both operands are numerics
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothNumerics() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaEqual#isValid(Evaluator)} when both operands are strings
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothStrings() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaEqual#isValid(Evaluator)} when both operands are booleans
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothBooleans() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaEqual#isValid(Evaluator)} when both operands are dates
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothDates() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaEqual#isValid(Evaluator)} when first operand is a date and second is a
   * duration
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstDateSecondDuration() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaEqual#isValid(Evaluator)} when first operand is a duration and second is a
   * date
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstDurationSecondDate() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaEqual#isValid(Evaluator)} when both operands are durations
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothDurations() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaEqual#getReturnType()} when both operands are integers
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothIntegers() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaEqual#getReturnType()} when first operand is integer and second numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeFirstIntSecondNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaEqual#getReturnType()} when first operand is numeric and second integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeFirstNumSecondInt() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaEqual#getReturnType()} when both operands are numerics
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothNumerics() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaEqual#getReturnType()} when both operands are strings
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothStrings() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaEqual#getReturnType()} when both operands are booleans
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothBooleans() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaEqual#getReturnType()} when both operands are dates
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothDates() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaEqual#getReturnType()} when both operands are durations
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothDurations() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaEqual#asText()}
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

    FormulaEqual formula = spy(FormulaEqual.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals("some_rule1 = some_rule2", formula.asText());
  }
}
