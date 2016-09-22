package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;

/**
 * Test class for {@link FormulaSmaller}
 * 
 * @author DUPIREFR
 */
public class FormulaSmallerTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaSmaller#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaSmaller formula = new FormulaSmaller();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_SMALLER), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaSmaller formula = new FormulaSmaller();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaSmaller#isValid()} when both operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothIntegers() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmaller#isValid()} when first operand is integer and second numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstIntSecondNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmaller#isValid()} when first operand is integer and second is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstIntSecondNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmaller#isValid()} when second operand is integer and first is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondIntFirstNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmaller#isValid()} when first operand is numeric and second integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNumSecondInt() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmaller#isValid()} when first operand is numeric and second is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNumSecondNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmaller#isValid()} when second operand is numeric and first is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondNumFirstNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmaller#isValid()} when both operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothNumerics() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmaller#isValid()} when both operands are strings
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothStrings() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmaller#isValid()} when both operands are booleans
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothBooleans() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmaller#isValid()} when both operands are dates
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothDates() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmaller#isValid()} when first operand is a date and second is a duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstDateSecondDuration() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmaller#isValid()} when first operand is a duration and second is a date
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstDurationSecondDate() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmaller#isValid()} when both operands are durations
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothDurations() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getReturnType()} when both operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothIntegers() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getReturnType()} when first operand is integer and second numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstIntSecondNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getReturnType()} when first operand is numeric and second integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstNumSecondInt() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getReturnType()} when both operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothNumerics() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getReturnType()} when both operands are strings
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothStrings() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getReturnType()} when both operands are booleans
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothBooleans() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getReturnType()} when both operands are dates
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothDates() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getReturnType()} when both operands are durations
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothDurations() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaSmaller formula =
				new FormulaSmaller(FormulaDescriptionFactory.getDescription(FormulaType.OP_SMALLER), operand1, operand2);
				
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getIntegerValue()).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getIntegerValue()).thenReturn(0);
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaSmaller#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getIntegerValue()).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getIntegerValue()).thenReturn(0);
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaSmaller#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getIntegerValue()).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getIntegerValue()).thenReturn(0);
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaSmaller#getBooleanValue()} when parameters exist, are integers and left >
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothIntegersLeftSupRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getIntegerValue()).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getIntegerValue()).thenReturn(0);
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getBooleanValue()} when parameters exist, are integers and left =
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothIntegersLeftEqRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getIntegerValue()).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getIntegerValue()).thenReturn(1);
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getBooleanValue()} when parameters exist, are integers and left <
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothIntegersLeftInfRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getIntegerValue()).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getIntegerValue()).thenReturn(2);
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getBooleanValue()} when parameters exist, are numerics and left >
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothNumericsLeftSupRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue()).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue()).thenReturn(0.5);
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getBooleanValue()} when parameters exist, are numerics and left =
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothNumericsLeftEqRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue()).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue()).thenReturn(1.5);
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getBooleanValue()} when parameters exist, are numerics and left <
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothNumericsLeftInfRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue()).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue()).thenReturn(2.5);
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getBooleanValue()} when parameters exist, are integer and numeric
	 * and left > right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixIntNumLeftSupRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue()).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue()).thenReturn(0.5);
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getBooleanValue()} when parameters exist, are integer and numeric
	 * and left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixIntNumLeftEqRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue()).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue()).thenReturn(1.0);
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getBooleanValue()} when parameters exist, are integer and numeric
	 * and left < right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixIntNumLeftInfRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue()).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue()).thenReturn(2.5);
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getBooleanValue()} when parameters exist, are numeric and integer
	 * and left > right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixNumIntLeftSupRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue()).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue()).thenReturn(0.0);
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getBooleanValue()} when parameters exist, are numeric and integer
	 * and left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixNumIntLeftEqRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue()).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue()).thenReturn(1.0);
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getBooleanValue()} when parameters exist, are numeric and integer
	 * and left < right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixNumIntLeftInfRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue()).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue()).thenReturn(2.0);
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getBooleanValue()} when parameters exist, are strings and left >
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothStringsLeftSupRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(operand1.getStringValue()).thenReturn("aab");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(operand2.getStringValue()).thenReturn("aaa");
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getBooleanValue()} when parameters exist, are strings and left =
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothStringsLeftEqRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(operand1.getStringValue()).thenReturn("aaa");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(operand2.getStringValue()).thenReturn("aaa");
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getBooleanValue()} when parameters exist, are strings and left <
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothStringsLeftInfRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(operand1.getStringValue()).thenReturn("aaa");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(operand2.getStringValue()).thenReturn("aab");
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getBooleanValue()} when parameters exist, are dates and left >
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDatesLeftSupRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(operand1.getDateValue()).thenReturn(LocalDate.of(2015, 1, 1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(operand2.getDateValue()).thenReturn(LocalDate.of(2014, 1, 1));
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getBooleanValue()} when parameters exist, are dates and left =
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDatesLeftEqRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(operand1.getDateValue()).thenReturn(LocalDate.of(2015, 1, 1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(operand2.getDateValue()).thenReturn(LocalDate.of(2015, 1, 1));
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getBooleanValue()} when parameters exist, are dates and left <
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDatesLeftInfRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(operand1.getDateValue()).thenReturn(LocalDate.of(2015, 1, 1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(operand2.getDateValue()).thenReturn(LocalDate.of(2016, 1, 1));
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaSmaller#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getIntegerValue()).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getIntegerValue()).thenReturn(0);
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaSmaller#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue()).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue()).thenReturn(0);
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaSmaller#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaSmaller formula = new FormulaSmaller();
		
		assertEquals("<", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaSmaller#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.asText()).thenReturn("some_rule1");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.asText()).thenReturn("some_rule2");
		
		FormulaSmaller formula = new FormulaSmaller(null, operand1, operand2);
		
		assertEquals("some_rule1 < some_rule2", formula.asText());
	}
	
}
