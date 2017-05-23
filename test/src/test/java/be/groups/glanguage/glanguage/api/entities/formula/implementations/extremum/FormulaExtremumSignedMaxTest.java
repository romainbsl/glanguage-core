package be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaExtremumSignedMax}
 * 
 * @author DUPIREFR
 */
public class FormulaExtremumSignedMaxTest extends BaseDatabaseTest {
	
	/*
	 * Constants
	 */
	private static final double DELTA = 1e-15;
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaExtremumSignedMax#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaExtremumSignedMax formula = new FormulaExtremumSignedMax();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_SMAX), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaExtremumSignedMax formula = new FormulaExtremumSignedMax();
		
		assertFalse(formula.isTerminal());
	}

	/**
	 * Tests {@link FormulaExtremumMax#isValid(Evaluator)} when parameters match (2 integers)
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidMatchingIntegers() throws GLanguageException {
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaExtremumSignedMax formula = spy(FormulaExtremumSignedMax.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_SMAX)).when(formula).getDescription();
		doReturn(Arrays.asList(param1, param2)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}

	/**
	 * Tests {@link FormulaExtremumMax#isValid(Evaluator)} when parameters match (2 numerics)
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidMatchingNumerics() throws GLanguageException {
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaExtremumSignedMax formula = spy(FormulaExtremumSignedMax.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_SMAX)).when(formula).getDescription();
		doReturn(Arrays.asList(param1, param2)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}

	/**
	 * Tests {@link FormulaExtremumMax#isValid(Evaluator)} when parameters match (1 integer, 1 numeric)
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidMatchingIntegerNumeric() throws GLanguageException {
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaExtremumSignedMax formula = spy(FormulaExtremumSignedMax.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_SMAX)).when(formula).getDescription();
		doReturn(Arrays.asList(param1, param2)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}

	/**
	 * Tests {@link FormulaExtremumMax#isValid(Evaluator)} when parameters match (1 numeric, 1 integer)
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidMatchingNumericInteger() throws GLanguageException {
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaExtremumSignedMax formula = spy(FormulaExtremumSignedMax.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_SMAX)).when(formula).getDescription();
		doReturn(Arrays.asList(param1, param2)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}

	/**
	 * Tests {@link FormulaExtremumMax#isValid(Evaluator)} when parameters don't match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotMatching() throws GLanguageException {
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaExtremumSignedMax formula = spy(FormulaExtremumSignedMax.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_SMAX)).when(formula).getDescription();
		doReturn(Arrays.asList(param1, param2)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}

	/**
	 * Tests {@link FormulaExtremumMax#isValid(Evaluator)} when parameters number don't match
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotMatchingNumber() throws GLanguageException {
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaExtremumSignedMax formula = spy(FormulaExtremumSignedMax.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_SMAX)).when(formula).getDescription();
		doReturn(Arrays.asList(param1)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}

	/**
	 * Tests {@link FormulaExtremumSignedMax#getIntegerValue()} when all parameters are integers
	 */
	@Test
	public void testGetIntegerValueIntegers() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getNumericValue(null)).thenReturn(-4.0);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getNumericValue(null)).thenReturn(3.0);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getNumericValue(null)).thenReturn(2.0);
		parameters.add(param3);
		
		FormulaExtremumSignedMax formula = spy(FormulaExtremumSignedMax.class);
		doReturn(parameters).when(formula).getParameters();
		
		assertEquals(Integer.valueOf(-4), formula.getIntegerValue());
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#getNumericValue()} when all parameters are numeric
	 */
	@Test
	public void testGetNumericValueNumerics() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue(null)).thenReturn(-3.5);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue(null)).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param3.getNumericValue(null)).thenReturn(2.8);
		parameters.add(param3);
		
		FormulaExtremumSignedMax formula = spy(FormulaExtremumSignedMax.class);
		doReturn(parameters).when(formula).getParameters();
		
		assertEquals(Double.valueOf(-3.5), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#getNumericValue()} when numeric and integer parameters
	 * mix is numeric
	 */
	@Test
	public void testGetNumericValueIntNum() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getNumericValue(null)).thenReturn(-3.0);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue(null)).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getNumericValue(null)).thenReturn(2.0);
		parameters.add(param3);
		
		FormulaExtremumSignedMax formula = spy(FormulaExtremumSignedMax.class);
		doReturn(parameters).when(formula).getParameters();
		
		assertEquals(Double.valueOf(-3), formula.getNumericValue(), DELTA);
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#getNumericValue()} when all parameters are integers
	 */
	@Test
	public void testGetNumericValueIntegers() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param1.getNumericValue(null)).thenReturn(-4.0);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param2.getNumericValue(null)).thenReturn(3.0);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param3.getNumericValue(null)).thenReturn(2.0);
		parameters.add(param3);
		
		FormulaExtremumSignedMax formula = spy(FormulaExtremumSignedMax.class);
		doReturn(parameters).when(formula).getParameters();
		
		assertEquals(Double.valueOf(-4), formula.getNumericValue(), DELTA);
	}
	
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue(null)).thenReturn(-1.5);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue(null)).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param3.getNumericValue(null)).thenReturn(2.8);
		parameters.add(param3);
		
		FormulaExtremumSignedMax formula = spy(FormulaExtremumSignedMax.class);
		doReturn(parameters).when(formula).getParameters();
		
		formula.getStringValue();
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue(null)).thenReturn(-1.5);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue(null)).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param3.getNumericValue(null)).thenReturn(2.8);
		parameters.add(param3);
		
		FormulaExtremumSignedMax formula = spy(FormulaExtremumSignedMax.class);
		doReturn(parameters).when(formula).getParameters();
		
		formula.getBooleanValue();
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#getDateValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDateValue() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue(null)).thenReturn(-1.5);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue(null)).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param3.getNumericValue(null)).thenReturn(2.8);
		parameters.add(param3);
		
		FormulaExtremumSignedMax formula = spy(FormulaExtremumSignedMax.class);
		doReturn(parameters).when(formula).getParameters();
		
		formula.getDateValue();
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param1.getNumericValue(null)).thenReturn(-1.5);
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param2.getNumericValue(null)).thenReturn(2.5);
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);
		when(param3.getNumericValue(null)).thenReturn(2.8);
		parameters.add(param3);
		
		FormulaExtremumSignedMax formula = spy(FormulaExtremumSignedMax.class);
		doReturn(parameters).when(formula).getParameters();
		
		formula.getDurationValue();
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#operationAsText()}
	 */
	@Test
	public void testOperationAsText() {
		FormulaExtremumSignedMax formula = new FormulaExtremumSignedMax();
		
		assertEquals("smax", formula.operationAsText());
	}
	
	/**
	 * Tests {@link FormulaExtremumSignedMax#operationAsText()}
	 */
	@Test
	public void testAsText() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();
		
		AbstractFormula param1 = mock(AbstractFormula.class);
		when(param1.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param1.asText()).thenReturn("some_rule1");
		parameters.add(param1);
		
		AbstractFormula param2 = mock(AbstractFormula.class);
		when(param2.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param2.asText()).thenReturn("some_rule2");
		parameters.add(param2);
		
		AbstractFormula param3 = mock(AbstractFormula.class);
		when(param3.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(param3.asText()).thenReturn("some_rule3");
		parameters.add(param3);
		
		FormulaExtremumSignedMax formula = spy(FormulaExtremumSignedMax.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_SMAX)).when(formula).getDescription();
		doReturn(parameters).when(formula).getParameters();
		
		assertEquals("smax(some_rule1, some_rule2, some_rule3)", formula.asText());
	}
	
}
