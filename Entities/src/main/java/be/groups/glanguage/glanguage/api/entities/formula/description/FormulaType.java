package be.groups.glanguage.glanguage.api.entities.formula.description;

public enum FormulaType {
	/*
	 * Enum constants
	 */
	/* Undefined */
	UNDEFINED(Values.UNDEFINED),
	
	/* Standard functions */
	F_ABS(Values.F_ABS),
	
	F_BANKERS_ROUNDED(Values.F_BANKERS_ROUNDED),
	
	F_CEIL(Values.F_CEIL),
	
	F_FLOOR(Values.F_FLOOR),
	
	F_FORMAT_DATE(Values.F_FORMAT_DATE),
	
	F_FORMAT_INTEGER(Values.F_FORMAT_INTEGER),
	
	F_FORMAT_NUMERIC(Values.F_FORMAT_NUMERIC),
	
	F_FORMAT_STRING(Values.F_FORMAT_STRING),
	
	F_ROUNDED(Values.F_ROUNDED),
	
	F_STRING_ITEM(Values.F_STRING_ITEM),
	
	F_SUBSTRING(Values.F_SUBSTRING),
	
	F_SIGN(Values.F_SIGN),
	
	F_TRUNC(Values.F_TRUNC),
	
	F_MIN(Values.F_MIN),
	
	F_MAX(Values.F_MAX),
	
	F_SMIN(Values.F_SMIN),
	
	F_SMAX(Values.F_SMAX),
	
	F_DATE(Values.F_DATE),
	
	F_MINUTES(Values.F_MINUTES),
	
	F_HOURS(Values.F_HOURS),
	
	F_DAYS(Values.F_DAYS),
	
	F_MONTHS(Values.F_MONTHS),
	
	F_YEARS(Values.F_YEARS),
	
	F_BRACKETS(Values.F_BRACKETS),
	
	F_IN(Values.F_IN),
	
	F_PUT_TEXT(Values.F_PUT_TEXT),
	
	F_STRING_LENGTH(Values.F_STRING_LENGTH),
	
	/* Instructions */
	I_IF(Values.I_IF),
	
	/* Unary operations */
	OP_NOT(Values.OP_NOT),
	
	OP_UNARY_PLUS(Values.OP_UNARY_PLUS),
	
	OP_UNARY_MINUS(Values.OP_UNARY_MINUS),
	
	OP_EXIST(Values.OP_EXIST),
	
	/* Binary operations */
	OP_MULTIPLY(Values.OP_MULTIPLY),
	
	OP_DIVIDE(Values.OP_DIVIDE),
	
	OP_INTEGER_DIVISION(Values.OP_INTEGER_DIVISION),
	
	OP_MODULO(Values.OP_MODULO),
	
	OP_PLUS(Values.OP_PLUS),
	
	OP_MINUS(Values.OP_MINUS),
	
	OP_EQUAL(Values.OP_EQUAL),
	
	OP_DIFFERENCE(Values.OP_DIFFERENCE),
	
	OP_SMALLER(Values.OP_SMALLER),
	
	OP_SMALLER_OR_EQUAL(Values.OP_SMALLER_OR_EQUAL),
	
	OP_GREATER(Values.OP_GREATER),
	
	OP_GREATER_OR_EQUAL(Values.OP_GREATER_OR_EQUAL),
	
	OP_AND(Values.OP_AND),
	
	OP_OR(Values.OP_OR),
	
	/* Group function */
	G_MULT(Values.G_MULT),
	
	G_SUM(Values.G_SUM),
	
	G_SUMV(Values.G_SUMV),
	
	/* Calls */
	C_APPLICABILITY(Values.C_APPLICABILITY),
	
	C_FORMULA(Values.C_FORMULA),
	
	C_GET(Values.C_GET),
	
	C_PRIMITIVE(Values.C_PRIMITIVE),
	
	C_RULE_REFERENCE(Values.C_RULE_REFERENCE),
	
	/* Terminal */
	TERMINAL_INTEGER(Values.TERMINAL_INTEGER),
	
	TERMINAL_NUMERIC(Values.TERMINAL_NUMERIC),
	
	TERMINAL_STRING(Values.TERMINAL_STRING),
	
	TERMINAL_BOOLEAN(Values.TERMINAL_BOOLEAN),
	
	TERMINAL_DATE(Values.TERMINAL_DATE),
	
	TERMINAL_DURATION(Values.TERMINAL_DURATION);
	
	/*
	 * Fields
	 */
	private String value;
	
	/*
	 * Constructors
	 */
	private FormulaType(String value) {
		this.value = value;
	}
	
	/*
	 * Getters
	 */
	public String getValue() {
		return value;
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
		public static final String F_STRING_LENGTH = "30";
		
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
		
		/* Instructions */
		public static final String I_IF = "301";
		
		/* Group function */
		public static final String G_MULT = "501";
		public static final String G_SUM = "502";
		public static final String G_SUMV = "503";
		
		/* Calls */
		public static final String C_APPLICABILITY = "701";
		public static final String C_FORMULA = "702";
		public static final String C_GET = "703";
		public static final String C_PRIMITIVE = "704";
		public static final String C_RULE_REFERENCE = "705";
		
		/* Terminal */
		public static final String TERMINAL_INTEGER = "1001";
		public static final String TERMINAL_NUMERIC = "1002";
		public static final String TERMINAL_STRING = "1003";
		public static final String TERMINAL_BOOLEAN = "1004";
		public static final String TERMINAL_DATE = "1005";
		public static final String TERMINAL_DURATION = "1006";
	}
	
}
