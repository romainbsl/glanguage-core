package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

/**
 * Test class for {@link FormulaTerminalString}
 * 
 * @author DUPIREFR
 */
public class FormulaTerminalStringTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaTerminalString#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaTerminalString formula = new FormulaTerminalString();
		
		assertEquals(Integer.valueOf(FormulaType.Values.TERMINAL_STRING), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalString#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaTerminalString formula = new FormulaTerminalString();
		assertTrue(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaTerminalString#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		FormulaTerminalString formula = new FormulaTerminalString(null, "string");
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalString#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		FormulaTerminalString formula = new FormulaTerminalString(null, "string");
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalString#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		FormulaTerminalString formula = new FormulaTerminalString(null, "string");
		assertEquals("string", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalString#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		FormulaTerminalString formula = new FormulaTerminalString(null, "string");
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalString#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		FormulaTerminalString formula = new FormulaTerminalString(null, "string");
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalString#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		FormulaTerminalString formula = new FormulaTerminalString(null, "string");
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalString#asText()}
	 */
	@Test
	public void testAsText() {
		FormulaTerminalString formula = new FormulaTerminalString(null, "string");
		assertEquals("string", formula.asText());
	}
	
}
