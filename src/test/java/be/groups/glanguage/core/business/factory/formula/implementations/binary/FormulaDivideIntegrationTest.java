package be.groups.glanguage.core.business.factory.formula.implementations.binary;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.binary.FormulaDivide;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaDivide}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaDivideIntegrationTest extends IntegrationTest {

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
   * Tests {@link FormulaDivide#isValid(Evaluator)} when operands are numerics
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidMatching() throws GLanguageException {
    AbstractFormula numerator = mock(AbstractFormula.class);
    when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula denominator = mock(AbstractFormula.class);
    when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaDivide formula = spy(FormulaDivide.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_DIVIDE)).when(formula)
                                                                             .getDescription();
    doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaDivide#isValid(Evaluator)} when operands are not numerics
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotMatching() throws GLanguageException {
    AbstractFormula numerator = mock(AbstractFormula.class);
    when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula denominator = mock(AbstractFormula.class);
    when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaDivide formula = spy(FormulaDivide.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_DIVIDE)).when(formula)
                                                                             .getDescription();
    doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaDivide#getReturnType()} when operands are numerics
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeMatching() throws GLanguageException {
    AbstractFormula numerator = mock(AbstractFormula.class);
    when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula denominator = mock(AbstractFormula.class);
    when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaDivide formula = spy(FormulaDivide.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_DIVIDE)).when(formula)
                                                                             .getDescription();
    doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

    assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaDivide#getReturnType()} when operands are not numerics
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeNotMatching() throws GLanguageException {
    AbstractFormula numerator = mock(AbstractFormula.class);
    when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula denominator = mock(AbstractFormula.class);
    when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaDivide formula = spy(FormulaDivide.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_DIVIDE)).when(formula)
                                                                             .getDescription();
    doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

    assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaDivide#asText()}
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

    FormulaDivide formula = spy(FormulaDivide.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_DIVIDE)).when(formula)
                                                                             .getDescription();
    doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

    assertEquals("some_rule1 / some_rule2", formula.asText());
  }
}
