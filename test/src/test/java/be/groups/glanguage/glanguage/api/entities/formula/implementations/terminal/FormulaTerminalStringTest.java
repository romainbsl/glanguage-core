package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
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
public class FormulaTerminalStringTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */

    /**
     * Tests {@link FormulaTerminalString#getDiscriminatorValue()}
     */
    @Test
    public void testGetDiscriminatorValue() {
        FormulaTerminalString formula = new FormulaTerminalString();

        assertEquals(Integer.valueOf(FormulaType.Values.TERMINAL_STRING), formula.getDiscriminatorValue());
    }

    /**
     * Tests {@link FormulaTerminalString#isTerminal()}
     */
    @Test
    public void testIsTerminal() {
        FormulaTerminalString formula = new FormulaTerminalString();
        assertTrue(formula.isTerminal());
    }

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
     * Tests {@link FormulaTerminalString#getReturnType()} when no parameters
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testGetReturnType() throws GLanguageException {
        FormulaTerminalString formula = spy(FormulaTerminalString.class);

        assertEquals(FormulaReturnType.STRING, formula.getReturnType());
    }

    /**
     * Tests {@link FormulaTerminalString#getIntegerValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetIntegerValue() throws GLanguageException {
        FormulaTerminalString formula = spy(FormulaTerminalString.class);
        formula.getIntegerValue();
    }

    /**
     * Tests {@link FormulaTerminalString#getNumericValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetNumericValue() throws GLanguageException {
        FormulaTerminalString formula = spy(FormulaTerminalString.class);
        formula.getNumericValue();
    }

    /**
     * Tests {@link FormulaTerminalString#getStringValue()}
     */
    @Test
    public void testGetStringValue() throws GLanguageException {
        FormulaTerminalString formula = spy(FormulaTerminalString.class);
        doReturn("string").when(formula).getConstantValue();

        assertEquals("string", formula.getStringValue());
    }

    /**
     * Tests {@link FormulaTerminalString#getBooleanValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetBooleanValue() throws GLanguageException {
        FormulaTerminalString formula = spy(FormulaTerminalString.class);
        formula.getBooleanValue();
    }

    /**
     * Tests {@link FormulaTerminalString#getDateValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetDateValue() throws GLanguageException {
        FormulaTerminalString formula = spy(FormulaTerminalString.class);
        formula.getDateValue();
    }

    /**
     * Tests {@link FormulaTerminalString#getDurationValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetDurationValue() throws GLanguageException {
        FormulaTerminalString formula = spy(FormulaTerminalString.class);
        formula.getDurationValue();
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
