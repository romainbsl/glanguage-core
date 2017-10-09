package be.groups.glanguage.core.business.factory.formula.implementations.string;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.string.FormulaStringLength;
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

    Assert.assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
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

    Assert.assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
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
