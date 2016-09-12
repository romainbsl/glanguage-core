package be.groups.glanguage.glanguage.api.entities.formula;

/**
 * Priorities of {@link AbstractFormula}'s
 * 
 * @author michotte
 *
 */
public enum FormulaPriority {

	UNDEFINED, INSTRUCTION, OR, AND, COMPARISON, ADDITION, MULTIPLICATION, UNARY, ATOMIC;

	/**
	 * Translate a specified formulaPriorityId into a FormulaPriority object
	 * 
	 * @param formulaPriorityId
	 *            the id that has to be translated into a FormulaPriority
	 * @return the FormulaPriority which ordinal number equals the specified
	 *         formulaPriorityId if it exists, null otherwise
	 */
	public static FormulaPriority translate(Integer formulaPriorityId) {
		for (FormulaPriority item : FormulaPriority.values()) {
			if (item.ordinal() == formulaPriorityId) {
				return item;
			}
		}
		return null;
	}

}
