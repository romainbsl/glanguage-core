package be.groups.glanguage.core.business.factory.formula.implementations.duration;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.duration.FormulaDurationDays;
import be.groups.glanguage.core.entities.formula.implementations.duration.FormulaDurationMinutes;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.test.categories.DatabaseTestCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaDurationDays}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaDurationDaysIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;
  /*
   * Tests
	 */

  /**
   * Tests {@link FormulaDurationDays#isValid(Evaluator)} with integer parameter
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidInteger() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaDurationDays formula = spy(FormulaDurationDays.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DAYS)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaDurationDays#isValid(Evaluator)} with date parameter
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidDate() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaDurationDays formula = spy(FormulaDurationDays.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DAYS)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaDurationDays#isValid(Evaluator)} with duration parameter
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidDuration() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaDurationDays formula = spy(FormulaDurationDays.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DAYS)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaDurationDays#isValid(Evaluator)} with parameter not integer nor date nor
   * duration
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotIntegerOrDateOrDuration() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaDurationDays formula = spy(FormulaDurationDays.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DAYS)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaDurationMinutes#getReturnType()} with integer parameter
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeInteger() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaDurationDays formula = spy(FormulaDurationDays.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DAYS)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    Assert.assertEquals(FormulaReturnType.DURATION, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaDurationDays#getReturnType()} with date parameter
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeDate() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaDurationDays formula = spy(FormulaDurationDays.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DAYS)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    Assert.assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaDurationDays#getReturnType()} with duration parameter
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeDuration() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaDurationDays formula = spy(FormulaDurationDays.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DAYS)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    Assert.assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaDurationDays#getReturnType()} with parameter not integer nor date nor
   * duration
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeNotIntegerOrDateOrDuration() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaDurationDays formula = spy(FormulaDurationDays.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DAYS)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    Assert.assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaDurationDays#asText()}
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testAsText() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(parameter.asText()).thenReturn("some_rule");

    FormulaDurationDays formula = spy(FormulaDurationDays.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DAYS)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertEquals("days(some_rule)", formula.asText());
  }
}
