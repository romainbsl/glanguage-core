package be.groups.glanguage.glanguage.api.entities.formula;

public enum FormulaDescription {
	/*
	 * Enum constants
	 */
	/* Undefined */
	UNDEFINED(Values.UNDEFINED, FormulaPriority.UNDEFINED, FormulaReturnType.UNDEFINED),

	/* Standard functions */
	F_ABS(Values.F_ABS, FormulaPriority.INSTRUCTION, FormulaReturnType.UNDEFINED),

	F_BANKERS_ROUNDED(Values.F_BANKERS_ROUNDED, FormulaPriority.INSTRUCTION, FormulaReturnType.UNDEFINED),

	F_CEIL(Values.F_CEIL, FormulaPriority.INSTRUCTION, FormulaReturnType.UNDEFINED),

	F_FLOOR(Values.F_FLOOR, FormulaPriority.INSTRUCTION, FormulaReturnType.UNDEFINED),

	F_FORMAT_DATE(Values.F_FORMAT_DATE, FormulaPriority.INSTRUCTION, FormulaReturnType.STRING),

	F_FORMAT_INTEGER(Values.F_FORMAT_INTEGER, FormulaPriority.INSTRUCTION, FormulaReturnType.STRING),

	F_FORMAT_NUMERIC(Values.F_FORMAT_NUMERIC, FormulaPriority.INSTRUCTION, FormulaReturnType.STRING),

	F_FORMAT_STRING(Values.F_FORMAT_STRING, FormulaPriority.INSTRUCTION, FormulaReturnType.STRING),

	F_ROUNDED(Values.F_ROUNDED, FormulaPriority.UNARY, FormulaReturnType.UNDEFINED),

	F_STRING_ITEM(Values.F_STRING_ITEM, FormulaPriority.INSTRUCTION, FormulaReturnType.STRING),

	F_SUBSTRING(Values.F_SUBSTRING, FormulaPriority.INSTRUCTION, FormulaReturnType.STRING),

	F_SIGN(Values.F_SIGN, FormulaPriority.INSTRUCTION, FormulaReturnType.UNDEFINED),

	F_TRUNC(Values.F_TRUNC, FormulaPriority.INSTRUCTION, FormulaReturnType.UNDEFINED),

	F_MIN(Values.F_MIN, FormulaPriority.INSTRUCTION, FormulaReturnType.UNDEFINED),

	F_MAX(Values.F_MAX, FormulaPriority.INSTRUCTION, FormulaReturnType.UNDEFINED),

	F_SMIN(Values.F_SMIN, FormulaPriority.INSTRUCTION, FormulaReturnType.UNDEFINED),

	F_SMAX(Values.F_SMAX, FormulaPriority.INSTRUCTION, FormulaReturnType.UNDEFINED),

	F_DATE(Values.F_DATE, FormulaPriority.INSTRUCTION, FormulaReturnType.INTEGER),

	F_MINUTES(Values.F_MINUTES, FormulaPriority.INSTRUCTION, FormulaReturnType.INTEGER),

	F_HOURS(Values.F_HOURS, FormulaPriority.INSTRUCTION, FormulaReturnType.INTEGER),

	F_DAYS(Values.F_DAYS, FormulaPriority.INSTRUCTION, FormulaReturnType.INTEGER),

	F_MONTHS(Values.F_MONTHS, FormulaPriority.INSTRUCTION, FormulaReturnType.INTEGER),

	F_YEARS(Values.F_YEARS, FormulaPriority.INSTRUCTION, FormulaReturnType.INTEGER),

	F_BRACKETS(Values.F_BRACKETS, FormulaPriority.INSTRUCTION, FormulaReturnType.UNDEFINED),

	F_IN(Values.F_IN, FormulaPriority.INSTRUCTION, FormulaReturnType.BOOLEAN),

	F_PUT_TEXT(Values.F_PUT_TEXT, FormulaPriority.INSTRUCTION, FormulaReturnType.UNDEFINED),

	F_RULE_REFERENCE(Values.F_RULE_REFERENCE, FormulaPriority.INSTRUCTION, FormulaReturnType.UNDEFINED),

	F_STRING_LENGTH(Values.F_STRING_LENGTH, FormulaPriority.INSTRUCTION, FormulaReturnType.INTEGER),

	/* Unary operations */
	OP_NOT(Values.OP_NOT, FormulaPriority.UNARY, FormulaReturnType.BOOLEAN),

	OP_UNARY_PLUS(Values.OP_UNARY_PLUS, FormulaPriority.UNARY, FormulaReturnType.UNDEFINED),

	OP_UNARY_MINUS(Values.OP_UNARY_MINUS, FormulaPriority.UNARY, FormulaReturnType.UNDEFINED),

	OP_EXIST(Values.OP_EXIST, FormulaPriority.UNARY, FormulaReturnType.BOOLEAN),

	/* Binary operations */
	OP_MULTIPLY(Values.OP_MULTIPLY, FormulaPriority.MULTIPLICATION, FormulaReturnType.UNDEFINED),

	OP_DIVIDE(Values.OP_DIVIDE, FormulaPriority.MULTIPLICATION, FormulaReturnType.UNDEFINED),

	OP_INTEGER_DIVISION(Values.OP_INTEGER_DIVISION, FormulaPriority.MULTIPLICATION, FormulaReturnType.INTEGER),

	OP_MODULO(Values.OP_MODULO, FormulaPriority.UNDEFINED, FormulaReturnType.INTEGER),

	OP_PLUS(Values.OP_PLUS, FormulaPriority.ADDITION, FormulaReturnType.UNDEFINED),

	OP_MINUS(Values.OP_MINUS, FormulaPriority.ADDITION, FormulaReturnType.UNDEFINED),

	OP_EQUAL(Values.OP_EQUAL, FormulaPriority.COMPARISON, FormulaReturnType.BOOLEAN),

	OP_DIFFERENCE(Values.OP_DIFFERENCE, FormulaPriority.COMPARISON, FormulaReturnType.BOOLEAN),

	OP_SMALLER(Values.OP_SMALLER, FormulaPriority.COMPARISON, FormulaReturnType.BOOLEAN),

	OP_SMALLER_OR_EQUAL(Values.OP_SMALLER_OR_EQUAL, FormulaPriority.COMPARISON, FormulaReturnType.BOOLEAN),

	OP_GREATER(Values.OP_GREATER, FormulaPriority.COMPARISON, FormulaReturnType.BOOLEAN),

	OP_GREATER_OR_EQUAL(Values.OP_GREATER_OR_EQUAL, FormulaPriority.COMPARISON, FormulaReturnType.BOOLEAN),

	OP_AND(Values.OP_AND, FormulaPriority.AND, FormulaReturnType.BOOLEAN),

	OP_OR(Values.OP_OR, FormulaPriority.OR, FormulaReturnType.BOOLEAN),

	/* Terminal */
	TERMINAL_INTEGER(Values.TERMINAL_INTEGER, FormulaPriority.ATOMIC, FormulaReturnType.INTEGER),

	TERMINAL_NUMERIC(Values.TERMINAL_NUMERIC, FormulaPriority.ATOMIC, FormulaReturnType.NUMERIC),

	TERMINAL_STRING(Values.TERMINAL_STRING, FormulaPriority.ATOMIC, FormulaReturnType.STRING),

	TERMINAL_BOOLEAN(Values.TERMINAL_BOOLEAN, FormulaPriority.ATOMIC, FormulaReturnType.BOOLEAN),

	TERMINAL_DATE(Values.TERMINAL_DATE, FormulaPriority.ATOMIC, FormulaReturnType.DATE),
	
	TERMINAL_DURATION(Values.TERMINAL_DURATION, FormulaPriority.ATOMIC, FormulaReturnType.DURATION);

	/*
	 * Fields
	 */
	private Integer id;

	private FormulaPriority priority;

	private FormulaReturnType returnType;

	/*
	 * Constructors
	 */
	private FormulaDescription(String id, FormulaPriority priority, FormulaReturnType returnType) {
		this.id = Integer.valueOf(id);
		this.priority = priority;
		this.returnType = returnType;
	}

	/*
	 * Getters
	 */
	public Integer getId() {
		return id;
	}

	public FormulaPriority getPriority() {
		return priority;
	}

	public FormulaReturnType getReturnType() {
		return returnType;
	}

	/*
	 * Classes
	 */
	public static class Values {
		/* Undefined */
		public static final String UNDEFINED = "0";

		/* Standard functions */
		public static final String F_ABS = "1";
		public static final String F_BANKERS_ROUNDED = "2";
		public static final String F_CEIL = "3";
		public static final String F_FLOOR = "4";
		public static final String F_FORMAT_DATE = "5";
		public static final String F_FORMAT_INTEGER = "6";
		public static final String F_FORMAT_NUMERIC = "7";
		public static final String F_FORMAT_STRING = "8";
		public static final String F_ROUNDED = "9";
		public static final String F_STRING_ITEM = "10";
		public static final String F_SUBSTRING = "11";
		public static final String F_SIGN = "12";
		public static final String F_TRUNC = "13";
		public static final String F_MIN = "17";
		public static final String F_MAX = "18";
		public static final String F_SMIN = "19";
		public static final String F_SMAX = "20";
		public static final String F_DATE = "21";
		public static final String F_MINUTES = "22";
		public static final String F_HOURS = "23";
		public static final String F_DAYS = "24";
		public static final String F_MONTHS = "25";
		public static final String F_YEARS = "26";
		public static final String F_BRACKETS = "27";
		public static final String F_IN = "28";
		public static final String F_PUT_TEXT = "29";
		public static final String F_RULE_REFERENCE = "30";
		public static final String F_STRING_LENGTH = "31";

		/* Unary operations */
		public static final String OP_NOT = "101";
		public static final String OP_UNARY_PLUS = "102";
		public static final String OP_UNARY_MINUS = "103";
		public static final String OP_EXIST = "104";

		/* Binary operations */
		public static final String OP_MULTIPLY = "201";
		public static final String OP_DIVIDE = "202";
		public static final String OP_INTEGER_DIVISION = "203";
		public static final String OP_MODULO = "204";
		public static final String OP_PLUS = "205";
		public static final String OP_MINUS = "206";
		public static final String OP_EQUAL = "207";
		public static final String OP_DIFFERENCE = "208";
		public static final String OP_SMALLER = "209";
		public static final String OP_SMALLER_OR_EQUAL = "210";
		public static final String OP_GREATER = "211";
		public static final String OP_GREATER_OR_EQUAL = "212";
		public static final String OP_AND = "213";
		public static final String OP_OR = "214";

		/* Terminal */
		public static final String TERMINAL_INTEGER = "1001";
		public static final String TERMINAL_NUMERIC = "1002";
		public static final String TERMINAL_STRING = "1003";
		public static final String TERMINAL_BOOLEAN = "1004";
		public static final String TERMINAL_DATE = "1005";
		public static final String TERMINAL_DURATION = "1006";
	}

}
