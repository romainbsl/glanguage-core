package be.groups.glanguage.core.business.factory.formula.implementations.format;

import be.groups.glanguage.core.IntegrationTest;
import be.groups.glanguage.core.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.format.FormulaFormatDate;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaFormatDate}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaFormatDateIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;
  /*
     * Tests
	 */

  /**
   * Tests {@link FormulaFormatDate#isValid(Evaluator)} when parameters match
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidMatching() throws GLanguageException {
    AbstractFormula date = mock(AbstractFormula.class);
    when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

    AbstractFormula format = mock(AbstractFormula.class);
    when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaFormatDate formula = spy(FormulaFormatDate.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_DATE)).when(formula)
                                                                                 .getDescription();
    doReturn(Arrays.asList(date, format)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaFormatDate#isValid(Evaluator)} when parameters don't match
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotMatching() throws GLanguageException {
    AbstractFormula date = mock(AbstractFormula.class);
    when(date.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula format = mock(AbstractFormula.class);
    when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    FormulaFormatDate formula = spy(FormulaFormatDate.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_DATE)).when(formula)
                                                                                 .getDescription();
    doReturn(Arrays.asList(date, format)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaFormatDate#getReturnType()} when parameters match
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeMatching() throws GLanguageException {
    FormulaFormatDate formula = spy(FormulaFormatDate.class);

    assertEquals(FormulaReturnType.STRING, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaFormatDate#asText()}
   */
  @Test
  public void testAsText() throws GLanguageException {
    AbstractFormula date = mock(AbstractFormula.class);
    when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
    when(date.asText()).thenReturn("10/01/2015");

    AbstractFormula format = mock(AbstractFormula.class);
    when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    when(format.asText()).thenReturn("yyyy-MM-dd");

    FormulaFormatDate formula = spy(FormulaFormatDate.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_DATE)).when(formula)
                                                                                 .getDescription();
    doReturn(Arrays.asList(date, format)).when(formula).getParameters();

    assertEquals("formatDate(10/01/2015; yyyy-MM-dd)", formula.asText());
  }
}
