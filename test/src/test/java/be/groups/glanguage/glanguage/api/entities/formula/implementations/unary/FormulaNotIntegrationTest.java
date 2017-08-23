package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

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
 * Test class for {@link FormulaNot}
 * 
 * @author DUPIREFR
 */
public class FormulaNotIntegrationTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */

	/**
	 * Tests {@link FormulaNot#isValid(Evaluator)} when operand is boolean
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBoolean() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

		FormulaNot formula = spy(FormulaNot.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_NOT)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();
		
		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaNot#isValid(Evaluator)} when operand is not boolean
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotBoolean() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

		FormulaNot formula = spy(FormulaNot.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_NOT)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaNot#getReturnType()} when operand is boolean
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBoolean() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

		FormulaNot formula = spy(FormulaNot.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_NOT)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaNot#getReturnType()} when operand is not boolean
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotBoolean() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

		FormulaNot formula = spy(FormulaNot.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_NOT)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
	}

	/**
	 * Tests {@link FormulaNot#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaNot formula = new FormulaNot();
		
		assertEquals("not", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaNot#asText()}
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testAsText() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand.asText()).thenReturn("some_rule");

		FormulaNot formula = spy(FormulaNot.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_NOT)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals("not some_rule", formula.asText());
	}
	
}
