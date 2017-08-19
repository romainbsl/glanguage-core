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
 * Test class for {@link FormulaTerminalBoolean}
 *
 * @author DUPIREFR
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaTerminalBooleanIntegrationTest extends DaoIntegrationTest {

  @Autowired
  private FormulaDescriptionFactory formulaDescriptionFactory;

	/*
   * Tests
	 */

  /**
   * Tests {@link FormulaTerminalBoolean#isValid(Evaluator)} when {@link
   * FormulaTerminalBoolean#getConstantValue()} is not null and well formatted
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValid() throws GLanguageException {
    FormulaTerminalBoolean formula = spy(FormulaTerminalBoolean.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_BOOLEAN)).when(formula)
                                                                                    .getDescription();
    doReturn("true").when(formula).getConstantValue();

    assertTrue(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalBoolean#isValid(Evaluator)} when {@link
   * FormulaTerminalBoolean#getConstantValue()} is not null but not well formatted
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNotWellFormatted() throws GLanguageException {
    FormulaTerminalBoolean formula = spy(FormulaTerminalBoolean.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_BOOLEAN)).when(formula)
                                                                                    .getDescription();
    doReturn("ture").when(formula).getConstantValue();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalBoolean#isValid(Evaluator)} when {@link
   * FormulaTerminalBoolean#getConstantValue()} is null
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testIsValidNull() throws GLanguageException {
    FormulaTerminalBoolean formula = spy(FormulaTerminalBoolean.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_BOOLEAN)).when(formula)
                                                                                    .getDescription();
    doReturn(null).when(formula).getConstantValue();

    assertFalse(formula.isValid(null));
  }

  /**
   * Tests {@link FormulaTerminalBoolean#asText()}
   */
  @Test
  @Category({DatabaseTestCategory.class})
  public void testAsText() throws GLanguageException {
    FormulaTerminalBoolean formula = spy(FormulaTerminalBoolean.class);
    doReturn(formulaDescriptionFactory.getDescription(FormulaType.TERMINAL_BOOLEAN)).when(formula)
                                                                                    .getDescription();
    doReturn("true").when(formula).getConstantValue();

    assertEquals("true", formula.asText());
  }
}
