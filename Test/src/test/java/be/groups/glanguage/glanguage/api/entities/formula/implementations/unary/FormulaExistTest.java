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
 * Test class for {@link FormulaExist}
 * 
 * @author DUPIREFR
 */
public class FormulaExistTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaExist#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaExist formula = new FormulaExist();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_EXIST), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaExist#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaExist formula = new FormulaExist();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaExist#isValid()} when operand is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidInteger() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaExist formula = new FormulaExist(FormulaDescriptionFactory.getDescription(FormulaType.OP_EXIST), operand);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaExist#isValid()} when operand is numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNumeric() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaExist formula = new FormulaExist(FormulaDescriptionFactory.getDescription(FormulaType.OP_EXIST), operand);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaExist#isValid()} when operand is string
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidString() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaExist formula = new FormulaExist(FormulaDescriptionFactory.getDescription(FormulaType.OP_EXIST), operand);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaExist#isValid()} when operand is boolean
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBoolean() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		
		FormulaExist formula = new FormulaExist(FormulaDescriptionFactory.getDescription(FormulaType.OP_EXIST), operand);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaExist#isValid()} when operand is date
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDate() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaExist formula = new FormulaExist(FormulaDescriptionFactory.getDescription(FormulaType.OP_EXIST), operand);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaExist#isValid()} when operand is duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDuration() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		FormulaExist formula = new FormulaExist(FormulaDescriptionFactory.getDescription(FormulaType.OP_EXIST), operand);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaExist#getReturnType()} when operand is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeInteger() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaExist formula = new FormulaExist(FormulaDescriptionFactory.getDescription(FormulaType.OP_EXIST), operand);
		
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaExist#getReturnType()} when operand is numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNumeric() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaExist formula = new FormulaExist(FormulaDescriptionFactory.getDescription(FormulaType.OP_EXIST), operand);
		
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaExist#getReturnType()} when operand is string
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeString() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaExist formula = new FormulaExist(FormulaDescriptionFactory.getDescription(FormulaType.OP_EXIST), operand);
		
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaExist#getReturnType()} when operand is boolean
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBoolean() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		
		FormulaExist formula = new FormulaExist(FormulaDescriptionFactory.getDescription(FormulaType.OP_EXIST), operand);
		
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaExist#getReturnType()} when operand is date
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDate() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaExist formula = new FormulaExist(FormulaDescriptionFactory.getDescription(FormulaType.OP_EXIST), operand);
		
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaExist#getReturnType()} when operand is duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDuration() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		FormulaExist formula = new FormulaExist(FormulaDescriptionFactory.getDescription(FormulaType.OP_EXIST), operand);
		
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaExist#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand.getValue(null)).thenReturn("some_value");
		
		FormulaExist formula = new FormulaExist(null, operand);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaExist#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand.getValue(null)).thenReturn("some_value");
		
		FormulaExist formula = new FormulaExist(null, operand);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaExist#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand.getValue(null)).thenReturn("some_value");
		
		FormulaExist formula = new FormulaExist(null, operand);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaExist#getBooleanValue()} when parameter exists
	 */
	@Test
	public void testGetBooleanValueParameterExists() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand.getValue(null)).thenReturn("some_value");
		
		FormulaExist formula = new FormulaExist(null, operand);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaExist#getBooleanValue()} when parameter doesn't exist
	 */
	@Test
	public void testGetBooleanValueParameterNotExist() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand.getValue(null)).thenReturn(null);
		
		FormulaExist formula = new FormulaExist(null, operand);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaExist#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand.getValue(null)).thenReturn("some_value");
		
		FormulaExist formula = new FormulaExist(null, operand);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaExist#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand.getValue(null)).thenReturn("some_value");
		
		FormulaExist formula = new FormulaExist(null, operand);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaExist#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaExist formula = new FormulaExist();
		
		assertEquals("?", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaExist#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand.asText()).thenReturn("some_rule");
		
		FormulaExist formula = new FormulaExist(null, operand);
		
		assertEquals("? some_rule", formula.asText());
	}
	
}
