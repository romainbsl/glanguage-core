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
	public void testIsValidMatching() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaModulo formula =
				new FormulaModulo(FormulaDescriptionFactory.getDescription(FormulaType.OP_MODULO), numerator, denominator);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaModulo#isValid()} when operands are not integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotMatching() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaModulo formula =
				new FormulaModulo(FormulaDescriptionFactory.getDescription(FormulaType.OP_MODULO), numerator, denominator);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaModulo#getReturnType()} when operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeMatching() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaModulo formula =
				new FormulaModulo(FormulaDescriptionFactory.getDescription(FormulaType.OP_MODULO), numerator, denominator);
				
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaModulo#getReturnType()} when operands are not integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotMatching() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaModulo formula =
				new FormulaModulo(FormulaDescriptionFactory.getDescription(FormulaType.OP_MODULO), numerator, denominator);
				
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaModulo#getNumericValue()}
	 */
	@Test
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(5);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(3);
		
		FormulaModulo formula = new FormulaModulo(null, numerator, denominator);
		
		assertEquals(Integer.valueOf(2), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaModulo#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);
		
		FormulaModulo formula = new FormulaModulo(null, numerator, denominator);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaModulo#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);
		
		FormulaModulo formula = new FormulaModulo(null, numerator, denominator);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaModulo#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);
		
		FormulaModulo formula = new FormulaModulo(null, numerator, denominator);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaModulo#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);
		
		FormulaModulo formula = new FormulaModulo(null, numerator, denominator);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaModulo#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.getIntegerValue(null)).thenReturn(3);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.getIntegerValue(null)).thenReturn(2);
		
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
	public void testAsText() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.asText()).thenReturn("some_rule1");
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.asText()).thenReturn("some_rule2");
		
		FormulaModulo formula = new FormulaModulo(null, numerator, denominator);
		
		assertEquals("some_rule1 \\ some_rule2", formula.asText());
	}
	
}
