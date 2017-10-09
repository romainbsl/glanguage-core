package be.groups.glanguage.core.entities.formula.implementations.call;

import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaPrimitive}
 * 
 * @author DUPIREFR
 */
public class FormulaPrimitiveTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaPrimitive#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaPrimitive formula = new FormulaPrimitive();
		
		assertEquals(Integer.valueOf(FormulaType.Values.C_PRIMITIVE), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaPrimitive#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaPrimitive formula = new FormulaPrimitive();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaPrimitive#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();
		FormulaPrimitive formula = Mockito.spy(FormulaPrimitive.class);
		doReturn(parameters).when(formula).getParameters();

		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaPrimitive#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();
		FormulaPrimitive formula = Mockito.spy(FormulaPrimitive.class);
		doReturn(parameters).when(formula).getParameters();

		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaPrimitive#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();
		FormulaPrimitive formula = Mockito.spy(FormulaPrimitive.class);
		doReturn(parameters).when(formula).getParameters();

		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaPrimitive#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();
		FormulaPrimitive formula = Mockito.spy(FormulaPrimitive.class);
		doReturn(parameters).when(formula).getParameters();

		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaPrimitive#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();
		FormulaPrimitive formula = Mockito.spy(FormulaPrimitive.class);
		doReturn(parameters).when(formula).getParameters();

		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaPrimitive#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();
		FormulaPrimitive formula = Mockito.spy(FormulaPrimitive.class);
		doReturn(parameters).when(formula).getParameters();

		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaPrimitive#asText()}
	 */
	@Test
	public void testAsText() throws GLanguageException {
		String primitive = "call";
		
		AbstractFormula calls3Param1 = mock(AbstractFormula.class);
		when(calls3Param1.asText()).thenReturn("some_rule1");
		
		AbstractFormula calls3Param2 = mock(AbstractFormula.class);
		when(calls3Param2.asText()).thenReturn("some_rule2");
		
		List<AbstractFormula> parameters = Arrays.asList(calls3Param1, calls3Param2);
		
		FormulaPrimitive formula = new FormulaPrimitive(null, primitive, parameters, null);
		
		assertEquals("call(some_rule1; some_rule2)", formula.asText());
	}
	
}
