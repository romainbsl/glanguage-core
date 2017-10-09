package be.groups.glanguage.core.entities.formula.implementations.binary;

import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaPlus}
 * 
 * @author DUPIREFR
 */
public class FormulaPlusTest {
	
	/*
	 * Constants
	 */
	private static final double DELTA = 1e-15;
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaPlus#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaPlus formula = new FormulaPlus();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_PLUS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaPlus#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaPlus formula = new FormulaPlus();
		
		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaPlus#getIntegerValue()} when both parameters are integers
	 */
	@Test
	public void testGetIntegerValueIntInt() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getIntegerValue(null)).thenReturn(1);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getIntegerValue(null)).thenReturn(2);

		FormulaPlus formula = spy(FormulaPlus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Integer.valueOf(3), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaPlus#getNumericValue()} when both parameters are numerics
	 */
	@Test
	public void testGetNumericValueNumNum() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand1.getNumericValue(null)).thenReturn(1.5);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(operand2.getNumericValue(null)).thenReturn(2.3);

		FormulaPlus formula = spy(FormulaPlus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Double.valueOf(3.8), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaPlus#getNumericValue()} when first parameter is integer and second is
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

		FormulaPlus formula = spy(FormulaPlus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Double.valueOf(3.3), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaPlus#getNumericValue()} when first parameter is numeric and second is
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

		FormulaPlus formula = spy(FormulaPlus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Double.valueOf(3.3), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaPlus#getStringValue()}
	 */
	@Test
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand1.getStringValue(null)).thenReturn("some_value1");
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(operand2.getStringValue(null)).thenReturn("some_value2");

		FormulaPlus formula = spy(FormulaPlus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals("some_value1some_value2", formula.getStringValue());
	}
	
	/**
	 * Tests {@link FormulaPlus#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(2.0);

		FormulaPlus formula = spy(FormulaPlus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaPlus#getDateValue()} when operands are not of the good type
	 */
	@Test(expected = IllegalStateException.class)
	public void testGetDateValueWrongParameterTypes() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(2.0);

		FormulaPlus formula = spy(FormulaPlus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaPlus#getDateValue()} when first operand is a date and second operand is a duration
	 */
	public void testGetDateValueFirstDateSecondDuration() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand1.getDateValue(null)).thenReturn(LocalDate.of(2016, 1, 1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(operand2.getDurationValue(null)).thenReturn(Duration.ofDays(1));

		FormulaPlus formula = spy(FormulaPlus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(LocalDate.of(2016, 1, 2), formula.getDateValue());
	}
	
	/**
	 * Tests {@link FormulaPlus#getDateValue()} when first operand is a duration and second operand is a date
	 */
	public void testGetDateValueFirstDurationSecondDate() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(operand1.getDurationValue(null)).thenReturn(Duration.ofDays(1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(operand2.getDateValue(null)).thenReturn(LocalDate.of(2016, 1, 1));

		FormulaPlus formula = spy(FormulaPlus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(LocalDate.of(2016, 1, 2), formula.getDateValue());
	}
	
	/**
	 * Tests {@link FormulaPlus#getDurationValue()} when operands are not of the good type
	 */
	@Test(expected = NullPointerException.class)
	public void testGetDurationValueWrongParameterTypes() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand1.getNumericValue(null)).thenReturn(1.0);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(operand2.getNumericValue(null)).thenReturn(2.0);

		FormulaPlus formula = spy(FormulaPlus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaPlus#getDurationValue()}
	 */
	public void testGetDurationValueBothDuration() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(operand1.getDurationValue(null)).thenReturn(Duration.ofDays(1));
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(operand2.getDurationValue(null)).thenReturn(Duration.ofDays(1));

		FormulaPlus formula = spy(FormulaPlus.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Duration.ofDays(2), formula.getDurationValue());
	}
	
	/**
	 * Tests {@link FormulaPlus#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaPlus formula = new FormulaPlus();
		
		assertEquals("+", formula.operationAsText());
	}

}
