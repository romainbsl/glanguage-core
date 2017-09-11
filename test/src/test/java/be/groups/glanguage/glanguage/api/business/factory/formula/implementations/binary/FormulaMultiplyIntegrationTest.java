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
 * Test class for {@link FormulaMultiply}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaMultiplyIntegrationTest extends IntegrationTest {

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
   * Tests {@link FormulaMultiply#isValid(Evaluator)} when both operands are integers
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothIntegers() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaMultiply formula = spy(FormulaMultiply.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MULTIPLY)).when(formula)
                                                                               .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMultiply#isValid(Evaluator)} when first operand is integer and second
   * numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstIntSecondNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaMultiply formula = spy(FormulaMultiply.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MULTIPLY)).when(formula)
                                                                               .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMultiply#isValid(Evaluator)} when first operand is integer and second is
   * not integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstIntSecondNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaMultiply formula = spy(FormulaMultiply.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MULTIPLY)).when(formula)
                                                                               .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMultiply#isValid(Evaluator)} when second operand is integer and first is
   * not integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidSecondIntFirstNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaMultiply formula = spy(FormulaMultiply.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MULTIPLY)).when(formula)
                                                                               .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMultiply#isValid(Evaluator)} when first operand is numeric and second
   * integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstNumSecondInt() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaMultiply formula = spy(FormulaMultiply.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MULTIPLY)).when(formula)
                                                                               .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMultiply#isValid(Evaluator)} when first operand is numeric and second is
   * not integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstNumSecondNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaMultiply formula = spy(FormulaMultiply.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MULTIPLY)).when(formula)
                                                                               .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMultiply#isValid(Evaluator)} when second operand is numeric and first is
   * not integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidSecondNumFirstNotIntOrNum() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaMultiply formula = spy(FormulaMultiply.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MULTIPLY)).when(formula)
                                                                               .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMultiply#isValid(Evaluator)} when both operands are numerics
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothNumerics() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaMultiply formula = spy(FormulaMultiply.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MULTIPLY)).when(formula)
                                                                               .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMultiply#asText()}
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

    FormulaMultiply formula = spy(FormulaMultiply.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MULTIPLY)).when(formula)
                                                                               .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals("some_rule1 * some_rule2", formula.asText());
  }
}
