package be.groups.glanguage.glanguage.api.entities.formula.implementations.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;

/**
 * Test class for {@link FormulaMathSign}
 * 
 * @author DUPIREFR
 */
public class FormulaMathSignTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaMathSign#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaMathSign formula = new FormulaMathSign();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_SIGN), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaMathSign#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaMathSign formula = new FormulaMathSign();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaMathSign#isValid()} when operand is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidInteger() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaMathSign formula =
				new FormulaMathSign(FormulaDescriptionFactory.getDescription(FormulaType.F_SIGN), Arrays.asList(operand));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMathSign#isValid()} when operand is numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNumeric() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaMathSign formula =
				new FormulaMathSign(FormulaDescriptionFactory.getDescription(FormulaType.F_SIGN), Arrays.asList(operand));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMathSign#isValid()} when operand is not integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotIntegerOrNumeric() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaMathSign formula =
				new FormulaMathSign(FormulaDescriptionFactory.getDescription(FormulaType.F_SIGN), Arrays.asList(operand));
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMathSign#getReturnType()} when operand is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeInteger() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaMathSign formula =
				new FormulaMathSign(FormulaDescriptionFactory.getDescription(FormulaType.F_SIGN), Arrays.asList(operand));
				
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMathSign#getReturnType()} when operand is numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNumeric() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaMathSign formula =
				new FormulaMathSign(FormulaDescriptionFactory.getDescription(FormulaType.F_SIGN), Arrays.asList(operand));
				
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMathSign#getReturnType()} when operand is not integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotIntegerOrNumeric() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaMathSign formula =
				new FormulaMathSign(FormulaDescriptionFactory.getDescription(FormulaType.F_SIGN), Arrays.asList(operand));
				
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMathSign#getIntegerValue()} when operand exists and is positive
	 */
	@Test
	public void testGetIntegerValueParameterExistsPositive() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getNumericValue()).thenReturn(1.0);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(operand));
		
		assertTrue(formula.getIntegerValue() >= 0);
	}
	
	/**
	 * Tests {@link FormulaMathSign#getIntegerValue()} when operand exists and is negative
	 */
	@Test
	public void testGetIntegerValueParameterExistsNegative() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getNumericValue()).thenReturn(-1.0);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(operand));
		
		assertTrue(formula.getIntegerValue() <= 0);
	}
	
	/**
	 * Tests {@link FormulaMathSign#getIntegerValue()} when operand doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetIntegerValueParameterNotExists() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getNumericValue()).thenReturn(null);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(operand));
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaMathSign#getNumericValue()} when operand exists and is positive
	 */
	@Test
	public void testGetNumericValueParameterExistsPositive() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue()).thenReturn(1.5);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(operand));
		
		assertTrue(formula.getNumericValue() >= 0);
	}
	
	/**
	 * Tests {@link FormulaMathSign#getNumericValue()} when operand exists and is negative
	 */
	@Test
	public void testGetNumericValueParameterExistsNegative() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue()).thenReturn(-1.5);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(operand));
		
		assertTrue(formula.getNumericValue() <= 0);
	}
	
	/**
	 * Tests {@link FormulaMathSign#getNumericValue()} when operand doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetNumericValueParameterNotExists() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue()).thenReturn(null);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(operand));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaMathSign#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue()).thenReturn(1);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(operand));
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaMathSign#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue()).thenReturn(1);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(operand));
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaMathSign#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue()).thenReturn(1);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(operand));
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaMathSign#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue()).thenReturn(1);
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(operand));
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaMathSign#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaMathSign formula = new FormulaMathSign();
		
		assertEquals("sign", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaMathSign#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand.asText()).thenReturn("some_rule");
		
		FormulaMathSign formula = new FormulaMathSign(null, Arrays.asList(operand));
		
		assertEquals("sign(some_rule)", formula.asText());
	}
	
}
