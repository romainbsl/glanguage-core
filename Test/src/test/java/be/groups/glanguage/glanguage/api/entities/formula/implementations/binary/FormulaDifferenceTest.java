package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.test.categories.DatabaseTestCategory;

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
	 * Tests {@link FormulaDifference#isValid()} when both operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothIntegers() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid()} when first operand is integer and second numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstIntSecondNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid()} when first operand is integer and second is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstIntSecondNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid()} when second operand is integer and first is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondIntFirstNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid()} when first operand is numeric and second integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNumSecondInt() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid()} when first operand is numeric and second is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstNumSecondNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid()} when second operand is numeric and first is not
	 * integer or numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidSecondNumFirstNotIntOrNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid()} when both operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothNumerics() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid()} when both operands are strings
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothStrings() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid()} when both operands are booleans
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothBooleans() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid()} when both operands are dates
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothDates() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid()} when first operand is a date and second is a
	 * duration
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstDateSecondDuration() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid()} when first operand is a duration and second is a
	 * date
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidFirstDurationSecondDate() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertFalse(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDifference#isValid()} when both operands are durations
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidBothDurations() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertTrue(formula.isValid());
	}
	
	/**
	 * Tests {@link FormulaDifference#getReturnType()} when both operands are integers
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothIntegers() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDifference#getReturnType()} when first operand is integer and second
	 * numeric
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstIntSecondNum() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDifference#getReturnType()} when first operand is numeric and second
	 * integer
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeFirstNumSecondInt() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDifference#getReturnType()} when both operands are numerics
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothNumerics() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDifference#getReturnType()} when both operands are strings
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothStrings() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDifference#getReturnType()} when both operands are booleans
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothBooleans() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.BOOLEAN);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertNull(formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDifference#getReturnType()} when both operands are dates
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothDates() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DATE);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDifference#getReturnType()} when both operands are durations
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeBothDurations() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DURATION);
		
		FormulaDifference formula =
				new FormulaDifference(FormulaDescriptionFactory.getDescription(FormulaType.OP_DIFFERENCE), operand1, operand2);
				
		assertEquals(FormulaReturnType.BOOLEAN, formula.getReturnType());
	}
	
	/**
	 * Tests {@link FormulaDifference#getIntegerValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetIntegerValue() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue()).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue()).thenReturn(0);
		
		FormulaDifference formula = new FormulaDifference(null, operand1, operand2);
		
		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaDifference#getNumericValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetNumericValue() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue()).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue()).thenReturn(0);
		
		FormulaDifference formula = new FormulaDifference(null, operand1, operand2);
		
		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaDifference#getStringValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetStringValue() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue()).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue()).thenReturn(0);
		
		FormulaDifference formula = new FormulaDifference(null, operand1, operand2);
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are integers and
	 * left <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothIntegersLeftDiffRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue()).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue()).thenReturn(0);
		
		FormulaDifference formula = new FormulaDifference(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are integers and
	 * left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothIntegersLeftEqRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue()).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue()).thenReturn(1);
		
		FormulaDifference formula = new FormulaDifference(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are numerics and
	 * left <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothNumericsLeftDiffRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getValue()).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getValue()).thenReturn(0.5);
		
		FormulaDifference formula = new FormulaDifference(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are numerics and
	 * left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothNumericsLeftEqRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getValue()).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getValue()).thenReturn(1.5);
		
		FormulaDifference formula = new FormulaDifference(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are integer and
	 * numeric and left <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixIntNumLeftDiffRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue()).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getValue()).thenReturn(0.5);
		
		FormulaDifference formula = new FormulaDifference(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are integer and
	 * numeric and left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixIntNumLeftEqRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue()).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getValue()).thenReturn(1.0);
		
		FormulaDifference formula = new FormulaDifference(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are numeric and
	 * integer and left <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixNumIntLeftDiffRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getValue()).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue()).thenReturn(0.0);
		
		FormulaDifference formula = new FormulaDifference(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are numeric and
	 * integer and left = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothMixNumIntLeftEqRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getValue()).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue()).thenReturn(1.0);
		
		FormulaDifference formula = new FormulaDifference(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are strings and left
	 * <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothStringsLeftDiffRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(operand1.getValue()).thenReturn("aab");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(operand2.getValue()).thenReturn("aaa");
		
		FormulaDifference formula = new FormulaDifference(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are strings and left
	 * = right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothStringsLeftEqRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(operand1.getValue()).thenReturn("aaa");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.STRING);
		when(operand2.getValue()).thenReturn("aaa");
		
		FormulaDifference formula = new FormulaDifference(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are dates and left
	 * <> right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDatesLeftDiffRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(operand1.getValue()).thenReturn(LocalDate.of(2015, 1, 1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(operand2.getValue()).thenReturn(LocalDate.of(2014, 1, 1));
		
		FormulaDifference formula = new FormulaDifference(null, operand1, operand2);
		
		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getBooleanValue()} when parameters exist, are dates and left =
	 * right
	 */
	@Test
	public void testGetBooleanValueParametersExistBothDatesLeftEqRight() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(operand1.getValue()).thenReturn(LocalDate.of(2015, 1, 1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.DATE);
		when(operand2.getValue()).thenReturn(LocalDate.of(2015, 1, 1));
		
		FormulaDifference formula = new FormulaDifference(null, operand1, operand2);
		
		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaDifference#getDateValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDateValue() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue()).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue()).thenReturn(0);
		
		FormulaDifference formula = new FormulaDifference(null, operand1, operand2);
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaDifference#getDurationValue()}
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testGetDurationValue() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getValue()).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getValue()).thenReturn(0);
		
		FormulaDifference formula = new FormulaDifference(null, operand1, operand2);
		
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
	public void testAsText() {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.asText()).thenReturn("some_rule1");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType()).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.asText()).thenReturn("some_rule2");
		
		FormulaDifference formula = new FormulaDifference(null, operand1, operand2);
		
		assertEquals("some_rule1 <> some_rule2", formula.asText());
	}
	
}
