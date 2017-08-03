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
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaDurationMonths}
 * 
 * @author DUPIREFR
 */
public class FormulaDurationMonthsIntegrationTest extends BaseDatabaseTest {
	
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
	 * Tests {@link FormulaDurationMonths#isValid(Evaluator)} with integer parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidInteger() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaDurationMonths formula = spy(FormulaDurationMonths.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_MONTHS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#isValid(Evaluator)} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDate() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

		FormulaDurationMonths formula = spy(FormulaDurationMonths.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_MONTHS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#isValid(Evaluator)} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDuration() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

		FormulaDurationMonths formula = spy(FormulaDurationMonths.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_MONTHS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#isValid(Evaluator)} with parameter not integer nor date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotIntegerOrDateOrDuration() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		FormulaDurationMonths formula = spy(FormulaDurationMonths.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_MONTHS)).when(formula).getDescription();
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

		FormulaDurationMonths formula = spy(FormulaDurationMonths.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_MONTHS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(FormulaReturnType.DURATION, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getReturnType()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDate() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

		FormulaDurationMonths formula = spy(FormulaDurationMonths.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_MONTHS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getReturnType()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDuration() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

		FormulaDurationMonths formula = spy(FormulaDurationMonths.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_MONTHS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getReturnType()} with parameter not integer nor date nor duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotIntegerOrDateOrDuration() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		FormulaDurationMonths formula = spy(FormulaDurationMonths.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_MONTHS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getIntegerValue()} with date parameter
	 */
	@Test
	public void testGetIntegerValueDateParameter() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 2, 10));

		FormulaDurationMonths formula = spy(FormulaDurationMonths.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(Integer.valueOf(2), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getIntegerValue()} with duration parameter
	 */
	@Test
	public void testGetIntegerValueDurationParameter() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofDays(2 * 31));

		FormulaDurationMonths formula = spy(FormulaDurationMonths.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(Integer.valueOf(2), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

		FormulaDurationMonths formula = spy(FormulaDurationMonths.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

		FormulaDurationMonths formula = spy(FormulaDurationMonths.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

		FormulaDurationMonths formula = spy(FormulaDurationMonths.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

		FormulaDurationMonths formula = spy(FormulaDurationMonths.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getDurationValue()} with integer parameter
	 */
	@Test
	public void testGetDurationValueIntegerParameter() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue(null)).thenReturn(2);

		FormulaDurationMonths formula = spy(FormulaDurationMonths.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(Duration.ofDays(2 * 31), formula.getDurationValue(null));
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
	@Category({DatabaseTestCategory.class})
	public void testAsText() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.asText()).thenReturn("some_rule");

		FormulaDurationMonths formula = spy(FormulaDurationMonths.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_MONTHS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals("months(some_rule)", formula.asText());
	}
	
}
