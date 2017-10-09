package be.groups.glanguage.core.entities.formula.implementations.string;

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
 * Test class for {@link FormulaStringLength}
 * 
 * @author DUPIREFR
 */
public class FormulaStringLengthTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaStringLength#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaStringLength formula = new FormulaStringLength();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_STRING_LENGTH), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaStringLength#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaStringLength formula = new FormulaStringLength();
		
		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaStringLength#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a string");

		FormulaStringLength formula = spy(FormulaStringLength.class);
		doReturn(Arrays.asList(string)).when(formula).getParameters();

		assertEquals(Integer.valueOf(8), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaStringLength#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a string");

		FormulaStringLength formula = spy(FormulaStringLength.class);
		doReturn(Arrays.asList(string)).when(formula).getParameters();

		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaStringLength#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a string");

		FormulaStringLength formula = spy(FormulaStringLength.class);
		doReturn(Arrays.asList(string)).when(formula).getParameters();

		assertEquals("8", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaStringLength#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a string");

		FormulaStringLength formula = spy(FormulaStringLength.class);
		doReturn(Arrays.asList(string)).when(formula).getParameters();

		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaStringLength#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a string");

		FormulaStringLength formula = spy(FormulaStringLength.class);
		doReturn(Arrays.asList(string)).when(formula).getParameters();

		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaStringLength#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.getStringValue(null)).thenReturn("a string");

		FormulaStringLength formula = spy(FormulaStringLength.class);
		doReturn(Arrays.asList(string)).when(formula).getParameters();

		formula.getDurationValue(null);
	}

}
