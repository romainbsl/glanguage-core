package be.groups.glanguage.glanguage.api.business.factory.formula.implementations.string;

import be.groups.glanguage.glanguage.api.*;
import be.groups.glanguage.glanguage.api.business.factory.*;
import be.groups.glanguage.glanguage.api.entities.evaluation.*;
import be.groups.glanguage.glanguage.api.entities.formula.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.string.*;
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
 * Test class for {@link FormulaStringLength}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaStringLengthIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

	/*
   * Tests
	 */

  /**
   * Tests {@link FormulaStringLength#isValid(Evaluator)} when parameter is string
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidMatching() throws GLanguageException {
    AbstractFormula string = mock(AbstractFormula.class);
    when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaStringLength formula = spy(FormulaStringLength.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_STRING_LENGTH)).when(formula)
                                                                                   .getDescription();
    doReturn(Arrays.asList(string)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaStringLength#isValid(Evaluator)} when parameter is not string
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotMatching() throws GLanguageException {
    AbstractFormula string = mock(AbstractFormula.class);
    when(string.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaStringLength formula = spy(FormulaStringLength.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_STRING_LENGTH)).when(formula)
                                                                                   .getDescription();
    doReturn(Arrays.asList(string)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaStringLength#getReturnType()} when parameter is string
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeMatching() throws GLanguageException {
    AbstractFormula string = mock(AbstractFormula.class);
    when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaStringLength formula = spy(FormulaStringLength.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_STRING_LENGTH)).when(formula)
                                                                                   .getDescription();
    doReturn(Arrays.asList(string)).when(formula).getParameters();

    assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaStringLength#getReturnType()} when parameter is not string
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeNotMatching() throws GLanguageException {
    AbstractFormula string = mock(AbstractFormula.class);
    when(string.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaStringLength formula = spy(FormulaStringLength.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_STRING_LENGTH)).when(formula)
                                                                                   .getDescription();
    doReturn(Arrays.asList(string)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaStringLength#asText()}
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testAsText() throws GLanguageException {
    AbstractFormula string = mock(AbstractFormula.class);
    when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    when(string.asText()).thenReturn("some_rule");

    FormulaStringLength formula = spy(FormulaStringLength.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_STRING_LENGTH)).when(formula)
                                                                                   .getDescription();
    doReturn(Arrays.asList(string)).when(formula).getParameters();

    assertEquals("stringLength(some_rule)", formula.asText());
  }
}
