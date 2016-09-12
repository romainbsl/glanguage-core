package be.groups.glanguage.glanguage.api.entities.formula;

public enum FormulaType {
	
	/* Undefined */
	UNDEFINED(Values.UNDEFINED),
	
	/* Standard functions */
	F_ABS(Values.F_ABS),
	F_BANKERS_ROUNDED(Values.F_BANKERS_ROUNDED), 
	F_CEIL(Values.F_CEIL), 
	F_FLOOR(Values.F_FLOOR), 
	F_FORMATDATE(Values.F_FORMATDATE), 
	F_FORMATINTEGER(Values.F_FORMATINTEGER), 
	F_FORMATNUMERIC(Values.F_FORMATNUMERIC), 
	F_FORMATSTRING(Values.F_FORMATSTRING), 
	F_ROUNDED(Values.F_ROUNDED), 
	F_STRINGITEM(Values.F_STRINGITEM), 
	F_SUBSTRING(Values.F_SUBSTRING), 
	F_SIGN(Values.F_SIGN), 
	F_TRUNC(Values.F_TRUNC), 
	F_MULT(Values.F_MULT), 
	F_SUM(Values.F_SUM), 
	F_SUMV(Values.F_SUMV), 
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
	F_RULE_REFERENCE(Values.F_RULE_REFERENCE),
	F_STRINGLENGTH(Values.F_STRINGLENGTH),
																	
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
																																													
	/* Terminal */
	TERMINAL_INTEGER(Values.TERMINAL_INTEGER), 
	TERMINAL_NUMERIC(Values.TERMINAL_NUMERIC), 
	TERMINAL_STRING(Values.TERMINAL_STRING), 
	TERMINAL_BOOLEAN(Values.TERMINAL_BOOLEAN), 
	TERMINAL_DATE(Values.TERMINAL_DATE);																																																									
																																																									
	private String value;
	
	private FormulaType(String value) {
		this.value = value;
	}
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Translate a specified formulaTypeId into a FormulaType object
	 * 
	 * @param formulaTypeId the id that has to be translated into a FormulaType
	 * @return the FormulaType which ordinal number equals the specified formulaTypeId if it exists, null otherwise
	 */
	public static FormulaType translate(Integer formulaTypeId) {
		switch (String.valueOf(formulaTypeId)) {
			/* Undefined */			
			case Values.UNDEFINED:
				return UNDEFINED;
				
			/* Standard functions */
			case Values.F_ABS:
				return F_ABS;
			case Values.F_BANKERS_ROUNDED:
				return F_BANKERS_ROUNDED;
			case Values.F_CEIL:
				return F_CEIL;
			case Values.F_FLOOR:
				return F_FLOOR;
			case Values.F_FORMATDATE:
				return F_FORMATDATE;
			case Values.F_FORMATINTEGER:
				return F_FORMATINTEGER;
			case Values.F_FORMATNUMERIC:
				return F_FORMATNUMERIC;
			case Values.F_FORMATSTRING:
				return F_FORMATSTRING;
			case Values.F_ROUNDED:
				return F_ROUNDED;
			case Values.F_STRINGITEM:
				return F_STRINGITEM;
			case Values.F_SUBSTRING:
				return F_SUBSTRING;
			case Values.F_SIGN:
				return F_SIGN;
			case Values.F_TRUNC:
				return F_TRUNC;
			case Values.F_MULT:
				return F_MULT;
			case Values.F_SUM:
				return F_SUM;
			case Values.F_SUMV:
				return F_SUMV;
			case Values.F_MIN:
				return F_MIN;
			case Values.F_MAX:
				return F_MAX;
			case Values.F_SMIN:
				return F_SMIN;
			case Values.F_SMAX:
				return F_SMAX;
			case Values.F_DATE:
				return F_DATE;
			case Values.F_MINUTES:
				return F_MINUTES;
			case Values.F_HOURS:
				return F_HOURS;
			case Values.F_DAYS:
				return F_DAYS;
			case Values.F_MONTHS:
				return F_MONTHS;
			case Values.F_YEARS:
				return F_YEARS;
			case Values.F_BRACKETS:
				return F_BRACKETS;
			case Values.F_IN:
				return F_IN;
			case Values.F_PUT_TEXT:
				return F_PUT_TEXT;
			case Values.F_RULE_REFERENCE:
				return F_RULE_REFERENCE;
			case Values.F_STRINGLENGTH:
				return F_STRINGLENGTH;
				
			/* Unary operations */
			case Values.OP_NOT:
				return OP_NOT;
			case Values.OP_UNARY_PLUS:
				return OP_UNARY_PLUS;
			case Values.OP_UNARY_MINUS:
				return OP_UNARY_MINUS;
			case Values.OP_EXIST:
				return OP_EXIST;
				
			/* Binary operations */
			case Values.OP_MULTIPLY:
				return OP_MULTIPLY;
			case Values.OP_DIVIDE:
				return OP_DIVIDE;
			case Values.OP_INTEGER_DIVISION:
				return OP_INTEGER_DIVISION;
			case Values.OP_MODULO:
				return OP_MODULO;
			case Values.OP_PLUS:
				return OP_PLUS;
			case Values.OP_MINUS:
				return OP_MINUS;
			case Values.OP_EQUAL:
				return OP_EQUAL;
			case Values.OP_DIFFERENCE:
				return OP_DIFFERENCE;
			case Values.OP_SMALLER:
				return OP_SMALLER;
			case Values.OP_SMALLER_OR_EQUAL:
				return OP_SMALLER_OR_EQUAL;
			case Values.OP_GREATER:
				return OP_GREATER;
			case Values.OP_GREATER_OR_EQUAL:
				return OP_GREATER_OR_EQUAL;
			case Values.OP_AND:
				return OP_AND;
			case Values.OP_OR:
				return OP_OR;				
				
			/* Terminal */
			case Values.TERMINAL_INTEGER:
				return TERMINAL_INTEGER;
			case Values.TERMINAL_NUMERIC:
				return TERMINAL_NUMERIC;
			case Values.TERMINAL_STRING:
				return TERMINAL_STRING;
			case Values.TERMINAL_BOOLEAN:
				return TERMINAL_BOOLEAN;
			case Values.TERMINAL_DATE:
				return TERMINAL_DATE;
			default:
				throw new IllegalArgumentException("Unknown" + formulaTypeId);
		}
	}
	
	public static class Values {
		/* Undefined */
		public static final String UNDEFINED = "0";
		
		/* Standard functions */
		public static final String F_ABS = "1";
		public static final String F_BANKERS_ROUNDED = "2";
		public static final String F_CEIL = "3";
		public static final String F_FLOOR = "4";
		public static final String F_FORMATDATE = "5";
		public static final String F_FORMATINTEGER = "6";
		public static final String F_FORMATNUMERIC = "7";
		public static final String F_FORMATSTRING = "8";
		public static final String F_ROUNDED = "9";
		public static final String F_STRINGITEM = "10";
		public static final String F_SUBSTRING = "11";
		public static final String F_SIGN = "12";
		public static final String F_TRUNC = "13";
		public static final String F_MULT = "14";
		public static final String F_SUM = "15";
		public static final String F_SUMV = "16";
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
		public static final String F_STRINGLENGTH = "31";
		
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
	}
	
}
