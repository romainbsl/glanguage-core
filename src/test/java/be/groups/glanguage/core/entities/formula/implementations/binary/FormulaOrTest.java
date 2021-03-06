package be.groups.glanguage.core.entities.formula.implementations.binary;

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
 * Test class for {@link FormulaOr}
 * 
 * @author DUPIREFR
 */
public class FormulaOrTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaOr#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaOr formula = new FormulaOr();
		
		assertEquals(Integer.valueOf(FormulaType.Values.OP_OR), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaOr#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaOr formula = new FormulaOr();
		
		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaOr#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(false);

		FormulaOr formula = spy(FormulaOr.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getIntegerValue();
	}
	
	/**
	 * Tests {@link FormulaOr#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(false);

		FormulaOr formula = spy(FormulaOr.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getNumericValue();
	}
	
	/**
	 * Tests {@link FormulaOr#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(false);

		FormulaOr formula = spy(FormulaOr.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaOr#getBooleanValue()} when parameters exist and both true
	 */
	@Test
	public void testGetBooleanValueParametersExistBothTrue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(true);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(true);

		FormulaOr formula = spy(FormulaOr.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaOr#getBooleanValue()} when parameters exist and left is false
	 */
	@Test
	public void testGetBooleanValueParametersExistLeftFalse() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(true);

		FormulaOr formula = spy(FormulaOr.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaOr#getBooleanValue()} when parameters exist and right is false
	 */
	@Test
	public void testGetBooleanValueParametersExistRightFalse() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(true);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(false);

		FormulaOr formula = spy(FormulaOr.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaOr#getBooleanValue()} when parameters exist and both false
	 */
	@Test
	public void testGetBooleanValueParametersExistBothFalse() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(false);

		FormulaOr formula = spy(FormulaOr.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.FALSE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaOr#getBooleanValue()} when left doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetBooleanValueLeftNotExists() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(null);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(true);

		FormulaOr formula = spy(FormulaOr.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaOr#getBooleanValue()} when left true and right doesn't exist
	 */
	@Test
	public void testGetBooleanValueLeftTrueRightNotExists() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(true);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(null);

		FormulaOr formula = spy(FormulaOr.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		assertEquals(Boolean.TRUE, formula.getBooleanValue());
	}
	
	/**
	 * Tests {@link FormulaOr#getBooleanValue()} when left false and right doesn't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetBooleanValueRightNotExists() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(null);

		FormulaOr formula = spy(FormulaOr.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaOr#getBooleanValue()} when both parameter don't exist
	 */
	@Test(expected = NullPointerException.class)
	public void testGetBooleanValueParametersNotExist() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(null);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(null);

		FormulaOr formula = spy(FormulaOr.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaOr#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(false);

		FormulaOr formula = spy(FormulaOr.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaOr#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula operand1 = mock(AbstractFormula.class);
		when(operand1.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand1.getBooleanValue(null)).thenReturn(false);
		
		AbstractFormula operand2 = mock(AbstractFormula.class);
		when(operand2.getReturnType(null)).thenReturn(FormulaReturnType.BOOLEAN);
		when(operand2.getBooleanValue(null)).thenReturn(false);

		FormulaOr formula = spy(FormulaOr.class);
		doReturn(Arrays.asList(operand1, operand2)).when(formula).getParameters();

		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaOr#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaOr formula = new FormulaOr();
		
		assertEquals("or", formula.operationAsText());
	}

}
