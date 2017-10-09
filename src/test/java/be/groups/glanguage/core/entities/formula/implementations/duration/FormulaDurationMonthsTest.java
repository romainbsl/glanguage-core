package be.groups.glanguage.core.entities.formula.implementations.duration;

import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaDurationMonths}
 * 
 * @author DUPIREFR
 */
public class FormulaDurationMonthsTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDurationMonths#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaDurationMonths formula = new FormulaDurationMonths();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_MONTHS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDurationMonths formula = new FormulaDurationMonths();
		
		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaDurationMonths#getIntegerValue()} with date parameter
	 */
	@Test
	public void testGetIntegerValueDateParameter() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 2, 10));

		FormulaDurationMonths formula = Mockito.spy(FormulaDurationMonths.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(Integer.valueOf(2), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getIntegerValue()} with duration parameter
	 */
	@Test
	public void testGetIntegerValueDurationParameter() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofDays(2 * 31));

		FormulaDurationMonths formula = Mockito.spy(FormulaDurationMonths.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(Integer.valueOf(2), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

		FormulaDurationMonths formula = Mockito.spy(FormulaDurationMonths.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

		FormulaDurationMonths formula = Mockito.spy(FormulaDurationMonths.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

		FormulaDurationMonths formula = Mockito.spy(FormulaDurationMonths.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DATE);
		when(parameter.getDateValue(null)).thenReturn(LocalDate.of(2015, 1, 10));

		FormulaDurationMonths formula = Mockito.spy(FormulaDurationMonths.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#getDurationValue()} with integer parameter
	 */
	@Test
	public void testGetDurationValueIntegerParameter() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue(null)).thenReturn(2);

		FormulaDurationMonths formula = Mockito.spy(FormulaDurationMonths.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(Duration.ofDays(2 * 31), formula.getDurationValue(null));
	}
	
	/**
	 * Tests {@link FormulaDurationMonths#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaDurationMonths formula = new FormulaDurationMonths();
		
		assertEquals("months", formula.operationAsText());
	}

}
