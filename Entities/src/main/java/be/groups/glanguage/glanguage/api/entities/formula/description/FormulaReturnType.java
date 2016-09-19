package be.groups.glanguage.glanguage.api.entities.formula.description;

/**
 * Return types of Formula's
 * 
 * @author michotte
 */
public enum FormulaReturnType {

	UNDEFINED, INTEGER, NUMERIC, STRING, BOOLEAN, DATE, DURATION;

	/**
	 * Translate a specified returnTypeId into a ReturnType object
	 * 
	 * @param returnTypeId
	 *            the id that has to be translated into a ReturnType
	 * @return the ReturnType which ordinal number equals the specified
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
