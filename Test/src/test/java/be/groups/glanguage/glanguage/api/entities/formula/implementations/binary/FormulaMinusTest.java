package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
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
 * Test class for {@link FormulaMinus}
 * 
 * @author DUPIREFR
 */
public class FormulaMinusTest extends BaseDatabaseTest {
	
	/*
	 * Constants
	 */
	private static final double DELTA = 1e-15;
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaMinus#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaMinus formula = new FormulaMinus();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_MINUS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaMinus#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaMinus formula = new FormulaMinus();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid()} when both operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothIntegers() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid()} when first operand is integer and second numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstIntSecondNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid()} when first operand is integer and second is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstIntSecondNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid()} when second operand is integer and first is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondIntFirstNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid()} when first operand is numeric and second integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNumSecondInt() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid()} when first operand is numeric and second is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNumSecondNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid()} when second operand is numeric and first is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondNumFirstNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid()} when both operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothNumerics() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid()} when first operand is a date and second is a duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstDateSecondDuration() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid()} when both operands are durations
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothDurations() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid()} when first operand is a duration and second is a date
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstDurationSecondDate() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid()} when both operands are dates
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothDate() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertFalse(formula.isValid());
	}
		
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when both operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothIntegers() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when first operand is integer and second numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstIntSecondNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when first operand is integer and second is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstIntSecondNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when second operand is integer and first is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeSecondIntFirstNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when first operand is numeric and second integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstNumSecondInt() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when first operand is numeric and second is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstNumSecondNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when second operand is numeric and first is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeSecondNumFirstNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when both operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothNumerics() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when both operands are durations
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothDurations() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertEquals(FormulaReturnType.DURATION, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when first operand is a date and second is a duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstDateSecondDuration() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertEquals(FormulaReturnType.DATE, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when first operand is a duration and second is a date
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstDurationSecondDate() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaMinus formula = new FormulaMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS), operand1, operand2);
		
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getIntegerValue()} when both parameters are integers
	 */
	@Test
	public void testGetIntegerValueIntInt() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getIntegerValue()).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getIntegerValue()).thenReturn(2);
		
		FormulaMinus formula = new FormulaMinus(null, operand1, operand2);
		
		assertEquals(Integer.valueOf(-1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaMinus#getNumericValue()} when both parameters are numerics
	 */
	@Test
	public void testGetNumericValueNumNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue()).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue()).thenReturn(2.3);
		
		FormulaMinus formula = new FormulaMinus(null, operand1, operand2);
		
		assertEquals(Double.valueOf(-0.8), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaMinus#getNumericValue()} when first parameter is integer and second is
	 * numeric
	 */
	@Test
	public void testGetNumericValueIntNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue()).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue()).thenReturn(2.3);
		
		FormulaMinus formula = new FormulaMinus(null, operand1, operand2);
		
		assertEquals(Double.valueOf(-1.3), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaMinus#getNumericValue()} when first parameter is numeric and second is
	 * integer
	 */
	@Test
	public void testGetNumericValueNumInt() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue()).thenReturn(2.3);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue()).thenReturn(1.0);
		
		FormulaMinus formula = new FormulaMinus(null, operand1, operand2);
		
		assertEquals(Double.valueOf(1.3), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaMinus#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue()).thenReturn(2.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue()).thenReturn(1.0);
		
		FormulaMinus formula = new FormulaMinus(null, operand1, operand2);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaMinus#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue()).thenReturn(2.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue()).thenReturn(1.0);
		
		FormulaMinus formula = new FormulaMinus(null, operand1, operand2);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaMinus#getDateValue()}
	 */
	@Test(expected = NullPointerException.class)
	public void testGetDateValueWrongTypes() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue()).thenReturn(2.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue()).thenReturn(1.0);
		
		FormulaMinus formula = new FormulaMinus(null, operand1, operand2);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaMinus#getDateValue()} when first operand is a date and second operand is a duration
	 */
	@Test
	public void testGetDateValueFirstDateSecondDuration() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(operand1.getDateValue()).thenReturn(LocalDate.of(2016, 1, 1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(operand2.getDurationValue()).thenReturn(Duration.ofDays(1));
		
		FormulaMinus formula = new FormulaMinus(null, operand1, operand2);
		
		assertEquals(LocalDate.of(2015, 12, 31), formula.getDateValue());
	}
	
	/**
	 * Tests {@link FormulaMinus#getDurationValue()}
	 */
	@Test(expected = NullPointerException.class)
	public void testGetDurationValueWrongTypes() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue()).thenReturn(2.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue()).thenReturn(1.0);
		
		FormulaMinus formula = new FormulaMinus(null, operand1, operand2);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaMinus#getDateValue()} when both operands are durations
	 */
	@Test
	public void testGetDurationValueBothDuration() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(operand1.getDurationValue()).thenReturn(Duration.ofDays(2));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(operand2.getDurationValue()).thenReturn(Duration.ofDays(1));
		
		FormulaMinus formula = new FormulaMinus(null, operand1, operand2);
		
		assertEquals(Duration.ofDays(1), formula.getDurationValue());
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
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.asText()).thenReturn("some_rule1");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.asText()).thenReturn("some_rule2");
		
		FormulaMinus formula = new FormulaMinus(null, operand1, operand2);
		
		assertEquals("some_rule1 - some_rule2", formula.asText());
	}
	
}
