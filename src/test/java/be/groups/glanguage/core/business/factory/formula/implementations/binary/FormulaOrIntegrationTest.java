package be.groups.glanguage.core.business.factory.formula.implementations.binary;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.binary.FormulaOr;
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
 * Test class for {@link FormulaOr}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaOrIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

	/*
   * Tests
	 */


  /**
   * Tests {@link FormulaOr#isValid(Evaluator)} when both operands are boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothBoolean() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    FormulaOr formula = spy(FormulaOr.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_OR)).when(formula)
                                                                         .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaOr#isValid(Evaluator)} when first operand is not boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstNotBoolean() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    FormulaOr formula = spy(FormulaOr.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_OR)).when(formula)
                                                                         .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaOr#isValid(Evaluator)} when second operand is not boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidSecondNotBoolean() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaOr formula = spy(FormulaOr.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_OR)).when(formula)
                                                                         .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaOr#isValid(Evaluator)} when both operands are not boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothNotBoolean() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaOr formula = spy(FormulaOr.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_OR)).when(formula)
                                                                         .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaOr#getReturnType()} when both operands are boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothBoolean() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    FormulaOr formula = spy(FormulaOr.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_OR)).when(formula)
                                                                         .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaOr#getReturnType()} when first operand is not boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeFirstNotBoolean() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    FormulaOr formula = spy(FormulaOr.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_OR)).when(formula)
                                                                         .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaOr#getReturnType()} when second operand is not boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeSecondNotBoolean() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaOr formula = spy(FormulaOr.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_OR)).when(formula)
                                                                         .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaOr#getReturnType()} when both operands are not boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothNotBoolean() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaOr formula = spy(FormulaOr.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_OR)).when(formula)
                                                                         .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaOr#asText()}
   */
  @Test
  @Category(DatabaseTestCategory.class)
  public void testAsText() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
    when(operand1.asText()).thenReturn("some_rule1");

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
    when(operand2.asText()).thenReturn("some_rule2");

    FormulaOr formula = spy(FormulaOr.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_OR)).when(formula)
                                                                         .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals("some_rule1 or some_rule2", formula.asText());
  }
}
