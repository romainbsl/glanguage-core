package be.groups.glanguage.glanguage.api.entities.formula.implementations.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

/**
 * Test class for {@link FormulaMathAbs}
 * 
 * @author DUPIREFR
 */
public class FormulaMathAbsTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaMathAbs#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaMathAbs formula = new FormulaMathAbs();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getIntegerValue()).thenReturn(1);
		
		FormulaMathAbs formula = new FormulaMathAbs(Arrays.asList(childFormula));
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getIntegerValue()} when parameter exists and is positive
	 */
	@Test
	public void testGetIntegerValueParameterExistsPositive() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getNumericValue()).thenReturn(1.0);
		
		FormulaMathAbs formula = new FormulaMathAbs(Arrays.asList(childFormula));
		
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getIntegerValue()} when parameter exists and is negative
	 */
	@Test
	public void testGetIntegerValueParameterExistsNegative() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getNumericValue()).thenReturn(-1.0);
		
		FormulaMathAbs formula = new FormulaMathAbs(Arrays.asList(childFormula));
		
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getIntegerValue()} when parameter doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetIntegerValueParameterNotExists() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getNumericValue()).thenReturn(null);
		
		FormulaMathAbs formula = new FormulaMathAbs(Arrays.asList(childFormula));
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getNumericValue()} when parameter exists and is positive
	 */
	@Test
	public void testGetNumericValueParameterExistsPositive() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(childFormula.getNumericValue()).thenReturn(1.5);
		
		FormulaMathAbs formula = new FormulaMathAbs(Arrays.asList(childFormula));
		
		assertEquals(Double.valueOf(1.5), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getNumericValue()} when parameter exists and is negative
	 */
	@Test
	public void testGetNumericValueParameterExistsNegative() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(childFormula.getNumericValue()).thenReturn(-1.5);
		
		FormulaMathAbs formula = new FormulaMathAbs(Arrays.asList(childFormula));
		
		assertEquals(Double.valueOf(1.5), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getNumericValue()} when parameter doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetNumericValueParameterNotExists() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(childFormula.getNumericValue()).thenReturn(null);
		
		FormulaMathAbs formula = new FormulaMathAbs(Arrays.asList(childFormula));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getIntegerValue()} when parameter exist but has wrong type
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetIntegerValueParameterWrongType() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(childFormula.getStringValue()).thenReturn("some_value");
		
		FormulaMathAbs formula = new FormulaMathAbs(Arrays.asList(childFormula));
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getIntegerValue()).thenReturn(1);
		
		FormulaMathAbs formula = new FormulaMathAbs(Arrays.asList(childFormula));
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.getIntegerValue()).thenReturn(1);
		
		FormulaMathAbs formula = new FormulaMathAbs(Arrays.asList(childFormula));
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getReturnType()} when integer
	 */
	@Test
	public void testGetReturnTypeInteger() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaMathAbs formula = new FormulaMathAbs(Arrays.asList(childFormula));
		
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getReturnType()} when numeric
	 */
	@Test
	public void testGetReturnTypeNumeric() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaMathAbs formula = new FormulaMathAbs(Arrays.asList(childFormula));
		
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMathAbs#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaMathAbs formula = new FormulaMathAbs();
		
		assertEquals("abs", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaMathAbs#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula childFormula = mock(AbstractFormula.class);
		when(childFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(childFormula.asText()).thenReturn("some_rule");
		
		FormulaMathAbs formula = new FormulaMathAbs(Arrays.asList(childFormula));
		
		assertEquals("abs(some_rule)", formula.asText());
	}
	
}
