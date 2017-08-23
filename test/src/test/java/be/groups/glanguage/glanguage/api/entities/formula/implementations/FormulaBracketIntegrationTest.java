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

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
