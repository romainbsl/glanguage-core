package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.Test;

/**
 * Test class for {@link FormulaTerminalDuration}
 * 
 * @author DUPIREFR
 */
public class FormulaTerminalDurationTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaTerminalDuration#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration();
		assertTrue(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration("P1Y2M3DT4H5M6.7S");
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration("P1Y2M3DT4H5M6.7S");
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration("P1Y2M3DT4H5M6.7S");
		assertEquals("P1Y2M3DT4H5M6.7S", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration("P1Y2M3DT4H5M6.7S");
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration("P1Y2M3DT4H5M6.7S");
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration("'P1Y2M3DT4H5M6.7S'");
		assertEquals(Duration.parse("P430DT4H5M6.7S"), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getDurationValue()} without time
	 */
	@Test
	public void testGetDurationValueWithoutTime() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration("'P1Y2M3D'");
		assertEquals(Duration.parse("P430D"), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getDurationValue()} without year
	 */
	@Test
	public void testGetDurationValueWithoutYear() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration("'P2M3DT4H5M6.7S'");
		assertEquals(Duration.parse("P65DT4H5M6.7S"), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getDurationValue()} without year and month
	 */
	@Test
	public void testGetDurationValueWithoutYearAndMonth() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration("'P3DT4H5M6.7S'");
		assertEquals(Duration.parse("P3DT4H5M6.7S"), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#getDurationValue()} without dates
	 */
	@Test
	public void testGetDurationValueWithoutDates() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration("'PT4H5M6.7S'");
		assertEquals(Duration.parse("PT4H5M6.7S"), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#asText()}
	 */
	@Test
	public void testAsText() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration("string");
		assertEquals("string", formula.asText());
	}
	
}
