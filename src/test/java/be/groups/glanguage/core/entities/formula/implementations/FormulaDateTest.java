package be.groups.glanguage.core.entities.formula.implementations;

import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaDate}
 * 
 * @author DUPIREFR
 */
public class FormulaDateTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDate#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaDate formula = new FormulaDate();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_DATE), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaDate#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDate formula = new FormulaDate();
		
		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaDate#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(parameter.getStringValue(null)).thenReturn("11/09/1992");

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getIntegerValue(null);
	}
	
	/**
	 * Tests {@link FormulaDate#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(parameter.getStringValue(null)).thenReturn("11/09/1992");

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaDate#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(parameter.getStringValue(null)).thenReturn("11/09/1992");

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaDate#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(parameter.getStringValue(null)).thenReturn("11/09/1992");

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaDate#getDateValue()} with string parameter
	 */
	@Test
	public void testGetDateValueWithStringParam() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(parameter.getStringValue(null)).thenReturn("11/09/1992");

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(LocalDate.of(1992, 9, 11), formula.getDateValue(null));
	}
	
	/**
	 * Tests {@link FormulaDate#getDateValue()} with integers parameters
	 */
	@Test
	public void testGetDateValueWithIntParams() throws GLanguageException {
		AbstractFormula dayParam = mock(AbstractFormula.class);
		when(dayParam.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(dayParam.getIntegerValue(null)).thenReturn(11);
		
		AbstractFormula monthParam = mock(AbstractFormula.class);
		when(monthParam.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(monthParam.getIntegerValue(null)).thenReturn(9);
		
		AbstractFormula yearParam = mock(AbstractFormula.class);
		when(yearParam.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(yearParam.getIntegerValue(null)).thenReturn(1992);

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(Arrays.asList(dayParam, monthParam, yearParam)).when(formula).getParameters();

		assertEquals(LocalDate.of(1992, 9, 11), formula.getDateValue(null));
	}
	
	/**
	 * Tests {@link FormulaDate#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(parameter.getStringValue(null)).thenReturn("11/09/1992");

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getDurationValue(null);
	}

}
