package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

/**
 * Test class for {@link FormulaRoundingTrunc}
 * 
 * @author DUPIREFR
 */
public class FormulaRoundingTruncTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaRoundingTrunc#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaRoundingTrunc formula = new FormulaRoundingTrunc();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaRoundingTrunc#getIntegerValue()} with integer parameter
	 */
	@Test
	public void testGetIntegerValueInt() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(117);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(10.0);
		
		FormulaRoundingTrunc formula = new FormulaRoundingTrunc(leftFormula, rightFormula);
		
		assertEquals(Integer.valueOf(117), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingTrunc#getIntegerValue()} with numeric parameter
	 */
	@Test
	public void testGetIntegerValueNum() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.5751);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(2.0);
		
		FormulaRoundingTrunc formula = new FormulaRoundingTrunc(leftFormula, rightFormula);
		
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingTrunc#getNumericValue()} with integer parameter
	 */
	@Test
	public void testGetNumericValueInt() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(117);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(10.0);
		
		FormulaRoundingTrunc formula = new FormulaRoundingTrunc(leftFormula, rightFormula);
		
		assertEquals(Double.valueOf(117), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingTrunc#getNumericValue()} with numeric parameter
	 */
	@Test
	public void testGetNumericValueNum() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.575);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(2.0);
		
		FormulaRoundingTrunc formula = new FormulaRoundingTrunc(leftFormula, rightFormula);
		
		assertEquals(Double.valueOf(1.57), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingTrunc#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.5751);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(2.0);
		
		FormulaRoundingTrunc formula = new FormulaRoundingTrunc(leftFormula, rightFormula);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaRoundingTrunc#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.5751);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(2.0);
		
		FormulaRoundingTrunc formula = new FormulaRoundingTrunc(leftFormula, rightFormula);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaRoundingTrunc#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.5751);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(2.0);
		
		FormulaRoundingTrunc formula = new FormulaRoundingTrunc(leftFormula, rightFormula);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaRoundingTrunc#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaRoundingTrunc formula = new FormulaRoundingTrunc();
		
		assertEquals("trunc", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaRoundingTrunc#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.asText()).thenReturn("some_rule");
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.asText()).thenReturn("2");
		
		FormulaRoundingTrunc formula = new FormulaRoundingTrunc(leftFormula, rightFormula);
		
		assertEquals("trunc(some_rule; 2)", formula.asText());
	}
	
}
