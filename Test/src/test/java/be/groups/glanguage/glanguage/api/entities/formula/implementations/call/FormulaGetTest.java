package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import be.groups.common.persistence.util.TransactionHelper;
import be.groups.common.test.utils.Environment;
import be.groups.glanguage.glanguage.api.business.factory.FormulaDescriptionFactory;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalString;
import be.groups.marmota.persistence.DatabaseIdentifier;
import be.groups.marmota.persistence.JpaUtil;
import be.groups.marmota.test.TNSNames;

/**
 * Test class for {@link FormulaGet}
 * 
 * @author DUPIREFR
 */
public class FormulaGetTest {
	
	/*
	 * Constants
	 */
	private static final String PERSON_1_NAME = "Michotte";
	private static final Integer PERSON_1_AGE = 29;
	private static final Double PERSON_1_NUMERIC_VALUE = 1111.11;
	private static final Boolean PERSON_1_IS_STILL_THERE = true;
	private static final LocalDate PERSON_1_BIRTH_DATE = LocalDate.of(1987, 8, 26);
	private static final Duration PERSON_1_DURATION_VALUE = Duration.ofDays(22);
	private static final ContextPerson contextPerson1 = new FormulaGetTest().new ContextPerson(PERSON_1_NAME, PERSON_1_AGE,
			PERSON_1_NUMERIC_VALUE, PERSON_1_IS_STILL_THERE, PERSON_1_BIRTH_DATE, PERSON_1_DURATION_VALUE);
			
	private static final String PERSON_2_NAME = "Dupire";
	private static final Integer PERSON_2_AGE = 24;
	private static final Double PERSON_2_NUMERIC_VALUE = 2222.22;
	private static final Boolean PERSON_2_IS_STILL_THERE = false;
	private static final LocalDate PERSON_2_BIRTH_DATE = LocalDate.of(1992, 9, 11);
	private static final Duration PERSON_2_DURATION_VALUE = Duration.ofDays(0);
	private static final ContextPerson contextPerson2 = new FormulaGetTest().new ContextPerson(PERSON_2_NAME, PERSON_2_AGE,
			PERSON_2_NUMERIC_VALUE, PERSON_2_IS_STILL_THERE, PERSON_2_BIRTH_DATE, PERSON_2_DURATION_VALUE);
			
	private static final String CONTRACT_LABEL = "Analyst Developer";
	private static final Integer CONTRACT_ID = 1;
	private static final Double CONTRACT_SALARY = 9999.99;
	private static final LocalDate CONTRACT_START_DATE = LocalDate.of(2010, 10, 01);
	private static final Duration CONTRACT_HOURS = Duration.ofHours(38);
	private static ContextContract contextContract = new FormulaGetTest().new ContextContract(CONTRACT_LABEL, CONTRACT_ID,
			CONTRACT_SALARY, CONTRACT_START_DATE, CONTRACT_HOURS);
			
	private static final String STRING_VALUE = "context root string value";
	private static final Integer INTEGER_VALUE = -1000;
	private static final Double NUMERIC_VALUE = -1111.11;
	private static final Boolean BOOLEAN_VALUE = false;
	private static final LocalDate DATE_VALUE = LocalDate.MIN;
	private static final Duration DURATION_VALUE = Duration.of(10000, ChronoUnit.MINUTES);
	private static Context context = new FormulaGetTest().new Context(Arrays.asList(contextPerson1, contextPerson2), contextContract,
			STRING_VALUE, INTEGER_VALUE, NUMERIC_VALUE, BOOLEAN_VALUE, DATE_VALUE, DURATION_VALUE);
			
	/*
	 * Setups
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		Environment.setUp();
		TNSNames.setUp();
		
		JpaUtil.setEntityManager(JpaUtil.createDataSource(DatabaseIdentifier.DEVELOPMENT_DB));
		
		if (!TransactionHelper.isActive()) {
			TransactionHelper.begin();
		}
	}
	
	@AfterClass
	public static void close() {
		if (TransactionHelper.isActive()) {
			TransactionHelper.rollback();
		}
	}
	
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
	public void testGetIntegerValueRootContext() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("integerValue");
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(new ArrayList<>());
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.INTEGER, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(INTEGER_VALUE, formula.getValue());
		assertEquals("get INTEGER integerValue", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValueContractContext() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("contract");
		identifiers.add("id");
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(new ArrayList<>());
		parameters.add(new ArrayList<>());
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.INTEGER, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(CONTRACT_ID, formula.getValue());
		assertEquals("get INTEGER contract.id", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValuePersonContextField() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("person");
		identifiers.add("age");
		
		FormulaTerminalInteger formula1Parameter1 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), String.valueOf(0));
		List<AbstractFormula> formulasParameter1 = new ArrayList<>();
		formulasParameter1.add(formula1Parameter1);
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(formulasParameter1);
		parameters.add(new ArrayList<>());
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.INTEGER, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(PERSON_1_AGE, formula.getValue());
		assertEquals("get INTEGER person(0).age", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getIntegerValue()}
	 */
	@Test
	public void testGetIntegerValuePersonContextMethod() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("person");
		identifiers.add("agePlus");
		
		FormulaTerminalInteger formula1Parameter1 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), String.valueOf(1));
		List<AbstractFormula> formulasParameter1 = new ArrayList<>();
		formulasParameter1.add(formula1Parameter1);
		
		FormulaTerminalInteger formula1Parameter2 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), String.valueOf(10));
		List<AbstractFormula> formulasParameter2 = new ArrayList<>();
		formulasParameter2.add(formula1Parameter2);
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(formulasParameter1);
		parameters.add(formulasParameter2);
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.INTEGER, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(PERSON_2_AGE + 10, formula.getValue());
		assertEquals("get INTEGER person(1).agePlus(10)", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getNumericValue()}
	 */
	@Test
	public void testGetNumericValueRootContext() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("numericValue");
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(new ArrayList<>());
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.NUMERIC, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(NUMERIC_VALUE, formula.getValue());
		assertEquals("get NUMERIC numericValue", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getNumericValue()}
	 */
	@Test
	public void testGetNumericValueContractContextField() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("contract");
		identifiers.add("salary");
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(new ArrayList<>());
		parameters.add(new ArrayList<>());
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.NUMERIC, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(CONTRACT_SALARY, formula.getValue());
		assertEquals("get NUMERIC contract.salary", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getNumericValue()}
	 */
	@Test
	public void testGetNumericValueContractContextMethod() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("contract");
		identifiers.add("doubleSalary");
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(new ArrayList<>());
		parameters.add(new ArrayList<>());
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.NUMERIC, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(CONTRACT_SALARY * 2, formula.getValue());
		assertEquals("get NUMERIC contract.doubleSalary", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getNumericValue()}
	 */
	@Test
	public void testGetNumericValueContractContextMethodWithParameter() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("contract");
		identifiers.add("doubleSalary");
		
		FormulaTerminalInteger formula1Parameter2 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), String.valueOf(3));
		List<AbstractFormula> formulasParameter2 = new ArrayList<>();
		formulasParameter2.add(formula1Parameter2);
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(new ArrayList<>());
		parameters.add(formulasParameter2);
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.NUMERIC, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(CONTRACT_SALARY * 3, formula.getValue());
		assertEquals("get NUMERIC contract.doubleSalary(3)", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getStringValue()}
	 */
	@Test
	public void testGetStringValueRootContext() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("stringValue");
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(new ArrayList<>());
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.STRING, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(STRING_VALUE, formula.getValue());
		assertEquals("get STRING stringValue", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getStringValue()}
	 */
	@Test
	public void testGetStringValueContractContextField() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("contract");
		identifiers.add("label");
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(new ArrayList<>());
		parameters.add(new ArrayList<>());
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.STRING, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(CONTRACT_LABEL, formula.getValue());
		assertEquals("get STRING contract.label", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getStringValue()}
	 */
	@Test
	public void testGetStringValueContractContextMethod() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("contract");
		identifiers.add("labelToUpper");
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(new ArrayList<>());
		parameters.add(new ArrayList<>());
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.STRING, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(CONTRACT_LABEL.toUpperCase(), formula.getValue());
		assertEquals("get STRING contract.labelToUpper", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getStringValue()}
	 */
	@Test
	public void testGetStringValuePersonContextMethodWithParameters() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("person");
		identifiers.add("nameReplace");
		
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
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(formulasParameter1);
		parameters.add(formulasParameter2);
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.STRING, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(PERSON_2_NAME.replace("pire", "mieux"), formula.getValue());
		assertEquals("get STRING person(1).nameReplace(\"pire\"; \"mieux\")", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValueRootContext() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("booleanValue");
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(new ArrayList<>());
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.BOOLEAN, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(BOOLEAN_VALUE, formula.getValue());
		assertEquals("get BOOLEAN booleanValue", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValuePersonContext1() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("person");
		identifiers.add("isStillThere");
		
		FormulaTerminalInteger formula1Parameter1 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), String.valueOf(0));
		List<AbstractFormula> formulasParameter1 = new ArrayList<>();
		formulasParameter1.add(formula1Parameter1);
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(formulasParameter1);
		parameters.add(new ArrayList<>());
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.BOOLEAN, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(PERSON_1_IS_STILL_THERE, formula.getValue());
		assertEquals("get BOOLEAN person(0).isStillThere", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getBooleanValue()}
	 */
	@Test
	public void testGetBooleanValuePersonContext2() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("person");
		identifiers.add("isStillThere");
		
		FormulaTerminalInteger formula1Parameter1 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), String.valueOf(1));
		List<AbstractFormula> formulasParameter1 = new ArrayList<>();
		formulasParameter1.add(formula1Parameter1);
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(formulasParameter1);
		parameters.add(new ArrayList<>());
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.BOOLEAN, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(PERSON_2_IS_STILL_THERE, formula.getValue());
		assertEquals("get BOOLEAN person(1).isStillThere", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getDateValue()}
	 */
	@Test
	public void testGetDateValueRootContext() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("dateValue");
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(new ArrayList<>());
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.DATE, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(DATE_VALUE, formula.getValue());
		assertEquals("get DATE dateValue", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getDateValue()}
	 */
	@Test
	public void testGetDateValuePersonContextField() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("person");
		identifiers.add("birthDate");
		
		FormulaTerminalInteger formula1Parameter1 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), String.valueOf(0));
		List<AbstractFormula> formulasParameter1 = new ArrayList<>();
		formulasParameter1.add(formula1Parameter1);
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(formulasParameter1);
		parameters.add(new ArrayList<>());
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.DATE, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(PERSON_1_BIRTH_DATE, formula.getValue());
		assertEquals("get DATE person(0).birthDate", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getDateValue()}
	 */
	@Test
	public void testGetDateValuePersonContextMethod() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("person");
		identifiers.add("birthDatePlusDays");
		
		FormulaTerminalInteger formula1Parameter1 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), String.valueOf(0));
		List<AbstractFormula> formulasParameter1 = new ArrayList<>();
		formulasParameter1.add(formula1Parameter1);
		FormulaTerminalInteger formula1Parameter2 =
				new FormulaTerminalInteger(FormulaDescriptionFactory.getDescription(FormulaType.TERMINAL_INTEGER), String.valueOf(1));
		List<AbstractFormula> formulasParameter2 = new ArrayList<>();
		formulasParameter2.add(formula1Parameter2);
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(formulasParameter1);
		parameters.add(formulasParameter2);
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.DATE, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(PERSON_1_BIRTH_DATE.plusDays(1), formula.getValue());
		assertEquals("get DATE person(0).birthDatePlusDays(1)", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getDurationValue()}
	 */
	@Test
	public void testGetDurationValueRootContext() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("durationValue");
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(new ArrayList<>());
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.DURATION, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(DURATION_VALUE, formula.getValue());
		assertEquals("get DURATION durationValue", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getDurationValue()}
	 */
	@Test
	public void testGetDurationValueContractContextField() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("contract");
		identifiers.add("hours");
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(new ArrayList<>());
		parameters.add(new ArrayList<>());
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.DURATION, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(CONTRACT_HOURS, formula.getValue());
		assertEquals("get DURATION contract.hours", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#getDurationValue()}
	 */
	@Test
	public void testGetDurationValueContractContextMethod() {
		List<String> identifiers = new ArrayList<>();
		identifiers.add("contract");
		identifiers.add("halfHours");
		
		List<List<AbstractFormula>> parameters = new ArrayList<>();
		parameters.add(new ArrayList<>());
		parameters.add(new ArrayList<>());
		
		FormulaGet formula = new FormulaGet(FormulaDescriptionFactory.getDescription(FormulaType.C_GET),
				FormulaDescriptionFactory.getDescription(FormulaType.C_PRIMITIVE), FormulaReturnType.DURATION, identifiers, parameters);
		formula.setContext(context);
		
		assertEquals(Duration.ofHours(CONTRACT_HOURS.toHours() / 2), formula.getValue());
		assertEquals("get DURATION contract.halfHours", formula.asText());
	}
	
	/**
	 * Tests {@link FormulaGet#asText()}
	 */
	@Test
	public void testAsText() {
		FormulaReturnType returnType = FormulaReturnType.BOOLEAN;
		
		List<String> identifiers = Arrays.asList("call1", "call2", "call3");
		
		AbstractFormula calls3Param1 = mock(AbstractFormula.class);
		when(calls3Param1.asText()).thenReturn("some_rule1");
		
		AbstractFormula calls3Param2 = mock(AbstractFormula.class);
		when(calls3Param2.asText()).thenReturn("some_rule2");
		
		List<List<AbstractFormula>> parameters =
				Arrays.asList(Arrays.asList(), Arrays.asList(), Arrays.asList(calls3Param1, calls3Param2));
				
		FormulaGet formula = new FormulaGet(null, null, returnType, identifiers, parameters);
		
		assertEquals("get BOOLEAN call1.call2.call3(some_rule1; some_rule2)", formula.asText());
	}
	
	@SuppressWarnings("unused")
	public class Context {
		private List<ContextPerson> person;
		private ContextContract contract;
		private String stringValue;
		private Integer integerValue;
		private Double numericValue;
		private Boolean booleanValue;
		private LocalDate dateValue;
		private Duration durationValue;
		
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
		private String name;
		private Integer age;
		private Double numericValue;
		private Boolean isStillThere;
		private LocalDate birthDate;
		private Duration durationValue;
		
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
		private String label;
		private Integer id;
		private Double salary;
		private LocalDate startDate;
		private Duration hours;
		
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
