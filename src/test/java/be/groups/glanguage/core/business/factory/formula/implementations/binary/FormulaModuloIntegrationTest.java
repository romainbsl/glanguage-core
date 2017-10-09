package be.groups.glanguage.core.business.factory.formula.implementations.binary;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.binary.FormulaModulo;
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
 * Test class for {@link FormulaModulo}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaModuloIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

	/*
   * Tests
	 */

  /**
   * Tests {@link FormulaModulo#isValid(Evaluator)} when operands are integers
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidMatching() throws GLanguageException {
    AbstractFormula numerator = mock(AbstractFormula.class);
    when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula denominator = mock(AbstractFormula.class);
    when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaModulo formula = spy(FormulaModulo.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MODULO)).when(formula)
                                                                             .getDescription();
    doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaModulo#isValid(Evaluator)} when operands are not integers
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotMatching() throws GLanguageException {
    AbstractFormula numerator = mock(AbstractFormula.class);
    when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula denominator = mock(AbstractFormula.class);
    when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaModulo formula = spy(FormulaModulo.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MODULO)).when(formula)
                                                                             .getDescription();
    doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaModulo#getReturnType()} when operands are integers
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeMatching() throws GLanguageException {
    AbstractFormula numerator = mock(AbstractFormula.class);
    when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula denominator = mock(AbstractFormula.class);
    when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaModulo formula = spy(FormulaModulo.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MODULO)).when(formula)
                                                                             .getDescription();
    doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

    assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaModulo#getReturnType()} when operands are not integers
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeNotMatching() throws GLanguageException {
    AbstractFormula numerator = mock(AbstractFormula.class);
    when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula denominator = mock(AbstractFormula.class);
    when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaModulo formula = spy(FormulaModulo.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MODULO)).when(formula)
                                                                             .getDescription();
    doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaModulo#asText()}
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

    FormulaModulo formula = spy(FormulaModulo.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_MODULO)).when(formula)
                                                                             .getDescription();
    doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

    assertEquals("some_rule1 \\ some_rule2", formula.asText());
  }
}
