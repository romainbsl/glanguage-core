package be.groups.glanguage.glanguage.api.business.factory.formula.implementations.extremum;

import be.groups.glanguage.glanguage.api.business.factory.*;
import be.groups.glanguage.glanguage.api.dao.*;
import be.groups.glanguage.glanguage.api.entities.evaluation.*;
import be.groups.glanguage.glanguage.api.entities.formula.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum.*;
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
 * Test class for {@link FormulaExtremumSignedMin}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaExtremumSignedMinIntegrationTest extends DaoIntegrationTest {

  /*
   * Constants
   */
  private static final double DELTA = 1e-15;
  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

	/*
   * Tests
	 */

  /**
   * Tests {@link FormulaExtremumMax#isValid(Evaluator)} when parameters match (2 integers)
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidMatchingIntegers() throws GLanguageException {
    AbstractFormula param1 = mock(AbstractFormula.class);
    when(param1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula param2 = mock(AbstractFormula.class);
    when(param2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaExtremumSignedMin formula = spy(FormulaExtremumSignedMin.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_SMIN)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(param1, param2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaExtremumMax#isValid(Evaluator)} when parameters match (2 numerics)
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidMatchingNumerics() throws GLanguageException {
    AbstractFormula param1 = mock(AbstractFormula.class);
    when(param1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula param2 = mock(AbstractFormula.class);
    when(param2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaExtremumSignedMin formula = spy(FormulaExtremumSignedMin.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_SMIN)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(param1, param2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaExtremumMax#isValid(Evaluator)} when parameters match (1 integer, 1
   * numeric)
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidMatchingIntegerNumeric() throws GLanguageException {
    AbstractFormula param1 = mock(AbstractFormula.class);
    when(param1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula param2 = mock(AbstractFormula.class);
    when(param2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaExtremumSignedMin formula = spy(FormulaExtremumSignedMin.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_SMIN)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(param1, param2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaExtremumMax#isValid(Evaluator)} when parameters match (1 numeric, 1
   * integer)
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidMatchingNumericInteger() throws GLanguageException {
    AbstractFormula param1 = mock(AbstractFormula.class);
    when(param1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula param2 = mock(AbstractFormula.class);
    when(param2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaExtremumSignedMin formula = spy(FormulaExtremumSignedMin.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_SMIN)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(param1, param2)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaExtremumMax#isValid(Evaluator)} when parameters don't match
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotMatching() throws GLanguageException {
    AbstractFormula param1 = mock(AbstractFormula.class);
    when(param1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula param2 = mock(AbstractFormula.class);
    when(param2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaExtremumSignedMin formula = spy(FormulaExtremumSignedMin.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_SMIN)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(param1, param2)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaExtremumMax#isValid(Evaluator)} when parameters number don't match
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotMatchingNumber() throws GLanguageException {
    AbstractFormula param1 = mock(AbstractFormula.class);
    when(param1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaExtremumSignedMin formula = spy(FormulaExtremumSignedMin.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_SMIN)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(param1)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaExtremumSignedMin#operationAsText()}
   */
  @Test
  public void testAsText() throws GLanguageException {
    List<AbstractFormula> parameters = new ArrayList<>();

    AbstractFormula param1 = mock(AbstractFormula.class);
    when(param1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(param1.asText()).thenReturn("some_rule1");
    parameters.add(param1);

    AbstractFormula param2 = mock(AbstractFormula.class);
    when(param2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(param2.asText()).thenReturn("some_rule2");
    parameters.add(param2);

    AbstractFormula param3 = mock(AbstractFormula.class);
    when(param3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(param3.asText()).thenReturn("some_rule3");
    parameters.add(param3);

    FormulaExtremumSignedMin formula = spy(FormulaExtremumSignedMin.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_SMIN)).when(formula)
                                                                          .getDescription();
    doReturn(parameters).when(formula).getParameters();

    assertEquals("smin(some_rule1, some_rule2, some_rule3)", formula.asText());
  }
}
