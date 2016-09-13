/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;

/**
 * Formula representing a constant integer value
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaDescription.Values.TERMINAL_STRING)
public class FormulaTerminalString extends AbstractTerminalFormula {

	protected FormulaTerminalString() {
		super();
	}

	public FormulaTerminalString(String constantValue) {
		super(FormulaDescription.TERMINAL_STRING, constantValue);
	}

}
