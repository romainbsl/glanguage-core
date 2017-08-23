package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaRoundingFloor}
 * 
 * @author DUPIREFR
 */
public class FormulaRoundingFloorTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaRoundingFloor#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaRoundingFloor formula = new FormulaRoundingFloor();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_FLOOR), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaRoundingFloor formula = new FormulaRoundingFloor();
		
		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaRoundingFloor#getIntegerValue()} with integer parameter
	 */
	@Test
	public void testGetIntegerValueInt() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue(null)).thenReturn(117);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(precision.getNumericValue(null)).thenReturn(10.0);

		FormulaRoundingFloor formula = spy(FormulaRoundingFloor.class);
		doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

		assertEquals(Integer.valueOf(110), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getIntegerValue()} with numeric parameter
	 */
	@Test
	public void testGetIntegerValueNum() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.getNumericValue(null)).thenReturn(1.567);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(precision.getNumericValue(null)).thenReturn(0.01);

		FormulaRoundingFloor formula = spy(FormulaRoundingFloor.class);
		doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

		assertEquals(Integer.valueOf(1), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getNumericValue()} with integer parameter
	 */
	@Test
	public void testGetNumericValueInt() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue(null)).thenReturn(117);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(precision.getNumericValue(null)).thenReturn(10.0);

		FormulaRoundingFloor formula = spy(FormulaRoundingFloor.class);
		doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

		assertEquals(Double.valueOf(110), formula.getNumericValue(null));
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getNumericValue()} with numeric parameter
	 */
	@Test
	public void testGetNumericValueNum() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.getNumericValue(null)).thenReturn(1.567);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(precision.getNumericValue(null)).thenReturn(0.01);

		FormulaRoundingFloor formula = spy(FormulaRoundingFloor.class);
		doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

		assertEquals(Double.valueOf(1.56), formula.getNumericValue(null));
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.getNumericValue(null)).thenReturn(1.567);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(precision.getNumericValue(null)).thenReturn(0.01);

		FormulaRoundingFloor formula = spy(FormulaRoundingFloor.class);
		doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.getNumericValue(null)).thenReturn(1.567);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(precision.getNumericValue(null)).thenReturn(0.01);

		FormulaRoundingFloor formula = spy(FormulaRoundingFloor.class);
		doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.getNumericValue(null)).thenReturn(1.567);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(precision.getNumericValue(null)).thenReturn(0.01);

		FormulaRoundingFloor formula = spy(FormulaRoundingFloor.class);
		doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(parameter.getNumericValue(null)).thenReturn(1.567);
		
		AbstractFormula precision = mock(AbstractFormula.class);
		when(precision.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(precision.getNumericValue(null)).thenReturn(0.01);

		FormulaRoundingFloor formula = spy(FormulaRoundingFloor.class);
		doReturn(Arrays.asList(parameter, precision)).when(formula).getParameters();

		formula.getDurationValue(null);
	}
	
	/**
	 * Tests {@link FormulaRoundingFloor#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaRoundingFloor formula = new FormulaRoundingFloor();
		
		assertEquals("floor", formula.operationAsText());
	}

}
