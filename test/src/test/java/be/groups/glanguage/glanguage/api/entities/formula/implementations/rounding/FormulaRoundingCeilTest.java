package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaRoundingCeil}
 *
 * @author DUPIREFR
 */
public class FormulaRoundingCeilTest extends BaseDatabaseTest {

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
     * Tests {@link FormulaRoundingCeil#isValid(Evaluator)} when parameter is integer
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidInteger() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_CEIL)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter)).when(formula).getParameters();

        assertTrue(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaRoundingCeil#isValid(Evaluator)} when parameter is numeric
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidNumeric() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_CEIL)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter)).when(formula).getParameters();

        assertTrue(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaRoundingCeil#isValid(Evaluator)} when parameter is not integer or numeric
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidNotIntegerOrNumeric() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_CEIL)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter)).when(formula).getParameters();

        assertFalse(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaRoundingCeil#isValid(Evaluator)} when parameter is integer and precision is
     * integer
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidIntegerInteger() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_CEIL)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        assertTrue(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaRoundingCeil#isValid(Evaluator)} when parameter is numeric and precision is
     * integer
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidNumericInteger() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_CEIL)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        assertTrue(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaRoundingCeil#isValid(Evaluator)} when parameter is numeric and precision is
     * numeric
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidNumericNumeric() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_CEIL)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        assertTrue(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaRoundingCeil#getReturnType()} when parameter is integer
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testGetReturnTypeInteger() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_CEIL)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter)).when(formula).getParameters();

        assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
    }

    /**
     * Tests {@link FormulaRoundingCeil#getReturnType()} when parameter is numeric
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testGetReturnTypeNumeric() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_CEIL)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter)).when(formula).getParameters();

        assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
    }

    /**
     * Tests {@link FormulaRoundingCeil#getReturnType()} when parameter is not integer or numeric
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testGetReturnTypeNotIntegerOrNumeric() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_CEIL)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter)).when(formula).getParameters();

        assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
    }

    /**
     * Tests {@link FormulaRoundingCeil#getReturnType()} when parameter is integer and precision is
     * integer
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testGetReturnTypeIntegerInteger() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_CEIL)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
    }

    /**
     * Tests {@link FormulaRoundingCeil#getReturnType()} when parameter is numeric and precision is
     * integer
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testGetReturnTypeNumericInteger() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_CEIL)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
    }

    /**
     * Tests {@link FormulaRoundingCeil#getReturnType()} when parameter is numeric and precision is
     * numeric
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testGetReturnTypeNumericNumeric() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_CEIL)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
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

    /**
     * Tests {@link FormulaRoundingCeil#asText()}
     */
    @Test
    public void testAsText() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        when(parameter.asText()).thenReturn("some_rule");

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        when(precision.asText()).thenReturn("0.01");

        FormulaRoundingCeil formula = spy(FormulaRoundingCeil.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_CEIL)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        assertEquals("ceil(some_rule; 0.01)", formula.asText());
    }

}
