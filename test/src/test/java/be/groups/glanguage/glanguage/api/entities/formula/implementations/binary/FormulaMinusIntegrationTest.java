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

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaMinus}
 * 
 * @author DUPIREFR
 */
public class FormulaMinusIntegrationTest extends BaseDatabaseTest {
	
	/*
	 * Constants
	 */
	private static final double DELTA = 1e-15;
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaMinus#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaMinus formula = new FormulaMinus();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_MINUS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaMinus#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaMinus formula = new FormulaMinus();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid(Evaluator)} when both operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothIntegers() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();
		
		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid(Evaluator)} when first operand is integer and second numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstIntSecondNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid(Evaluator)} when first operand is integer and second is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstIntSecondNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid(Evaluator)} when second operand is integer and first is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondIntFirstNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid(Evaluator)} when first operand is numeric and second integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNumSecondInt() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid(Evaluator)} when first operand is numeric and second is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNumSecondNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid(Evaluator)} when second operand is numeric and first is not integer
	 * or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondNumFirstNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid(Evaluator)} when both operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothNumerics() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid(Evaluator)} when first operand is a date and second is a duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstDateSecondDuration() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid(Evaluator)} when both operands are durations
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothDurations() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid(Evaluator)} when first operand is a duration and second is a date
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstDurationSecondDate() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaMinus#isValid(Evaluator)} when both operands are dates
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothDate() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
		
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when both operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothIntegers() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.INTEGER, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when first operand is integer and second numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstIntSecondNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when first operand is integer and second is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstIntSecondNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when second operand is integer and first is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeSecondIntFirstNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when first operand is numeric and second integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstNumSecondInt() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when first operand is numeric and second is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstNumSecondNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when second operand is numeric and first is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeSecondNumFirstNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when both operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothNumerics() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.NUMERIC, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when both operands are durations
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothDurations() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.DURATION, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when first operand is a date and second is a duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstDateSecondDuration() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.DATE, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getReturnType()} when first operand is a duration and second is a date
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstDurationSecondDate() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaMinus#getIntegerValue()} when both parameters are integers
	 */
	@Test
	public void testGetIntegerValueIntInt() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getIntegerValue(null)).thenReturn(2);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Integer.valueOf(-1), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaMinus#getNumericValue()} when both parameters are numerics
	 */
	@Test
	public void testGetNumericValueNumNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue(null)).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue(null)).thenReturn(2.3);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Double.valueOf(-0.8), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaMinus#getNumericValue()} when first parameter is integer and second is
	 * numeric
	 */
	@Test
	public void testGetNumericValueIntNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue(null)).thenReturn(2.3);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Double.valueOf(-1.3), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaMinus#getNumericValue()} when first parameter is numeric and second is
	 * integer
	 */
	@Test
	public void testGetNumericValueNumInt() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue(null)).thenReturn(2.3);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(1.0);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Double.valueOf(1.3), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaMinus#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(2.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(1.0);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaMinus#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(2.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(1.0);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaMinus#getDateValue()}
	 */
	@Test(expected = NullPointerException.class)
	public void testGetDateValueWrongTypes() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(2.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(1.0);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaMinus#getDateValue()} when first operand is a date and second operand is a duration
	 */
	@Test
	public void testGetDateValueFirstDateSecondDuration() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand1.getDateValue(null)).thenReturn(LocalDate.of(2016, 1, 1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(operand2.getDurationValue(null)).thenReturn(Duration.ofDays(1));

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(LocalDate.of(2015, 12, 31), formula.getDateValue());
	}
	
	/**
	 * Tests {@link FormulaMinus#getDurationValue()}
	 */
	@Test(expected = NullPointerException.class)
	public void testGetDurationValueWrongTypes() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(2.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(1.0);

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaMinus#getDateValue()} when both operands are durations
	 */
	@Test
	public void testGetDurationValueBothDuration() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(operand1.getDurationValue(null)).thenReturn(Duration.ofDays(2));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(operand2.getDurationValue(null)).thenReturn(Duration.ofDays(1));

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Duration.ofDays(1), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaMinus#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaMinus formula = new FormulaMinus();
		
		assertEquals("-", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaMinus#asText()}
	 */
	@Test
	@Category(DatabaseTestCategory.class)
	public void testAsText() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.asText()).thenReturn("some_rule1");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.asText()).thenReturn("some_rule2");

		FormulaMinus formula = spy(FormulaMinus.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_MINUS)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals("some_rule1 - some_rule2", formula.asText());
	}
	
}
