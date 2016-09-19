package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

/**
 * Test class for {@link FormulaRoundingCeil}
 * 
 * @author DUPIREFR
 */
public class FormulaRoundingCeilTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaRoundingCeil#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaRoundingCeil formula = new FormulaRoundingCeil();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaRoundingCeil#getIntegerValue()} with integer parameter
	 */
	@Test
	public void testGetIntegerValueInt() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(111);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(10.0);
		
		FormulaRoundingCeil formula = new FormulaRoundingCeil(leftFormula, rightFormula);
		
		assertEquals(Integer.valueOf(120), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingCeil#getIntegerValue()} with numeric parameter
	 */
	@Test
	public void testGetIntegerValueNum() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.544);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(0.01);
		
		FormulaRoundingCeil formula = new FormulaRoundingCeil(leftFormula, rightFormula);
		
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingCeil#getNumericValue()} with integer parameter
	 */
	@Test
	public void testGetNumericValueInt() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(111);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(10.0);
		
		FormulaRoundingCeil formula = new FormulaRoundingCeil(leftFormula, rightFormula);
		
		assertEquals(Double.valueOf(120), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingCeil#getNumericValue()} with numeric parameter
	 */
	@Test
	public void testGetNumericValueNum() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.544);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(0.01);
		
		FormulaRoundingCeil formula = new FormulaRoundingCeil(leftFormula, rightFormula);
		
		assertEquals(Double.valueOf(1.55), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingCeil#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.544);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(0.01);
		
		FormulaRoundingCeil formula = new FormulaRoundingCeil(leftFormula, rightFormula);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaRoundingCeil#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.544);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(0.01);
		
		FormulaRoundingCeil formula = new FormulaRoundingCeil(leftFormula, rightFormula);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaRoundingCeil#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.544);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(0.01);
		
		FormulaRoundingCeil formula = new FormulaRoundingCeil(leftFormula, rightFormula);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaRoundingCeil#getReturnType()} when first parameter is integer
	 */
	@Test
	public void testGetReturnTypeInteger() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaRoundingCeil formula = new FormulaRoundingCeil(leftFormula, rightFormula);
		
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaRoundingCeil#getReturnType()} when first parameter is numeric
	 */
	@Test
	public void testGetReturnTypeNumeric() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaRoundingCeil formula = new FormulaRoundingCeil(leftFormula, rightFormula);
		
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaRoundingCeil#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaRoundingCeil formula = new FormulaRoundingCeil();
		
		assertEquals("ceil", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaRoundingCeil#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.asText()).thenReturn("some_rule");
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.asText()).thenReturn("0.01");
		
		FormulaRoundingCeil formula = new FormulaRoundingCeil(leftFormula, rightFormula);
		
		assertEquals("ceil(some_rule; 0.01)", formula.asText());
	}
	
}
