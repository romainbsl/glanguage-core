package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

/**
 * Test class for {@link FormulaSmallerOrEqual}
 * 
 * @author DUPIREFR
 */
public class FormulaSmallerOrEqualTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaSmallerOrEqual#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual();

		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are
	 * integers and left > right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothIntegersLeftSupRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(1);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getIntegerValue()).thenReturn(0);

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are
	 * integers and left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothIntegersLeftEqRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(1);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getIntegerValue()).thenReturn(1);

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are
	 * integers and left < right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothIntegersLeftInfRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(1);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getIntegerValue()).thenReturn(2);

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are
	 * numerics and left > right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothNumericsLeftSupRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.5);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(0.5);

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are
	 * numerics and left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothNumericsLeftEqRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.5);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(1.5);

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are
	 * numerics and left < right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothNumericsLeftInfRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.5);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(2.5);

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are
	 * integer and numeric and left > right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixIntNumLeftSupRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getNumericValue()).thenReturn(1.0);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(0.5);

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are
	 * integer and numeric and left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixIntNumLeftEqRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getNumericValue()).thenReturn(1.0);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(1.0);

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are
	 * integer and numeric and left < right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixIntNumLeftInfRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getNumericValue()).thenReturn(1.0);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(2.5);

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are
	 * numeric and integer and left > right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixNumIntLeftSupRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.5);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(0.0);

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are
	 * numeric and integer and left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixNumIntLeftEqRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.0);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(1.0);

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are
	 * numeric and integer and left < right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixNumIntLeftInfRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.5);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(2.0);

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are
	 * strings and left > right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothStringsLeftSupRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(leftFormula.getStringValue()).thenReturn("aab");

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(rightFormula.getStringValue()).thenReturn("aaa");

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are
	 * strings and left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothStringsLeftEqRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(leftFormula.getStringValue()).thenReturn("aaa");

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(rightFormula.getStringValue()).thenReturn("aaa");

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are
	 * strings and left < right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothStringsLeftInfRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(leftFormula.getStringValue()).thenReturn("aaa");

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(rightFormula.getStringValue()).thenReturn("aab");

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are
	 * dates and left > right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDatesLeftSupRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(leftFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 1));

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(rightFormula.getDateValue()).thenReturn(LocalDate.of(2014, 1, 1));

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are
	 * dates and left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDatesLeftEqRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(leftFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 1));

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(rightFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 1));

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are
	 * dates and left < right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDatesLeftInfRight() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(leftFormula.getDateValue()).thenReturn(LocalDate.of(2015, 1, 1));

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(rightFormula.getDateValue()).thenReturn(LocalDate.of(2016, 1, 1));

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when at least one parameter
	 * has wrong type
	 */
	@Test(expected = IllegalStateException.class)
	public void testGetBooleanValueParametersWrongType() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(1);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		when(rightFormula.getBooleanValue()).thenReturn(true);

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		formula.getBooleanValue();
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(1);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getIntegerValue()).thenReturn(0);

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		formula.getIntegerValue();
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(1);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getIntegerValue()).thenReturn(0);

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		formula.getNumericValue();
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(1);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getIntegerValue()).thenReturn(0);

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		formula.getStringValue();
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(1);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getIntegerValue()).thenReturn(0);

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		formula.getDateValue();
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual();

		assertEquals("<=", formula.operationAsText());
	}

	/**
	 * Tests {@link FormulaSmallerOrEqual#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.asText()).thenReturn("some_rule1");

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.asText()).thenReturn("some_rule2");

		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(leftFormula, rightFormula);

		assertEquals("some_rule1 <= some_rule2", formula.asText());
	}

}
