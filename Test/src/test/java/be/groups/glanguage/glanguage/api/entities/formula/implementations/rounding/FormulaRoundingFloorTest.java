package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

/**
 * Test class for {@link FormulaRoundingFloor}
 * 
 * @author DUPIREFR
 */
public class FormulaRoundingFloorTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaRoundingFloor#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaRoundingFloor formula = new FormulaRoundingFloor();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getIntegerValue()} with integer parameter
	 */
	@Test
	public void testGetIntegerValueInt() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(117);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(10.0);
		
		FormulaRoundingFloor formula = new FormulaRoundingFloor(leftFormula, rightFormula);
		
		assertEquals(Integer.valueOf(110), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getIntegerValue()} with numeric parameter
	 */
	@Test
	public void testGetIntegerValueNum() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.567);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(0.01);
		
		FormulaRoundingFloor formula = new FormulaRoundingFloor(leftFormula, rightFormula);
		
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getNumericValue()} with integer parameter
	 */
	@Test
	public void testGetNumericValueInt() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(117);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(10.0);
		
		FormulaRoundingFloor formula = new FormulaRoundingFloor(leftFormula, rightFormula);
		
		assertEquals(Double.valueOf(110), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getNumericValue()} with numeric parameter
	 */
	@Test
	public void testGetNumericValueNum() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.567);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(0.01);
		
		FormulaRoundingFloor formula = new FormulaRoundingFloor(leftFormula, rightFormula);
		
		assertEquals(Double.valueOf(1.56), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.567);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(0.01);
		
		FormulaRoundingFloor formula = new FormulaRoundingFloor(leftFormula, rightFormula);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.567);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(0.01);
		
		FormulaRoundingFloor formula = new FormulaRoundingFloor(leftFormula, rightFormula);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.567);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(0.01);
		
		FormulaRoundingFloor formula = new FormulaRoundingFloor(leftFormula, rightFormula);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.567);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(0.01);
		
		FormulaRoundingFloor formula = new FormulaRoundingFloor(leftFormula, rightFormula);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaRoundingFloor formula = new FormulaRoundingFloor();
		
		assertEquals("floor", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.asText()).thenReturn("some_rule");
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.asText()).thenReturn("0.01");
		
		FormulaRoundingFloor formula = new FormulaRoundingFloor(leftFormula, rightFormula);
		
		assertEquals("floor(some_rule; 0.01)", formula.asText());
	}
	
}
