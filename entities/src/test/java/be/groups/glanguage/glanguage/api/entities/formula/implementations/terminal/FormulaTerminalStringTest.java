package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Test class for {@link FormulaTerminalString}
 *
 * @author DUPIREFR
 */
public class FormulaTerminalStringTest {

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
     * Tests {@link FormulaTerminalString#getReturnType()} when no parameters
     */
    @Test
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

}
