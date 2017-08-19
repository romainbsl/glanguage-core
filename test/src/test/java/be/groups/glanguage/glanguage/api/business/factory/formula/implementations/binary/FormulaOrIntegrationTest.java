package be.groups.glanguage.glanguage.api.business.factory.formula.implementations.binary;

import be.groups.glanguage.glanguage.api.business.factory.*;
import be.groups.glanguage.glanguage.api.dao.*;
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
 * Test class for {@link FormulaOr}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaOrIntegrationTest extends DaoIntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

	/*
   * Tests
	 */

  /**
   * Tests {@link FormulaOr#getDiscriminatorValue()}
   */
  @Test
  @Ignore
  public void testGetDiscriminatorValue() {
    /*TODO -> Unit Test ?
    FormulaOr formula = new FormulaOr();

    assertEquals(Integer.valueOf(FormulaType.Values.OP_OR), formula.getDiscriminatorValue());
    */
  }

  /**
   * Tests {@link FormulaOr#isTerminal()}
   */
  @Test
  @Ignore
  public void testIsTerminal() {
    /*TODO -> Unit Test ?
    FormulaOr formula = new FormulaOr();

    assertFalse(formula.isTerminal());
    */
  }

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
   * Tests {@link FormulaOr#operationAsText()}
   */
  @Test
  @Ignore
  public void testOperationAsText() {
    /*TODO -> Unit Test ?
    FormulaOr formula = new FormulaOr();

    assertEquals("or", formula.operationAsText());
    */
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
