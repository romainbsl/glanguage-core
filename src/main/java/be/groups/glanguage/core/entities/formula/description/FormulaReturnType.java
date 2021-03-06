package be.groups.glanguage.core.entities.formula.description;

/**
 * Enum representing the return types of a formula
 *
 * @author michotte
 */
public enum FormulaReturnType {

	UNDEFINED, INTEGER, NUMERIC, STRING, BOOLEAN, DATE, DURATION, LIST, PROCEDURE;

	/**
	 * Translate a specified returnTypeId into a {@link FormulaReturnType} enum
	 * 
	 * @param returnTypeId
	 *            the id that has to be translated into a ReturnType
	 * @return the {@link FormulaReturnType} which ordinal number equals the specified
	 *         returnTypeId if it exists, null otherwise
	 */
	public static FormulaReturnType translate(Integer returnTypeId) {
		for (FormulaReturnType item : FormulaReturnType.values()) {
			if (item.ordinal() == returnTypeId) {
				return item;
			}
		}
		return null;
	}

}
