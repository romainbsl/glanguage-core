/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaType;

/**
 * Formula representing a constant integer value
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.TERMINAL_NUMERIC)
public class FormulaTerminalNumeric extends AbstractTerminalFormula {

	public FormulaTerminalNumeric() {
		super();
	}
	
}
