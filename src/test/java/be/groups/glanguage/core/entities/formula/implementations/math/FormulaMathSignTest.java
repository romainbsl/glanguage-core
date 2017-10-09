package be.groups.glanguage.core.entities.formula.implementations.math;

import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
	 * Tests {@link FormulaMathSign#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaMathSign formula = new FormulaMathSign();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_SIGN), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaMathSign#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaMathSign formula = new FormulaMathSign();
		
		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaMathSign#getIntegerValue()} when operand exists and is positive
	 */
	@Test
	public void testGetIntegerValueParameterExistsPositive() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getNumericValue(null)).thenReturn(1.0);

		FormulaMathSign formula = Mockito.spy(FormulaMathSign.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertTrue(formula.getIntegerValue(null) >= 0);
	}
	
	/**
	 * Tests {@link FormulaMathSign#getIntegerValue()} when operand exists and is negative
	 */
	@Test
	public void testGetIntegerValueParameterExistsNegative() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getNumericValue(null)).thenReturn(-1.0);

		FormulaMathSign formula = Mockito.spy(FormulaMathSign.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertTrue(formula.getIntegerValue(null) <= 0);
	}
	
	/**
	 * Tests {@link FormulaMathSign#getIntegerValue()} when operand doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetIntegerValueParameterNotExists() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getNumericValue(null)).thenReturn(null);

		FormulaMathSign formula = Mockito.spy(FormulaMathSign.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getIntegerValue(null);
	}
	
	/**
	 * Tests {@link FormulaMathSign#getNumericValue()} when operand exists and is positive
	 */
	@Test
	public void testGetNumericValueParameterExistsPositive() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue(null)).thenReturn(1.5);

		FormulaMathSign formula = Mockito.spy(FormulaMathSign.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertTrue(formula.getNumericValue(null) >= 0);
	}
	
	/**
	 * Tests {@link FormulaMathSign#getNumericValue()} when operand exists and is negative
	 */
	@Test
	public void testGetNumericValueParameterExistsNegative() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue(null)).thenReturn(-1.5);

		FormulaMathSign formula = Mockito.spy(FormulaMathSign.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertTrue(formula.getNumericValue(null) <= 0);
	}
	
	/**
	 * Tests {@link FormulaMathSign#getNumericValue()} when operand doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetNumericValueParameterNotExists() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue(null)).thenReturn(null);

		FormulaMathSign formula = Mockito.spy(FormulaMathSign.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaMathSign#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);

		FormulaMathSign formula = Mockito.spy(FormulaMathSign.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaMathSign#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);

		FormulaMathSign formula = Mockito.spy(FormulaMathSign.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaMathSign#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);

		FormulaMathSign formula = Mockito.spy(FormulaMathSign.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaMathSign#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);

		FormulaMathSign formula = Mockito.spy(FormulaMathSign.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getDurationValue(null);
	}
	
	/**
	 * Tests {@link FormulaMathSign#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaMathSign formula = new FormulaMathSign();
		
		assertEquals("sign", formula.operationAsText());
	}

}
