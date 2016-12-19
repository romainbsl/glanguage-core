package be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link FormulaExtremumSignedMax}
 * 
 * @author DUPIREFR
 */
public class FormulaExtremumSignedMaxTest {
	
	/*
	 * Constants
	 */
	private static final double DELTA = 1e-15;
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaExtremumSignedMax#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaExtremumSignedMax formula = new FormulaExtremumSignedMax();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_SMAX), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaExtremumSignedMax formula = new FormulaExtremumSignedMax();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#getIntegerValue()} when all parameters are integers
	 */
	@Test
	public void testGetIntegerValueIntegers() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getNumericValue(null)).thenReturn(-4.0);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getNumericValue(null)).thenReturn(3.0);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getNumericValue(null)).thenReturn(2.0);
		parameters.add(param3);
		
		FormulaExtremumSignedMax formula = new FormulaExtremumSignedMax(null, parameters);
		
		assertEquals(Integer.valueOf(-4), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#getNumericValue()} when all parameters are numeric
	 */
	@Test
	public void testGetNumericValueNumerics() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue(null)).thenReturn(-3.5);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue(null)).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param3.getNumericValue(null)).thenReturn(2.8);
		parameters.add(param3);
		
		FormulaExtremumSignedMax formula = new FormulaExtremumSignedMax(null, parameters);
		
		assertEquals(Double.valueOf(-3.5), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#getNumericValue()} when numeric and integer parameters
	 * mix is numeric
	 */
	@Test
	public void testGetNumericValueIntNum() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getNumericValue(null)).thenReturn(-3.0);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue(null)).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getNumericValue(null)).thenReturn(2.0);
		parameters.add(param3);
		
		FormulaExtremumSignedMax formula = new FormulaExtremumSignedMax(null, parameters);
		
		assertEquals(Double.valueOf(-3), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#getNumericValue()} when all parameters are integers
	 */
	@Test
	public void testGetNumericValueIntegers() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getNumericValue(null)).thenReturn(-4.0);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getNumericValue(null)).thenReturn(3.0);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getNumericValue(null)).thenReturn(2.0);
		parameters.add(param3);
		
		FormulaExtremumSignedMax formula = new FormulaExtremumSignedMax(null, parameters);
		
		assertEquals(Double.valueOf(-4), formula.getNumericValue(), DELTA);
	}
	
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue(null)).thenReturn(-1.5);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue(null)).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param3.getNumericValue(null)).thenReturn(2.8);
		parameters.add(param3);
		
		FormulaExtremumSignedMax formula = new FormulaExtremumSignedMax(null, parameters);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue(null)).thenReturn(-1.5);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue(null)).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param3.getNumericValue(null)).thenReturn(2.8);
		parameters.add(param3);
		
		FormulaExtremumSignedMax formula = new FormulaExtremumSignedMax(null, parameters);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue(null)).thenReturn(-1.5);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue(null)).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param3.getNumericValue(null)).thenReturn(2.8);
		parameters.add(param3);
		
		FormulaExtremumSignedMax formula = new FormulaExtremumSignedMax(null, parameters);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue(null)).thenReturn(-1.5);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue(null)).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param3.getNumericValue(null)).thenReturn(2.8);
		parameters.add(param3);
		
		FormulaExtremumSignedMax formula = new FormulaExtremumSignedMax(null, parameters);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaExtremumSignedMax formula = new FormulaExtremumSignedMax();
		
		assertEquals("smax", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#operationAsText()}
	 */
	@Test
	public void testAsText() {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param1.asText()).thenReturn("some_rule1");
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param2.asText()).thenReturn("some_rule2");
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param3.asText()).thenReturn("some_rule3");
		parameters.add(param3);
		
		FormulaExtremumSignedMax formula = new FormulaExtremumSignedMax(null, parameters);
		
		assertEquals("smax(some_rule1, some_rule2, some_rule3)", formula.asText());
	}
	
}
