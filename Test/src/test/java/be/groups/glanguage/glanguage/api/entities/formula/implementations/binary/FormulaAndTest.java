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
 * Test class for {@link FormulaAnd}
 * 
 * @author DUPIREFR
 */
public class FormulaAndTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaAnd#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaAnd formula = new FormulaAnd();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_AND), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaAnd#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaAnd formula = new FormulaAnd();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaAnd#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(false);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(false);
		
		FormulaAnd formula = new FormulaAnd(leftFormula, rightFormula);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaAnd#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(false);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(false);
		
		FormulaAnd formula = new FormulaAnd(leftFormula, rightFormula);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaAnd#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(false);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(false);
		
		FormulaAnd formula = new FormulaAnd(leftFormula, rightFormula);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaAnd#getBooleanValue()} when parameters exist and both true
	 */
	@Test
	public void testGetBooleanValueParametersExistBothTrue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(true);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(true);
		
		FormulaAnd formula = new FormulaAnd(leftFormula, rightFormula);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaAnd#getBooleanValue()} when parameters exist and left is false
	 */
	@Test
	public void testGetBooleanValueParametersExistLeftFalse() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(false);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(true);
		
		FormulaAnd formula = new FormulaAnd(leftFormula, rightFormula);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaAnd#getBooleanValue()} when parameters exist and right is false
	 */
	@Test
	public void testGetBooleanValueParametersExistRightFalse() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(true);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(false);
		
		FormulaAnd formula = new FormulaAnd(leftFormula, rightFormula);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaAnd#getBooleanValue()} when parameters exist and both false
	 */
	@Test
	public void testGetBooleanValueParametersExistBothFalse() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(false);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(false);
		
		FormulaAnd formula = new FormulaAnd(leftFormula, rightFormula);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaAnd#getBooleanValue()} when left doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetBooleanValueLeftNotExists() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(null);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(true);
		
		FormulaAnd formula = new FormulaAnd(leftFormula, rightFormula);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaAnd#getBooleanValue()} when left true and right doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetBooleanValueLeftTrueRightNotExists() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(true);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(null);
		
		FormulaAnd formula = new FormulaAnd(leftFormula, rightFormula);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaAnd#getBooleanValue()} when left false and right doesn't exist
	 */
	@Test
	public void testGetBooleanValueLeftFalseRightNotExists() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(false);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(null);
		
		FormulaAnd formula = new FormulaAnd(leftFormula, rightFormula);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaAnd#getBooleanValue()} when both parameter don't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetBooleanValueParametersNotExist() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(null);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(null);
		
		FormulaAnd formula = new FormulaAnd(leftFormula, rightFormula);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaAnd#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(false);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(false);
		
		FormulaAnd formula = new FormulaAnd(leftFormula, rightFormula);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaAnd#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(false);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(false);
		
		FormulaAnd formula = new FormulaAnd(leftFormula, rightFormula);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaAnd#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaAnd formula = new FormulaAnd();
		
		assertEquals("and", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaAnd#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.asText()).thenReturn("some_rule1");
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.asText()).thenReturn("some_rule2");
		
		FormulaAnd formula = new FormulaAnd(leftFormula, rightFormula);
		
		assertEquals("some_rule1 and some_rule2", formula.asText());
	}
	
}
