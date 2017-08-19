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
 * Test class for {@link FormulaAnd}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaAndIntegrationTest extends DaoIntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;
  /*
   * Tests
	 */

  /**
   * Tests {@link FormulaAnd#isValid(Evaluator)} when both operands are boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothBoolean() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    FormulaAnd formula = spy(FormulaAnd.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_AND)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaAnd#isValid(Evaluator)} when first operand is not boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidFirstNotBoolean() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    FormulaAnd formula = spy(FormulaAnd.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_AND)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaAnd#isValid(Evaluator)} when second operand is not boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidSecondNotBoolean() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaAnd formula = spy(FormulaAnd.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_AND)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaAnd#isValid(Evaluator)} when both operands are not boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBothNotBoolean() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaAnd formula = spy(FormulaAnd.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_AND)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaAnd#getReturnType()} when both operands are boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothBoolean() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    FormulaAnd formula = spy(FormulaAnd.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_AND)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaAnd#getReturnType()} when first operand is not boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeFirstNotBoolean() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    FormulaAnd formula = spy(FormulaAnd.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_AND)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaAnd#getReturnType()} when second operand is not boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeSecondNotBoolean() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaAnd formula = spy(FormulaAnd.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_AND)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaAnd#getReturnType()} when both operands are not boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBothNotBoolean() throws GLanguageException {
    AbstractFormula operand1 = mock(AbstractFormula.class);
    when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula operand2 = mock(AbstractFormula.class);
    when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaAnd formula = spy(FormulaAnd.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_AND)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaAnd#asText()}
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

    FormulaAnd formula = spy(FormulaAnd.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_AND)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

    assertEquals("some_rule1 and some_rule2", formula.asText());
  }
}
