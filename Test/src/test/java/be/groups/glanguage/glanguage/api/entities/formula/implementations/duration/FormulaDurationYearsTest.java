package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDate;
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
	public void testIsValidDate() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaDurationYears formula =
				new FormulaDurationYears(FormulaDescriptionFactory.getDescription(FormulaType.F_YEARS), Arrays.asList(parameter));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#isValid()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDurationYears formula =
				new FormulaDurationYears(FormulaDescriptionFactory.getDescription(FormulaType.F_YEARS), Arrays.asList(parameter));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#isValid()} with parameter not date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotDateOrDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaDurationYears formula =
				new FormulaDurationYears(FormulaDescriptionFactory.getDescription(FormulaType.F_YEARS), Arrays.asList(parameter));
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getReturnType()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDate() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaDurationYears formula =
				new FormulaDurationYears(FormulaDescriptionFactory.getDescription(FormulaType.F_YEARS), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.DURATION, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getReturnType()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDurationYears formula =
				new FormulaDurationYears(FormulaDescriptionFactory.getDescription(FormulaType.F_YEARS), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.DURATION, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getReturnType()} with parameter not date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotDateOrDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaDurationYears formula =
				new FormulaDurationYears(FormulaDescriptionFactory.getDescription(FormulaType.F_YEARS), Arrays.asList(parameter));
				
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getIntegerValue()} with date parameter
	 */
	@Test
	public void testGetIntegerValueDateParameter() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 2, 10));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(parameter));
		
		assertEquals(Integer.valueOf(2015), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getIntegerValue()} with duration parameter
	 */
	@Test
	public void testGetIntegerValueDurationParameter() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue()).thenReturn(Duration.ofDays(2 * 365));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(parameter));
		
		assertEquals(Integer.valueOf(2), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(parameter));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(parameter));
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(parameter));
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(parameter));
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getDurationValue()} with date parameter
	 */
	@Test
	public void testGetDurationValueDateParameter() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 2, 10));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(parameter));
		
		assertEquals(Duration.ofDays(2015 * 365), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaDurationYears#getDurationValue()} with duration parameter
	 */
	@Test
	public void testGetDurationValueDurationParameter() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue()).thenReturn(Duration.ofDays(2 * 365));
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(parameter));
		
		assertEquals(Duration.ofDays(2 * 365), formula.getDurationValue());
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
	public void testAsText() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.asText()).thenReturn("some_rule");
		
		FormulaDurationYears formula = new FormulaDurationYears(null, Arrays.asList(parameter));
		
		assertEquals("years(some_rule)", formula.asText());
	}
	
}
