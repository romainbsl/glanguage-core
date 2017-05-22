package be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

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
	 * Tests {@link FormulaExtremumMin#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaExtremumMin formula = new FormulaExtremumMin();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_MIN), formula.getDiscriminatorValue());
	}
	
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
	public void testGetIntegerValueIntegers() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getNumericValue(null)).thenReturn(-1.0);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getNumericValue(null)).thenReturn(3.0);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getNumericValue(null)).thenReturn(2.0);
		parameters.add(param3);
		
		FormulaExtremumMin formula = spy(FormulaExtremumMin.class);
		doReturn(parameters).when(formula).getParameters();
		
		assertEquals(Integer.valueOf(-1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaExtremumMin#getNumericValue()} when all parameters are numeric
	 */
	@Test
	public void testGetNumericValueNumerics() throws GLanguageException {
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

		FormulaExtremumMin formula = spy(FormulaExtremumMin.class);
		doReturn(parameters).when(formula).getParameters();
		
		assertEquals(Double.valueOf(-1.5), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaExtremumMin#getNumericValue()} when numeric and integer parameters mix is
	 * numeric
	 */
	@Test
	public void testGetNumericValueIntNum() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getNumericValue(null)).thenReturn(-1.0);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue(null)).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getNumericValue(null)).thenReturn(2.0);
		parameters.add(param3);

		FormulaExtremumMin formula = spy(FormulaExtremumMin.class);
		doReturn(parameters).when(formula).getParameters();
		
		assertEquals(Double.valueOf(-1), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaExtremumMin#getNumericValue()} when all parameters are integers
	 */
	@Test
	public void testGetNumericValueIntegers() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getNumericValue(null)).thenReturn(-1.0);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getNumericValue(null)).thenReturn(3.0);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getNumericValue(null)).thenReturn(2.0);
		parameters.add(param3);

		FormulaExtremumMin formula = spy(FormulaExtremumMin.class);
		doReturn(parameters).when(formula).getParameters();
		
		assertEquals(Double.valueOf(-1), formula.getNumericValue(), DELTA);
	}
	
	
	/**
	 * Tests {@link FormulaExtremumMin#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
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

		FormulaExtremumMin formula = spy(FormulaExtremumMin.class);
		doReturn(parameters).when(formula).getParameters();
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaExtremumMin#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
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

		FormulaExtremumMin formula = spy(FormulaExtremumMin.class);
		doReturn(parameters).when(formula).getParameters();
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaExtremumMin#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
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

		FormulaExtremumMin formula = spy(FormulaExtremumMin.class);
		doReturn(parameters).when(formula).getParameters();
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaExtremumMin#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
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

		FormulaExtremumMin formula = spy(FormulaExtremumMin.class);
		doReturn(parameters).when(formula).getParameters();
		
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
	public void testAsText() throws GLanguageException {
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

		FormulaExtremumMin formula = spy(FormulaExtremumMin.class);
		doReturn(parameters).when(formula).getParameters();
		
		assertEquals("min(some_rule1, some_rule2, some_rule3)", formula.asText());
	}
	
}
