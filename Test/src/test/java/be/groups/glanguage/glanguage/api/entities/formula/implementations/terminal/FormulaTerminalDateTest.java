package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

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
	@Test(expected = IllegalAccessError.class)
	public void testGetIntegerValue() {
		FormulaTerminalDate formula = new FormulaTerminalDate("01/01/2015");
		formula.getIntegerValue();
	}

	/**
	 * Tests {@link FormulaTerminalDate#getNumericValue()}
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetNumericValue() {
		FormulaTerminalDate formula = new FormulaTerminalDate("01/01/2015");
		formula.getNumericValue();
	}

	/**
	 * Tests {@link FormulaTerminalDate#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		FormulaTerminalDate formula = new FormulaTerminalDate("01/01/2015");
		assertEquals("01/01/2015", formula.getStringValue());
	}

	/**
	 * Tests {@link FormulaTerminalDate#getDateValue()}
	 */
	@Test
	public void testGetDateValue() {
		FormulaTerminalDate formula = new FormulaTerminalDate("01/01/2015");
		assertEquals(LocalDate.of(2015, 1, 1), formula.getDateValue());
	}

	/**
	 * Tests {@link FormulaTerminalDate#getBooleanValue()}
	 */
	@Test(expected = IllegalAccessError.class)
	public void testGetBooleanValue() {
		FormulaTerminalDate formula = new FormulaTerminalDate("01/01/2015");
		formula.getBooleanValue();
	}

	/**
	 * Tests {@link FormulaTerminalDate#getReturnType()}
	 */
	@Test
	public void testGetReturnType() {
		FormulaTerminalDate formula = new FormulaTerminalDate("01/01/2015");
		assertEquals(FormulaReturnType.DATE, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaTerminalDate#asText()}
	 */
	@Test
	public void testAsText() {
		FormulaTerminalDate formula = new FormulaTerminalDate("01/01/2015");
		assertEquals("01/01/2015", formula.asText());
	}

}
