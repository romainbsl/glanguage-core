package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Test;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

/**
 * Test class for {@link FormulaGreater}
 * 
 * @author DUPIREFR
 */
public class FormulaGreaterTest {

	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaGreater#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaGreater formula = new FormulaGreater();

		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when parameters exist, are
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when parameters exist, are
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when parameters exist, are
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when parameters exist, are
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when parameters exist, are
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when parameters exist, are
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when parameters exist, are
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when parameters exist, are
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when parameters exist, are
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when parameters exist, are
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when parameters exist, are
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when parameters exist, are
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when parameters exist, are
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when parameters exist, are
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when parameters exist, are
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when parameters exist, are
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when parameters exist, are
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when parameters exist, are
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}

	/**
	 * Tests {@link FormulaGreater#getBooleanValue()} when at least one parameter
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

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		formula.getBooleanValue();
	}

	/**
	 * Tests {@link FormulaGreater#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(1);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getIntegerValue()).thenReturn(0);

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		formula.getIntegerValue();
	}

	/**
	 * Tests {@link FormulaGreater#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(1);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getIntegerValue()).thenReturn(0);

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		formula.getNumericValue();
	}

	/**
	 * Tests {@link FormulaGreater#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(1);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getIntegerValue()).thenReturn(0);

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		formula.getStringValue();
	}

	/**
	 * Tests {@link FormulaGreater#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(1);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getIntegerValue()).thenReturn(0);

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		formula.getDateValue();
	}

	/**
	 * Tests {@link FormulaGreater#getReturnType()}
	 */
	@Test
	public void testGetReturnType() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.getIntegerValue()).thenReturn(1);

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.getIntegerValue()).thenReturn(0);

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaGreater#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaGreater formula = new FormulaGreater();

		assertEquals(">", formula.operationAsText());
	}

	/**
	 * Tests {@link FormulaGreater#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula leftFormula = mock(AbstractFormula.class);
		when(leftFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(leftFormula.asText()).thenReturn("some_rule1");

		AbstractFormula rightFormula = mock(AbstractFormula.class);
		when(rightFormula.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(rightFormula.asText()).thenReturn("some_rule2");

		FormulaGreater formula = new FormulaGreater(leftFormula, rightFormula);

		assertEquals("some_rule1 > some_rule2", formula.asText());
	}

}
