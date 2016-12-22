package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link FormulaDurationDays}
 * 
 * @author DUPIREFR
 */
public class FormulaDurationDaysTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDurationDays#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaDurationDays formula = new FormulaDurationDays();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_DAYS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDurationDays formula = new FormulaDurationDays();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#isValid()} with integer parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidInteger() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaDurationDays formula =
				new FormulaDurationDays(FormulaDescriptionFactory.getDescription(FormulaType.F_DAYS), Arrays.asList(parameter));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#isValid()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDate() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaDurationDays formula =
				new FormulaDurationDays(FormulaDescriptionFactory.getDescription(FormulaType.F_DAYS), Arrays.asList(parameter));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#isValid()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDurationDays formula =
				new FormulaDurationDays(FormulaDescriptionFactory.getDescription(FormulaType.F_DAYS), Arrays.asList(parameter));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#isValid()} with parameter not integer nor date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotIntegerOrDateOrDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaDurationDays formula =
				new FormulaDurationDays(FormulaDescriptionFactory.getDescription(FormulaType.F_DAYS), Arrays.asList(parameter));
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getReturnType()} with integer parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeInteger() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaDurationMinutes formula =
				new FormulaDurationMinutes(FormulaDescriptionFactory.getDescription(FormulaType.F_DAYS), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.DURATION, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getReturnType()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDate() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaDurationDays formula =
				new FormulaDurationDays(FormulaDescriptionFactory.getDescription(FormulaType.F_DAYS), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getReturnType()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDurationDays formula =
				new FormulaDurationDays(FormulaDescriptionFactory.getDescription(FormulaType.F_DAYS), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getReturnType()} with parameter not integer nor date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotIntegerOrDateOrDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaDurationDays formula =
				new FormulaDurationDays(FormulaDescriptionFactory.getDescription(FormulaType.F_DAYS), Arrays.asList(parameter));
				
		assertNull(formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getIntegerValue()} with date parameter
	 */
	@Test
	public void testGetIntegerValueDateParameter() throws GLanguageEvaluationException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationDays formula = new FormulaDurationDays(null, Arrays.asList(parameter));
		
		assertEquals(Integer.valueOf(10), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getIntegerValue()} with duration parameter
	 */
	@Test
	public void testGetIntegerValueDurationParameter() throws GLanguageEvaluationException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofDays(10L));
		
		FormulaDurationDays formula = new FormulaDurationDays(null, Arrays.asList(parameter));
		
		assertEquals(Integer.valueOf(10), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() throws GLanguageEvaluationException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationDays formula = new FormulaDurationDays(null, Arrays.asList(parameter));
		
		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() throws GLanguageEvaluationException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationDays formula = new FormulaDurationDays(null, Arrays.asList(parameter));
		
		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() throws GLanguageEvaluationException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationDays formula = new FormulaDurationDays(null, Arrays.asList(parameter));
		
		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() throws GLanguageEvaluationException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationDays formula = new FormulaDurationDays(null, Arrays.asList(parameter));
		
		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getDurationValue()} with integer parameter
	 */
	@Test
	public void testGetDurationValueIntegerParameter() throws GLanguageEvaluationException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue(null)).thenReturn(10);
		
		FormulaDurationDays formula = new FormulaDurationDays(null, Arrays.asList(parameter));
		
		assertEquals(Duration.ofDays(10L), formula.getDurationValue(null));
	}
	
	/**
	 * Tests {@link FormulaDurationDays#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaDurationDays formula = new FormulaDurationDays();
		
		assertEquals("days", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.asText()).thenReturn("some_rule");
		
		FormulaDurationDays formula = new FormulaDurationDays(null, Arrays.asList(parameter));
		
		assertEquals("days(some_rule)", formula.asText());
	}
	
}
