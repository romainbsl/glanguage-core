package be.groups.glanguage.glanguage.api.business.factory.formula.implementations;

import be.groups.glanguage.glanguage.api.business.factory.*;
import be.groups.glanguage.glanguage.api.dao.*;
import be.groups.glanguage.glanguage.api.entities.evaluation.*;
import be.groups.glanguage.glanguage.api.entities.formula.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.*;
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
 * Test class for {@link FormulaDate}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaDateIntegrationTest extends DaoIntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;
  /*
   * Tests
	 */

  /**
   * Tests {@link FormulaDate#isValid(Evaluator)} with string parameter
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidString() throws GLanguageException {
    AbstractFormula string = mock(AbstractFormula.class);
    when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaDate formula = spy(FormulaDate.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(string)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaDate#isValid(Evaluator)} with parameter different from string
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotString() throws GLanguageException {
    AbstractFormula string = mock(AbstractFormula.class);
    when(string.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaDate formula = spy(FormulaDate.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(string)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaDate#isValid(Evaluator)} with integers parameters
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidIntegers() throws GLanguageException {
    AbstractFormula day = mock(AbstractFormula.class);
    when(day.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula month = mock(AbstractFormula.class);
    when(month.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula year = mock(AbstractFormula.class);
    when(year.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaDate formula = spy(FormulaDate.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(day, month, year)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaDate#isValid(Evaluator)} with integers parameters, but not enough
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidIntegersNotEnough() throws GLanguageException {
    AbstractFormula day = mock(AbstractFormula.class);
    when(day.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula month = mock(AbstractFormula.class);
    when(month.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaDate formula = spy(FormulaDate.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(day, month)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaDate#getReturnType()} with string parameter
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeString() throws GLanguageException {
    AbstractFormula string = mock(AbstractFormula.class);
    when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaDate formula = spy(FormulaDate.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(string)).when(formula).getParameters();

    assertEquals(FormulaReturnType.DATE, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaDate#getReturnType()} with parameter different from string
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeNotString() throws GLanguageException {
    AbstractFormula string = mock(AbstractFormula.class);
    when(string.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaDate formula = spy(FormulaDate.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(string)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaDate#getReturnType()} with integers parameters
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeIntegers() throws GLanguageException {
    AbstractFormula day = mock(AbstractFormula.class);
    when(day.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula month = mock(AbstractFormula.class);
    when(month.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula year = mock(AbstractFormula.class);
    when(year.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaDate formula = spy(FormulaDate.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(day, month, year)).when(formula).getParameters();

    assertEquals(FormulaReturnType.DATE, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaDate#getReturnType()} with integers parameters, but not enough
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeIntegersNotEnough() throws GLanguageException {
    AbstractFormula day = mock(AbstractFormula.class);
    when(day.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula month = mock(AbstractFormula.class);
    when(month.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaDate formula = spy(FormulaDate.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(day, month)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaDate#asText()} with string parameter
   */
  @Test
  @Category(DatabaseTestCategory.class)
  public void testAsTextWithStringParam() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    when(parameter.asText()).thenReturn("11/09/1992");

    FormulaDate formula = spy(FormulaDate.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(parameter)).when(formula).getParameters();

    assertEquals("date(11/09/1992)", formula.asText());
  }

  /**
   * Tests {@link FormulaDate#asText()} with integers parameters
   */
  @Test
  @Category(DatabaseTestCategory.class)
  public void testAsTextWithIntParams() throws GLanguageException {
    AbstractFormula dayParam = mock(AbstractFormula.class);
    when(dayParam.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(dayParam.asText()).thenReturn("11");

    AbstractFormula monthParam = mock(AbstractFormula.class);
    when(monthParam.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(monthParam.asText()).thenReturn("9");

    AbstractFormula yearParam = mock(AbstractFormula.class);
    when(yearParam.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(yearParam.asText()).thenReturn("1992");

    FormulaDate formula = spy(FormulaDate.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula)
                                                                          .getDescription();
    doReturn(Arrays.asList(dayParam, monthParam, yearParam)).when(formula).getParameters();

    assertEquals("date(11; 9; 1992)", formula.asText());
  }
}
