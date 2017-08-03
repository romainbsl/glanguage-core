package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

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
 * Test class for {@link FormulaFormatDate}
 *
 * @author DUPIREFR
 */
public class FormulaFormatDateIntegrationTest extends BaseDatabaseTest {

	/*
     * Tests
	 */

    /**
     * Tests {@link FormulaFormatDate#isValid(Evaluator)} when parameters match
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidMatching() throws GLanguageException {
        AbstractFormula date = mock(AbstractFormula.class);
        when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

        AbstractFormula format = mock(AbstractFormula.class);
        when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

        FormulaFormatDate formula = spy(FormulaFormatDate.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_DATE)).when(formula).getDescription();
        doReturn(Arrays.asList(date, format)).when(formula).getParameters();

        assertTrue(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaFormatDate#isValid(Evaluator)} when parameters don't match
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidNotMatching() throws GLanguageException {
        AbstractFormula date = mock(AbstractFormula.class);
        when(date.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

        AbstractFormula format = mock(AbstractFormula.class);
        when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

        FormulaFormatDate formula = spy(FormulaFormatDate.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_DATE)).when(formula).getDescription();
        doReturn(Arrays.asList(date, format)).when(formula).getParameters();

        assertFalse(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaFormatDate#getReturnType()} when parameters match
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testGetReturnTypeMatching() throws GLanguageException {
        FormulaFormatDate formula = spy(FormulaFormatDate.class);

        assertEquals(FormulaReturnType.STRING, formula.getReturnType(null));
    }

    /**
     * Tests {@link FormulaFormatDate#asText()}
     */
    @Test
    public void testAsText() throws GLanguageException {
        AbstractFormula date = mock(AbstractFormula.class);
        when(date.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
        when(date.asText()).thenReturn("10/01/2015");

        AbstractFormula format = mock(AbstractFormula.class);
        when(format.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
        when(format.asText()).thenReturn("yyyy-MM-dd");

        FormulaFormatDate formula = spy(FormulaFormatDate.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_FORMAT_DATE)).when(formula).getDescription();
        doReturn(Arrays.asList(date, format)).when(formula).getParameters();

        assertEquals("formatDate(10/01/2015; yyyy-MM-dd)", formula.asText());
    }

}
