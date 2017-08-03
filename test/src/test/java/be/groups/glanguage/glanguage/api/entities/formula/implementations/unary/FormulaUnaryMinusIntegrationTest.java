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
 * Test class for {@link FormulaUnaryMinus}
 * 
 * @author DUPIREFR
 */
public class FormulaUnaryMinusIntegrationTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaUnaryMinus#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaUnaryMinus formula = new FormulaUnaryMinus();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_UNARY_MINUS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaUnaryMinus formula = new FormulaUnaryMinus();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#isValid(Evaluator)} when operand is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidInteger() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#isValid(Evaluator)} when operand is numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNumeric() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#isValid(Evaluator)} when operand is not integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotIntegerOrNumeric() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

		FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getReturnType()} when operand is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeInteger() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getReturnType()} when operand is numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNumeric() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getReturnType()} when operand is not integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotIntegerOrNumeric() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

		FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getIntegerValue()} when parameter exists
	 */
	@Test
	public void testGetIntegerValueParameterExists() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);

		FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(Integer.valueOf(-1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getIntegerValue()} when parameter doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetIntegerValueParameterNotExists() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(null);

		FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getNumericValue()} when parameter exists
	 */
	@Test
	public void testGetNumericValueParameterExists() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue(null)).thenReturn(1.5);

		FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(Double.valueOf(-1.5), formula.getNumericValue());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getNumericValue()} when parameter doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetNumericValueParameterNotExists() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue(null)).thenReturn(null);

		FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);

		FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);

		FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);

		FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);

		FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaUnaryMinus formula = new FormulaUnaryMinus();
		
		assertEquals("-", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaUnaryMinus#asText()}
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testAsText() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.asText()).thenReturn("some_rule");

		FormulaUnaryMinus formula = spy(FormulaUnaryMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_UNARY_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals("- some_rule", formula.asText());
	}
	
}
