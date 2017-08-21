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
   * Tests {@link FormulaModulo#getDiscriminatorValue()}
   */
  @Test
  @Ignore
  public void testGetDiscriminatorValue() {
    /*TODO -> Unit Test ?
    FormulaModulo formula = new FormulaModulo();

    assertEquals(Integer.valueOf(FormulaType.Values.OP_MODULO), formula.getDiscriminatorValue());
    */
  }

  /**
   * Tests {@link FormulaModulo#isTerminal()}
   */
  @Test
  @Ignore
  public void testIsTerminal() {
    /*TODO -> Unit Test ?
    FormulaModulo formula = new FormulaModulo();

    assertFalse(formula.isTerminal());
    */
  }

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
   * Tests {@link FormulaModulo#operationAsText()}
   */
  @Test
  @Ignore
  public void testOperationAsText() {
    /*TODO -> Unit Test ?
    FormulaModulo formula = new FormulaModulo();

    assertEquals("\\", formula.operationAsText());
    */
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
