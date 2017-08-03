package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

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
 * Test class for {@link FormulaModulo}
 * 
 * @author DUPIREFR
 */
public class FormulaModuloTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaModulo#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaModulo formula = new FormulaModulo();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_MODULO), formula.getDiscriminatorValue());
	}
	
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
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(3);

		FormulaModulo formula = spy(FormulaModulo.class);
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		assertEquals(Integer.valueOf(2), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaModulo#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);

		FormulaModulo formula = spy(FormulaModulo.class);
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaModulo#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);

		FormulaModulo formula = spy(FormulaModulo.class);
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaModulo#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);

		FormulaModulo formula = spy(FormulaModulo.class);
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaModulo#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);

		FormulaModulo formula = spy(FormulaModulo.class);
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaModulo#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);

		FormulaModulo formula = spy(FormulaModulo.class);
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaModulo#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaModulo formula = new FormulaModulo();
		
		assertEquals("\\", formula.operationAsText());
	}

}
