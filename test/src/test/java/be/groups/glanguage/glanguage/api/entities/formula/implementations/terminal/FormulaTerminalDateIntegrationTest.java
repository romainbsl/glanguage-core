package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Test class for {@link FormulaTerminalDate}
 *
 * @author DUPIREFR
 */
public class FormulaTerminalDateIntegrationTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */

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
