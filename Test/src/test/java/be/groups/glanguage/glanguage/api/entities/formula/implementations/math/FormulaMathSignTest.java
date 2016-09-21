package be.groups.glanguage.glanguage.api.entities.formula.implementations.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

/**
 * Test class for {@link FormulaMathSign}
 * 
 * @author DUPIREFR
 */
public class FormulaMathSignTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaMathSign#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaMathSign formula = new FormulaMathSign();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaMathSign#getIntegerValue()} when parameter exists and is positive
	 */
	@Test
	public void testGetIntegerValueParameterExistsPositive() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getNumericValue()).thenReturn(1.0);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(childFormula));
		
		assertTrue(formula.getIntegerValue() >= 0);
	}
	
	/**
	 * Tests {@link FormulaMathSign#getIntegerValue()} when parameter exists and is negative
	 */
	@Test
	public void testGetIntegerValueParameterExistsNegative() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getNumericValue()).thenReturn(-1.0);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(childFormula));
		
		assertTrue(formula.getIntegerValue() <= 0);
	}
	
	/**
	 * Tests {@link FormulaMathSign#getIntegerValue()} when parameter doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetIntegerValueParameterNotExists() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getNumericValue()).thenReturn(null);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(childFormula));
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaMathSign#getIntegerValue()} when parameter exist but has wrong type
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetIntegerValueParameterWrongType() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(childFormula.getStringValue()).thenReturn("some_value");
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(childFormula));
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaMathSign#getNumericValue()} when parameter exists and is positive
	 */
	@Test
	public void testGetNumericValueParameterExistsPositive() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(childFormula.getNumericValue()).thenReturn(1.5);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(childFormula));
		
		assertTrue(formula.getNumericValue() >= 0);
	}
	
	/**
	 * Tests {@link FormulaMathSign#getNumericValue()} when parameter exists and is negative
	 */
	@Test
	public void testGetNumericValueParameterExistsNegative() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(childFormula.getNumericValue()).thenReturn(-1.5);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(childFormula));
		
		assertTrue(formula.getNumericValue() <= 0);
	}
	
	/**
	 * Tests {@link FormulaMathSign#getNumericValue()} when parameter doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetNumericValueParameterNotExists() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(childFormula.getNumericValue()).thenReturn(null);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(childFormula));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaMathSign#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getIntegerValue()).thenReturn(1);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(childFormula));
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaMathSign#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getIntegerValue()).thenReturn(1);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(childFormula));
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaMathSign#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getIntegerValue()).thenReturn(1);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(childFormula));
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaMathSign#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getIntegerValue()).thenReturn(1);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(childFormula));
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaMathSign#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaMathSign formula = new FormulaMathSign();
		
		assertEquals("sign", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaMathSign#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.asText()).thenReturn("some_rule");
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(childFormula));
		
		assertEquals("sign(some_rule)", formula.asText());
	}
	
}
