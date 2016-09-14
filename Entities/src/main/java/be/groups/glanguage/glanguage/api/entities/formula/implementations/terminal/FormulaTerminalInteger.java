/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;

/**
 * Formula representing a constant integer value
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaDescription.Values.TERMINAL_INTEGER)
public class FormulaTerminalInteger extends AbstractTerminalFormula {

	protected FormulaTerminalInteger() {
		super();
	}

	public FormulaTerminalInteger(String constantValue) {
		super(FormulaDescription.TERMINAL_INTEGER, constantValue);
	}

	@Transient
	@Override
	public Integer getIntegerValue() {
		try {
			return Integer.valueOf(getConstantValue());
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException("Contant value must reprensent an integer value : " + getConstantValue());
		}
	}

	@Transient
	@Override
	public Double getNumericValue() {
		return getIntegerValue().doubleValue();
	}

}
