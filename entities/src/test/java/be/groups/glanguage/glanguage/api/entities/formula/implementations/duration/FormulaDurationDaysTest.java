package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaDurationDays}
 * 
 * @author DUPIREFR
 */
public class FormulaDurationDaysTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDurationDays#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaDurationDays formula = new FormulaDurationDays();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_DAYS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaDurationDays#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDurationDays formula = new FormulaDurationDays();
		
		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaDurationDays#getIntegerValue()} with date parameter
	 */
	@Test
	public void testGetIntegerValueDateParameter() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

		FormulaDurationDays formula = spy(FormulaDurationDays.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(Integer.valueOf(10), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getIntegerValue()} with duration parameter
	 */
	@Test
	public void testGetIntegerValueDurationParameter() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofDays(10L));

		FormulaDurationDays formula = spy(FormulaDurationDays.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(Integer.valueOf(10), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

		FormulaDurationDays formula = spy(FormulaDurationDays.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

		FormulaDurationDays formula = spy(FormulaDurationDays.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

		FormulaDurationDays formula = spy(FormulaDurationDays.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

		FormulaDurationDays formula = spy(FormulaDurationDays.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationDays#getDurationValue()} with integer parameter
	 */
	@Test
	public void testGetDurationValueIntegerParameter() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue(null)).thenReturn(10);

		FormulaDurationDays formula = spy(FormulaDurationDays.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(Duration.ofDays(10L), formula.getDurationValue(null));
	}
	
	/**
	 * Tests {@link FormulaDurationDays#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaDurationDays formula = new FormulaDurationDays();
		
		assertEquals("days", formula.operationAsText());
	}

}
