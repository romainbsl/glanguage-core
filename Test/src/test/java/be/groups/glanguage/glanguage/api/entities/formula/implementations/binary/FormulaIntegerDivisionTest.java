package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link FormulaIntegerDivision}
 * 
 * @author DUPIREFR
 */
public class FormulaIntegerDivisionTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaIntegerDivision#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaIntegerDivision formula = new FormulaIntegerDivision();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_INTEGER_DIVISION), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaIntegerDivision formula = new FormulaIntegerDivision();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#isValid()} when operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidMatching() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaIntegerDivision formula = new FormulaIntegerDivision(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_INTEGER_DIVISION), numerator, denominator);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#isValid()} when operands are not integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotMatching() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaIntegerDivision formula = new FormulaIntegerDivision(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_INTEGER_DIVISION), numerator, denominator);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#getReturnType()} when operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeMatching() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaIntegerDivision formula = new FormulaIntegerDivision(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_INTEGER_DIVISION), numerator, denominator);
				
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#getReturnType()} when operands are not integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotMatching() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaIntegerDivision formula = new FormulaIntegerDivision(
				FormulaDescriptionFactory.getDescription(FormulaType.OP_INTEGER_DIVISION), numerator, denominator);
				
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#getNumericValue()}
	 */
	@Test
	public void testGetIntegerValue() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);
		
		FormulaIntegerDivision formula = new FormulaIntegerDivision(null, numerator, denominator);
		
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);
		
		FormulaIntegerDivision formula = new FormulaIntegerDivision(null, numerator, denominator);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);
		
		FormulaIntegerDivision formula = new FormulaIntegerDivision(null, numerator, denominator);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);
		
		FormulaIntegerDivision formula = new FormulaIntegerDivision(null, numerator, denominator);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);
		
		FormulaIntegerDivision formula = new FormulaIntegerDivision(null, numerator, denominator);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);
		
		FormulaIntegerDivision formula = new FormulaIntegerDivision(null, numerator, denominator);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaIntegerDivision formula = new FormulaIntegerDivision();
		
		assertEquals("/", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaIntegerDivision#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.asText()).thenReturn("some_rule1");
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.asText()).thenReturn("some_rule2");
		
		FormulaIntegerDivision formula = new FormulaIntegerDivision(null, numerator, denominator);
		
		assertEquals("some_rule1 / some_rule2", formula.asText());
	}
	
}
