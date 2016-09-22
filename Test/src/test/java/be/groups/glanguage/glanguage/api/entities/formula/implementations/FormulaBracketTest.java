package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;

/**
 * Test class for {@link FormulaBracket}
 * 
 * @author DUPIREFR
 */
public class FormulaBracketTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaBracket#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaBracket formula = new FormulaBracket();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_BRACKETS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaBracket#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaBracket formula = new FormulaBracket();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaBracket#isValid()} with integer parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidInteger() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaBracket formula = new FormulaBracket(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS), parameter);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaBracket#isValid()} with numeric parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNumeric() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaBracket formula = new FormulaBracket(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS), parameter);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaBracket#isValid()} with string parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidString() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaBracket formula = new FormulaBracket(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS), parameter);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaBracket#isValid()} with boolean parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBoolean() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		
		FormulaBracket formula = new FormulaBracket(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS), parameter);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaBracket#isValid()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDate() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaBracket formula = new FormulaBracket(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS), parameter);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaBracket#isValid()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaBracket formula = new FormulaBracket(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS), parameter);
		
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaBracket#getReturnType()} with integer parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeInteger() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaBracket formula = new FormulaBracket(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS), parameter);
		
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaBracket#getReturnType()} with numeric parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNumeric() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaBracket formula = new FormulaBracket(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS), parameter);
		
		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaBracket#getReturnType()} with string parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeString() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaBracket formula = new FormulaBracket(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS), parameter);
		
		assertEquals(FormulaReturnType.STRING, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaBracket#getReturnType()} with boolean parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBoolean() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		
		FormulaBracket formula = new FormulaBracket(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS), parameter);
		
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaBracket#getReturnType()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDate() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaBracket formula = new FormulaBracket(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS), parameter);
		
		assertEquals(FormulaReturnType.DATE, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaBracket#getReturnType()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaBracket formula = new FormulaBracket(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS), parameter);
		
		assertEquals(FormulaReturnType.DURATION, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaBracket#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getIntegerValue()).thenReturn(1);
		
		FormulaBracket formula = new FormulaBracket(null, parameter);
		
		assertEquals(Integer.valueOf(1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaBracket#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getNumericValue()).thenReturn(2.5);
		
		FormulaBracket formula = new FormulaBracket(null, parameter);
		
		assertEquals(Double.valueOf(2.5), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaBracket#getStringValue()}
	 */
	@Test
	public void testGetStringValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getStringValue()).thenReturn("some_value");
		
		FormulaBracket formula = new FormulaBracket(null, parameter);
		
		assertEquals("some_value", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaBracket#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getBooleanValue()).thenReturn(true);
		
		FormulaBracket formula = new FormulaBracket(null, parameter);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaBracket#getDateValue()}
	 */
	@Test
	public void testGetDateValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 1, 1));
		
		FormulaBracket formula = new FormulaBracket(null, parameter);
		
		assertEquals(LocalDate.of(2015, 1, 1), formula.getDateValue());
	}
	
	/**
	 * Tests {@link FormulaBracket#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getDurationValue()).thenReturn(Duration.ofDays(2L));
		
		FormulaBracket formula = new FormulaBracket(null, parameter);
		
		assertEquals(Duration.ofDays(2L), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaBracket#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(parameter.asText()).thenReturn("some_rule");
		
		FormulaBracket formula = new FormulaBracket(null, parameter);
		
		assertEquals("(some_rule)", formula.asText());
	}
	
}
