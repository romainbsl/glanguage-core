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
 * Test class for {@link FormulaDurationMonths}
 * 
 * @author DUPIREFR
 */
public class FormulaDurationMonthsTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDurationMonths#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaDurationMonths formula = new FormulaDurationMonths();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_MONTHS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDurationMonths formula = new FormulaDurationMonths();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#isValid()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDate() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaDurationMonths formula =
				new FormulaDurationMonths(FormulaDescriptionFactory.getDescription(FormulaType.F_MONTHS), Arrays.asList(parameter));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#isValid()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDurationMonths formula =
				new FormulaDurationMonths(FormulaDescriptionFactory.getDescription(FormulaType.F_MONTHS), Arrays.asList(parameter));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#isValid()} with parameter not date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotDateOrDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaDurationMonths formula =
				new FormulaDurationMonths(FormulaDescriptionFactory.getDescription(FormulaType.F_MONTHS), Arrays.asList(parameter));
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getReturnType()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDate() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaDurationMonths formula =
				new FormulaDurationMonths(FormulaDescriptionFactory.getDescription(FormulaType.F_MONTHS), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.DURATION, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getReturnType()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDurationMonths formula =
				new FormulaDurationMonths(FormulaDescriptionFactory.getDescription(FormulaType.F_MONTHS), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.DURATION, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getReturnType()} with parameter not date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotDateOrDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaDurationMonths formula =
				new FormulaDurationMonths(FormulaDescriptionFactory.getDescription(FormulaType.F_MONTHS), Arrays.asList(parameter));
				
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getIntegerValue()} with date parameter
	 */
	@Test
	public void testGetIntegerValueDateParameter() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 2, 10));
		
		FormulaDurationMonths formula = new FormulaDurationMonths(null, Arrays.asList(parameter));
		
		assertEquals(Integer.valueOf(2), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getIntegerValue()} with duration parameter
	 */
	@Test
	public void testGetIntegerValueDurationParameter() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue()).thenReturn(Duration.ofDays(2 * 31));
		
		FormulaDurationMonths formula = new FormulaDurationMonths(null, Arrays.asList(parameter));
		
		assertEquals(Integer.valueOf(2), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationMonths formula = new FormulaDurationMonths(null, Arrays.asList(parameter));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationMonths formula = new FormulaDurationMonths(null, Arrays.asList(parameter));
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationMonths formula = new FormulaDurationMonths(null, Arrays.asList(parameter));
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 1, 10));
		
		FormulaDurationMonths formula = new FormulaDurationMonths(null, Arrays.asList(parameter));
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getDurationValue()} with date parameter
	 */
	@Test
	public void testGetDurationValueDateParameter() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue()).thenReturn(LocalDate.of(2015, 2, 10));
		
		FormulaDurationMonths formula = new FormulaDurationMonths(null, Arrays.asList(parameter));
		
		assertEquals(Duration.ofDays(2 * 31), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getDurationValue()} with duration parameter
	 */
	@Test
	public void testGetDurationValueDurationParameter() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue()).thenReturn(Duration.ofDays(2 * 31));
		
		FormulaDurationMonths formula = new FormulaDurationMonths(null, Arrays.asList(parameter));
		
		assertEquals(Duration.ofDays(2 * 31), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaDurationMonths formula = new FormulaDurationMonths();
		
		assertEquals("months", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.asText()).thenReturn("some_rule");
		
		FormulaDurationMonths formula = new FormulaDurationMonths(null, Arrays.asList(parameter));
		
		assertEquals("months(some_rule)", formula.asText());
	}
	
}
