package be.groups.glanguage.glanguage.api.business.factory.formula.implementations.duration;

import be.groups.glanguage.glanguage.api.business.factory.*;
import be.groups.glanguage.glanguage.api.dao.*;
import be.groups.glanguage.glanguage.api.entities.evaluation.*;
import be.groups.glanguage.glanguage.api.entities.formula.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.*;
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
 * Test class for {@link FormulaDurationYears}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaDurationYearsIntegrationTest extends DaoIntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

	/*
   * Tests
	 */

  /**
   * Tests {@link FormulaDurationYears#isValid(Evaluator)} with date parameter
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidDate() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaDurationYears formula = spy(FormulaDurationYears.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_YEARS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaDurationYears#isValid(Evaluator)} with duration parameter
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidDuration() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaDurationYears formula = spy(FormulaDurationYears.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_YEARS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaDurationYears#isValid(Evaluator)} with parameter not integer nor date nor
   * duration
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotIntegerOrDateOrDuration() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaDurationYears formula = spy(FormulaDurationYears.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_YEARS)).when(formula)
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

    FormulaDurationYears formula = spy(FormulaDurationYears.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_YEARS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertEquals(FormulaReturnType.DURATION, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaDurationYears#getReturnType()} with date parameter
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeDate() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaDurationYears formula = spy(FormulaDurationYears.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_YEARS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaDurationYears#getReturnType()} with duration parameter
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeDuration() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaDurationYears formula = spy(FormulaDurationYears.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_YEARS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaDurationYears#getReturnType()} with parameter not integer nor date nor
   * duration
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeNotIntegerOrDateOrDuration() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaDurationYears formula = spy(FormulaDurationYears.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_YEARS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaDurationYears#operationAsText()}
   */
  @Test
  public void testOperationAsText() {
    FormulaDurationYears formula = new FormulaDurationYears();

    assertEquals("years", formula.operationAsText());
  }

  /**
   * Tests {@link FormulaDurationYears#asText()}
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testAsText() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(parameter.asText()).thenReturn("some_rule");

    FormulaDurationYears formula = spy(FormulaDurationYears.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_YEARS)).when(formula)
                                                                           .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertEquals("years(some_rule)", formula.asText());
  }
}
