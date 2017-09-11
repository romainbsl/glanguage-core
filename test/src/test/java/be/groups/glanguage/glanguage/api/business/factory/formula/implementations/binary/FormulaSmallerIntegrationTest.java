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
 * Test class for {@link FormulaSmaller}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaSmallerIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

	/*
   * Tests
	 */

  /**
   * Tests {@link FormulaSmaller#getDiscriminatorValue()}
   */
  @Test
  public void testGetDiscriminatorValue() {
    /*TODO -> Unit Test ?
    FormulaSmaller formula = new FormulaSmaller();

    assertEquals(Integer.valueOf(FormulaType.Values.OP_SMALLER), formula.getDiscriminatorValue());
    */
  }

  /**
   * Tests {@link FormulaSmaller#isTerminal()}
   */
  @Test
  public void testIsTerminal() {
    /*TODO -> Unit Test ?
    FormulaSmaller formula = new FormulaSmaller();

    assertFalse(formula.isTerminal());
    */
  }

  /**
   * Tests {@link FormulaSmaller#isValid(Evaluator)} when both operands are integers
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothIntegers() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaSmaller#isValid(Evaluator)} when first operand is integer and second
   * numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstIntSecondNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaSmaller#isValid(Evaluator)} when first operand is integer and second is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstIntSecondNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaSmaller#isValid(Evaluator)} when second operand is integer and first is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidSecondIntFirstNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaSmaller#isValid(Evaluator)} when first operand is numeric and second
   * integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstNumSecondInt() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaSmaller#isValid(Evaluator)} when first operand is numeric and second is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstNumSecondNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaSmaller#isValid(Evaluator)} when second operand is numeric and first is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidSecondNumFirstNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaSmaller#isValid(Evaluator)} when both operands are numerics
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothNumerics() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaSmaller#isValid(Evaluator)} when both operands are strings
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothStrings() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaSmaller#isValid(Evaluator)} when both operands are booleans
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothBooleans() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaSmaller#isValid(Evaluator)} when both operands are dates
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothDates() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaSmaller#isValid(Evaluator)} when first operand is a date and second is a
   * duration
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstDateSecondDuration() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaSmaller#isValid(Evaluator)} when first operand is a duration and second is
   * a date
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstDurationSecondDate() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaSmaller#isValid(Evaluator)} when both operands are durations
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothDurations() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaSmaller#getReturnType()} when both operands are integers
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothIntegers() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaSmaller#getReturnType()} when first operand is integer and second numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeFirstIntSecondNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaSmaller#getReturnType()} when first operand is numeric and second integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeFirstNumSecondInt() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaSmaller#getReturnType()} when both operands are numerics
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothNumerics() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaSmaller#getReturnType()} when both operands are strings
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothStrings() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaSmaller#getReturnType()} when both operands are booleans
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothBooleans() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaSmaller#getReturnType()} when both operands are dates
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothDates() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaSmaller#getReturnType()} when both operands are durations
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothDurations() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaSmaller#getIntegerValue()}
   */
  @Test(expected = GLanguageException.class)
  public void testGetIntegerValue() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(operand1.getIntegerValue(null)).thenReturn(1);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(operand2.getIntegerValue(null)).thenReturn(0);

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    formula.getIntegerValue();
  }

  /**
   * Tests {@link FormulaSmaller#operationAsText()}
   */
  @Test
  @Ignore
  public void testOperationAsText() {
    /*TODO -> Unit Test ?
    FormulaSmaller formula = new FormulaSmaller();

    assertEquals("<", formula.operationAsText());
    */
  }

  /**
   * Tests {@link FormulaSmaller#asText()}
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

    FormulaSmaller formula = spy(FormulaSmaller.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER)).when(formula)
                                                                              .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals("some_rule1 < some_rule2", formula.asText());
  }
}
