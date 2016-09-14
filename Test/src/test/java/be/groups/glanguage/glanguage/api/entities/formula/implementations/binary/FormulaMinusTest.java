package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

/**
 * Test class for {@link FormulaMinus}
 * 
 * @author DUPIREFR
 */
public class FormulaMinusTest {
	
	/*
	 * Constants
	 */
	private static final double DELTA = 1e-15;

	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaMinus#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaMinus formula = new FormulaMinus();

		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaMinus#getIntegerValue()} when both parameters are
	 * integers
	 */
	@Test
	public void testGetIntegerValueIntInt() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getNumericValue()).thenReturn(1.0);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(2.0);

		FormulaMinus formula = new FormulaMinus(leftFormula, rightFormula);

		assertEquals(Integer.valueOf(-1), formula.getIntegerValue());
	}

	/**
	 * Tests {@link FormulaMinus#getNumericValue()} when both parameters are
	 * numerics
	 */
	@Test
	public void testGetNumericValueNumNum() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(1.5);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(2.3);

		FormulaMinus formula = new FormulaMinus(leftFormula, rightFormula);

		assertEquals(Double.valueOf(-0.8), formula.getNumericValue(), DELTA);
	}

	/**
	 * Tests {@link FormulaMinus#getNumericValue()} when first parameter is
	 * integer and second is numeric
	 */
	@Test
	public void testGetNumericValueIntNum() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getNumericValue()).thenReturn(1.0);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(rightFormula.getNumericValue()).thenReturn(2.3);

		FormulaMinus formula = new FormulaMinus(leftFormula, rightFormula);

		assertEquals(Double.valueOf(-1.3), formula.getNumericValue(), DELTA);
	}

	/**
	 * Tests {@link FormulaMinus#getNumericValue()} when first parameter is
	 * numeric and second is integer
	 */
	@Test
	public void testGetNumericValueNumInt() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(leftFormula.getNumericValue()).thenReturn(2.3);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(1.0);

		FormulaMinus formula = new FormulaMinus(leftFormula, rightFormula);

		assertEquals(Double.valueOf(1.3), formula.getNumericValue(), DELTA);
	}

	/**
	 * Tests {@link FormulaMinus#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getNumericValue()).thenReturn(2.0);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(1.0);

		FormulaMinus formula = new FormulaMinus(leftFormula, rightFormula);

		formula.getStringValue();
	}

	/**
	 * Tests {@link FormulaMinus#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getNumericValue()).thenReturn(2.0);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(1.0);

		FormulaMinus formula = new FormulaMinus(leftFormula, rightFormula);

		formula.getBooleanValue();
	}

	/**
	 * Tests {@link FormulaMinus#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getNumericValue()).thenReturn(2.0);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getNumericValue()).thenReturn(1.0);

		FormulaMinus formula = new FormulaMinus(leftFormula, rightFormula);

		formula.getDateValue();
	}

	/**
	 * Tests {@link FormulaMinus#getReturnType()} when both parameters are
	 * integers
	 */
	@Test
	public void testGetReturnTypeIntInt() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);

		FormulaMinus formula = new FormulaMinus(leftFormula, rightFormula);

		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaMinus#getReturnType()} when first parameter is integer
	 * and the second is numeric
	 */
	@Test
	public void testGetReturnTypeIntNum() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);

		FormulaMinus formula = new FormulaMinus(leftFormula, rightFormula);

		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaMinus#getReturnType()} when first parameter is numeric
	 * and the second is integer
	 */
	@Test
	public void testGetReturnTypeNumInt() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);

		FormulaMinus formula = new FormulaMinus(leftFormula, rightFormula);

		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaMinus#getReturnType()} when both parameters are
	 * numerics
	 */
	@Test
	public void testGetReturnTypeNumNum() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);

		FormulaMinus formula = new FormulaMinus(leftFormula, rightFormula);

		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaMinus#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaMinus formula = new FormulaMinus();

		assertEquals("-", formula.operationAsText());
	}

	/**
	 * Tests {@link FormulaMinus#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.asText()).thenReturn("some_rule1");

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.asText()).thenReturn("some_rule2");

		FormulaMinus formula = new FormulaMinus(leftFormula, rightFormula);

		assertEquals("some_rule1 - some_rule2", formula.asText());
	}

}
