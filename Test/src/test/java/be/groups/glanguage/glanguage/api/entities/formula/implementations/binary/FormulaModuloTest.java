package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;

/**
 * Test class for {@link FormulaModulo}
 * 
 * @author DUPIREFR
 */
public class FormulaModuloTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaModulo#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaModulo formula = new FormulaModulo();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_MODULO), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaModulo#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaModulo formula = new FormulaModulo();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaModulo#isValid()} when operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidMatching() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaModulo formula =
				new FormulaModulo(FormulaDescriptionFactory.getDescription(FormulaType.OP_MODULO), numerator, denominator);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaModulo#isValid()} when operands are not integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotMatching() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaModulo formula =
				new FormulaModulo(FormulaDescriptionFactory.getDescription(FormulaType.OP_MODULO), numerator, denominator);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaModulo#getReturnType()} when operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeMatching() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaModulo formula =
				new FormulaModulo(FormulaDescriptionFactory.getDescription(FormulaType.OP_MODULO), numerator, denominator);
				
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaModulo#getReturnType()} when operands are not integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotMatching() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaModulo formula =
				new FormulaModulo(FormulaDescriptionFactory.getDescription(FormulaType.OP_MODULO), numerator, denominator);
				
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaModulo#getNumericValue()}
	 */
	@Test
	public void testGetIntegerValue() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue()).thenReturn(5);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue()).thenReturn(3);
		
		FormulaModulo formula = new FormulaModulo(null, numerator, denominator);
		
		assertEquals(Integer.valueOf(2), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaModulo#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue()).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue()).thenReturn(2);
		
		FormulaModulo formula = new FormulaModulo(null, numerator, denominator);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaModulo#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue()).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue()).thenReturn(2);
		
		FormulaModulo formula = new FormulaModulo(null, numerator, denominator);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaModulo#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue()).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue()).thenReturn(2);
		
		FormulaModulo formula = new FormulaModulo(null, numerator, denominator);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaModulo#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue()).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue()).thenReturn(2);
		
		FormulaModulo formula = new FormulaModulo(null, numerator, denominator);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaModulo#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue()).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue()).thenReturn(2);
		
		FormulaModulo formula = new FormulaModulo(null, numerator, denominator);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaModulo#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaModulo formula = new FormulaModulo();
		
		assertEquals("\\", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaModulo#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.asText()).thenReturn("some_rule1");
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.asText()).thenReturn("some_rule2");
		
		FormulaModulo formula = new FormulaModulo(null, numerator, denominator);
		
		assertEquals("some_rule1 \\ some_rule2", formula.asText());
	}
	
}
