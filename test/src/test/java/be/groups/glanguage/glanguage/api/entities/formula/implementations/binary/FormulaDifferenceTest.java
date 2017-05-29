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

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaDifference}
 * 
 * @author DUPIREFR
 */
public class FormulaDifferenceTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDifference#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaDifference formula = new FormulaDifference();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_DIFFERENCE), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDifference formula = new FormulaDifference();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid(Evaluator)} when both operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothIntegers() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();
				
		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid(Evaluator)} when first operand is integer and second numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstIntSecondNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid(Evaluator)} when first operand is integer and second is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstIntSecondNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid(Evaluator)} when second operand is integer and first is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondIntFirstNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid(Evaluator)} when first operand is numeric and second integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNumSecondInt() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid(Evaluator)} when first operand is numeric and second is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNumSecondNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid(Evaluator)} when second operand is numeric and first is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondNumFirstNotIntOrNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid(Evaluator)} when both operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothNumerics() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid(Evaluator)} when both operands are strings
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothStrings() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid(Evaluator)} when both operands are booleans
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothBooleans() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid(Evaluator)} when both operands are dates
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothDates() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid(Evaluator)} when first operand is a date and second is a
	 * duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstDateSecondDuration() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid(Evaluator)} when first operand is a duration and second is a
	 * date
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstDurationSecondDate() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid(Evaluator)} when both operands are durations
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothDurations() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDifference#getReturnType()} when both operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothIntegers() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDifference#getReturnType()} when first operand is integer and second
	 * numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstIntSecondNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDifference#getReturnType()} when first operand is numeric and second
	 * integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstNumSecondInt() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDifference#getReturnType()} when both operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothNumerics() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDifference#getReturnType()} when both operands are strings
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothStrings() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDifference#getReturnType()} when both operands are booleans
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothBooleans() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDifference#getReturnType()} when both operands are dates
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothDates() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDifference#getReturnType()} when both operands are durations
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothDurations() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDifference#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(0);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaDifference#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(0);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaDifference#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(0);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are integers and
	 * left <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothIntegersLeftDiffRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(0);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are integers and
	 * left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothIntegersLeftEqRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(1);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are numerics and
	 * left <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothNumericsLeftDiffRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getValue(null)).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getValue(null)).thenReturn(0.5);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are numerics and
	 * left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothNumericsLeftEqRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getValue(null)).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getValue(null)).thenReturn(1.5);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are integer and
	 * numeric and left <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixIntNumLeftDiffRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getValue(null)).thenReturn(0.5);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are integer and
	 * numeric and left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixIntNumLeftEqRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getValue(null)).thenReturn(1.0);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are numeric and
	 * integer and left <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixNumIntLeftDiffRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getValue(null)).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(0.0);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are numeric and
	 * integer and left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixNumIntLeftEqRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getValue(null)).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(1.0);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are strings and left
	 * <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothStringsLeftDiffRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand1.getValue(null)).thenReturn("aab");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand2.getValue(null)).thenReturn("aaa");

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are strings and left
	 * = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothStringsLeftEqRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand1.getValue(null)).thenReturn("aaa");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand2.getValue(null)).thenReturn("aaa");

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are dates and left
	 * <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDatesLeftDiffRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand1.getValue(null)).thenReturn(LocalDate.of(2015, 1, 1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand2.getValue(null)).thenReturn(LocalDate.of(2014, 1, 1));

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are dates and left =
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDatesLeftEqRight() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand1.getValue(null)).thenReturn(LocalDate.of(2015, 1, 1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand2.getValue(null)).thenReturn(LocalDate.of(2015, 1, 1));

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(0);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaDifference#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue(null)).thenReturn(0);

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaDifference#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaDifference formula = new FormulaDifference();
		
		assertEquals("<>", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaDifference#asText()}
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

		FormulaDifference formula = spy(FormulaDifference.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE)).when(formula).getDescription();
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals("some_rule1 <> some_rule2", formula.asText());
	}
	
}
