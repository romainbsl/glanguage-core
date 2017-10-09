package be.groups.glanguage.core.business.factory.formula.implementations.unary;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.unary.FormulaNot;
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
 * Test class for {@link FormulaNot}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaNotIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;
  /*
   * Tests
	 */

  /**
   * Tests {@link FormulaNot#isValid(Evaluator)} when operand is boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBoolean() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    FormulaNot formula = spy(FormulaNot.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_NOT)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaNot#isValid(Evaluator)} when operand is not boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotBoolean() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaNot formula = spy(FormulaNot.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_NOT)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaNot#getReturnType()} when operand is boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBoolean() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    FormulaNot formula = spy(FormulaNot.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_NOT)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaNot#getReturnType()} when operand is not boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeNotBoolean() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaNot formula = spy(FormulaNot.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_NOT)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaNot#asText()}
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testAsText() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
    when(operand.asText()).thenReturn("some_rule");

    FormulaNot formula = spy(FormulaNot.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_NOT)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertEquals("not some_rule", formula.asText());
  }
}
