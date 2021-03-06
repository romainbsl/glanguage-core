package be.groups.glanguage.core.entities.formula.implementations.binary;

import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaDivide}
 * 
 * @author DUPIREFR
 */
public class FormulaDivideTest {
	
	/*
	 * Constants
	 */
	private static final double DELTA = 1e-15;
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDivide#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaDivide formula = new FormulaDivide();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_DIVIDE), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaDivide#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDivide formula = new FormulaDivide();
		
		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaDivide#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(numerator.getValue(null)).thenReturn(1);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(denominator.getValue(null)).thenReturn(0);

		FormulaDivide formula = spy(FormulaDivide.class);
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaDivide#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(numerator.getNumericValue(null)).thenReturn(3.2);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(denominator.getNumericValue(null)).thenReturn(2.5);

		FormulaDivide formula = spy(FormulaDivide.class);
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		assertEquals(Double.valueOf(1.28), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaDivide#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(numerator.getValue(null)).thenReturn(1);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(denominator.getValue(null)).thenReturn(0);

		FormulaDivide formula = spy(FormulaDivide.class);
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaDivide#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(numerator.getValue(null)).thenReturn(1);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(denominator.getValue(null)).thenReturn(0);

		FormulaDivide formula = spy(FormulaDivide.class);
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaDivide#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(numerator.getValue(null)).thenReturn(1);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(denominator.getValue(null)).thenReturn(0);

		FormulaDivide formula = spy(FormulaDivide.class);
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaDivide#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(numerator.getValue(null)).thenReturn(1);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(denominator.getValue(null)).thenReturn(0);

		FormulaDivide formula = spy(FormulaDivide.class);
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaDivide#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaDivide formula = new FormulaDivide();
		
		assertEquals("/", formula.operationAsText());
	}

}
