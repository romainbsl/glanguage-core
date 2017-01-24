/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Formula representing a constant integer value
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.TERMINAL_STRING)
public class FormulaTerminalString extends AbstractTerminalFormula {

	protected FormulaTerminalString() {
		super();
	}

	public FormulaTerminalString(FormulaDescription description, String constantValue) throws GLanguageException {
		super(description, constantValue);
	}
	
	public String asText() {
		StringBuilder sb = new StringBuilder("\"");
		sb.append(super.asText());
		sb.append("\"");
		return sb.toString();
	}
		
}
