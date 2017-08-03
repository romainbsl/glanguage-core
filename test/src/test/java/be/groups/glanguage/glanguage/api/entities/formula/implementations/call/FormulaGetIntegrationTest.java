package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.BaseDatabaseTest;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalString;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FormulaGet}
 * 
 * @author DUPIREFR
 */
public class FormulaGetIntegrationTest extends BaseDatabaseTest {
	
	/*
	 * Constants
	 */
	private static final String PERSON_1_NAME = "Michotte";
	private static final Integer PERSON_1_AGE = 29;
	private static final Double PERSON_1_NUMERIC_VALUE = 1111.11;
	private static final Boolean PERSON_1_IS_STILL_THERE = true;
	private static final LocalDate PERSON_1_BIRTH_DATE = LocalDate.of(1987, 8, 26);
	private static final Duration PERSON_1_DURATION_VALUE = Duration.ofDays(22);
	private static final ContextPerson contextPerson1 = new FormulaGetIntegrationTest().new ContextPerson(PERSON_1_NAME, PERSON_1_AGE,
																										  PERSON_1_NUMERIC_VALUE, PERSON_1_IS_STILL_THERE, PERSON_1_BIRTH_DATE, PERSON_1_DURATION_VALUE);
			
	private static final String PERSON_2_NAME = "Dupire";
	private static final Integer PERSON_2_AGE = 24;
	private static final Double PERSON_2_NUMERIC_VALUE = 2222.22;
	private static final Boolean PERSON_2_IS_STILL_THERE = false;
	private static final LocalDate PERSON_2_BIRTH_DATE = LocalDate.of(1992, 9, 11);
	private static final Duration PERSON_2_DURATION_VALUE = Duration.ofDays(0);
	private static final ContextPerson contextPerson2 = new FormulaGetIntegrationTest().new ContextPerson(PERSON_2_NAME, PERSON_2_AGE,
																										  PERSON_2_NUMERIC_VALUE, PERSON_2_IS_STILL_THERE, PERSON_2_BIRTH_DATE, PERSON_2_DURATION_VALUE);
			
	private static final String CONTRACT_LABEL = "Analyst Developer";
	private static final Integer CONTRACT_ID = 1;
	private static final Double CONTRACT_SALARY = 9999.99;
	private static final LocalDate CONTRACT_START_DATE = LocalDate.of(2010, 10, 01);
	private static final Duration CONTRACT_HOURS = Duration.ofHours(38);
	private static ContextContract contextContract = new FormulaGetIntegrationTest().new ContextContract(CONTRACT_LABEL, CONTRACT_ID,
																										 CONTRACT_SALARY, CONTRACT_START_DATE, CONTRACT_HOURS);
			
	private static final String STRING_VALUE = "context root string value";
	private static final Integer INTEGER_VALUE = -1000;
	private static final Double NUMERIC_VALUE = -1111.11;
	private static final Boolean BOOLEAN_VALUE = false;
	private static final LocalDate DATE_VALUE = LocalDate.MIN;
	private static final Duration DURATION_VALUE = Duration.of(10000, ChronoUnit.MINUTES);
	private static Context context = new FormulaGetIntegrationTest().new Context(Arrays.asList(contextPerson1, contextPerson2), contextContract,
																				 STRING_VALUE, INTEGER_VALUE, NUMERIC_VALUE, BOOLEAN_VALUE, DATE_VALUE, DURATION_VALUE);

	/*
	 * Tests
	 */
	/**
	 * Tests {@link FormulaGet#getDiscriminatorValue()}
	 */
	@Test
	public void testGetDiscriminatorValue() {
		FormulaGet formula = new FormulaGet();
		
		assertEquals(Integer.valueOf(FormulaType.Values.C_GET), formula.getDiscriminatorValue());
	}
	
	/**
	 * Tests {@link FormulaGet#isTerminal()}
	 */
	@Test
	public void testIsTerminal() {
		FormulaGet formula = new FormulaGet();
		
		assertFalse(formula.isTerminal());
	}
	
	/**
	 * Tests {@link FormulaGet#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValueRootContext() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();

		FormulaPrimitive primitive = spy(FormulaPrimitive.class);
		doReturn("integerValue").when(primitive).getConstantValue();
		doReturn(parameters).when(primitive).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.INTEGER).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("1").when(formula).getConstantValue();
		doReturn(Collections.singletonList(primitive)).when(formula).getParameters();

		assertEquals(INTEGER_VALUE, formula.getValue());
		assertEquals("get INTEGER integerValue", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValueContractContext() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();

		FormulaPrimitive primitive1 = spy(FormulaPrimitive.class);
		doReturn("contract").when(primitive1).getConstantValue();
		doReturn(parameters).when(primitive1).getParameters();
		FormulaPrimitive primitive2 = spy(FormulaPrimitive.class);
		doReturn("id").when(primitive2).getConstantValue();
		doReturn(parameters).when(primitive2).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.INTEGER).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("1").when(formula).getConstantValue();
		doReturn(Arrays.asList(primitive1, primitive2)).when(formula).getParameters();

		assertEquals(CONTRACT_ID, formula.getValue());
		assertEquals("get INTEGER contract.id", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValuePersonContextField() throws GLanguageException {
		FormulaTerminalInteger formula1Parameter1 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER),
										   String.valueOf(0));
		List<AbstractFormula> formulasParameter1 = new ArrayList<>();
		formulasParameter1.add(formula1Parameter1);

		List<AbstractFormula> formulasParameter2 = new ArrayList<>();

		FormulaPrimitive primitive1 = spy(FormulaPrimitive.class);
		doReturn("person").when(primitive1).getConstantValue();
		doReturn(formulasParameter1).when(primitive1).getParameters();
		FormulaPrimitive primitive2 = spy(FormulaPrimitive.class);
		doReturn("age").when(primitive2).getConstantValue();
		doReturn(formulasParameter2).when(primitive2).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.INTEGER).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("1").when(formula).getConstantValue();
		doReturn(Arrays.asList(primitive1, primitive2)).when(formula).getParameters();

		assertEquals(PERSON_1_AGE, formula.getValue());
		assertEquals("get INTEGER person(0).age", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValuePersonContextMethod() throws GLanguageException {
		FormulaTerminalInteger formula1Parameter1 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), String.valueOf(1));
		List<AbstractFormula> formulasParameter1 = new ArrayList<>();
		formulasParameter1.add(formula1Parameter1);
		
		FormulaTerminalInteger formula1Parameter2 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), String.valueOf(10));
		List<AbstractFormula> formulasParameter2 = new ArrayList<>();
		formulasParameter2.add(formula1Parameter2);

		FormulaPrimitive primitive1 = spy(FormulaPrimitive.class);
		doReturn("person").when(primitive1).getConstantValue();
		doReturn(formulasParameter1).when(primitive1).getParameters();
		FormulaPrimitive primitive2 = spy(FormulaPrimitive.class);
		doReturn("agePlus").when(primitive2).getConstantValue();
		doReturn(formulasParameter2).when(primitive2).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.INTEGER).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("1").when(formula).getConstantValue();
		doReturn(Arrays.asList(primitive1, primitive2)).when(formula).getParameters();

		assertEquals(PERSON_2_AGE + 10, formula.getValue());
		assertEquals("get INTEGER person(1).agePlus(10)", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getNumericValue()}
	 */
	@Test
	public void testGetNumericValueRootContext() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();

		FormulaPrimitive primitive = spy(FormulaPrimitive.class);
		doReturn("numericValue").when(primitive).getConstantValue();
		doReturn(parameters).when(primitive).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.NUMERIC).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("2").when(formula).getConstantValue();
		doReturn(Collections.singletonList(primitive)).when(formula).getParameters();

		assertEquals(NUMERIC_VALUE, formula.getValue());
		assertEquals("get NUMERIC numericValue", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getNumericValue()}
	 */
	@Test
	public void testGetNumericValueContractContextField() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();

		FormulaPrimitive primitive1 = spy(FormulaPrimitive.class);
		doReturn("contract").when(primitive1).getConstantValue();
		doReturn(parameters).when(primitive1).getParameters();
		FormulaPrimitive primitive2 = spy(FormulaPrimitive.class);
		doReturn("salary").when(primitive2).getConstantValue();
		doReturn(parameters).when(primitive2).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.NUMERIC).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("2").when(formula).getConstantValue();
		doReturn(Arrays.asList(primitive1, primitive2)).when(formula).getParameters();

		assertEquals(CONTRACT_SALARY, formula.getValue());
		assertEquals("get NUMERIC contract.salary", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getNumericValue()}
	 */
	@Test
	public void testGetNumericValueContractContextMethod() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();

		FormulaPrimitive primitive1 = spy(FormulaPrimitive.class);
		doReturn("contract").when(primitive1).getConstantValue();
		doReturn(parameters).when(primitive1).getParameters();
		FormulaPrimitive primitive2 = spy(FormulaPrimitive.class);
		doReturn("doubleSalary").when(primitive2).getConstantValue();
		doReturn(parameters).when(primitive2).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.NUMERIC).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("2").when(formula).getConstantValue();
		doReturn(Arrays.asList(primitive1, primitive2)).when(formula).getParameters();
		
		assertEquals(CONTRACT_SALARY * 2, formula.getValue());
		assertEquals("get NUMERIC contract.doubleSalary", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getNumericValue()}
	 */
	@Test
	public void testGetNumericValueContractContextMethodWithParameter() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();

		FormulaTerminalInteger formula1Parameter2 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), String.valueOf(3));
		List<AbstractFormula> formulasParameter2 = new ArrayList<>();
		formulasParameter2.add(formula1Parameter2);

		FormulaPrimitive primitive1 = spy(FormulaPrimitive.class);
		doReturn("contract").when(primitive1).getConstantValue();
		doReturn(parameters).when(primitive1).getParameters();
		FormulaPrimitive primitive2 = spy(FormulaPrimitive.class);
		doReturn("doubleSalary").when(primitive2).getConstantValue();
		doReturn(formulasParameter2).when(primitive2).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.NUMERIC).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("2").when(formula).getConstantValue();
		doReturn(Arrays.asList(primitive1, primitive2)).when(formula).getParameters();

		assertEquals(CONTRACT_SALARY * 3, formula.getValue());
		assertEquals("get NUMERIC contract.doubleSalary(3)", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getStringValue()}
	 */
	@Test
	public void testGetStringValueRootContext() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();

		FormulaPrimitive primitive = spy(FormulaPrimitive.class);
		doReturn("stringValue").when(primitive).getConstantValue();
		doReturn(parameters).when(primitive).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.STRING).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("3").when(formula).getConstantValue();
		doReturn(Collections.singletonList(primitive)).when(formula).getParameters();
		
		assertEquals(STRING_VALUE, formula.getValue());
		assertEquals("get STRING stringValue", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getStringValue()}
	 */
	@Test
	public void testGetStringValueContractContextField() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();

		FormulaPrimitive primitive1 = spy(FormulaPrimitive.class);
		doReturn("contract").when(primitive1).getConstantValue();
		doReturn(parameters).when(primitive1).getParameters();
		FormulaPrimitive primitive2 = spy(FormulaPrimitive.class);
		doReturn("label").when(primitive2).getConstantValue();
		doReturn(parameters).when(primitive2).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.STRING).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("3").when(formula).getConstantValue();
		doReturn(Arrays.asList(primitive1, primitive2)).when(formula).getParameters();

		assertEquals(CONTRACT_LABEL, formula.getValue());
		assertEquals("get STRING contract.label", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getStringValue()}
	 */
	@Test
	public void testGetStringValueContractContextMethod() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();

		FormulaPrimitive primitive1 = spy(FormulaPrimitive.class);
		doReturn("contract").when(primitive1).getConstantValue();
		doReturn(parameters).when(primitive1).getParameters();
		FormulaPrimitive primitive2 = spy(FormulaPrimitive.class);
		doReturn("labelToUpper").when(primitive2).getConstantValue();
		doReturn(parameters).when(primitive2).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.STRING).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("3").when(formula).getConstantValue();
		doReturn(Arrays.asList(primitive1, primitive2)).when(formula).getParameters();

		assertEquals(CONTRACT_LABEL.toUpperCase(), formula.getValue());
		assertEquals("get STRING contract.labelToUpper", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getStringValue()}
	 */
	@Test
	public void testGetStringValuePersonContextMethodWithParameters() throws GLanguageException {
		FormulaTerminalInteger formula1Parameter1 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), String.valueOf(1));
		List<AbstractFormula> formulasParameter1 = new ArrayList<>();
		formulasParameter1.add(formula1Parameter1);
		
		FormulaTerminalString formula1Parameter2 =
				new FormulaTerminalString(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_STRING), "pire");
		FormulaTerminalString formula2Parameter2 =
				new FormulaTerminalString(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_STRING), "mieux");
		List<AbstractFormula> formulasParameter2 = new ArrayList<>();
		formulasParameter2.add(formula1Parameter2);
		formulasParameter2.add(formula2Parameter2);

		FormulaPrimitive primitive1 = spy(FormulaPrimitive.class);
		doReturn("person").when(primitive1).getConstantValue();
		doReturn(formulasParameter1).when(primitive1).getParameters();
		FormulaPrimitive primitive2 = spy(FormulaPrimitive.class);
		doReturn("nameReplace").when(primitive2).getConstantValue();
		doReturn(formulasParameter2).when(primitive2).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.STRING).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("3").when(formula).getConstantValue();
		doReturn(Arrays.asList(primitive1, primitive2)).when(formula).getParameters();

		assertEquals(PERSON_2_NAME.replace("pire", "mieux"), formula.getValue());
		assertEquals("get STRING person(1).nameReplace(\"pire\"; \"mieux\")", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValueRootContext() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();

		FormulaPrimitive primitive = spy(FormulaPrimitive.class);
		doReturn("booleanValue").when(primitive).getConstantValue();
		doReturn(parameters).when(primitive).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.BOOLEAN).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("4").when(formula).getConstantValue();
		doReturn(Collections.singletonList(primitive)).when(formula).getParameters();

		assertEquals(BOOLEAN_VALUE, formula.getValue());
		assertEquals("get BOOLEAN booleanValue", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValuePersonContext1() throws GLanguageException {
		FormulaTerminalInteger formula1Parameter1 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), String.valueOf(0));
		List<AbstractFormula> formulasParameter1 = new ArrayList<>();
		formulasParameter1.add(formula1Parameter1);

		List<AbstractFormula> parameters = new ArrayList<>();

		FormulaPrimitive primitive1 = spy(FormulaPrimitive.class);
		doReturn("person").when(primitive1).getConstantValue();
		doReturn(formulasParameter1).when(primitive1).getParameters();

		FormulaPrimitive primitive2 = spy(FormulaPrimitive.class);
		doReturn("isStillThere").when(primitive2).getConstantValue();
		doReturn(parameters).when(primitive2).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.BOOLEAN).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("4").when(formula).getConstantValue();
		doReturn(Arrays.asList(primitive1, primitive2)).when(formula).getParameters();

		assertEquals(PERSON_1_IS_STILL_THERE, formula.getValue());
		assertEquals("get BOOLEAN person(0).isStillThere", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValuePersonContext2() throws GLanguageException {
		FormulaTerminalInteger formula1Parameter1 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), String.valueOf(1));
		List<AbstractFormula> formulasParameter1 = new ArrayList<>();
		formulasParameter1.add(formula1Parameter1);

		List<AbstractFormula> parameters = new ArrayList<>();

		FormulaPrimitive primitive1 = spy(FormulaPrimitive.class);
		doReturn("person").when(primitive1).getConstantValue();
		doReturn(formulasParameter1).when(primitive1).getParameters();

		FormulaPrimitive primitive2 = spy(FormulaPrimitive.class);
		doReturn("isStillThere").when(primitive2).getConstantValue();
		doReturn(parameters).when(primitive2).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.BOOLEAN).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("4").when(formula).getConstantValue();
		doReturn(Arrays.asList(primitive1, primitive2)).when(formula).getParameters();
		
		assertEquals(PERSON_2_IS_STILL_THERE, formula.getValue());
		assertEquals("get BOOLEAN person(1).isStillThere", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getDateValue()}
	 */
	@Test
	public void testGetDateValueRootContext() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();

		FormulaPrimitive primitive = spy(FormulaPrimitive.class);
		doReturn("dateValue").when(primitive).getConstantValue();
		doReturn(parameters).when(primitive).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.DATE).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("5").when(formula).getConstantValue();
		doReturn(Collections.singletonList(primitive)).when(formula).getParameters();
		
		assertEquals(DATE_VALUE, formula.getValue());
		assertEquals("get DATE dateValue", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getDateValue()}
	 */
	@Test
	public void testGetDateValuePersonContextField() throws GLanguageException {
		FormulaTerminalInteger formula1Parameter1 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER),
										   String.valueOf(0));
		List<AbstractFormula> formulasParameter1 = new ArrayList<>();
		formulasParameter1.add(formula1Parameter1);

		List<AbstractFormula> parameters = new ArrayList<>();

		FormulaPrimitive primitive1 = spy(FormulaPrimitive.class);
		doReturn("person").when(primitive1).getConstantValue();
		doReturn(formulasParameter1).when(primitive1).getParameters();

		FormulaPrimitive primitive2 = spy(FormulaPrimitive.class);
		doReturn("birthDate").when(primitive2).getConstantValue();
		doReturn(parameters).when(primitive2).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.DATE).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("5").when(formula).getConstantValue();
		doReturn(Arrays.asList(primitive1, primitive2)).when(formula).getParameters();

		assertEquals(PERSON_1_BIRTH_DATE, formula.getValue());
		assertEquals("get DATE person(0).birthDate", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getDateValue()}
	 */
	@Test
	public void testGetDateValuePersonContextMethod() throws GLanguageException {
		FormulaTerminalInteger formula1Parameter1 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), String.valueOf(0));
		List<AbstractFormula> formulasParameter1 = new ArrayList<>();
		formulasParameter1.add(formula1Parameter1);
		FormulaTerminalInteger formula1Parameter2 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), String.valueOf(1));
		List<AbstractFormula> formulasParameter2 = new ArrayList<>();
		formulasParameter2.add(formula1Parameter2);

		FormulaPrimitive primitive1 = spy(FormulaPrimitive.class);
		doReturn("person").when(primitive1).getConstantValue();
		doReturn(formulasParameter1).when(primitive1).getParameters();

		FormulaPrimitive primitive2 = spy(FormulaPrimitive.class);
		doReturn("birthDatePlusDays").when(primitive2).getConstantValue();
		doReturn(formulasParameter2).when(primitive2).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.DATE).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("5").when(formula).getConstantValue();
		doReturn(Arrays.asList(primitive1, primitive2)).when(formula).getParameters();

		assertEquals(PERSON_1_BIRTH_DATE.plusDays(1), formula.getValue());
		assertEquals("get DATE person(0).birthDatePlusDays(1)", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getDurationValue()}
	 */
	@Test
	public void testGetDurationValueRootContext() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();

		FormulaPrimitive primitive = spy(FormulaPrimitive.class);
		doReturn("durationValue").when(primitive).getConstantValue();
		doReturn(parameters).when(primitive).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.DURATION).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("6").when(formula).getConstantValue();
		doReturn(Collections.singletonList(primitive)).when(formula).getParameters();

		assertEquals(DURATION_VALUE, formula.getValue());
		assertEquals("get DURATION durationValue", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getDurationValue()}
	 */
	@Test
	public void testGetDurationValueContractContextField() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();

		FormulaPrimitive primitive1 = spy(FormulaPrimitive.class);
		doReturn("contract").when(primitive1).getConstantValue();
		doReturn(parameters).when(primitive1).getParameters();

		FormulaPrimitive primitive2 = spy(FormulaPrimitive.class);
		doReturn("hours").when(primitive2).getConstantValue();
		doReturn(parameters).when(primitive2).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.DURATION).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("6").when(formula).getConstantValue();
		doReturn(Arrays.asList(primitive1, primitive2)).when(formula).getParameters();
		
		assertEquals(CONTRACT_HOURS, formula.getValue());
		assertEquals("get DURATION contract.hours", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getDurationValue()}
	 */
	@Test
	public void testGetDurationValueContractContextMethod() throws GLanguageException {
		List<AbstractFormula> parameters = new ArrayList<>();

		FormulaPrimitive primitive1 = spy(FormulaPrimitive.class);
		doReturn("contract").when(primitive1).getConstantValue();
		doReturn(parameters).when(primitive1).getParameters();

		FormulaPrimitive primitive2 = spy(FormulaPrimitive.class);
		doReturn("halfHours").when(primitive2).getConstantValue();
		doReturn(parameters).when(primitive2).getParameters();

		FormulaGet formula = spy(FormulaGet.class);
		doReturn(FormulaReturnType.DURATION).when(formula).getReturnType(null);
		doReturn(context).when(formula).getContext();
		doReturn("6").when(formula).getConstantValue();
		doReturn(Arrays.asList(primitive1, primitive2)).when(formula).getParameters();

		assertEquals(Duration.ofHours(CONTRACT_HOURS.toHours() / 2), formula.getValue());
		assertEquals("get DURATION contract.halfHours", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#asText()}
	 */
	@Test
	public void testAsText() throws GLanguageException {
		FormulaReturnType returnType = FormulaReturnType.BOOLEAN;
		
		List<String> identifiers = Arrays.asList("call1", "call2", "call3");
		
		AbstractFormula calls3Param1 = mock(AbstractFormula.class);
		when(calls3Param1.asText()).thenReturn("some_rule1");
		
		AbstractFormula calls3Param2 = mock(AbstractFormula.class);
		when(calls3Param2.asText()).thenReturn("some_rule2");
		
		List<List<AbstractFormula>> parameters =
				Arrays.asList(Arrays.asList(), Arrays.asList(), Arrays.asList(calls3Param1, calls3Param2));
				
		FormulaGet formula = new FormulaGet(null, null, returnType, identifiers, parameters, null);
		
		assertEquals("get BOOLEAN call1.call2.call3(some_rule1; some_rule2)", formula.asText());
	}
	
	@SuppressWarnings("unused")
	public class Context {
		public List<ContextPerson> person;
		public ContextContract contract;
		public String stringValue;
		public Integer integerValue;
		public Double numericValue;
		public Boolean booleanValue;
		public LocalDate dateValue;
		public Duration durationValue;
		
		public Context(List<ContextPerson> person, ContextContract contract, String stringValue, Integer integerValue,
				Double numericValue, Boolean booleanValue, LocalDate dateValue, Duration durationValue) {
			super();
			this.person = person;
			this.contract = contract;
			this.stringValue = stringValue;
			this.integerValue = integerValue;
			this.numericValue = numericValue;
			this.booleanValue = booleanValue;
			this.dateValue = dateValue;
			this.durationValue = durationValue;
		}
		
		public ContextPerson person(Integer index) {
			if (person.size() > index) {
				return person.get(index);
			} else {
				return null;
			}
		}
	}
	
	@SuppressWarnings("unused")
	public class ContextPerson {
		public String name;
		public Integer age;
		public Double numericValue;
		public Boolean isStillThere;
		public LocalDate birthDate;
		public Duration durationValue;
		
		public ContextPerson(String name, Integer age, Double numericValue, Boolean isStillThere, LocalDate birthDate,
				Duration durationValue) {
			super();
			this.name = name;
			this.age = age;
			this.numericValue = numericValue;
			this.isStillThere = isStillThere;
			this.birthDate = birthDate;
			this.durationValue = durationValue;
		}
		
		public String nameReplace(String toReplace, String replaceBy) {
			return name.replace(toReplace, replaceBy);
		}
		
		public Integer agePlus(Integer plus) {
			return age + plus;
		}
		
		public LocalDate birthDatePlusDays(Integer daysToAdd) {
			return birthDate.plusDays(daysToAdd);
		}
	}
	
	@SuppressWarnings("unused")
	public class ContextContract {
		public String label;
		public Integer id;
		public Double salary;
		public LocalDate startDate;
		public Duration hours;
		
		public ContextContract(String label, Integer id, Double salary, LocalDate startDate, Duration hours) {
			super();
			this.label = label;
			this.id = id;
			this.salary = salary;
			this.startDate = startDate;
			this.hours = hours;
		}
		
		public String labelToUpper() {
			return label.toUpperCase();
		}
		
		public Double doubleSalary() {
			return salary * 2;
		}
		
		public Double doubleSalary(Integer multiple) {
			return salary * multiple;
		}
		
		public Duration halfHours() {
			return Duration.ofHours(hours.toHours() / 2);
		}
		
	}
}
