package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

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
	 * Tests {@link FormulaTerminalBoolean#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean();
		assertTrue(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaTerminalBoolean#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValue() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(true);
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaTerminalBoolean#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(true);
		formula.getIntegerValue();
	}

	/**
	 * Tests {@link FormulaTerminalBoolean#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(true);
		formula.getNumericValue();
	}

	/**
	 * Tests {@link FormulaTerminalBoolean#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(true);
		assertEquals("true", formula.getStringValue());
	}

	/**
	 * Tests {@link FormulaTerminalBoolean#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(true);
		formula.getDateValue();
	}

	/**
	 * Tests {@link FormulaTerminalBoolean#getReturnType()}
	 */
	@Test
	public void testGetReturnType() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(true);
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaTerminalBoolean#asText()}
	 */
	@Test
	public void testAsText() {
		FormulaTerminalBoolean formula = new FormulaTerminalBoolean(true);
		assertEquals("true", formula.asText());
	}

}
