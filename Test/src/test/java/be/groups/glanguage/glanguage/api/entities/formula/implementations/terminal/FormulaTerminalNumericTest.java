package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for {@link FormulaTerminalNumeric}
 * 
 * @author DUPIREFR
 */
public class FormulaTerminalNumericTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaTerminalNumeric#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric();
		assertTrue(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaTerminalNumeric#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric("1.5");
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}

	/**
	 * Tests {@link FormulaTerminalNumeric#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric("1.5");
		assertEquals(Double.valueOf(1.5), formula.getNumericValue());
	}

	/**
	 * Tests {@link FormulaTerminalNumeric#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric("1.5");
		assertEquals("1.5", formula.getStringValue());
	}

	/**
	 * Tests {@link FormulaTerminalNumeric#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric("1.5");
		formula.getDateValue();
	}

	/**
	 * Tests {@link FormulaTerminalNumeric#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric("1.5");
		formula.getBooleanValue();
	}

	/**
	 * Tests {@link FormulaTerminalNumeric#asText()}
	 */
	@Test
	public void testAsText() {
		FormulaTerminalNumeric formula = new FormulaTerminalNumeric("1.5");
		assertEquals("1.5", formula.asText());
	}

}
