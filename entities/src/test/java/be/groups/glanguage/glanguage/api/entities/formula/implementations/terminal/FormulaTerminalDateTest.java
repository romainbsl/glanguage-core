package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Test class for {@link FormulaTerminalDate}
 *
 * @author DUPIREFR
 */
public class FormulaTerminalDateTest {

	/*
	 * Tests
	 */

    /**
     * Tests {@link FormulaTerminalDate#getDiscriminatorValue()}
     */
    @Test
    public void testGetDiscriminatorValue() {
        FormulaTerminalDate formula = new FormulaTerminalDate();

        assertEquals(Integer.valueOf(FormulaType.Values.TERMINAL_DATE), formula.getDiscriminatorValue());
    }

    /**
     * Tests {@link FormulaTerminalDate#isTerminal()}
     */
    @Test
    public void testIsTerminal() {
        FormulaTerminalDate formula = new FormulaTerminalDate();
        assertTrue(formula.isTerminal());
    }

    /**
     * Tests {@link FormulaTerminalDate#getReturnType()} when no parameters
     */
    @Test
    public void testGetReturnType() throws GLanguageException {
        FormulaTerminalDate formula = spy(FormulaTerminalDate.class);

        assertEquals(FormulaReturnType.DATE, formula.getReturnType());
    }

    /**
     * Tests {@link FormulaTerminalDate#getIntegerValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetIntegerValue() throws GLanguageException {
        FormulaTerminalDate formula = spy(FormulaTerminalDate.class);
        formula.getIntegerValue();
    }

    /**
     * Tests {@link FormulaTerminalDate#getNumericValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetNumericValue() throws GLanguageException {
        FormulaTerminalDate formula = spy(FormulaTerminalDate.class);
        formula.getNumericValue();
    }

    /**
     * Tests {@link FormulaTerminalDate#getStringValue()}
     */
    @Test
    public void testGetStringValue() throws GLanguageException {
        FormulaTerminalDate formula = spy(FormulaTerminalDate.class);
        doReturn("01/01/2015").when(formula).getConstantValue();

        assertEquals("01/01/2015", formula.getStringValue());
    }

    /**
     * Tests {@link FormulaTerminalDate#getBooleanValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetBooleanValue() throws GLanguageException {
        FormulaTerminalDate formula = spy(FormulaTerminalDate.class);
        formula.getBooleanValue();
    }

    /**
     * Tests {@link FormulaTerminalDate#getDateValue()}
     */
    @Test
    public void testGetDateValue() throws GLanguageException {
        FormulaTerminalDate formula = spy(FormulaTerminalDate.class);
        doReturn("01/01/2015").when(formula).getConstantValue();

        assertEquals(LocalDate.of(2015, 1, 1), formula.getDateValue());
    }

    /**
     * Tests {@link FormulaTerminalDate#getDurationValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetDurationValue() throws GLanguageException {
        FormulaTerminalDate formula = spy(FormulaTerminalDate.class);
        formula.getDurationValue();
    }

}
