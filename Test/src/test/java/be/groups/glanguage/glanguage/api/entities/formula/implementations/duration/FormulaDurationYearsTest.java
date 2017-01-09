package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
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
 * Test class for {@link FormulaDurationYears}
 * 
 * @author DUPIREFR
 */
public class FormulaDurationYearsTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDurationYears#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaDurationYears formula = new FormulaDurationYears();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_YEARS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDurationYears formula = new FormulaDurationYears();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#isValid()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDate() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaDurationYears formula =
				new FormulaDurationYears(FormulaDescriptionFactory.getDescription(FormulaType.F_YEARS), Arrays.asList(parameter));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#isValid()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDuration() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDurationYears formula =
				new FormulaDurationYears(FormulaDescriptionFactory.getDescription(FormulaType.F_YEARS), Arrays.asList(parameter));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#isValid()} with parameter not integer nor date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotIntegerOrDateOrDuration() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaDurationYears formula =
				new FormulaDurationYears(FormulaDescriptionFactory.getDescription(FormulaType.F_YEARS), Arrays.asList(parameter));
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getReturnType()} with integer parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeInteger() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaDurationMinutes formula =
				new FormulaDurationMinutes(FormulaDescriptionFactory.getDescription(FormulaType.F_YEARS), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.DURATION, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getReturnType()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDate() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaDurationYears formula =
				new FormulaDurationYears(FormulaDescriptionFactory.getDescription(FormulaType.F_YEARS), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getReturnType()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDuration() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDurationYears formula =
				new FormulaDurationYears(FormulaDescriptionFactory.getDescription(FormulaType.F_YEARS), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getReturnType()} with parameter not integer nor date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotIntegerOrDateOrDuration() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaDurationYears formula =
				new FormulaDurationYears(FormulaDescriptionFactory.getDescription(FormulaType.F_YEARS), Arrays.asList(parameter));
				
		assertNull(formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getIntegerValue()} with date parameter
	 */
	@Test
	public void testGetIntegerValueDateParameter() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 2, 10));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(parameter));
		
		assertEquals(Integer.valueOf(2015), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getIntegerValue()} with duration parameter
	 */
	@Test
	public void testGetIntegerValueDurationParameter() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofDays(2 * 365));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(parameter));
		
		assertEquals(Integer.valueOf(2), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(parameter));
		
		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(parameter));
		
		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(parameter));
		
		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(parameter));
		
		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getDurationValue()} with integer parameter
	 */
	@Test
	public void testGetDurationValueIntegerParameter() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue(null)).thenReturn(2015);
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(parameter));
		
		assertEquals(Duration.ofDays(2015 * 365), formula.getDurationValue(null));
	}
	
	/**
	 * Tests {@link FormulaDurationYears#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaDurationYears formula = new FormulaDurationYears();
		
		assertEquals("years", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#asText()}
	 */
	@Test
	public void testAsText() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.asText()).thenReturn("some_rule");
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(parameter));
		
		assertEquals("years(some_rule)", formula.asText());
	}
	
}
