package be.groups.glanguage.core.business.factory.formula.implementations.math;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.math.FormulaMathAbs;
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
 * Test class for {@link FormulaMathAbs}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaMathAbsIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

	/*
   * Tests
	 */

  /**
   * Tests {@link FormulaMathAbs#isValid(Evaluator)} when operand is integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidInteger() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaMathAbs formula = spy(FormulaMathAbs.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_ABS)).when(formula)
                                                                         .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMathAbs#isValid(Evaluator)} when operand is numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNumeric() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaMathAbs formula = spy(FormulaMathAbs.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_ABS)).when(formula)
                                                                         .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMathAbs#isValid(Evaluator)} when operand is not integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotIntegerOrNumeric() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaMathAbs formula = spy(FormulaMathAbs.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_ABS)).when(formula)
                                                                         .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaMathAbs#getReturnType()} when operand is integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeInteger() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaMathAbs formula = spy(FormulaMathAbs.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_ABS)).when(formula)
                                                                         .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaMathAbs#getReturnType()} when operand is numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeNumeric() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaMathAbs formula = spy(FormulaMathAbs.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_ABS)).when(formula)
                                                                         .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaMathAbs#getReturnType()} when operand is not integer or numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeNotIntegerOrNumeric() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaMathAbs formula = spy(FormulaMathAbs.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_ABS)).when(formula)
                                                                         .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaMathAbs#asText()}
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testAsText() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(operand.asText()).thenReturn("some_rule");

    FormulaMathAbs formula = spy(FormulaMathAbs.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_ABS)).when(formula)
                                                                         .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertEquals("abs(some_rule)", formula.asText());
  }
}
