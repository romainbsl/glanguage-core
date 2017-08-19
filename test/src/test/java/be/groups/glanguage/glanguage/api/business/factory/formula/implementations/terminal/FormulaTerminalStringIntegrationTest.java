package be.groups.glanguage.glanguage.api.business.factory.formula.implementations.terminal;

import be.groups.glanguage.glanguage.api.business.factory.*;
import be.groups.glanguage.glanguage.api.dao.*;
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
 * Test class for {@link FormulaTerminalString}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaTerminalStringIntegrationTest extends DaoIntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;
  /*
   * Tests
	 */

  /**
   * Tests {@link FormulaTerminalString#isValid(Evaluator)} when {@link
   * FormulaTerminalString#getConstantValue()} is not null and well formatted
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValid() throws GLanguageException {
    FormulaTerminalString formula = spy(FormulaTerminalString.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_STRING)).when(formula)
                                                                                   .getDescription();
    doReturn("string").when(formula).getConstantValue();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalString#isValid(Evaluator)} when {@link
   * FormulaTerminalString#getConstantValue()} is null
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNull() throws GLanguageException {
    FormulaTerminalString formula = spy(FormulaTerminalString.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_STRING)).when(formula)
                                                                                   .getDescription();
    doReturn(null).when(formula).getConstantValue();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalString#asText()}
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testAsText() throws GLanguageException {
    FormulaTerminalString formula = spy(FormulaTerminalString.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_STRING)).when(formula)
                                                                                   .getDescription();
    doReturn("string").when(formula).getConstantValue();

    assertEquals("\"string\"", formula.asText());
  }
}
