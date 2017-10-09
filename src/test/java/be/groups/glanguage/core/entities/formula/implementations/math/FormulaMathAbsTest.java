package be.groups.glanguage.core.entities.formula.implementations.math;

import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaMathAbs}
 * 
 * @author DUPIREFR
 */
public class FormulaMathAbsTest {
	
	/*
	 * Tests
	 */

	/**
	 * Tests {@link FormulaMathAbs#getIntegerValue()} when operand exists and is positive
	 */
	@Test
	public void testGetIntegerValueParameterExistsPositive() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getNumericValue(null)).thenReturn(1.0);

		FormulaMathAbs formula = Mockito.spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(Integer.valueOf(1), formula.getIntegerValue(null));
	}

	/**
	 * Tests {@link FormulaMathAbs#getIntegerValue()} when operand exists and is negative
	 */
	@Test
	public void testGetIntegerValueParameterExistsNegative() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getNumericValue(null)).thenReturn(-1.0);

		FormulaMathAbs formula = Mockito.spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(Integer.valueOf(1), formula.getIntegerValue(null));
	}

	/**
	 * Tests {@link FormulaMathAbs#getIntegerValue()} when operand doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetIntegerValueParameterNotExists() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getNumericValue(null)).thenReturn(null);

		FormulaMathAbs formula = Mockito.spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getIntegerValue(null);
	}

	/**
	 * Tests {@link FormulaMathAbs#getNumericValue()} when operand exists and is positive
	 */
	@Test
	public void testGetNumericValueParameterExistsPositive() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue(null)).thenReturn(1.5);

		FormulaMathAbs formula = Mockito.spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(Double.valueOf(1.5), formula.getNumericValue(null));
	}

	/**
	 * Tests {@link FormulaMathAbs#getNumericValue()} when operand exists and is negative
	 */
	@Test
	public void testGetNumericValueParameterExistsNegative() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue(null)).thenReturn(-1.5);

		FormulaMathAbs formula = Mockito.spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(Double.valueOf(1.5), formula.getNumericValue(null));
	}

	/**
	 * Tests {@link FormulaMathAbs#getNumericValue()} when operand doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetNumericValueParameterNotExists() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue(null)).thenReturn(null);

		FormulaMathAbs formula = Mockito.spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getNumericValue(null);
	}

	/**
	 * Tests {@link FormulaMathAbs#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);

		FormulaMathAbs formula = Mockito.spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getStringValue(null);
	}

	/**
	 * Tests {@link FormulaMathAbs#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);

		FormulaMathAbs formula = Mockito.spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getBooleanValue(null);
	}

	/**
	 * Tests {@link FormulaMathAbs#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);

		FormulaMathAbs formula = Mockito.spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getDateValue(null);
	}

	/**
	 * Tests {@link FormulaMathAbs#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);

		FormulaMathAbs formula = Mockito.spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getDurationValue(null);
	}

	/**
	 * Tests {@link FormulaMathAbs#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaMathAbs formula = new FormulaMathAbs();

		assertEquals("abs", formula.operationAsText());
	}

}
