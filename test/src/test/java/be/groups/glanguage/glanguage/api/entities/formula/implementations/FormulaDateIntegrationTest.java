package be.groups.glanguage.glanguage.api.entities.formula.implementations;

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
 * Test class for {@link FormulaDate}
 * 
 * @author DUPIREFR
 */
public class FormulaDateIntegrationTest extends BaseDatabaseTest {
	
	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaDate#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaDate formula = new FormulaDate();
		
		assertEquals(Integer.valueOf(FormulaType.Values.F_DATE), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaDate#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaDate formula = new FormulaDate();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaDate#isValid(Evaluator)} with string parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidString() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		
		FormulaDate formula = spy(FormulaDate.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula).getDescription();
		doReturn(Arrays.asList(string)).when(formula).getParameters();
		
		assertTrue(formula.isValid(null));
	}

	/**
	 * Tests {@link FormulaDate#isValid(Evaluator)} with parameter different from string
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidNotString() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula).getDescription();
		doReturn(Arrays.asList(string)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDate#isValid(Evaluator)} with integers parameters
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidIntegers() throws GLanguageException {
		AbstractFormula day = mock(AbstractFormula.class);
		when(day.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula month = mock(AbstractFormula.class);
		when(month.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula year = mock(AbstractFormula.class);
		when(year.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula).getDescription();
		doReturn(Arrays.asList(day, month, year)).when(formula).getParameters();

		assertTrue(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDate#isValid(Evaluator)} with integers parameters, but not enough
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testIsValidIntegersNotEnough() throws GLanguageException {
		AbstractFormula day = mock(AbstractFormula.class);
		when(day.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula month = mock(AbstractFormula.class);
		when(month.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula).getDescription();
		doReturn(Arrays.asList(day, month)).when(formula).getParameters();

		assertFalse(formula.isValid(null));
	}
	
	/**
	 * Tests {@link FormulaDate#getReturnType()} with string parameter
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeString() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.STRING);

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula).getDescription();
		doReturn(Arrays.asList(string)).when(formula).getParameters();

		assertEquals(FormulaReturnType.DATE, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDate#getReturnType()} with parameter different from string
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeNotString() throws GLanguageException {
		AbstractFormula string = mock(AbstractFormula.class);
		when(string.getReturnType(null)).thenReturn(FormulaReturnType.NUMERIC);

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula).getDescription();
		doReturn(Arrays.asList(string)).when(formula).getParameters();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDate#getReturnType()} with integers parameters
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeIntegers() throws GLanguageException {
		AbstractFormula day = mock(AbstractFormula.class);
		when(day.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula month = mock(AbstractFormula.class);
		when(month.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula year = mock(AbstractFormula.class);
		when(year.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula).getDescription();
		doReturn(Arrays.asList(day, month, year)).when(formula).getParameters();

		assertEquals(FormulaReturnType.DATE, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDate#getReturnType()} with integers parameters, but not enough
	 */
	@Test
	@Category({DatabaseTestCategory.class})
	public void testGetReturnTypeIntegersNotEnough() throws GLanguageException {
		AbstractFormula day = mock(AbstractFormula.class);
		when(day.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		
		AbstractFormula month = mock(AbstractFormula.class);
		when(month.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula).getDescription();
		doReturn(Arrays.asList(day, month)).when(formula).getParameters();

		assertEquals(FormulaReturnType.UNDEFINED, formula.getReturnType(null));
	}
	
	/**
	 * Tests {@link FormulaDate#getIntegerValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetIntegerValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(parameter.getStringValue(null)).thenReturn("11/09/1992");

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getIntegerValue(null);
	}
	
	/**
	 * Tests {@link FormulaDate#getNumericValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetNumericValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(parameter.getStringValue(null)).thenReturn("11/09/1992");

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getNumericValue(null);
	}
	
	/**
	 * Tests {@link FormulaDate#getStringValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetStringValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(parameter.getStringValue(null)).thenReturn("11/09/1992");

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getStringValue(null);
	}
	
	/**
	 * Tests {@link FormulaDate#getBooleanValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetBooleanValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(parameter.getStringValue(null)).thenReturn("11/09/1992");

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getBooleanValue(null);
	}
	
	/**
	 * Tests {@link FormulaDate#getDateValue()} with string parameter
	 */
	@Test
	public void testGetDateValueWithStringParam() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(parameter.getStringValue(null)).thenReturn("11/09/1992");

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals(LocalDate.of(1992, 9, 11), formula.getDateValue(null));
	}
	
	/**
	 * Tests {@link FormulaDate#getDateValue()} with integers parameters
	 */
	@Test
	public void testGetDateValueWithIntParams() throws GLanguageException {
		AbstractFormula dayParam = mock(AbstractFormula.class);
		when(dayParam.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(dayParam.getIntegerValue(null)).thenReturn(11);
		
		AbstractFormula monthParam = mock(AbstractFormula.class);
		when(monthParam.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(monthParam.getIntegerValue(null)).thenReturn(9);
		
		AbstractFormula yearParam = mock(AbstractFormula.class);
		when(yearParam.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(yearParam.getIntegerValue(null)).thenReturn(1992);

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(Arrays.asList(dayParam, monthParam, yearParam)).when(formula).getParameters();

		assertEquals(LocalDate.of(1992, 9, 11), formula.getDateValue(null));
	}
	
	/**
	 * Tests {@link FormulaDate#getDurationValue()}
	 */
	@Test(expected = GLanguageException.class)
	public void testGetDurationValue() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(parameter.getStringValue(null)).thenReturn("11/09/1992");

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		formula.getDurationValue(null);
	}
	
	/**
	 * Tests {@link FormulaDate#asText()} with string parameter
	 */
	@Test
	@Category(DatabaseTestCategory.class)
	public void testAsTextWithStringParam() throws GLanguageException {
		AbstractFormula parameter = mock(AbstractFormula.class);
		when(parameter.getReturnType(null)).thenReturn(FormulaReturnType.STRING);
		when(parameter.asText()).thenReturn("11/09/1992");

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula).getDescription();
		doReturn(Arrays.asList(parameter)).when(formula).getParameters();

		assertEquals("date(11/09/1992)", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaDate#asText()} with integers parameters
	 */
	@Test
	@Category(DatabaseTestCategory.class)
	public void testAsTextWithIntParams() throws GLanguageException {
		AbstractFormula dayParam = mock(AbstractFormula.class);
		when(dayParam.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(dayParam.asText()).thenReturn("11");
		
		AbstractFormula monthParam = mock(AbstractFormula.class);
		when(monthParam.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(monthParam.asText()).thenReturn("9");
		
		AbstractFormula yearParam = mock(AbstractFormula.class);
		when(yearParam.getReturnType(null)).thenReturn(FormulaReturnType.INTEGER);
		when(yearParam.asText()).thenReturn("1992");

		FormulaDate formula = spy(FormulaDate.class);
		doReturn(FormulaDescriptionFactory.getDescription(FormulaType.F_DATE)).when(formula).getDescription();
		doReturn(Arrays.asList(dayParam, monthParam, yearParam)).when(formula).getParameters();

		assertEquals("date(11; 9; 1992)", formula.asText());
	}
	
}
