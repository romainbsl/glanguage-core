package be.groups.glanguage.core.entities.formula.implementations.rounding;

import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaRoundingCeil}
 *
 * @author DUPIREFR
 */
public class FormulaRoundingCeilTest {

	/*
	 * Tests
	 */

    /**
     * Tests {@link FormulaRoundingCeil#getDiscriminatorValue()}
     */
    @Test
    public void testGetDiscriminatorValue() {
        FormulaRoundingCeil formula = new FormulaRoundingCeil();

        assertEquals(Integer.valueOf(FormulaType.Values.F_CEIL), formula.getDiscriminatorValue());
    }

    /**
     * Tests {@link FormulaRoundingCeil#isTerminal()}
     */
    @Test
    public void testIsTerminal() {
        FormulaRoundingCeil formula = new FormulaRoundingCeil();

        assertFalse(formula.isTerminal());
    }

    /**
     * Tests {@link FormulaRoundingCeil#getIntegerValue()} with integer parameter
     */
    @Test
    public void testGetIntegerValueInt() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
        when(parameter.getIntegerValue(null)).thenReturn(111);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        when(precision.getNumericValue(null)).thenReturn(10.0);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        assertEquals(Integer.valueOf(120), formula.getIntegerValue(null));
    }

    /**
     * Tests {@link FormulaRoundingCeil#getIntegerValue()} with numeric parameter
     */
    @Test
    public void testGetIntegerValueNum() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        when(parameter.getNumericValue(null)).thenReturn(1.544);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        when(precision.getNumericValue(null)).thenReturn(0.01);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        assertEquals(Integer.valueOf(1), formula.getIntegerValue(null));
    }

    /**
     * Tests {@link FormulaRoundingCeil#getNumericValue()} with integer parameter
     */
    @Test
    public void testGetNumericValueInt() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
        when(parameter.getIntegerValue(null)).thenReturn(111);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        when(precision.getNumericValue(null)).thenReturn(10.0);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        assertEquals(Double.valueOf(120), formula.getNumericValue(null));
    }

    /**
     * Tests {@link FormulaRoundingCeil#getNumericValue()} with numeric parameter
     */
    @Test
    public void testGetNumericValueNum() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        when(parameter.getNumericValue(null)).thenReturn(1.544);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        when(precision.getNumericValue(null)).thenReturn(0.01);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        assertEquals(Double.valueOf(1.55), formula.getNumericValue(null));
    }

    /**
     * Tests {@link FormulaRoundingCeil#getStringValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetStringValue() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        when(parameter.getNumericValue(null)).thenReturn(1.544);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        when(precision.getNumericValue(null)).thenReturn(0.01);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        formula.getStringValue(null);
    }

    /**
     * Tests {@link FormulaRoundingCeil#getBooleanValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetBooleanValue() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        when(parameter.getNumericValue(null)).thenReturn(1.544);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        when(precision.getNumericValue(null)).thenReturn(0.01);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        formula.getBooleanValue(null);
    }

    /**
     * Tests {@link FormulaRoundingCeil#getDateValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetDateValue() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        when(parameter.getNumericValue(null)).thenReturn(1.544);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        when(precision.getNumericValue(null)).thenReturn(0.01);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        formula.getDateValue(null);
    }

    /**
     * Tests {@link FormulaRoundingCeil#getDurationValue()}
     */
    @Test(expected = GLanguageException.class)
    public void testGetDurationValue() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        when(parameter.getNumericValue(null)).thenReturn(1.544);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        when(precision.getNumericValue(null)).thenReturn(0.01);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        formula.getDurationValue(null);
    }

    /**
     * Tests {@link FormulaRoundingCeil#operationAsText()}
     */
    @Test
    public void testOperationAsText() {
        FormulaRoundingCeil formula = new FormulaRoundingCeil();

        assertEquals("ceil", formula.operationAsText());
    }

}
