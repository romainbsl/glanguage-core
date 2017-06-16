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
public class FormulaNotTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaNot#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaNot formula = new FormulaNot();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_NOT), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaNot#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaNot formula = new FormulaNot();
		
		assertFalse(formula.isTerminal());
	}
	
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
	 * Tests {@link FormulaNot#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand.getBooleanValue(null)).thenReturn(true);

		FormulaNot formula = spy(FormulaNot.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaNot#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand.getBooleanValue(null)).thenReturn(true);

		FormulaNot formula = spy(FormulaNot.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaNot#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand.getBooleanValue(null)).thenReturn(true);

		FormulaNot formula = spy(FormulaNot.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaNot#getBooleanValue()} when parameter exists
	 */
	@Test
	public void testGetBooleanValueParameterExists() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand.getBooleanValue(null)).thenReturn(true);

		FormulaNot formula = spy(FormulaNot.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaNot#getBooleanValue()} when parameter doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetBooleanValueParameterNotExist() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand.getBooleanValue(null)).thenReturn(null);

		FormulaNot formula = spy(FormulaNot.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaNot#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand.getBooleanValue(null)).thenReturn(true);

		FormulaNot formula = spy(FormulaNot.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaNot#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand.getBooleanValue(null)).thenReturn(true);

		FormulaNot formula = spy(FormulaNot.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getDurationValue();
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
