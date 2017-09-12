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
 * Test class for {@link FormulaPlus}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaPlusIntegrationTest extends IntegrationTest {

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
   * Tests {@link FormulaPlus#isValid(Evaluator)} when both operands are integers
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothIntegers() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaPlus#isValid(Evaluator)} when first operand is integer and second numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstIntSecondNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaPlus#isValid(Evaluator)} when first operand is integer and second is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstIntSecondNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaPlus#isValid(Evaluator)} when second operand is integer and first is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidSecondIntFirstNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaPlus#isValid(Evaluator)} when first operand is numeric and second integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstNumSecondInt() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaPlus#isValid(Evaluator)} when first operand is numeric and second is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstNumSecondNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaPlus#isValid(Evaluator)} when second operand is numeric and first is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidSecondNumFirstNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaPlus#isValid(Evaluator)} when both operands are numerics
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothNumerics() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaPlus#isValid(Evaluator)} when both operands are strings
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothStrings() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaPlus#isValid(Evaluator)} when first operand is a date and second is a
   * duration
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstDateSecondDuration() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaPlus#isValid(Evaluator)} when both operands are durations
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothDurations() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaPlus#isValid(Evaluator)} when first operand is a duration and second is a
   * date
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstDurationSecondDate() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaPlus#isValid(Evaluator)} when both operands are dates
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothDate() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaPlus#getReturnType()} when both operands are integers
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothIntegers() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaPlus#getReturnType()} when first operand is integer and second numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeFirstIntSecondNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaPlus#getReturnType()} when first operand is integer and second is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeFirstIntSecondNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaPlus#getReturnType()} when second operand is integer and first is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeSecondIntFirstNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaPlus#getReturnType()} when first operand is numeric and second integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeFirstNumSecondInt() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaPlus#getReturnType()} when first operand is numeric and second is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeFirstNumSecondNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaPlus#getReturnType()} when second operand is numeric and first is not
   * integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeSecondNumFirstNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaPlus#getReturnType()} when both operands are numerics
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothNumerics() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaPlus#getReturnType()} when both operands are strings
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothStrings() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.STRING, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaPlus#getReturnType()} when both operands are durations
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothDurations() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.DURATION, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaPlus#getReturnType()} when first operand is a date and second is a
   * duration
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeFirstDateSecondDuration() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.DATE, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaPlus#getReturnType()} when first operand is a duration and second is a
   * date
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeFirstDurationSecondDate() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.DATE, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaPlus#asText()}
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

    FormulaPlus formula = spy(FormulaPlus.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_PLUS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals("some_rule1 + some_rule2", formula.asText());
  }
}
