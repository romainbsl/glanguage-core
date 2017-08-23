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
 * Test class for {@link FormulaTerminalBoolean}
 * 
 * @author DUPIREFR
 */
public class FormulaTerminalBooleanIntegrationTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */

	/**
	 * Tests {@link FormulaTerminalBoolean#isValid(Evaluator)}
	 * when {@link FormulaTerminalBoolean#getConstantValue()} is not null and well formatted
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValid() throws GLanguageException {
		FormulaTerminalBoolean formula = spy(FormulaTerminalBoolean.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_BOOLEAN)).when(formula).getDescription();
		doReturn("true").when(formula).getConstantValue();

		assertTrue(formula.isValid(null));
	}

	/**
	 * Tests {@link FormulaTerminalBoolean#isValid(Evaluator)}
	 * when {@link FormulaTerminalBoolean#getConstantValue()} is not null but not well formatted
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotWellFormatted() throws GLanguageException {
		FormulaTerminalBoolean formula = spy(FormulaTerminalBoolean.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_BOOLEAN)).when(formula).getDescription();
		doReturn("ture").when(formula).getConstantValue();

		assertFalse(formula.isValid(null));
	}

	/**
	 * Tests {@link FormulaTerminalBoolean#isValid(Evaluator)}
	 * when {@link FormulaTerminalBoolean#getConstantValue()} is null
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNull() throws GLanguageException {
		FormulaTerminalBoolean formula = spy(FormulaTerminalBoolean.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_BOOLEAN)).when(formula).getDescription();
		doReturn(null).when(formula).getConstantValue();

		assertFalse(formula.isValid(null));
	}

	/**
	 * Tests {@link FormulaTerminalBoolean#asText()}
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testAsText() throws GLanguageException {
		FormulaTerminalBoolean formula = spy(FormulaTerminalBoolean.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_BOOLEAN)).when(formula).getDescription();
		doReturn("true").when(formula).getConstantValue();

		assertEquals("true", formula.asText());
	}
	
}
