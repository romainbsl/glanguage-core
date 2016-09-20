package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

/**
 * Test class for {@link FormulaMultiply}
 * 
 * @author DUPIREFR
 */
public class FormulaMultiplyTest {
	
	/*
	 * Constants
	 */
	private static final double DELTA = 1e-15;
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaMultiply#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaMultiply formula = new FormulaMultiply();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_MULTIPLY), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaMultiply#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaMultiply formula = new FormulaMultiply();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaMultiply#getIntegerValue()} when both parameters are integers
	 */
	@Test
	public void testGetIntegerValueIntInt() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getNumericValue()).thenReturn(2.0);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(3.0);
		
		FormulaMultiply formula = new FormulaMultiply(leftFormula, rightFormula);
		
		assertEquals(Integer.valueOf(6), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaMultiply#getNumericValue()} when both parameters are numerics
	 */
	@Test
	public void testGetNumericValueNumNum() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.5);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(2.3);
		
		FormulaMultiply formula = new FormulaMultiply(leftFormula, rightFormula);
		
		assertEquals(Double.valueOf(3.45), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaMultiply#getNumericValue()} when first parameter is integer and second is
	 * numeric
	 */
	@Test
	public void testGetNumericValueIntNum() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getNumericValue()).thenReturn(2.0);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(2.3);
		
		FormulaMultiply formula = new FormulaMultiply(leftFormula, rightFormula);
		
		assertEquals(Double.valueOf(4.6), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaMultiply#getNumericValue()} when first parameter is numeric and second is
	 * integer
	 */
	@Test
	public void testGetNumericValueNumInt() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(2.3);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(2.0);
		
		FormulaMultiply formula = new FormulaMultiply(leftFormula, rightFormula);
		
		assertEquals(Double.valueOf(4.6), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaMultiply#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getNumericValue()).thenReturn(2.0);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(3.0);
		
		FormulaMultiply formula = new FormulaMultiply(leftFormula, rightFormula);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaMultiply#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getNumericValue()).thenReturn(2.0);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(3.0);
		
		FormulaMultiply formula = new FormulaMultiply(leftFormula, rightFormula);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaMultiply#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getNumericValue()).thenReturn(2.0);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(3.0);
		
		FormulaMultiply formula = new FormulaMultiply(leftFormula, rightFormula);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaMultiply#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getNumericValue()).thenReturn(2.0);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(3.0);
		
		FormulaMultiply formula = new FormulaMultiply(leftFormula, rightFormula);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaMultiply#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaMultiply formula = new FormulaMultiply();
		
		assertEquals("*", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaMultiply#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.asText()).thenReturn("some_rule1");
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.asText()).thenReturn("some_rule2");
		
		FormulaMultiply formula = new FormulaMultiply(leftFormula, rightFormula);
		
		assertEquals("some_rule1 * some_rule2", formula.asText());
	}
	
}
