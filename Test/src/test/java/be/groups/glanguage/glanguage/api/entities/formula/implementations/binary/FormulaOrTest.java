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
 * Test class for {@link FormulaOr}
 * 
 * @author DUPIREFR
 */
public class FormulaOrTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaOr#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaOr formula = new FormulaOr();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_OR), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaOr#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaOr formula = new FormulaOr();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaOr#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(false);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(false);
		
		FormulaOr formula = new FormulaOr(leftFormula, rightFormula);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaOr#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(false);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(false);
		
		FormulaOr formula = new FormulaOr(leftFormula, rightFormula);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaOr#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(false);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(false);
		
		FormulaOr formula = new FormulaOr(leftFormula, rightFormula);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaOr#getBooleanValue()} when parameters exist and both true
	 */
	@Test
	public void testGetBooleanValueParametersExistBothTrue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(true);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(true);
		
		FormulaOr formula = new FormulaOr(leftFormula, rightFormula);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaOr#getBooleanValue()} when parameters exist and left is false
	 */
	@Test
	public void testGetBooleanValueParametersExistLeftFalse() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(false);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(true);
		
		FormulaOr formula = new FormulaOr(leftFormula, rightFormula);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaOr#getBooleanValue()} when parameters exist and right is false
	 */
	@Test
	public void testGetBooleanValueParametersExistRightFalse() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(true);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(false);
		
		FormulaOr formula = new FormulaOr(leftFormula, rightFormula);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaOr#getBooleanValue()} when parameters exist and both false
	 */
	@Test
	public void testGetBooleanValueParametersExistBothFalse() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(false);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(false);
		
		FormulaOr formula = new FormulaOr(leftFormula, rightFormula);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaOr#getBooleanValue()} when left doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetBooleanValueLeftNotExists() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(null);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(true);
		
		FormulaOr formula = new FormulaOr(leftFormula, rightFormula);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaOr#getBooleanValue()} when left true and right doesn't exist
	 */
	@Test
	public void testGetBooleanValueLeftTrueRightNotExists() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(true);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(null);
		
		FormulaOr formula = new FormulaOr(leftFormula, rightFormula);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaOr#getBooleanValue()} when left false and right doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetBooleanValueRightNotExists() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(false);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(null);
		
		FormulaOr formula = new FormulaOr(leftFormula, rightFormula);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaOr#getBooleanValue()} when both parameter don't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetBooleanValueParametersNotExist() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(null);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(null);
		
		FormulaOr formula = new FormulaOr(leftFormula, rightFormula);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaOr#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(false);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(false);
		
		FormulaOr formula = new FormulaOr(leftFormula, rightFormula);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaOr#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.getBooleanValue()).thenReturn(false);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(false);
		
		FormulaOr formula = new FormulaOr(leftFormula, rightFormula);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaOr#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaOr formula = new FormulaOr();
		
		assertEquals("or", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaOr#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(leftFormula.asText()).thenReturn("some_rule1");
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.asText()).thenReturn("some_rule2");
		
		FormulaOr formula = new FormulaOr(leftFormula, rightFormula);
		
		assertEquals("some_rule1 or some_rule2", formula.asText());
	}
	
}
