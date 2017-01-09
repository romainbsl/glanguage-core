package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.time.Duration;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link FormulaEqual}
 * 
 * @author DUPIREFR
 */
public class FormulaEqualTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaEqual#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaEqual formula = new FormulaEqual();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_EQUAL), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaEqual formula = new FormulaEqual();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaEqual#isValid()} when both operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothIntegers() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaEqual#isValid()} when first operand is integer and second numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstIntSecondNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaEqual#isValid()} when first operand is integer and second is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstIntSecondNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaEqual#isValid()} when second operand is integer and first is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondIntFirstNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaEqual#isValid()} when first operand is numeric and second integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNumSecondInt() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaEqual#isValid()} when first operand is numeric and second is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNumSecondNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaEqual#isValid()} when second operand is numeric and first is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondNumFirstNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaEqual#isValid()} when both operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothNumerics() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaEqual#isValid()} when both operands are strings
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothStrings() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaEqual#isValid()} when both operands are booleans
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothBooleans() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaEqual#isValid()} when both operands are dates
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothDates() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaEqual#isValid()} when first operand is a date and second is a duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstDateSecondDuration() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaEqual#isValid()} when first operand is a duration and second is a date
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstDurationSecondDate() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaEqual#isValid()} when both operands are durations
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothDurations() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaEqual#getReturnType()} when both operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothIntegers() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaEqual#getReturnType()} when first operand is integer and second numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstIntSecondNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaEqual#getReturnType()} when first operand is numeric and second integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstNumSecondInt() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaEqual#getReturnType()} when both operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothNumerics() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaEqual#getReturnType()} when both operands are strings
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothStrings() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaEqual#getReturnType()} when both operands are booleans
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothBooleans() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaEqual#getReturnType()} when both operands are dates
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothDates() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaEqual#getReturnType()} when both operands are durations
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothDurations() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		FormulaEqual formula = new FormulaEqual(FormulaDescriptionFactory.getDescription(FormulaType.OP_EQUAL), operand1, operand2);
		
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaEqual#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(0);
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaEqual#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(0);
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaEqual#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(0);
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are integers and left
	 * <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothIntegersLeftDiffRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(0);
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are integers and left =
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothIntegersLeftEqRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(1);
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are numerics and left
	 * <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothNumericsLeftDiffRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getValue(null)).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getValue(null)).thenReturn(0.5);
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are numerics and left =
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothNumericsLeftEqRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getValue(null)).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getValue(null)).thenReturn(1.5);
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are integer and numeric
	 * and left <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixIntNumLeftDiffRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getValue(null)).thenReturn(0.5);
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are integer and numeric
	 * and left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixIntNumLeftEqRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getValue(null)).thenReturn(1.0);
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are numeric and integer
	 * and left <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixNumIntLeftDiffRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getValue(null)).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(0.0);
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are numeric and integer
	 * and left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixNumIntLeftEqRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getValue(null)).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(1.0);
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are strings and left
	 * <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothStringsLeftDiffRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand1.getValue(null)).thenReturn("aab");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand2.getValue(null)).thenReturn("aaa");
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are strings and left =
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothStringsLeftEqRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand1.getValue(null)).thenReturn("aaa");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand2.getValue(null)).thenReturn("aaa");
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are dates and left
	 * <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDatesLeftDiffRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand1.getValue(null)).thenReturn(LocalDate.of(2015, 1, 1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand2.getValue(null)).thenReturn(LocalDate.of(2014, 1, 1));
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are dates and left =
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDatesLeftEqRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand1.getValue(null)).thenReturn(LocalDate.of(2015, 1, 1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand2.getValue(null)).thenReturn(LocalDate.of(2015, 1, 1));
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are durations and left
	 * <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDurationsLeftDiffRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(operand1.getValue(null)).thenReturn(Duration.ofDays(2L));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(operand2.getValue(null)).thenReturn(Duration.ofDays(3L));
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getBooleanValue()} when parameters exist, are durations and left =
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDurationsLeftEqRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(operand1.getValue(null)).thenReturn(Duration.ofDays(2L));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(operand2.getValue(null)).thenReturn(Duration.ofDays(2L));
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaEqual#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(0);
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaEqual#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(0);
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaEqual#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaEqual formula = new FormulaEqual();
		
		assertEquals("=", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaEqual#asText()}
	 */
	@Test
	public void testAsText() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.asText()).thenReturn("some_rule1");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.asText()).thenReturn("some_rule2");
		
		FormulaEqual formula = new FormulaEqual(null, operand1, operand2);
		
		assertEquals("some_rule1 = some_rule2", formula.asText());
	}
	
}
