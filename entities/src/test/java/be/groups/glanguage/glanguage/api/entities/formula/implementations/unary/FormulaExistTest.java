package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaExist}
 * 
 * @author DUPIREFR
 */
public class FormulaExistTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaExist#getDiscriminatorValue()}
	 */

	/**
	 * Tests {@link FormulaExist#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand.getValue(null)).thenReturn("some_value");

		FormulaExist formula = spy(FormulaExist.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getIntegerValue();
	}

	/**
	 * Tests {@link FormulaExist#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand.getValue(null)).thenReturn("some_value");

		FormulaExist formula = spy(FormulaExist.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getNumericValue();
	}

	/**
	 * Tests {@link FormulaExist#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand.getValue(null)).thenReturn("some_value");

		FormulaExist formula = spy(FormulaExist.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getStringValue();
	}

	/**
	 * Tests {@link FormulaExist#getBooleanValue()} when parameter exists
	 */
	@Test
	public void testGetBooleanValueParameterExists() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand.getValue(null)).thenReturn("some_value");

		FormulaExist formula = spy(FormulaExist.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaExist#getBooleanValue()} when parameter doesn't exist
	 */
	@Test
	public void testGetBooleanValueParameterNotExist() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand.getValue(null)).thenReturn(null);

		FormulaExist formula = spy(FormulaExist.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaExist#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand.getValue(null)).thenReturn("some_value");

		FormulaExist formula = spy(FormulaExist.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getDateValue();
	}

	/**
	 * Tests {@link FormulaExist#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand.getValue(null)).thenReturn("some_value");

		FormulaExist formula = spy(FormulaExist.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getDurationValue();
	}

	/**
	 * Tests {@link FormulaExist#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaExist formula = new FormulaExist();

		assertEquals("?", formula.operationAsText());
	}

}
