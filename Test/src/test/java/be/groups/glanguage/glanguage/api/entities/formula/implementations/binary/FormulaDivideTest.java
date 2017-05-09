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
 * Test class for {@link FormulaDivide}
 * 
 * @author DUPIREFR
 */
public class FormulaDivideTest extends BaseDatabaseTest {
	
	/*
	 * Constants
	 */
	private static final double DELTA = 1e-15;
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDivide#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaDivide formula = new FormulaDivide();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_DIVIDE), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaDivide#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDivide formula = new FormulaDivide();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaDivide#isValid()} when operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidMatching() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaDivide formula =
				new FormulaDivide(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIVIDE), numerator, denominator);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDivide#isValid()} when operands are not numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotMatching() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaDivide formula =
				new FormulaDivide(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIVIDE), numerator, denominator);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDivide#getReturnType()} when operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeMatching() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaDivide formula =
				new FormulaDivide(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIVIDE), numerator, denominator);
				
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDivide#getReturnType()} when operands are not numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotMatching() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaDivide formula =
				new FormulaDivide(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIVIDE), numerator, denominator);
				
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDivide#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(numerator.getValue(null)).thenReturn(1);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(denominator.getValue(null)).thenReturn(0);
		
		FormulaDivide formula = new FormulaDivide(null, numerator, denominator);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaDivide#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(numerator.getNumericValue(null)).thenReturn(3.2);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(denominator.getNumericValue(null)).thenReturn(2.5);
		
		FormulaDivide formula = new FormulaDivide(null, numerator, denominator);
		
		assertEquals(Double.valueOf(1.28), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaDivide#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(numerator.getValue(null)).thenReturn(1);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(denominator.getValue(null)).thenReturn(0);
		
		FormulaDivide formula = new FormulaDivide(null, numerator, denominator);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaDivide#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(numerator.getValue(null)).thenReturn(1);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(denominator.getValue(null)).thenReturn(0);
		
		FormulaDivide formula = new FormulaDivide(null, numerator, denominator);
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaDivide#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(numerator.getValue(null)).thenReturn(1);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(denominator.getValue(null)).thenReturn(0);
		
		FormulaDivide formula = new FormulaDivide(null, numerator, denominator);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaDivide#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(numerator.getValue(null)).thenReturn(1);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(denominator.getValue(null)).thenReturn(0);
		
		FormulaDivide formula = new FormulaDivide(null, numerator, denominator);
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaDivide#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaDivide formula = new FormulaDivide();
		
		assertEquals("/", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaDivide#asText()}
	 */
	@Test
	public void testAsText() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.asText()).thenReturn("some_rule1");
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.asText()).thenReturn("some_rule2");
		
		FormulaDivide formula = new FormulaDivide(null, numerator, denominator);
		
		assertEquals("some_rule1 / some_rule2", formula.asText());
	}
	
}
