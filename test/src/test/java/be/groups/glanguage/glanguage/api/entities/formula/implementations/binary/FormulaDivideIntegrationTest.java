package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

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
 * Test class for {@link FormulaDivide}
 * 
 * @author DUPIREFR
 */
public class FormulaDivideIntegrationTest extends BaseDatabaseTest {
	
	/*
	 * Constants
	 */
	private static final double DELTA = 1e-15;
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDivide#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaDivide formula = new FormulaDivide();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_DIVIDE), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaDivide#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDivide formula = new FormulaDivide();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaDivide#isValid(Evaluator)} when operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidMatching() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaDivide formula = spy(FormulaDivide.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIVIDE)).when(formula).getDescription();
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}

	/**
	 * Tests {@link FormulaDivide#isValid(Evaluator)} when operands are not numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotMatching() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaDivide formula = spy(FormulaDivide.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIVIDE)).when(formula).getDescription();
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDivide#getReturnType()} when operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeMatching() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaDivide formula = spy(FormulaDivide.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIVIDE)).when(formula).getDescription();
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDivide#getReturnType()} when operands are not numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotMatching() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaDivide formula = spy(FormulaDivide.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIVIDE)).when(formula).getDescription();
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaDivide#asText()}
	 */
	@Test
	@Category(DatabaseTestCategory.class)
	public void testAsText() throws GLanguageException {
		AbstractFormula numerator = mock(AbstractFormula.class);
		when(numerator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(numerator.asText()).thenReturn("some_rule1");
		
		AbstractFormula denominator = mock(AbstractFormula.class);
		when(denominator.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(denominator.asText()).thenReturn("some_rule2");

		FormulaDivide formula = spy(FormulaDivide.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIVIDE)).when(formula).getDescription();
		doReturn(Arrays.asList(numerator, denominator)).when(formula).getParameters();

		assertEquals("some_rule1 / some_rule2", formula.asText());
	}
	
}
