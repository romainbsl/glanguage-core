package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

/**
 * Test class for {@link FormulaEqual}
 * 
 * @author DUPIREFR
 */
public class FormulaEqualTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaEqual#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaEqual formula = new FormulaEqual();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_EQUAL), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaEqual formula = new FormulaEqual();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaEqual#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getValue()).thenReturn(1);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getValue()).thenReturn(0);
		
		FormulaEqual formula = new FormulaEqual(leftFormula, rightFormula);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaEqual#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getValue()).thenReturn(1);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getValue()).thenReturn(0);
		
		FormulaEqual formula = new FormulaEqual(leftFormula, rightFormula);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaEqual#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getValue()).thenReturn(1);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getValue()).thenReturn(0);
		
		FormulaEqual formula = new FormulaEqual(leftFormula, rightFormula);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are integers and left
	 * <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothIntegersLeftDiffRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getValue()).thenReturn(1);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getValue()).thenReturn(0);
		
		FormulaEqual formula = new FormulaEqual(leftFormula, rightFormula);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are integers and left =
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothIntegersLeftEqRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getValue()).thenReturn(1);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getValue()).thenReturn(1);
		
		FormulaEqual formula = new FormulaEqual(leftFormula, rightFormula);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are numerics and left
	 * <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothNumericsLeftDiffRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getValue()).thenReturn(1.5);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getValue()).thenReturn(0.5);
		
		FormulaEqual formula = new FormulaEqual(leftFormula, rightFormula);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are numerics and left =
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothNumericsLeftEqRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getValue()).thenReturn(1.5);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getValue()).thenReturn(1.5);
		
		FormulaEqual formula = new FormulaEqual(leftFormula, rightFormula);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are integer and numeric
	 * and left <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixIntNumLeftDiffRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getValue()).thenReturn(1.0);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getValue()).thenReturn(0.5);
		
		FormulaEqual formula = new FormulaEqual(leftFormula, rightFormula);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are integer and numeric
	 * and left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixIntNumLeftEqRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getValue()).thenReturn(1.0);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getValue()).thenReturn(1.0);
		
		FormulaEqual formula = new FormulaEqual(leftFormula, rightFormula);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are numeric and integer
	 * and left <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixNumIntLeftDiffRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getValue()).thenReturn(1.5);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getValue()).thenReturn(0.0);
		
		FormulaEqual formula = new FormulaEqual(leftFormula, rightFormula);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are numeric and integer
	 * and left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixNumIntLeftEqRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getValue()).thenReturn(1.0);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getValue()).thenReturn(1.0);
		
		FormulaEqual formula = new FormulaEqual(leftFormula, rightFormula);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are strings and left
	 * <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothStringsLeftDiffRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(leftFormula.getValue()).thenReturn("aab");
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(rightFormula.getValue()).thenReturn("aaa");
		
		FormulaEqual formula = new FormulaEqual(leftFormula, rightFormula);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are strings and left =
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothStringsLeftEqRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(leftFormula.getValue()).thenReturn("aaa");
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(rightFormula.getValue()).thenReturn("aaa");
		
		FormulaEqual formula = new FormulaEqual(leftFormula, rightFormula);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are dates and left
	 * <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDatesLeftDiffRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(leftFormula.getValue()).thenReturn(LocalDate.of(2015, 1, 1));
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(rightFormula.getValue()).thenReturn(LocalDate.of(2014, 1, 1));
		
		FormulaEqual formula = new FormulaEqual(leftFormula, rightFormula);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are dates and left =
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDatesLeftEqRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(leftFormula.getValue()).thenReturn(LocalDate.of(2015, 1, 1));
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(rightFormula.getValue()).thenReturn(LocalDate.of(2015, 1, 1));
		
		FormulaEqual formula = new FormulaEqual(leftFormula, rightFormula);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getValue()).thenReturn(1);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getValue()).thenReturn(0);
		
		FormulaEqual formula = new FormulaEqual(leftFormula, rightFormula);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaEqual#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getValue()).thenReturn(1);
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getValue()).thenReturn(0);
		
		FormulaEqual formula = new FormulaEqual(leftFormula, rightFormula);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaEqual#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaEqual formula = new FormulaEqual();
		
		assertEquals("=", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaEqual#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.asText()).thenReturn("some_rule1");
		
		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.asText()).thenReturn("some_rule2");
		
		FormulaEqual formula = new FormulaEqual(leftFormula, rightFormula);
		
		assertEquals("some_rule1 = some_rule2", formula.asText());
	}
	
}