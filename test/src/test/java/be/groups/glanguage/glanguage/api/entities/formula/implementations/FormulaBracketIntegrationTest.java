package be.groups.glanguage.glanguage.api.entities.formula.implementations;

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
 * Test class for {@link FormulaBracket}
 * 
 * @author DUPIREFR
 */
public class FormulaBracketIntegrationTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaBracket#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaBracket formula = new FormulaBracket();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_BRACKETS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaBracket#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaBracket formula = new FormulaBracket();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaBracket#isValid(Evaluator)} with integer parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidInteger() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaBracket#isValid(Evaluator)} with numeric parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNumeric() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaBracket#isValid(Evaluator)} with string parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidString() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaBracket#isValid(Evaluator)} with boolean parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBoolean() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaBracket#isValid(Evaluator)} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDate() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaBracket#isValid(Evaluator)} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidDuration() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaBracket#getReturnType()} with integer parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeInteger() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaBracket#getReturnType()} with numeric parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNumeric() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaBracket#getReturnType()} with string parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeString() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(FormulaReturnType.STRING, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaBracket#getReturnType()} with boolean parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBoolean() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaBracket#getReturnType()} with date parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDate() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(FormulaReturnType.DATE, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaBracket#getReturnType()} with duration parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeDuration() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(FormulaReturnType.DURATION, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaBracket#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getIntegerValue(null)).thenReturn(1);

		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(Integer.valueOf(1), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaBracket#getNumericValue()}
	 */
	@Test
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getNumericValue(null)).thenReturn(2.5);

		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(Double.valueOf(2.5), formula.getNumericValue(null));
	}
	
	/**
	 * Tests {@link FormulaBracket#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getStringValue(null)).thenReturn("some_value");

		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals("some_value", formula.getStringValue(null));
	}
	
	/**
	 * Tests {@link FormulaBracket#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getBooleanValue(null)).thenReturn(true);

		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(Boolean.TRUE, formula.getBooleanValue(null));
	}
	
	/**
	 * Tests {@link FormulaBracket#getDateValue()}
	 */
	@Test
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 1));

		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(LocalDate.of(2015, 1, 1), formula.getDateValue(null));
	}
	
	/**
	 * Tests {@link FormulaBracket#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofDays(2L));

		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(Duration.ofDays(2L), formula.getDurationValue(null));
	}
	
	/**
	 * Tests {@link FormulaBracket#asText()}
	 */
	@Test
	@Category(DatabaseTestCategory.class)
	public void testAsText() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(parameter.asText()).thenReturn("some_rule");

		FormulaBracket formula = spy(FormulaBracket.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_BRACKETS)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals("(some_rule)", formula.asText());
	}
	
}
