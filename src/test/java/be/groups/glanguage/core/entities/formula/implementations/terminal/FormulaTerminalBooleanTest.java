package be.groups.glanguage.core.entities.formula.implementations.terminal;

import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Test class for {@link FormulaTerminalBoolean}
 * 
 * @author DUPIREFR
 */
public class FormulaTerminalBooleanTest {
	
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
	 * Tests {@link FormulaTerminalBoolean#getReturnType()} when no parameters
	 */
	@Test
	public void testGetReturnType() throws GLanguageException {
		FormulaTerminalBoolean formula = spy(FormulaTerminalBoolean.class);

		Assert.assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
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

}
