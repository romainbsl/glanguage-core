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
 * Test class for {@link FormulaRoundingArithmetic}
 *
 * @author DUPIREFR
 */
public class FormulaRoundingArithmeticIntegrationTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */

    /**
     * Tests {@link FormulaRoundingArithmetic#isValid(Evaluator)} when parameter is integer
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidInteger() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        FormulaRoundingArithmetic formula = spy(FormulaRoundingArithmetic.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ROUNDED)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter)).when(formula).getParameters();

        assertTrue(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaRoundingArithmetic#isValid(Evaluator)} when parameter is numeric
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidNumeric() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

        FormulaRoundingArithmetic formula = spy(FormulaRoundingArithmetic.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ROUNDED)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter)).when(formula).getParameters();

        assertTrue(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaRoundingArithmetic#isValid(Evaluator)} when parameter is not integer or numeric
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidNotIntegerOrNumeric() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

        FormulaRoundingArithmetic formula = spy(FormulaRoundingArithmetic.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ROUNDED)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter)).when(formula).getParameters();

        assertFalse(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaRoundingArithmetic#isValid(Evaluator)} when parameter is integer and precision is
     * integer
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidIntegerInteger() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        FormulaRoundingArithmetic formula = spy(FormulaRoundingArithmetic.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ROUNDED)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        assertTrue(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaRoundingArithmetic#isValid(Evaluator)} when parameter is numeric and precision is
     * integer
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidNumericInteger() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        FormulaRoundingArithmetic formula = spy(FormulaRoundingArithmetic.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ROUNDED)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        assertTrue(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaRoundingArithmetic#isValid(Evaluator)} when parameter is numeric and precision is
     * numeric
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidNumericNumeric() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

        FormulaRoundingArithmetic formula = spy(FormulaRoundingArithmetic.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ROUNDED)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        assertTrue(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaRoundingArithmetic#getReturnType()} when parameter is integer
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testGetReturnTypeInteger() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        FormulaRoundingArithmetic formula = spy(FormulaRoundingArithmetic.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ROUNDED)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter)).when(formula).getParameters();

        assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
    }

    /**
     * Tests {@link FormulaRoundingArithmetic#getReturnType()} when parameter is numeric
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testGetReturnTypeNumeric() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

        FormulaRoundingArithmetic formula = spy(FormulaRoundingArithmetic.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ROUNDED)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter)).when(formula).getParameters();

        assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
    }

    /**
     * Tests {@link FormulaRoundingArithmetic#getReturnType()} when parameter is not integer or numeric
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testGetReturnTypeNotIntegerOrNumeric() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

        FormulaRoundingArithmetic formula = spy(FormulaRoundingArithmetic.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ROUNDED)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter)).when(formula).getParameters();

        assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
    }

    /**
     * Tests {@link FormulaRoundingArithmetic#getReturnType()} when parameter is integer and
     * precision is integer
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testGetReturnTypeIntegerInteger() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        FormulaRoundingArithmetic formula = spy(FormulaRoundingArithmetic.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ROUNDED)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
    }

    /**
     * Tests {@link FormulaRoundingArithmetic#getReturnType()} when parameter is numeric and
     * precision is integer
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testGetReturnTypeNumericInteger() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        FormulaRoundingArithmetic formula = spy(FormulaRoundingArithmetic.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ROUNDED)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
    }

    /**
     * Tests {@link FormulaRoundingArithmetic#getReturnType()} when parameter is numeric and
     * precision is numeric
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testGetReturnTypeNumericNumeric() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

        FormulaRoundingArithmetic formula = spy(FormulaRoundingArithmetic.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ROUNDED)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
    }

    /**
     * Tests {@link FormulaRoundingArithmetic#asText()}
     */
    @Test
    @Category(DatabaseTestCategory.class)
    public void testAsText() throws GLanguageException {
        AbstractFormula parameter = mock(AbstractFormula.class);
        when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        when(parameter.asText()).thenReturn("some_rule");

        AbstractFormula precision = mock(AbstractFormula.class);
        when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
        when(precision.asText()).thenReturn("0.01");

        FormulaRoundingArithmetic formula = spy(FormulaRoundingArithmetic.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ROUNDED)).when(formula).getDescription();
        doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

        assertEquals("round(some_rule; 0.01)", formula.asText());
    }

}
