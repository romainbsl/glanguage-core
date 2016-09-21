package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

/**
 * Test class for {@link FormulaTerminalDate}
 * 
 * @author DUPIREFR
 */
public class FormulaTerminalDateTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaTerminalDate#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaTerminalDate formula = new FormulaTerminalDate();
		
		assertEquals(Integer.valueOf(FormulaType.Values.TERMINAL_DATE), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaTerminalDate formula = new FormulaTerminalDate();
		assertTrue(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		FormulaTerminalDate formula = new FormulaTerminalDate(null, "01/01/2015");
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		FormulaTerminalDate formula = new FormulaTerminalDate(null, "01/01/2015");
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		FormulaTerminalDate formula = new FormulaTerminalDate(null, "01/01/2015");
		assertEquals("01/01/2015", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		FormulaTerminalDate formula = new FormulaTerminalDate(null, "01/01/2015");
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#getDateValue()}
	 */
	@Test
	public void testGetDateValue() {
		FormulaTerminalDate formula = new FormulaTerminalDate(null, "01/01/2015");
		assertEquals(LocalDate.of(2015, 1, 1), formula.getDateValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		FormulaTerminalDate formula = new FormulaTerminalDate(null, "01/01/2015");
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDate#asText()}
	 */
	@Test
	public void testAsText() {
		FormulaTerminalDate formula = new FormulaTerminalDate(null, "01/01/2015");
		assertEquals("01/01/2015", formula.asText());
	}
	
}
