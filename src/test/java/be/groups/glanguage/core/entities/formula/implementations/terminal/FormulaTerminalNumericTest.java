package be.groups.glanguage.core.entities.formula.implementations.terminal;

import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

/**
 * Test class for {@link FormulaTerminalNumeric}
 *
 * @author DUPIREFR
 */
public class FormulaTerminalNumericTest {

	/*
	 * Tests
	 */

    /**
     * Tests {@link FormulaTerminalNumeric#getDiscriminatorValue()}
     */
    @Test
    public void testGetDiscriminatorValue() {
        FormulaTerminalNumeric formula = new FormulaTerminalNumeric();

        assertEquals(Integer.valueOf(FormulaType.Values.TERMINAL_NUMERIC), formula.getDiscriminatorValue());
    }

    /**
     * Tests {@link FormulaTerminalNumeric#isTerminal()}
     */
    @Test
    public void testIsTerminal() {
        FormulaTerminalNumeric formula = new FormulaTerminalNumeric();
        assertTrue(formula.isTerminal());
    }

    /**
     * Tests {@link FormulaTerminalNumeric#getReturnType()} when no parameters
     */
    @Test
    public void testGetReturnType() throws GLanguageException {
        FormulaTerminalNumeric formula = Mockito.spy(FormulaTerminalNumeric.class);

        assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
    }

    /**
     * Tests {@link FormulaTerminalNumeric#getIntegerValue()}
     */
    @Test
    public void testGetIntegerValue() throws GLanguageException {
        FormulaTerminalNumeric formula = Mockito.spy(FormulaTerminalNumeric.class);
        doReturn("1.5").when(formula).getConstantValue();

        assertEquals(Integer.valueOf(1), formula.getIntegerValue());
    }

    /**
     * Tests {@link FormulaTerminalNumeric#getNumericValue()}
     */
    @Test
    public void testGetNumericValue() throws GLanguageException {
        FormulaTerminalNumeric formula = Mockito.spy(FormulaTerminalNumeric.class);
        doReturn("1.5").when(formula).getConstantValue();

        assertEquals(Double.valueOf(1.5), formula.getNumericValue());
    }

    /**
     * Tests {@link FormulaTerminalNumeric#getStringValue()}
     */
    @Test
    public void testGetStringValue() throws GLanguageException {
        FormulaTerminalNumeric formula = Mockito.spy(FormulaTerminalNumeric.class);
        doReturn("1.5").when(formula).getConstantValue();

        assertEquals("1.5", formula.getStringValue());
    }

    /**
     * Tests {@link FormulaTerminalNumeric#getBooleanValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetBooleanValue() throws GLanguageException {
        FormulaTerminalNumeric formula = Mockito.spy(FormulaTerminalNumeric.class);
        formula.getBooleanValue();
    }

    /**
     * Tests {@link FormulaTerminalNumeric#getDateValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetDateValue() throws GLanguageException {
        FormulaTerminalNumeric formula = Mockito.spy(FormulaTerminalNumeric.class);
        formula.getDateValue();
    }

    /**
     * Tests {@link FormulaTerminalNumeric#getDurationValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetDurationValue() throws GLanguageException {
        FormulaTerminalNumeric formula = Mockito.spy(FormulaTerminalNumeric.class);
        formula.getDurationValue();
    }

}
