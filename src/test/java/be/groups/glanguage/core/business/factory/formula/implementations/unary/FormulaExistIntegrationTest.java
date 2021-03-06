package be.groups.glanguage.core.business.factory.formula.implementations.unary;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.unary.FormulaExist;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaExist}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaExistIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

	/*
   * Tests
	 */
  /**
   * Tests {@link FormulaExist#getDiscriminatorValue()}
   */

  /**
   * Tests {@link FormulaExist#isValid(Evaluator)} when operand is integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidInteger() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaExist formula = spy(FormulaExist.class);
    Mockito.doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EXIST)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaExist#isValid(Evaluator)} when operand is numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNumeric() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaExist formula = spy(FormulaExist.class);
    Mockito.doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EXIST)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaExist#isValid(Evaluator)} when operand is string
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidString() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaExist formula = spy(FormulaExist.class);
    Mockito.doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EXIST)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaExist#isValid(Evaluator)} when operand is boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBoolean() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    FormulaExist formula = spy(FormulaExist.class);
    Mockito.doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EXIST)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaExist#isValid(Evaluator)} when operand is date
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidDate() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaExist formula = spy(FormulaExist.class);
    Mockito.doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EXIST)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaExist#isValid(Evaluator)} when operand is duration
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidDuration() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaExist formula = spy(FormulaExist.class);
    Mockito.doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EXIST)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaExist#getReturnType()} when operand is integer
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeInteger() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaExist formula = spy(FormulaExist.class);
    Mockito.doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EXIST)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaExist#getReturnType()} when operand is numeric
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeNumeric() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaExist formula = spy(FormulaExist.class);
    Mockito.doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EXIST)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaExist#getReturnType()} when operand is string
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeString() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaExist formula = spy(FormulaExist.class);
    Mockito.doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EXIST)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaExist#getReturnType()} when operand is boolean
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeBoolean() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    FormulaExist formula = spy(FormulaExist.class);
    Mockito.doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EXIST)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaExist#getReturnType()} when operand is date
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeDate() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaExist formula = spy(FormulaExist.class);
    Mockito.doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EXIST)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaExist#getReturnType()} when operand is duration
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeDuration() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaExist formula = spy(FormulaExist.class);
    Mockito.doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EXIST)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
  }

  /**
   * Tests {@link FormulaExist#asText()}
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testAsText() throws GLanguageException {
    AbstractFormula operand = mock(AbstractFormula.class);
    when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    when(operand.asText()).thenReturn("some_rule");

    FormulaExist formula = spy(FormulaExist.class);
    Mockito.doReturn(formulaDescriptionFactory.getDescription(FormulaType.OP_EXIST)).when(formula)
                                                                            .getDescription();
    doReturn(Arrays.asList(operand)).when(formula).getParameters();

    assertEquals("? some_rule", formula.asText());
  }
}
