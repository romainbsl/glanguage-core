package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link FormulaUnaryMinus}
 * 
 * @author DUPIREFR
 */
public class FormulaUnaryMinusTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaUnaryMinus#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaUnaryMinus formula = new FormulaUnaryMinus();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_UNARY_MINUS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaUnaryMinus formula = new FormulaUnaryMinus();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#isValid()} when operand is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidInteger() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaUnaryMinus formula =
				new FormulaUnaryMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS), operand);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#isValid()} when operand is numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNumeric() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaUnaryMinus formula =
				new FormulaUnaryMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS), operand);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#isValid()} when operand is not integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotIntegerOrNumeric() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaUnaryMinus formula =
				new FormulaUnaryMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS), operand);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getReturnType()} when operand is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeInteger() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaUnaryMinus formula =
				new FormulaUnaryMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS), operand);
				
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getReturnType()} when operand is numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNumeric() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaUnaryMinus formula =
				new FormulaUnaryMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS), operand);
				
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getReturnType()} when operand is not integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotIntegerOrNumeric() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaUnaryMinus formula =
				new FormulaUnaryMinus(FormulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS), operand);
				
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getIntegerValue()} when parameter exists
	 */
	@Test
	public void testGetIntegerValueParameterExists() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);
		
		FormulaUnaryMinus formula = new FormulaUnaryMinus(null, operand);
		
		assertEquals(Integer.valueOf(-1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getIntegerValue()} when parameter doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetIntegerValueParameterNotExists() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(null);
		
		FormulaUnaryMinus formula = new FormulaUnaryMinus(null, operand);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getNumericValue()} when parameter exists
	 */
	@Test
	public void testGetNumericValueParameterExists() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue(null)).thenReturn(1.5);
		
		FormulaUnaryMinus formula = new FormulaUnaryMinus(null, operand);
		
		assertEquals(Double.valueOf(-1.5), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getNumericValue()} when parameter doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetNumericValueParameterNotExists() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue(null)).thenReturn(null);
		
		FormulaUnaryMinus formula = new FormulaUnaryMinus(null, operand);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);
		
		FormulaUnaryMinus formula = new FormulaUnaryMinus(null, operand);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);
		
		FormulaUnaryMinus formula = new FormulaUnaryMinus(null, operand);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);
		
		FormulaUnaryMinus formula = new FormulaUnaryMinus(null, operand);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);
		
		FormulaUnaryMinus formula = new FormulaUnaryMinus(null, operand);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaUnaryMinus formula = new FormulaUnaryMinus();
		
		assertEquals("-", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.asText()).thenReturn("some_rule");
		
		FormulaUnaryMinus formula = new FormulaUnaryMinus(null, operand);
		
		assertEquals("- some_rule", formula.asText());
	}
	
}
