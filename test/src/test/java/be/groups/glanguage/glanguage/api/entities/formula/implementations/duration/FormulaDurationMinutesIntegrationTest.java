package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
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
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaDurationMinutes}
 * 
 * @author DUPIREFR
 */
public class FormulaDurationMinutesIntegrationTest extends BaseDatabaseTest {
	
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
	 * Tests {@link FormulaDurationMinutes#isValid(Evaluator)} with integer parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidInteger() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaDurationMinutes formula = spy(FormulaDurationMinutes.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#isValid(Evaluator)} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDate() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

		FormulaDurationMinutes formula = spy(FormulaDurationMinutes.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#isValid(Evaluator)} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDuration() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

		FormulaDurationMinutes formula = spy(FormulaDurationMinutes.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#isValid(Evaluator)} with parameter not integer nor date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotIntegerOrDateOrDuration() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		FormulaDurationMinutes formula = spy(FormulaDurationMinutes.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getReturnType()} with integer parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeInteger() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaDurationMinutes formula = spy(FormulaDurationMinutes.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

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

		FormulaDurationMinutes formula = spy(FormulaDurationMinutes.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getReturnType()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDuration() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

		FormulaDurationMinutes formula = spy(FormulaDurationMinutes.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

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

		FormulaDurationMinutes formula = spy(FormulaDurationMinutes.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getIntegerValue()} when parameter is a duration
	 */
	@Test
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofHours(2L));

		FormulaDurationMinutes formula = spy(FormulaDurationMinutes.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(Integer.valueOf(120), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofHours(2L));

		FormulaDurationMinutes formula = spy(FormulaDurationMinutes.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofHours(2L));

		FormulaDurationMinutes formula = spy(FormulaDurationMinutes.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofHours(2L));

		FormulaDurationMinutes formula = spy(FormulaDurationMinutes.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationMinutes#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofHours(2L));

		FormulaDurationMinutes formula = spy(FormulaDurationMinutes.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

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

		FormulaDurationMinutes formula = spy(FormulaDurationMinutes.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

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
	@Category({DatabaseTestCategory.class})
	public void testAsText() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.asText()).thenReturn("some_rule");

		FormulaDurationMinutes formula = spy(FormulaDurationMinutes.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_MINUTES)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals("minutes(some_rule)", formula.asText());
	}
	
}
