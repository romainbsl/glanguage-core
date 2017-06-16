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

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Test class for {@link FormulaTerminalDate}
 *
 * @author DUPIREFR
 */
public class FormulaTerminalDateTest extends BaseDatabaseTest {

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
     * Tests {@link FormulaTerminalDate#isValid(Evaluator)}
     * when {@link FormulaTerminalDate#getConstantValue()} is not null and well formatted
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValid() throws GLanguageException {
        FormulaTerminalDate formula = spy(FormulaTerminalDate.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DATE)).when(formula).getDescription();
        doReturn("01/01/2015").when(formula).getConstantValue();

        assertTrue(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaTerminalDate#isValid(Evaluator)}
     * when {@link FormulaTerminalDate#getConstantValue()} is not null but not well formatted
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidNotWellFormatted() throws GLanguageException {
        FormulaTerminalDate formula = spy(FormulaTerminalDate.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DATE)).when(formula).getDescription();
        doReturn("2015/01/01").when(formula).getConstantValue();

        assertFalse(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaTerminalDate#isValid(Evaluator)}
     * when {@link FormulaTerminalDate#getConstantValue()} is null
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidNull() throws GLanguageException {
        FormulaTerminalDate formula = spy(FormulaTerminalDate.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DATE)).when(formula).getDescription();
        doReturn(null).when(formula).getConstantValue();

        assertFalse(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaTerminalDate#getReturnType()} when no parameters
     */
    @Test
    @Category({DatabaseTestCategory.class})
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

    /**
     * Tests {@link FormulaTerminalDate#asText()}
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testAsText() throws GLanguageException {
        FormulaTerminalDate formula = spy(FormulaTerminalDate.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DATE)).when(formula).getDescription();
        doReturn("01/01/2015").when(formula).getConstantValue();

        assertEquals("01/01/2015", formula.asText());
    }

}
