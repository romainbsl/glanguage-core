package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
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
 * Test class for {@link FormulaDurationHours}
 * 
 * @author DUPIREFR
 */
public class FormulaDurationHoursTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDurationHours#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaDurationHours formula = new FormulaDurationHours();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_HOURS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaDurationHours#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDurationHours formula = new FormulaDurationHours();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaDurationHours#isValid()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDate() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaDurationHours formula =
				new FormulaDurationHours(FormulaDescriptionFactory.getDescription(FormulaType.F_HOURS), Arrays.asList(parameter));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationHours#isValid()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDurationHours formula =
				new FormulaDurationHours(FormulaDescriptionFactory.getDescription(FormulaType.F_HOURS), Arrays.asList(parameter));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationHours#isValid()} with parameter not date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotDateOrDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaDurationHours formula =
				new FormulaDurationHours(FormulaDescriptionFactory.getDescription(FormulaType.F_HOURS), Arrays.asList(parameter));
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getReturnType()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDate() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaDurationHours formula =
				new FormulaDurationHours(FormulaDescriptionFactory.getDescription(FormulaType.F_HOURS), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.DURATION, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getReturnType()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDurationHours formula =
				new FormulaDurationHours(FormulaDescriptionFactory.getDescription(FormulaType.F_HOURS), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.DURATION, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getReturnType()} with parameter not date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotDateOrDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaDurationHours formula =
				new FormulaDurationHours(FormulaDescriptionFactory.getDescription(FormulaType.F_HOURS), Arrays.asList(parameter));
				
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationHours formula = new FormulaDurationHours(null, Arrays.asList(parameter));
		
		assertEquals(Integer.valueOf(2), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationHours formula = new FormulaDurationHours(null, Arrays.asList(parameter));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationHours formula = new FormulaDurationHours(null, Arrays.asList(parameter));
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationHours formula = new FormulaDurationHours(null, Arrays.asList(parameter));
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationHours formula = new FormulaDurationHours(null, Arrays.asList(parameter));
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationHours formula = new FormulaDurationHours(null, Arrays.asList(parameter));
		
		assertEquals(Duration.ofHours(2L), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaDurationHours#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaDurationHours formula = new FormulaDurationHours();
		
		assertEquals("hours", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaDurationHours#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.asText()).thenReturn("some_rule");
		
		FormulaDurationHours formula = new FormulaDurationHours(null, Arrays.asList(parameter));
		
		assertEquals("hours(some_rule)", formula.asText());
	}
	
}
