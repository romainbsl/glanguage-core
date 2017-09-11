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
 * Test class for {@link FormulaTerminalDuration}
 * 
 * @author DUPIREFR
 */
public class FormulaTerminalDurationIntegrationTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaTerminalDuration#getDiscriminatorValue()}
	 */

	/**
	 * Tests {@link FormulaTerminalDuration#isValid(Evaluator)}
	 * when {@link FormulaTerminalDuration#getConstantValue()} is not null and well formatted
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValid() throws GLanguageException {
		FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DURATION)).when(formula).getDescription();
		doReturn("'P1Y2M3DT4H5M6.7S'").when(formula).getConstantValue();

		assertTrue(formula.isValid(null));
	}

	/**
	 * Tests {@link FormulaTerminalDuration#isValid(Evaluator)}
	 * when {@link FormulaTerminalDuration#getConstantValue()} is not null but not well formatted
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotWellFormatted() throws GLanguageException {
		FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DURATION)).when(formula).getDescription();
		doReturn("1Y2M3DT4H5M6.7S").when(formula).getConstantValue();

		assertFalse(formula.isValid(null));
	}

	/**
	 * Tests {@link FormulaTerminalDuration#isValid(Evaluator)}
	 * when {@link FormulaTerminalDuration#getConstantValue()} is null
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNull() throws GLanguageException {
		FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DURATION)).when(formula).getDescription();
		doReturn(null).when(formula).getConstantValue();

		assertFalse(formula.isValid(null));
	}

	/**
	 * Tests {@link FormulaTerminalDuration#asText()}
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testAsText() throws GLanguageException {
		FormulaTerminalDuration formula = spy(FormulaTerminalDuration.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_DURATION)).when(formula).getDescription();
		doReturn("'P1Y2M3DT4H5M6.7S'").when(formula).getConstantValue();

		assertEquals("'P1Y2M3DT4H5M6.7S'", formula.asText());
	}
	
}
