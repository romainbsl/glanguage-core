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
 * Test class for {@link FormulaSubString}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaSubStringIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

	/*
   * Tests
	 */

  /**
   * Tests {@link FormulaStringItem#isValid(Evaluator)} when parameters match
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidMatching() throws GLanguageException {
    AbstractFormula string = mock(AbstractFormula.class);
    when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula beginIndex = mock(AbstractFormula.class);
    when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula endIndex = mock(AbstractFormula.class);
    when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaSubString formula = spy(FormulaSubString.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_SUBSTRING)).when(formula)
                                                                               .getDescription();
    doReturn(Arrays.asList(string, beginIndex, endIndex)).when(formula).getParameters();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaStringItem#isValid(Evaluator)} when parameters don't match
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotMatching() throws GLanguageException {
    AbstractFormula string = mock(AbstractFormula.class);
    when(string.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula beginIndex = mock(AbstractFormula.class);
    when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula endIndex = mock(AbstractFormula.class);
    when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaSubString formula = spy(FormulaSubString.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_SUBSTRING)).when(formula)
                                                                               .getDescription();
    doReturn(Arrays.asList(string, beginIndex, endIndex)).when(formula).getParameters();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaStringItem#getReturnType()} when parameters match
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeMatching() throws GLanguageException {
    AbstractFormula string = mock(AbstractFormula.class);
    when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

    AbstractFormula beginIndex = mock(AbstractFormula.class);
    when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula endIndex = mock(AbstractFormula.class);
    when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaSubString formula = spy(FormulaSubString.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_SUBSTRING)).when(formula)
                                                                               .getDescription();
    doReturn(Arrays.asList(string, beginIndex, endIndex)).when(formula).getParameters();

    assertEquals(FormulaReturnType.STRING, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaStringItem#getReturnType()} when parameters don't match
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testGetReturnTypeNotMatching() throws GLanguageException {
    AbstractFormula string = mock(AbstractFormula.class);
    when(string.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula beginIndex = mock(AbstractFormula.class);
    when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    AbstractFormula endIndex = mock(AbstractFormula.class);
    when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

    FormulaSubString formula = spy(FormulaSubString.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_SUBSTRING)).when(formula)
                                                                               .getDescription();
    doReturn(Arrays.asList(string, beginIndex, endIndex)).when(formula).getParameters();

    assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
  }

  /**
   * Tests {@link FormulaSubString#asText()}
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testAsText() throws GLanguageException {
    AbstractFormula string = mock(AbstractFormula.class);
    when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
    when(string.asText()).thenReturn("some_rule");

    AbstractFormula beginIndex = mock(AbstractFormula.class);
    when(beginIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(beginIndex.asText()).thenReturn("3");

    AbstractFormula endIndex = mock(AbstractFormula.class);
    when(endIndex.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
    when(endIndex.asText()).thenReturn("7");

    FormulaSubString formula = spy(FormulaSubString.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.F_SUBSTRING)).when(formula)
                                                                               .getDescription();
    doReturn(Arrays.asList(string, beginIndex, endIndex)).when(formula).getParameters();

    assertEquals("subString(some_rule; 3; 7)", formula.asText());
  }
}
