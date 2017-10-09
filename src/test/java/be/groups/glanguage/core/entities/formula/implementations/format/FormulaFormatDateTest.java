package be.groups.glanguage.core.entities.formula.implementations.format;

import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaFormatDate}
 *
 * @author DUPIREFR
 */
public class FormulaFormatDateTest {

	/*
     * Tests
	 */

    /**
     * Tests {@link FormulaFormatDate#getDiscriminatorValue()}
     */
    @Test
    public void testGetDiscriminatorValue() {
        FormulaFormatDate formula = new FormulaFormatDate();

        assertEquals(Integer.valueOf(FormulaType.Values.F_FORMAT_DATE), formula.getDiscriminatorValue());
    }

    /**
     * Tests {@link FormulaFormatDate#isTerminal()}
     */
    @Test
    public void testIsTerminal() {
        FormulaFormatDate formula = new FormulaFormatDate();

        assertFalse(formula.isTerminal());
    }

    /**
     * Tests {@link FormulaFormatDate#getIntegerValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetIntegerValue() throws GLanguageException {
        AbstractFormula date = mock(AbstractFormula.class);
        when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
        when(date.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

        AbstractFormula format = mock(AbstractFormula.class);
        when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
        when(format.getStringValue(null)).thenReturn("yyyy-MM-dd");

        FormulaFormatDate formula = Mockito.spy(FormulaFormatDate.class);
        doReturn(Arrays.asList(date, format)).when(formula).getParameters();

        formula.getIntegerValue(null);
    }

    /**
     * Tests {@link FormulaFormatDate#getNumericValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetNumericValue() throws GLanguageException {
        AbstractFormula date = mock(AbstractFormula.class);
        when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
        when(date.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

        AbstractFormula format = mock(AbstractFormula.class);
        when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
        when(format.getStringValue(null)).thenReturn("yyyy-MM-dd");

        FormulaFormatDate formula = Mockito.spy(FormulaFormatDate.class);
        doReturn(Arrays.asList(date, format)).when(formula).getParameters();

        formula.getNumericValue(null);
    }

    /**
     * Tests {@link FormulaFormatDate#getStringValue()}
     */
    @Test
    public void testGetStringValue() throws GLanguageException {
        AbstractFormula date = mock(AbstractFormula.class);
        when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
        when(date.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

        AbstractFormula format = mock(AbstractFormula.class);
        when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
        when(format.getStringValue(null)).thenReturn("yyyy-MM-dd");

        FormulaFormatDate formula = Mockito.spy(FormulaFormatDate.class);
        doReturn(Arrays.asList(date, format)).when(formula).getParameters();

        assertEquals("2015-01-10", formula.getStringValue(null));
    }

    /**
     * Tests {@link FormulaFormatDate#getStringValue()} with another format
     */
    @Test
    public void testGetStringValueAnotherFormat() throws GLanguageException {
        AbstractFormula date = mock(AbstractFormula.class);
        when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
        when(date.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

        AbstractFormula format = mock(AbstractFormula.class);
        when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
        when(format.getStringValue(null)).thenReturn("dd/MM/yyyy");

        FormulaFormatDate formula = Mockito.spy(FormulaFormatDate.class);
        doReturn(Arrays.asList(date, format)).when(formula).getParameters();

        assertEquals("10/01/2015", formula.getStringValue(null));
    }

    /**
     * Tests {@link FormulaFormatDate#getBooleanValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetBooleanValue() throws GLanguageException {
        AbstractFormula date = mock(AbstractFormula.class);
        when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
        when(date.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

        AbstractFormula format = mock(AbstractFormula.class);
        when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
        when(format.getStringValue(null)).thenReturn("yyyy-MM-dd");

        FormulaFormatDate formula = Mockito.spy(FormulaFormatDate.class);
        doReturn(Arrays.asList(date, format)).when(formula).getParameters();

        formula.getBooleanValue(null);
    }

    /**
     * Tests {@link FormulaFormatDate#getDateValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetDateValue() throws GLanguageException {
        AbstractFormula date = mock(AbstractFormula.class);
        when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
        when(date.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

        AbstractFormula format = mock(AbstractFormula.class);
        when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
        when(format.getStringValue(null)).thenReturn("yyyy-MM-dd");

        FormulaFormatDate formula = Mockito.spy(FormulaFormatDate.class);
        doReturn(Arrays.asList(date, format)).when(formula).getParameters();

        formula.getDateValue(null);
    }

    /**
     * Tests {@link FormulaFormatDate#getDurationValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetDurationValue() throws GLanguageException {
        AbstractFormula date = mock(AbstractFormula.class);
        when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
        when(date.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

        AbstractFormula format = mock(AbstractFormula.class);
        when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
        when(format.getStringValue(null)).thenReturn("yyyy-MM-dd");

        FormulaFormatDate formula = Mockito.spy(FormulaFormatDate.class);
        doReturn(Arrays.asList(date, format)).when(formula).getParameters();

        formula.getDurationValue(null);
    }

    /**
     * Tests {@link FormulaFormatDate#operationAsText()}
     */
    @Test
    public void testOperationAsText() {
        FormulaFormatDate formula = new FormulaFormatDate();

        assertEquals("formatDate", formula.operationAsText());
    }

}
