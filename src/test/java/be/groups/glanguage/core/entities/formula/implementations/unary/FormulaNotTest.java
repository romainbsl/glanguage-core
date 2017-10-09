package be.groups.glanguage.core.entities.formula.implementations.unary;

import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaNot}
 * 
 * @author DUPIREFR
 */
public class FormulaNotTest {
	
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

}
