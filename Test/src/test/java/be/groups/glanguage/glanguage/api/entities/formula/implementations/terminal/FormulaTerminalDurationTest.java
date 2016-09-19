package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.util.Arrays;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

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
	 * Tests {@link FormulaTerminalDuration#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration("P1Y2M3DT4H5M6.7S");
		formula.getDateValue();
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
	 * Tests {@link FormulaTerminalDuration#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration("P1Y2M3DT4H5M6.7S");
		assertEquals(Duration.parse("P430DT4H5M6.7S"), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaTerminalDuration#isValid()}
	 */
	@Test
	public void testIsValid() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration("string");
		
		FormulaDescription formulaDescription = mock(FormulaDescription.class);
		when(formulaDescription.isValid(Arrays.asList())).thenReturn(true);
		formula.setDescription(formulaDescription);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaTerminalNumeric#getReturnType()}
	 */
	@Test
	public void testGetReturnType() {
		FormulaTerminalDuration formula = new FormulaTerminalDuration("string");
		
		FormulaDescription formulaDescription = mock(FormulaDescription.class);
		when(formulaDescription.getReturnType(Arrays.asList())).thenReturn(FormulaReturnType.STRING);
		formula.setDescription(formulaDescription);
		
		assertEquals(FormulaReturnType.STRING, formula.getReturnType());
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
