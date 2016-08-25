package be.groups.glanguage.glanguage.api.entities.formula;

public enum FormulaType {
	
	UNDEFINED(Values.UNDEFINED),
	OR(Values.OR),
	AND(Values.AND),
	NOT(Values.NOT),
	PLUS(Values.PLUS),
	MINUS(Values.MINUS),
	TERMINAL_INTEGER(Values.TERMINAL_INTEGER);

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
			case Values.UNDEFINED:
				return UNDEFINED;				
			case Values.OR:
				return OR;	
			case Values.AND:
				return AND;	
			case Values.NOT:
				return NOT;	
			case Values.PLUS:
				return PLUS;	
			case Values.MINUS:
				return MINUS;	
			case Values.TERMINAL_INTEGER:
				return TERMINAL_INTEGER;	
			default:
				throw new IllegalArgumentException("Unknown" + formulaTypeId);
		}
	}
	
	public static class Values {		
		public static final String UNDEFINED = "0";
		public static final String OR = "1";
		public static final String AND = "2";
		public static final String NOT = "3";
		public static final String PLUS = "4";
		public static final String MINUS = "5";

		public static final String TERMINAL_INTEGER = "101";
		public static final String TERMINAL_NUMERIC = "102";
		public static final String TERMINAL_STRING = "103";
		public static final String TERMINAL_BOOLEAN = "104";
		public static final String TERMINAL_DATE = "105";
	}

}
