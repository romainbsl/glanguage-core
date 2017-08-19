package be.groups.glanguage.glanguage.api.business.factory.formula.implementations.unary;

import be.groups.glanguage.glanguage.api.business.factory.*;
import be.groups.glanguage.glanguage.api.dao.*;
import be.groups.glanguage.glanguage.api.entities.evaluation.*;
import be.groups.glanguage.glanguage.api.entities.formula.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.unary.*;
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
 * Test class for {@link FormulaNot}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaNotIntegrationTest extends DaoIntegrationTest {

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
   * Tests {@link FormulaNot#operationAsText()}
   */
  @Test
  @Ignore
  public void testOperationAsText() {
    /* TODO -> Unit Test ?
    FormulaNot formula = new FormulaNot();

    assertEquals("not", formula.operationAsText());
    */
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
