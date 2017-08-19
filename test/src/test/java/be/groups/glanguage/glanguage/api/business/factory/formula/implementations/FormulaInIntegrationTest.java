package be.groups.glanguage.glanguage.api.business.factory.formula.implementations;

import be.groups.glanguage.glanguage.api.business.factory.*;
import be.groups.glanguage.glanguage.api.dao.*;
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
 * Test class for {@link FormulaIn}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaInIntegrationTest extends DaoIntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;
  /*
   * Tests
	 */

  @Test
  @Category({DatabaseTestCategory.class})
  public void testValidateInteger() {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    try {
      formula.validate(Arrays.asList(parameter, element1, element2, element3), null);
    } catch (Exception e) {
      fail();
    }
  }

  @Test(expected = GLanguageException.class)
  @Category({DatabaseTestCategory.class})
  public void testValidateIntegerNotMatchingType() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    formula.validate(Arrays.asList(parameter, element1), null);
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testValidateIntegerNumeric() {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    try {
      formula.validate(Arrays.asList(parameter, element1, element2, element3), null);
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testValidateIntegerNumericInteger() {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    try {
      formula.validate(Arrays.asList(parameter, element1, element2, element3), null);
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testValidateNumeric() {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    try {
      formula.validate(Arrays.asList(parameter, element1, element2, element3), null);
    } catch (Exception e) {
      fail();
    }
  }

  @Test(expected = GLanguageException.class)
  @Category({DatabaseTestCategory.class})
  public void testValidateNumericNotMatchingType() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    formula.validate(Arrays.asList(parameter, element1), null);
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testValidateNumericInteger() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    try {
      formula.validate(Arrays.asList(parameter, element1, element2, element3), null);
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testValidateNumericIntegerNumeric() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
    when(parameter.getIntegerValue(null)).thenReturn(1);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(element1.getIntegerValue(null)).thenReturn(1);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
    when(element2.getIntegerValue(null)).thenReturn(2);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(element3.getIntegerValue(null)).thenReturn(3);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    try {
      formula.validate(Arrays.asList(parameter, element1, element2, element3), null);
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testValidateString() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    try {
      formula.validate(Arrays.asList(parameter, element1, element2, element3), null);
    } catch (Exception e) {
      fail();
    }
  }

  @Test(expected = GLanguageException.class)
  @Category({DatabaseTestCategory.class})
  public void testValidateStringNotMatchingType() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    formula.validate(Arrays.asList(parameter, element1), null);
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testValidateBoolean() {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    try {
      formula.validate(Arrays.asList(parameter, element1, element2, element3), null);
    } catch (Exception e) {
      fail();
    }
  }

  @Test(expected = GLanguageException.class)
  @Category({DatabaseTestCategory.class})
  public void testValidateBooleanNotMatchingType() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    formula.validate(Arrays.asList(parameter, element1), null);
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testValidateDate() {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    try {
      formula.validate(Arrays.asList(parameter, element1, element2, element3), null);
    } catch (Exception e) {
      fail();
    }
  }

  @Test(expected = GLanguageException.class)
  @Category({DatabaseTestCategory.class})
  public void testValidateDateNotMatchingType() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    formula.validate(Arrays.asList(parameter, element1), null);
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testValidateDuration() {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    try {
      formula.validate(Arrays.asList(parameter, element1, element2, element3), null);
    } catch (Exception e) {
      fail();
    }
  }

  @Test(expected = GLanguageException.class)
  @Category({DatabaseTestCategory.class})
  public void testValidateDurationNotMatchingType() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    formula.validate(Arrays.asList(parameter, element1), null);
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidInteger() {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    assertTrue(formula.isValid(null));
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidIntegerNotMatchingType() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    assertFalse(formula.isValid(null));
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidIntegerNumeric() {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    assertTrue(formula.isValid(null));
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidIntegerNumericInteger() {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    assertTrue(formula.isValid(null));
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNumeric() {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    assertTrue(formula.isValid(null));
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNumericNotMatchingType() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    assertFalse(formula.isValid(null));
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNumericInteger() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    assertTrue(formula.isValid(null));
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNumericIntegerNumeric() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
    when(parameter.getIntegerValue(null)).thenReturn(1);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(element1.getIntegerValue(null)).thenReturn(1);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
    when(element2.getIntegerValue(null)).thenReturn(2);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(element3.getIntegerValue(null)).thenReturn(3);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    assertTrue(formula.isValid(null));
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidString() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    assertTrue(formula.isValid(null));
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidStringNotMatchingType() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    assertFalse(formula.isValid(null));
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBoolean() {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    assertTrue(formula.isValid(null));
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidBooleanNotMatchingType() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    assertFalse(formula.isValid(null));
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidDate() {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    assertTrue(formula.isValid(null));
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidDateNotMatchingType() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    assertFalse(formula.isValid(null));
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidDuration() {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    assertTrue(formula.isValid(null));
  }

  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidDurationNotMatchingType() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaIn#asText()}
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testAsText() throws GLanguageException {
    AbstractFormula parameter = mock(AbstractFormula.class);
    when(parameter.asText()).thenReturn("1");

    AbstractFormula element1 = mock(AbstractFormula.class);
    when(element1.asText()).thenReturn("1");

    AbstractFormula element2 = mock(AbstractFormula.class);
    when(element2.asText()).thenReturn("2");

    AbstractFormula element3 = mock(AbstractFormula.class);
    when(element3.asText()).thenReturn("3");

    FormulaIn formula = spy(FormulaIn.class);
    doReturn(Arrays.asList(parameter, element1, element2, element3)).when(formula).getParameters();
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_IN)).when(formula)
                                                                        .getDescription();

    assertEquals("1 in (1; 2; 3)", formula.asText());
  }
}
