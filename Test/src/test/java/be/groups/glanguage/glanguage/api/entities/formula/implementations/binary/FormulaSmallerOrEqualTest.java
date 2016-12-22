package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link FormulaSmallerOrEqual}
 * 
 * @author DUPIREFR
 */
public class FormulaSmallerOrEqualTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaSmallerOrEqual#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_SMALLER_OR_EQUAL), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#isValid()} when both operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothIntegers() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#isValid()} when first operand is integer and second
	 * numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstIntSecondNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#isValid()} when first operand is integer and second is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstIntSecondNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#isValid()} when second operand is integer and first is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondIntFirstNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#isValid()} when first operand is numeric and second
	 * integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNumSecondInt() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#isValid()} when first operand is numeric and second is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNumSecondNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#isValid()} when second operand is numeric and first is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondNumFirstNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#isValid()} when both operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothNumerics() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#isValid()} when both operands are strings
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothStrings() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#isValid()} when both operands are booleans
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothBooleans() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#isValid()} when both operands are dates
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothDates() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#isValid()} when first operand is a date and second is a
	 * duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstDateSecondDuration() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#isValid()} when first operand is a duration and second is
	 * a date
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstDurationSecondDate() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#isValid()} when both operands are durations
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothDurations() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getReturnType()} when both operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothIntegers() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getReturnType()} when first operand is integer and second
	 * numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstIntSecondNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getReturnType()} when first operand is numeric and second
	 * integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstNumSecondInt() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getReturnType()} when both operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothNumerics() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getReturnType()} when both operands are strings
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothStrings() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getReturnType()} when both operands are booleans
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothBooleans() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getReturnType()} when both operands are dates
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothDates() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getReturnType()} when both operands are durations
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothDurations() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER_OR_EQUAL), operand1, operand2);
				
		assertNull(formula.getReturnType());
	}
	
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getIntegerValue(null)).thenReturn(0);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getIntegerValue(null)).thenReturn(0);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getIntegerValue(null)).thenReturn(0);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are integers and
	 * left > right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothIntegersLeftSupRight() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getIntegerValue(null)).thenReturn(0);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are integers and
	 * left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothIntegersLeftEqRight() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getIntegerValue(null)).thenReturn(1);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are integers and
	 * left < right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothIntegersLeftInfRight() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getIntegerValue(null)).thenReturn(2);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are numerics and
	 * left > right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothNumericsLeftSupRight() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue(null)).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue(null)).thenReturn(0.5);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are numerics and
	 * left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothNumericsLeftEqRight() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue(null)).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue(null)).thenReturn(1.5);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are numerics and
	 * left < right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothNumericsLeftInfRight() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue(null)).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue(null)).thenReturn(2.5);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are integer and
	 * numeric and left > right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixIntNumLeftSupRight() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue(null)).thenReturn(0.5);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are integer and
	 * numeric and left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixIntNumLeftEqRight() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue(null)).thenReturn(1.0);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are integer and
	 * numeric and left < right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixIntNumLeftInfRight() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue(null)).thenReturn(2.5);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are numeric and
	 * integer and left > right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixNumIntLeftSupRight() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue(null)).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(0.0);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are numeric and
	 * integer and left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixNumIntLeftEqRight() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue(null)).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(1.0);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are numeric and
	 * integer and left < right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixNumIntLeftInfRight() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue(null)).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(2.0);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are strings and
	 * left > right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothStringsLeftSupRight() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand1.getStringValue(null)).thenReturn("aab");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand2.getStringValue(null)).thenReturn("aaa");
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are strings and
	 * left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothStringsLeftEqRight() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand1.getStringValue(null)).thenReturn("aaa");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand2.getStringValue(null)).thenReturn("aaa");
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are strings and
	 * left < right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothStringsLeftInfRight() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand1.getStringValue(null)).thenReturn("aaa");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand2.getStringValue(null)).thenReturn("aab");
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are dates and
	 * left > right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDatesLeftSupRight() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand1.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand2.getDateValue(null)).thenReturn(LocalDate.of(2014, 1, 1));
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are dates and
	 * left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDatesLeftEqRight() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand1.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand2.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 1));
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getBooleanValue()} when parameters exist, are dates and
	 * left < right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDatesLeftInfRight() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand1.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand2.getDateValue(null)).thenReturn(LocalDate.of(2016, 1, 1));
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getIntegerValue(null)).thenReturn(0);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() throws GLanguageEvaluationException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(0);
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#operationAsText()}
	 */
	@Test
	public void testOperationAsText() throws GLanguageEvaluationException {
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual();
		
		assertEquals("<=", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaSmallerOrEqual#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.asText()).thenReturn("some_rule1");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.asText()).thenReturn("some_rule2");
		
		FormulaSmallerOrEqual formula = new FormulaSmallerOrEqual(null, operand1, operand2);
		
		assertEquals("some_rule1 <= some_rule2", formula.asText());
	}
	
}
