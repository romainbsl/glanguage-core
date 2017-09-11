package be.groups.glanguage.glanguage.api.business.factory.formula.implementations.terminal;

import be.groups.glanguage.glanguage.api.*;
import be.groups.glanguage.glanguage.api.business.factory.*;
import be.groups.glanguage.glanguage.api.entities.evaluation.*;
import be.groups.glanguage.glanguage.api.entities.formula.description.*;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.*;
import be.groups.glanguage.glanguage.api.error.exception.*;
import be.groups.glanguage.glanguage.api.test.categories.*;
import org.junit.*;
import org.junit.experimental.categories.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaTerminalDuration}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaTerminalDurationIntegrationTest extends IntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

	/*
   * Tests
	 */
  /**
   * Tests {@link FormulaTerminalDuration#getDiscriminatorValue()}
   */

  /**
   * Tests {@link FormulaTerminalDuration#isValid(Evaluator)} when {@link
   * FormulaTerminalDuration#getConstantValue()} is not null and well formatted
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValid() throws GLanguageException {
    FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DURATION)).when(formula)
                                                                                     .getDescription();
    doReturn("'P1Y2M3DT4H5M6.7S'").when(formula).getConstantValue();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalDuration#isValid(Evaluator)} when {@link
   * FormulaTerminalDuration#getConstantValue()} is not null but not well formatted
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotWellFormatted() throws GLanguageException {
    FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DURATION)).when(formula)
                                                                                     .getDescription();
    doReturn("1Y2M3DT4H5M6.7S").when(formula).getConstantValue();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalDuration#isValid(Evaluator)} when {@link
   * FormulaTerminalDuration#getConstantValue()} is null
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNull() throws GLanguageException {
    FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DURATION)).when(formula)
                                                                                     .getDescription();
    doReturn(null).when(formula).getConstantValue();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalDuration#asText()}
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testAsText() throws GLanguageException {
    FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DURATION)).when(formula)
                                                                                     .getDescription();
    doReturn("'P1Y2M3DT4H5M6.7S'").when(formula).getConstantValue();

    assertEquals("'P1Y2M3DT4H5M6.7S'", formula.asText());
  }
}
