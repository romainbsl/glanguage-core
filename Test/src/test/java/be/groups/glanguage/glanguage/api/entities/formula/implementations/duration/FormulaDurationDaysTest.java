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
	 * Tests {@link FormulaDurationDays#isValid()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDate() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
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
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDurationDays formula =
				new FormulaDurationDays(FormulaDescriptionFactory.getDescription(FormulaType.F_DAYS), Arrays.asList(parameter));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#isValid()} with parameter not date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotDateOrDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaDurationDays formula =
				new FormulaDurationDays(FormulaDescriptionFactory.getDescription(FormulaType.F_DAYS), Arrays.asList(parameter));
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getReturnType()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDate() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaDurationDays formula =
				new FormulaDurationDays(FormulaDescriptionFactory.getDescription(FormulaType.F_DAYS), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.DURATION, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getReturnType()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDurationDays formula =
				new FormulaDurationDays(FormulaDescriptionFactory.getDescription(FormulaType.F_DAYS), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.DURATION, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getReturnType()} with parameter not date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotDateOrDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaDurationDays formula =
				new FormulaDurationDays(FormulaDescriptionFactory.getDescription(FormulaType.F_DAYS), Arrays.asList(parameter));
				
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getIntegerValue()} with date parameter
	 */
	@Test
	public void testGetIntegerValueDateParameter() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationDays formula = new FormulaDurationDays(null, Arrays.asList(parameter));
		
		assertEquals(Integer.valueOf(10), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getIntegerValue()} with duration parameter
	 */
	@Test
	public void testGetIntegerValueDurationParameter() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue()).thenReturn(Duration.ofDays(10L));
		
		FormulaDurationDays formula = new FormulaDurationDays(null, Arrays.asList(parameter));
		
		assertEquals(Integer.valueOf(10), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationDays formula = new FormulaDurationDays(null, Arrays.asList(parameter));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationDays formula = new FormulaDurationDays(null, Arrays.asList(parameter));
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationDays formula = new FormulaDurationDays(null, Arrays.asList(parameter));
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationDays formula = new FormulaDurationDays(null, Arrays.asList(parameter));
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getDurationValue()} with date parameter
	 */
	@Test
	public void testGetDurationValueDateParameter() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationDays formula = new FormulaDurationDays(null, Arrays.asList(parameter));
		
		assertEquals(Duration.ofDays(10L), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getDurationValue()} with duration parameter
	 */
	@Test
	public void testGetDurationValueDurationParameter() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue()).thenReturn(Duration.ofDays(10L));
		
		FormulaDurationDays formula = new FormulaDurationDays(null, Arrays.asList(parameter));
		
		assertEquals(Duration.ofDays(10L), formula.getDurationValue());
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
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.asText()).thenReturn("some_rule");
		
		FormulaDurationDays formula = new FormulaDurationDays(null, Arrays.asList(parameter));
		
		assertEquals("days(some_rule)", formula.asText());
	}
	
}
