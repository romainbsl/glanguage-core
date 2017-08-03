package be.groups.glanguage.glanguage.api.entities.formula.implementations.math;

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
 * Test class for {@link FormulaMathAbs}
 * 
 * @author DUPIREFR
 */
public class FormulaMathAbsIntegrationTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaMathAbs#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaMathAbs formula = new FormulaMathAbs();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_ABS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaMathAbs#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaMathAbs formula = new FormulaMathAbs();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaMathAbs#isValid(Evaluator)} when operand is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidInteger() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaMathAbs formula = spy(FormulaMathAbs.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ABS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaMathAbs#isValid(Evaluator)} when operand is numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNumeric() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaMathAbs formula = spy(FormulaMathAbs.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ABS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaMathAbs#isValid(Evaluator)} when operand is not integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotIntegerOrNumeric() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		FormulaMathAbs formula = spy(FormulaMathAbs.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ABS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getReturnType()} when operand is integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeInteger() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaMathAbs formula = spy(FormulaMathAbs.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ABS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getReturnType()} when operand is numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNumeric() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaMathAbs formula = spy(FormulaMathAbs.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ABS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getReturnType()} when operand is not integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotIntegerOrNumeric() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		FormulaMathAbs formula = spy(FormulaMathAbs.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ABS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getIntegerValue()} when operand exists and is positive
	 */
	@Test
	public void testGetIntegerValueParameterExistsPositive() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getNumericValue(null)).thenReturn(1.0);

		FormulaMathAbs formula = spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(Integer.valueOf(1), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getIntegerValue()} when operand exists and is negative
	 */
	@Test
	public void testGetIntegerValueParameterExistsNegative() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getNumericValue(null)).thenReturn(-1.0);

		FormulaMathAbs formula = spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(Integer.valueOf(1), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getIntegerValue()} when operand doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetIntegerValueParameterNotExists() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getNumericValue(null)).thenReturn(null);

		FormulaMathAbs formula = spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getIntegerValue(null);
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getNumericValue()} when operand exists and is positive
	 */
	@Test
	public void testGetNumericValueParameterExistsPositive() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue(null)).thenReturn(1.5);

		FormulaMathAbs formula = spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(Double.valueOf(1.5), formula.getNumericValue(null));
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getNumericValue()} when operand exists and is negative
	 */
	@Test
	public void testGetNumericValueParameterExistsNegative() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue(null)).thenReturn(-1.5);

		FormulaMathAbs formula = spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals(Double.valueOf(1.5), formula.getNumericValue(null));
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getNumericValue()} when operand doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetNumericValueParameterNotExists() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand.getNumericValue(null)).thenReturn(null);

		FormulaMathAbs formula = spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);

		FormulaMathAbs formula = spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);

		FormulaMathAbs formula = spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);

		FormulaMathAbs formula = spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaMathAbs#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.getIntegerValue(null)).thenReturn(1);

		FormulaMathAbs formula = spy(FormulaMathAbs.class);
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		formula.getDurationValue(null);
	}
	
	/**
	 * Tests {@link FormulaMathAbs#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaMathAbs formula = new FormulaMathAbs();
		
		assertEquals("abs", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaMathAbs#asText()}
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testAsText() throws GLanguageException {
		AbstractFormula operand = mock(AbstractFormula.class);
		when(operand.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand.asText()).thenReturn("some_rule");

		FormulaMathAbs formula = spy(FormulaMathAbs.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_ABS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand)).when(formula).getParameters();

		assertEquals("abs(some_rule)", formula.asText());
	}
	
}
