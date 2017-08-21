package be.groups.glanguage.glanguage.api.business.factory.formula.implementations.math;

import be.groups.glanguage.glanguage.api.*;
import be.groups.glanguage.glanguage.api.business.factory.*;
import be.groups.glanguage.glanguage.api.entities.evaluation.*;
import be.groups.glanguage.glanguage.api.entities.formula.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.math.*;
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
