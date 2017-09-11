package be.groups.glanguage.glanguage.api.entities.formula.implementations.string;

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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaStringLength}
 * 
 * @author DUPIREFR
 */
public class FormulaStringLengthIntegrationTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */

	/**
	 * Tests {@link FormulaStringLength#isValid(Evaluator)} when parameter is string
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidMatching() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		FormulaStringLength formula = spy(FormulaStringLength.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_STRING_LENGTH)).when(formula).getDescription();
		doReturn(Arrays.asList(string)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaStringLength#isValid(Evaluator)} when parameter is not string
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotMatching() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaStringLength formula = spy(FormulaStringLength.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_STRING_LENGTH)).when(formula).getDescription();
		doReturn(Arrays.asList(string)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaStringLength#getReturnType()} when parameter is string
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeMatching() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		FormulaStringLength formula = spy(FormulaStringLength.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_STRING_LENGTH)).when(formula).getDescription();
		doReturn(Arrays.asList(string)).when(formula).getParameters();

		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaStringLength#getReturnType()} when parameter is not string
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotMatching() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaStringLength formula = spy(FormulaStringLength.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_STRING_LENGTH)).when(formula).getDescription();
		doReturn(Arrays.asList(string)).when(formula).getParameters();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
	}

	/**
	 * Tests {@link FormulaStringLength#asText()}
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testAsText() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(string.asText()).thenReturn("some_rule");

		FormulaStringLength formula = spy(FormulaStringLength.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_STRING_LENGTH)).when(formula).getDescription();
		doReturn(Arrays.asList(string)).when(formula).getParameters();
		
		assertEquals("stringLength(some_rule)", formula.asText());
	}
	
}
