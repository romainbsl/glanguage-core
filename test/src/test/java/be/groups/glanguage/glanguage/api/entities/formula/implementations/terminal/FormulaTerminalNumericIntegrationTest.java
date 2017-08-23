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
 * Test class for {@link FormulaTerminalNumeric}
 *
 * @author DUPIREFR
 */
public class FormulaTerminalNumericIntegrationTest extends BaseDatabaseTest {

	/*
	 * Tests
	 */

    /**
     * Tests {@link FormulaTerminalNumeric#isValid(Evaluator)}
     * when {@link FormulaTerminalNumeric#getConstantValue()} is not null and well formatted
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValid() throws GLanguageException {
        FormulaTerminalNumeric formula = spy(FormulaTerminalNumeric.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC)).when(formula).getDescription();
        doReturn("1.5").when(formula).getConstantValue();

        assertTrue(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaTerminalNumeric#isValid(Evaluator)}
     * when {@link FormulaTerminalNumeric#getConstantValue()} is not null but not well formatted
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidNotWellFormatted() throws GLanguageException {
        FormulaTerminalNumeric formula = spy(FormulaTerminalNumeric.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC)).when(formula).getDescription();
        doReturn("1,5").when(formula).getConstantValue();

        assertFalse(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaTerminalNumeric#isValid(Evaluator)}
     * when {@link FormulaTerminalNumeric#getConstantValue()} is null
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testIsValidNull() throws GLanguageException {
        FormulaTerminalNumeric formula = spy(FormulaTerminalNumeric.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC)).when(formula).getDescription();
        doReturn(null).when(formula).getConstantValue();

        assertFalse(formula.isValid(null));
    }

    /**
     * Tests {@link FormulaTerminalNumeric#asText()}
     */
    @Test
    @Category({DatabaseTestCategory.class})
    public void testAsText() throws GLanguageException {
        FormulaTerminalNumeric formula = spy(FormulaTerminalNumeric.class);
        doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_NUMERIC)).when(formula).getDescription();
        doReturn("1.5").when(formula).getConstantValue();

        assertEquals("1.5", formula.asText());
    }

}
