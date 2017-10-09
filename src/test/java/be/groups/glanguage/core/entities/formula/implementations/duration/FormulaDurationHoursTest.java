package be.groups.glanguage.core.entities.formula.implementations.duration;

import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaDurationHours}
 * 
 * @author DUPIREFR
 */
public class FormulaDurationHoursTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDurationHours#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaDurationHours formula = new FormulaDurationHours();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_HOURS), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaDurationHours#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDurationHours formula = new FormulaDurationHours();
		
		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaDurationHours#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofHours(2L));

		FormulaDurationHours formula = Mockito.spy(FormulaDurationHours.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(Integer.valueOf(2), formula.getIntegerValue(null));
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofHours(2L));

		FormulaDurationHours formula = Mockito.spy(FormulaDurationHours.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofHours(2L));

		FormulaDurationHours formula = Mockito.spy(FormulaDurationHours.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofHours(2L));

		FormulaDurationHours formula = Mockito.spy(FormulaDurationHours.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.DURATION);
		when(parameter.getDurationValue(null)).thenReturn(Duration.ofHours(2L));

		FormulaDurationHours formula = Mockito.spy(FormulaDurationHours.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getDateValue(null);
	}
	
	/**
	 * Tests {@link FormulaDurationHours#getDurationValue()}
	 */
	@Test
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(parameter.getIntegerValue(null)).thenReturn(2);

		FormulaDurationHours formula = Mockito.spy(FormulaDurationHours.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(Duration.ofHours(2L), formula.getDurationValue(null));
	}
	
	/**
	 * Tests {@link FormulaDurationHours#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaDurationHours formula = new FormulaDurationHours();
		
		assertEquals("hours", formula.operationAsText());
	}

}
