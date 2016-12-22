package be.groups.glanguage.glanguage.api.entities.formula.implementations.math;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link FormulaMathAbs}
 * 
 * @author DUPIREFR
 */
public class FormulaMathAbsTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaMathAbs#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaMathAbs formula = new FormulaMathAbs();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_ABS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaMathAbs#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaMathAbs formula = new FormulaMathAbs();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaMathAbs#isValid()} when operand is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidInteger() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaMathAbs formula =
				new FormulaMathAbs(FormulaDescriptionFactory.getDescription(FormulaType.F_ABS), Arrays.asList(operand));
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMathAbs#isValid()} when operand is numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNumeric() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaMathAbs formula =
				new FormulaMathAbs(FormulaDescriptionFactory.getDescription(FormulaType.F_ABS), Arrays.asList(operand));
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMathAbs#isValid()} when operand is not integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotIntegerOrNumeric() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaMathAbs formula =
				new FormulaMathAbs(FormulaDescriptionFactory.getDescription(FormulaType.F_ABS), Arrays.asList(operand));
		
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getReturnType()} when operand is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeInteger() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaMathAbs formula =
				new FormulaMathAbs(FormulaDescriptionFactory.getDescription(FormulaType.F_ABS), Arrays.asList(operand));
		
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getReturnType()} when operand is numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNumeric() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaMathAbs formula =
				new FormulaMathAbs(FormulaDescriptionFactory.getDescription(FormulaType.F_ABS), Arrays.asList(operand));
		
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getReturnType()} when operand is not integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotIntegerOrNumeric() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaMathAbs formula =
				new FormulaMathAbs(FormulaDescriptionFactory.getDescription(FormulaType.F_ABS), Arrays.asList(operand));
		
		assertNull(formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getIntegerValue()} when operand exists and is positive
	 */
	@Test
	public void testGetIntegerValueParameterExistsPositive() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getNumericValue(null)).thenReturn(1.0);
		
		FormulaMathAbs formula = new FormulaMathAbs(null, Arrays.asList(operand));
		
		assertEquals(Integer.valueOf(1), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getIntegerValue()} when operand exists and is negative
	 */
	@Test
	public void testGetIntegerValueParameterExistsNegative() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getNumericValue(null)).thenReturn(-1.0);
		
		FormulaMathAbs formula = new FormulaMathAbs(null, Arrays.asList(operand));
		
		assertEquals(Integer.valueOf(1), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getIntegerValue()} when operand doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetIntegerValueParameterNotExists() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getNumericValue(null)).thenReturn(null);
		
		FormulaMathAbs formula = new FormulaMathAbs(null, Arrays.asList(operand));
		
		formula.getIntegerValue(null);
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getNumericValue()} when operand exists and is positive
	 */
	@Test
	public void testGetNumericValueParameterExistsPositive() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue(null)).thenReturn(1.5);
		
		FormulaMathAbs formula = new FormulaMathAbs(null, Arrays.asList(operand));
		
		assertEquals(Double.valueOf(1.5), formula.getNumericValue(null));
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getNumericValue()} when operand exists and is negative
	 */
	@Test
	public void testGetNumericValueParameterExistsNegative() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue(null)).thenReturn(-1.5);
		
		FormulaMathAbs formula = new FormulaMathAbs(null, Arrays.asList(operand));
		
		assertEquals(Double.valueOf(1.5), formula.getNumericValue(null));
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getNumericValue()} when operand doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetNumericValueParameterNotExists() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue(null)).thenReturn(null);
		
		FormulaMathAbs formula = new FormulaMathAbs(null, Arrays.asList(operand));
		
		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);
		
		FormulaMathAbs formula = new FormulaMathAbs(null, Arrays.asList(operand));
		
		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);
		
		FormulaMathAbs formula = new FormulaMathAbs(null, Arrays.asList(operand));
		
		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);
		
		FormulaMathAbs formula = new FormulaMathAbs(null, Arrays.asList(operand));
		
		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() throws GLanguageEvaluationException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);
		
		FormulaMathAbs formula = new FormulaMathAbs(null, Arrays.asList(operand));
		
		formula.getDurationValue(null);
	}
	
	/**
	 * Tests {@link FormulaMathAbs#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaMathAbs formula = new FormulaMathAbs();
		
		assertEquals("abs", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaMathAbs#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.asText()).thenReturn("some_rule");
		
		FormulaMathAbs formula = new FormulaMathAbs(null, Arrays.asList(operand));
		
		assertEquals("abs(some_rule)", formula.asText());
	}
	
}
