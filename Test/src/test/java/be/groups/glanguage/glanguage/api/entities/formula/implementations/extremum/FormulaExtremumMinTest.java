package be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

/**
 * Test class for {@link FormulaExtremumMin}
 * 
 * @author DUPIREFR
 */
public class FormulaExtremumMinTest {
	
	/*
	 * Constants
	 */
	private static final double DELTA = 1e-15;
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaExtremumMin#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaExtremumMin formula = new FormulaExtremumMin();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaExtremumMin#getIntegerValue()} when all parameters are integers
	 */
	@Test
	public void testGetIntegerValueIntegers() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getNumericValue()).thenReturn(-1.0);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getNumericValue()).thenReturn(3.0);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getNumericValue()).thenReturn(2.0);
		parameters.add(param3);
		
		FormulaExtremumMin formula = new FormulaExtremumMin(parameters);
		
		assertEquals(Integer.valueOf(-1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaExtremumMin#getNumericValue()} when all parameters are numeric
	 */
	@Test
	public void testGetNumericValueNumerics() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(-1.5);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue()).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param3.getNumericValue()).thenReturn(2.8);
		parameters.add(param3);
		
		FormulaExtremumMin formula = new FormulaExtremumMin(parameters);
		
		assertEquals(Double.valueOf(-1.5), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaExtremumMin#getNumericValue()} when numeric and integer parameters mix is
	 * numeric
	 */
	@Test
	public void testGetNumericValueIntNum() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getNumericValue()).thenReturn(-1.0);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue()).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getNumericValue()).thenReturn(2.0);
		parameters.add(param3);
		
		FormulaExtremumMin formula = new FormulaExtremumMin(parameters);
		
		assertEquals(Double.valueOf(-1), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaExtremumMin#getNumericValue()} when all parameters are integers
	 */
	@Test
	public void testGetNumericValueIntegers() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getNumericValue()).thenReturn(-1.0);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getNumericValue()).thenReturn(3.0);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getNumericValue()).thenReturn(2.0);
		parameters.add(param3);
		
		FormulaExtremumMin formula = new FormulaExtremumMin(parameters);
		
		assertEquals(Double.valueOf(-1), formula.getNumericValue(), DELTA);
	}
	
	
	/**
	 * Tests {@link FormulaExtremumMin#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(-1.5);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue()).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param3.getNumericValue()).thenReturn(2.8);
		parameters.add(param3);
		
		FormulaExtremumMin formula = new FormulaExtremumMin(parameters);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaExtremumMin#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(-1.5);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue()).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param3.getNumericValue()).thenReturn(2.8);
		parameters.add(param3);
		
		FormulaExtremumMin formula = new FormulaExtremumMin(parameters);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaExtremumMin#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(-1.5);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue()).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param3.getNumericValue()).thenReturn(2.8);
		parameters.add(param3);
		
		FormulaExtremumMin formula = new FormulaExtremumMin(parameters);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaExtremumMin#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue()).thenReturn(-1.5);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue()).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(param3.getNumericValue()).thenReturn(2.8);
		parameters.add(param3);
		
		FormulaExtremumMin formula = new FormulaExtremumMin(parameters);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaExtremumMin#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaExtremumMin formula = new FormulaExtremumMin();
		
		assertEquals("min", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaExtremumMin#operationAsText()}
	 */
	@Test
	public void testAsText() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param1.asText()).thenReturn("some_rule1");
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param2.asText()).thenReturn("some_rule2");
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(param3.asText()).thenReturn("some_rule3");
		parameters.add(param3);
		
		FormulaExtremumMin formula = new FormulaExtremumMin(parameters);
		
		assertEquals("min(some_rule1, some_rule2, some_rule3)", formula.asText());
	}
	
}