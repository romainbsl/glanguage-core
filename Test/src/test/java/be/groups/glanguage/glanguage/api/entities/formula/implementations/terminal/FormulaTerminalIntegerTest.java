package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

/**
 * Test class for {@link FormulaTerminalInteger}
 * 
 * @author DUPIREFR
 */
public class FormulaTerminalIntegerTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaTerminalInteger#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaTerminalInteger formula = new FormulaTerminalInteger();
		assertTrue(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaTerminalInteger#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() {
		FormulaTerminalInteger formula = new FormulaTerminalInteger("1");
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}

	/**
	 * Tests {@link FormulaTerminalInteger#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() {
		FormulaTerminalInteger formula = new FormulaTerminalInteger("1");
		assertEquals(Double.valueOf(1), formula.getNumericValue());
	}

	/**
	 * Tests {@link FormulaTerminalInteger#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		FormulaTerminalInteger formula = new FormulaTerminalInteger("1");
		assertEquals("1", formula.getStringValue());
	}

	/**
	 * Tests {@link FormulaTerminalInteger#getDateValue()}
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetDateValue() {
		FormulaTerminalInteger formula = new FormulaTerminalInteger("1");
		formula.getDateValue();
	}

	/**
	 * Tests {@link FormulaTerminalInteger#getBooleanValue()}
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetBooleanValue() {
		FormulaTerminalInteger formula = new FormulaTerminalInteger("1");
		formula.getBooleanValue();
	}

	/**
	 * Tests {@link FormulaTerminalInteger#getReturnType()}
	 */
	@Test
	public void testGetReturnType() {
		FormulaTerminalInteger formula = new FormulaTerminalInteger("1");
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaTerminalInteger#asText()}
	 */
	@Test
	public void testAsText() {
		FormulaTerminalInteger formula = new FormulaTerminalInteger("1");
		assertEquals("1", formula.asText());
	}

}
