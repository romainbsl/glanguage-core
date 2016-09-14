package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

/**
 * Test class for {@link FormulaModulo}
 * 
 * @author DUPIREFR
 */
public class FormulaModuloTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaModulo#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaModulo formula = new FormulaModulo();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaModulo#getNumericValue()}
	 */
	@Test
	public void testGetIntegerValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(5);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getIntegerValue()).thenReturn(3);
		
		FormulaModulo formula = new FormulaModulo(leftFormula, rightFormula);
		
		assertEquals(Integer.valueOf(2), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaModulo#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(3);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getIntegerValue()).thenReturn(2);
		
		FormulaModulo formula = new FormulaModulo(leftFormula, rightFormula);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaModulo#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(3);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getIntegerValue()).thenReturn(2);
		
		FormulaModulo formula = new FormulaModulo(leftFormula, rightFormula);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaModulo#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(3);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getIntegerValue()).thenReturn(2);
		
		FormulaModulo formula = new FormulaModulo(leftFormula, rightFormula);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaModulo#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(3);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getIntegerValue()).thenReturn(2);
		
		FormulaModulo formula = new FormulaModulo(leftFormula, rightFormula);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaModulo#getReturnType()}
	 */
	@Test
	public void testGetReturnType() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaModulo formula = new FormulaModulo(leftFormula, rightFormula);
		
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaModulo#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaModulo formula = new FormulaModulo();
		
		assertEquals("\\", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaModulo#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.asText()).thenReturn("some_rule1");
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.asText()).thenReturn("some_rule2");
		
		FormulaModulo formula = new FormulaModulo(leftFormula, rightFormula);
		
		assertEquals("some_rule1 \\ some_rule2", formula.asText());
	}
	
}
