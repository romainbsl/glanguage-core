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
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
	public void testIsValidInteger() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaDurationMinutes formula =
				new FormulaDurationMinutes(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES), Arrays.asList(parameter));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#isValid()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDate() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaDurationMinutes formula =
				new FormulaDurationMinutes(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES), Arrays.asList(parameter));
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#isValid()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDuration() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDurationMinutes formula =
				new FormulaDurationMinutes(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES), Arrays.asList(parameter));
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#isValid()} with parameter not integer nor date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotIntegerOrDateOrDuration() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaDurationMinutes formula =
				new FormulaDurationMinutes(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES), Arrays.asList(parameter));
				
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
				new FormulaDurationMinutes(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.DURATION, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getReturnType()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDate() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		FormulaDurationMinutes formula =
				new FormulaDurationMinutes(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES), Arrays.asList(parameter));
				
		assertNull(formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getReturnType()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDuration() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDurationMinutes formula =
				new FormulaDurationMinutes(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES), Arrays.asList(parameter));
				
		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getReturnType()} with parameter not integer nor date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotIntegerOrDateOrDuration() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaDurationMinutes formula =
				new FormulaDurationMinutes(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES), Arrays.asList(parameter));
				
		assertNull(formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getIntegerValue()} when parameter is a duration
	 */
	@Test
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(parameter));
		
		assertEquals(Integer.valueOf(120), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(parameter));
		
		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(parameter));
		
		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getBooleanValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(parameter));
		
		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofHours(2L));
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(parameter));
		
		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue(null)).thenReturn(120);
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(parameter));
		
		assertEquals(Duration.ofMinutes(120L), formula.getDurationValue(null));
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
	public void testAsText() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.asText()).thenReturn("some_rule");
		
		FormulaDurationMinutes formula = new FormulaDurationMinutes(null, Arrays.asList(parameter));
		
		assertEquals("minutes(some_rule)", formula.asText());
	}
	
}
