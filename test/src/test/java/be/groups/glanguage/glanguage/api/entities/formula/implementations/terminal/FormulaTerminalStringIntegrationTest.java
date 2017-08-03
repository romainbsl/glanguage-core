package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Test class for {@link FormulaTerminalString}
 *
 * @author DUPIREFR
 */
public class FormulaTerminalStringIntegrationTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */

    /**
     * Tests {@link FormulaTerminalString#isValid(Evaluator)}
     * when {@link FormulaTerminalString#getConstantValue()} is not null and well formatted
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValid() throws GLanguageException {
        FormulaTerminalString formula = spy(FormulaTerminalString.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_STRING)).when(formula).getDescription();
        doReturn("string").when(formula).getConstantValue();

        assertTrue(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaTerminalString#isValid(Evaluator)}
     * when {@link FormulaTerminalString#getConstantValue()} is null
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidNull() throws GLanguageException {
        FormulaTerminalString formula = spy(FormulaTerminalString.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_STRING)).when(formula).getDescription();
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
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_STRING)).when(formula).getDescription();
        doReturn("string").when(formula).getConstantValue();

        assertEquals("\"string\"", formula.asText());
    }

}
