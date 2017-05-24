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

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Test class for {@link FormulaTerminalBoolean}
 * 
 * @author DUPIREFR
 */
public class FormulaTerminalBooleanTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaTerminalBoolean#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean();
		
		assertEquals(Integer.valueOf(FormulaType.Values.TERMINAL_BOOLEAN), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean();
		assertTrue(formula.isTerminal());
	}

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
	 * Tests {@link FormulaTerminalBoolean#getReturnType()} when no parameters
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnType() throws GLanguageException {
		FormulaTerminalBoolean formula = spy(FormulaTerminalBoolean.class);

		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaTerminalBoolean#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		FormulaTerminalBoolean formula = spy(FormulaTerminalBoolean.class);
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		FormulaTerminalBoolean formula = spy(FormulaTerminalBoolean.class);
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageException {
		FormulaTerminalBoolean formula = spy(FormulaTerminalBoolean.class);
		doReturn("true").when(formula).getConstantValue();

		assertEquals("true", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValue() throws GLanguageException {
		FormulaTerminalBoolean formula = spy(FormulaTerminalBoolean.class);
		doReturn("true").when(formula).getConstantValue();

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalBoolean#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		FormulaTerminalBoolean formula = spy(FormulaTerminalBoolean.class);
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		FormulaTerminalBoolean formula = spy(FormulaTerminalBoolean.class);
		formula.getDurationValue();
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
