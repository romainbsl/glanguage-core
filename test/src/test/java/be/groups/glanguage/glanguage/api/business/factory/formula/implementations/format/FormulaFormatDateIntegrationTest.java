package be.groups.glanguage.glanguage.api.business.factory.formula.implementations.format;

import be.groups.glanguage.glanguage.api.business.factory.*;
import be.groups.glanguage.glanguage.api.dao.*;
import be.groups.glanguage.glanguage.api.entities.evaluation.*;
import be.groups.glanguage.glanguage.api.entities.formula.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.format.*;
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
 * Test class for {@link FormulaFormatDate}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaFormatDateIntegrationTest extends DaoIntegrationTest {

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
