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
 * Test class for {@link FormulaIntegerDivision}
 * 
 * @author DUPIREFR
 */
public class FormulaIntegerDivisionTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaIntegerDivision#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaIntegerDivision formula = new FormulaIntegerDivision();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_INTEGER_DIVISION), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaIntegerDivision formula = new FormulaIntegerDivision();
		
		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaIntegerDivision#getNumericValue()}
	 */
	@Test
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);

		FormulaIntegerDivision formula = spy(FormulaIntegerDivision.class);
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);

		FormulaIntegerDivision formula = spy(FormulaIntegerDivision.class);
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);

		FormulaIntegerDivision formula = spy(FormulaIntegerDivision.class);
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);

		FormulaIntegerDivision formula = spy(FormulaIntegerDivision.class);
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);

		FormulaIntegerDivision formula = spy(FormulaIntegerDivision.class);
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);

		FormulaIntegerDivision formula = spy(FormulaIntegerDivision.class);
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaIntegerDivision formula = new FormulaIntegerDivision();
		
		assertEquals("/", formula.operationAsText());
	}

}
