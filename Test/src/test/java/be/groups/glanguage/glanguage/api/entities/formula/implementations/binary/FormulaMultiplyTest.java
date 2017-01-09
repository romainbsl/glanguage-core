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

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link FormulaMultiply}
 * 
 * @author DUPIREFR
 */
public class FormulaMultiplyTest extends BaseDatabaseTest {
	
	/*
	 * Constants
	 */
	private static final double DELTA = 1e-15;
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaMultiply#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaMultiply formula = new FormulaMultiply();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_MULTIPLY), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaMultiply#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaMultiply formula = new FormulaMultiply();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaMultiply#isValid()} when both operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothIntegers() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaMultiply formula =
				new FormulaMultiply(FormulaDescriptionFactory.getDescription(FormulaType.OP_MULTIPLY), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMultiply#isValid()} when first operand is integer and second numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstIntSecondNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaMultiply formula =
				new FormulaMultiply(FormulaDescriptionFactory.getDescription(FormulaType.OP_MULTIPLY), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMultiply#isValid()} when first operand is integer and second is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstIntSecondNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaMultiply formula =
				new FormulaMultiply(FormulaDescriptionFactory.getDescription(FormulaType.OP_MULTIPLY), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMultiply#isValid()} when second operand is integer and first is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondIntFirstNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaMultiply formula =
				new FormulaMultiply(FormulaDescriptionFactory.getDescription(FormulaType.OP_MULTIPLY), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMultiply#isValid()} when first operand is numeric and second integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNumSecondInt() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaMultiply formula =
				new FormulaMultiply(FormulaDescriptionFactory.getDescription(FormulaType.OP_MULTIPLY), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMultiply#isValid()} when first operand is numeric and second is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNumSecondNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaMultiply formula =
				new FormulaMultiply(FormulaDescriptionFactory.getDescription(FormulaType.OP_MULTIPLY), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMultiply#isValid()} when second operand is numeric and first is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondNumFirstNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaMultiply formula =
				new FormulaMultiply(FormulaDescriptionFactory.getDescription(FormulaType.OP_MULTIPLY), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMultiply#isValid()} when both operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothNumerics() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaMultiply formula =
				new FormulaMultiply(FormulaDescriptionFactory.getDescription(FormulaType.OP_MULTIPLY), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMultiply#getIntegerValue()} when both parameters are integers
	 */
	@Test
	public void testGetIntegerValueIntInt() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(2.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(3.0);
		
		FormulaMultiply formula = new FormulaMultiply(null, operand1, operand2);
		
		assertEquals(Integer.valueOf(6), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaMultiply#getNumericValue()} when both parameters are numerics
	 */
	@Test
	public void testGetNumericValueNumNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue(null)).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue(null)).thenReturn(2.3);
		
		FormulaMultiply formula = new FormulaMultiply(null, operand1, operand2);
		
		assertEquals(Double.valueOf(3.45), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaMultiply#getNumericValue()} when first parameter is integer and second is
	 * numeric
	 */
	@Test
	public void testGetNumericValueIntNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(2.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue(null)).thenReturn(2.3);
		
		FormulaMultiply formula = new FormulaMultiply(null, operand1, operand2);
		
		assertEquals(Double.valueOf(4.6), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaMultiply#getNumericValue()} when first parameter is numeric and second is
	 * integer
	 */
	@Test
	public void testGetNumericValueNumInt() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue(null)).thenReturn(2.3);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(2.0);
		
		FormulaMultiply formula = new FormulaMultiply(null, operand1, operand2);
		
		assertEquals(Double.valueOf(4.6), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaMultiply#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(2.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(3.0);
		
		FormulaMultiply formula = new FormulaMultiply(null, operand1, operand2);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaMultiply#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(2.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(3.0);
		
		FormulaMultiply formula = new FormulaMultiply(null, operand1, operand2);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaMultiply#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(2.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(3.0);
		
		FormulaMultiply formula = new FormulaMultiply(null, operand1, operand2);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaMultiply#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(2.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(3.0);
		
		FormulaMultiply formula = new FormulaMultiply(null, operand1, operand2);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaMultiply#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaMultiply formula = new FormulaMultiply();
		
		assertEquals("*", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaMultiply#asText()}
	 */
	@Test
	public void testAsText() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.asText()).thenReturn("some_rule1");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.asText()).thenReturn("some_rule2");
		
		FormulaMultiply formula = new FormulaMultiply(null, operand1, operand2);
		
		assertEquals("some_rule1 * some_rule2", formula.asText());
	}
	
}
