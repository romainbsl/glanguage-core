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
 * Test class for {@link FormulaPlus}
 * 
 * @author DUPIREFR
 */
public class FormulaPlusTest extends BaseDatabaseTest {
	
	/*
	 * Constants
	 */
	private static final double DELTA = 1e-15;
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaPlus#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaPlus formula = new FormulaPlus();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_PLUS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaPlus#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaPlus formula = new FormulaPlus();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaPlus#isValid()} when both operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothIntegers() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaPlus#isValid()} when first operand is integer and second numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstIntSecondNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaPlus#isValid()} when first operand is integer and second is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstIntSecondNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaPlus#isValid()} when second operand is integer and first is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondIntFirstNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaPlus#isValid()} when first operand is numeric and second integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNumSecondInt() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaPlus#isValid()} when first operand is numeric and second is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNumSecondNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaPlus#isValid()} when second operand is numeric and first is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondNumFirstNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaPlus#isValid()} when both operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothNumerics() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaPlus#isValid()} when both operands are strings
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothStrings() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaPlus#isValid()} when first operand is a date and second is a duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstDateSecondDuration() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaPlus#isValid()} when both operands are durations
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothDurations() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaPlus#isValid()} when first operand is a duration and second is a date
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstDurationSecondDate() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaPlus#isValid()} when both operands are dates
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothDate() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaPlus#getReturnType()} when both operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothIntegers() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaPlus#getReturnType()} when first operand is integer and second numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstIntSecondNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaPlus#getReturnType()} when first operand is integer and second is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstIntSecondNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaPlus#getReturnType()} when second operand is integer and first is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeSecondIntFirstNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaPlus#getReturnType()} when first operand is numeric and second integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstNumSecondInt() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaPlus#getReturnType()} when first operand is numeric and second is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstNumSecondNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaPlus#getReturnType()} when second operand is numeric and first is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeSecondNumFirstNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaPlus#getReturnType()} when both operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothNumerics() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaPlus#getReturnType()} when both operands are strings
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothStrings() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertEquals(FormulaReturnType.STRING, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaPlus#getReturnType()} when both operands are durations
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothDurations() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertEquals(FormulaReturnType.DURATION, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaPlus#getReturnType()} when first operand is a date and second is a duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstDateSecondDuration() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertEquals(FormulaReturnType.DATE, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaPlus#getReturnType()} when first operand is a duration and second is a date
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstDurationSecondDate() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaPlus formula = new FormulaPlus(FormulaDescriptionFactory.getDescription(FormulaType.OP_PLUS), operand1, operand2);
		
		assertEquals(FormulaReturnType.DATE, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaPlus#getIntegerValue()} when both parameters are integers
	 */
	@Test
	public void testGetIntegerValueIntInt() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getIntegerValue(null)).thenReturn(2);
		
		FormulaPlus formula = new FormulaPlus(null, operand1, operand2);
		
		assertEquals(Integer.valueOf(3), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaPlus#getNumericValue()} when both parameters are numerics
	 */
	@Test
	public void testGetNumericValueNumNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue(null)).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue(null)).thenReturn(2.3);
		
		FormulaPlus formula = new FormulaPlus(null, operand1, operand2);
		
		assertEquals(Double.valueOf(3.8), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaPlus#getNumericValue()} when first parameter is integer and second is
	 * numeric
	 */
	@Test
	public void testGetNumericValueIntNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue(null)).thenReturn(2.3);
		
		FormulaPlus formula = new FormulaPlus(null, operand1, operand2);
		
		assertEquals(Double.valueOf(3.3), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaPlus#getNumericValue()} when first parameter is numeric and second is
	 * integer
	 */
	@Test
	public void testGetNumericValueNumInt() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue(null)).thenReturn(2.3);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(1.0);
		
		FormulaPlus formula = new FormulaPlus(null, operand1, operand2);
		
		assertEquals(Double.valueOf(3.3), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaPlus#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand1.getStringValue(null)).thenReturn("some_value1");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand2.getStringValue(null)).thenReturn("some_value2");
		
		FormulaPlus formula = new FormulaPlus(null, operand1, operand2);
		
		assertEquals("some_value1some_value2", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaPlus#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(2.0);
		
		FormulaPlus formula = new FormulaPlus(null, operand1, operand2);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaPlus#getDateValue()} when operands are not of the good type
	 */
	@Test(expected = IllegalStateException.class)
	public void testGetDateValueWrongParameterTypes() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(2.0);
		
		FormulaPlus formula = new FormulaPlus(null, operand1, operand2);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaPlus#getDateValue()} when first operand is a date and second operand is a duration
	 */
	public void testGetDateValueFirstDateSecondDuration() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand1.getDateValue(null)).thenReturn(LocalDate.of(2016, 1, 1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(operand2.getDurationValue(null)).thenReturn(Duration.ofDays(1));
		
		FormulaPlus formula = new FormulaPlus(null, operand1, operand2);
		
		assertEquals(LocalDate.of(2016, 1, 2), formula.getDateValue());
	}
	
	/**
	 * Tests {@link FormulaPlus#getDateValue()} when first operand is a duration and second operand is a date
	 */
	public void testGetDateValueFirstDurationSecondDate() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(operand1.getDurationValue(null)).thenReturn(Duration.ofDays(1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand2.getDateValue(null)).thenReturn(LocalDate.of(2016, 1, 1));
		
		FormulaPlus formula = new FormulaPlus(null, operand1, operand2);
		
		assertEquals(LocalDate.of(2016, 1, 2), formula.getDateValue());
	}
	
	/**
	 * Tests {@link FormulaPlus#getDurationValue()} when operands are not of the good type
	 */
	@Test(expected = NullPointerException.class)
	public void testGetDurationValueWrongParameterTypes() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(2.0);
		
		FormulaPlus formula = new FormulaPlus(null, operand1, operand2);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaPlus#getDurationValue()}
	 */
	public void testGetDurationValueBothDuration() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(operand1.getDurationValue(null)).thenReturn(Duration.ofDays(1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(operand2.getDurationValue(null)).thenReturn(Duration.ofDays(1));
		
		FormulaPlus formula = new FormulaPlus(null, operand1, operand2);
		
		assertEquals(Duration.ofDays(2), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaPlus#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaPlus formula = new FormulaPlus();
		
		assertEquals("+", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaPlus#asText()}
	 */
	@Test
	public void testAsText() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.asText()).thenReturn("some_rule1");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.asText()).thenReturn("some_rule2");
		
		FormulaPlus formula = new FormulaPlus(null, operand1, operand2);
		
		assertEquals("some_rule1 + some_rule2", formula.asText());
	}
	
}
