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
 * Test class for {@link FormulaDurationMinutes}
 * 
 * @author DUPIREFR
 */
public class FormulaDurationMinutesTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDurationMinutes#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaDurationMinutes formula = new FormulaDurationMinutes();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_MINUTES), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDurationMinutes formula = new FormulaDurationMinutes();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#isValid()} with integer parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidInteger() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaDurationMinutes formula =
				new FormulaDurationMinutes(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES), Arrays.asList(parameter));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#isValid()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDate() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaDurationMinutes formula =
				new FormulaDurationMinutes(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES), Arrays.asList(parameter));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#isValid()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDurationMinutes formula =
				new FormulaDurationMinutes(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES), Arrays.asList(parameter));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#isValid()} with parameter not integer nor date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotIntegerOrDateOrDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaDurationMinutes formula =
				new FormulaDurationMinutes(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES), Arrays.asList(parameter));
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getReturnType()} with integer parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeInteger() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaDurationMinutes formula =
				new FormulaDurationMinutes(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.DURATION, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getReturnType()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDate() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaDurationMinutes formula =
				new FormulaDurationMinutes(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getReturnType()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDurationMinutes formula =
				new FormulaDurationMinutes(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getReturnType()} with parameter not integer nor date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotIntegerOrDateOrDuration() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaDurationMinutes formula =
				new FormulaDurationMinutes(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES), Arrays.asList(parameter));
				
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(parameter));
		
		assertEquals(Integer.valueOf(120), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(parameter));
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(parameter));
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(parameter));
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(parameter));
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue()).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(parameter));
		
		assertEquals(Duration.ofMinutes(120L), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaDurationMinutes formula = new FormulaDurationMinutes();
		
		assertEquals("minutes", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#asText()}
	 */
	@Test
	public void testAsText() {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.asText()).thenReturn("some_rule");
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(parameter));
		
		assertEquals("minutes(some_rule)", formula.asText());
	}
	
}
