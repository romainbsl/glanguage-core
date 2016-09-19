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
@DiscriminatorValue(FormulaDescription.Values.TERMINAL_BOOLEAN)
public class FormulaTerminalBoolean extends AbstractTerminalFormula {

	private Boolean booleanValue;

	protected FormulaTerminalBoolean() {
		super();
	}

	public FormulaTerminalBoolean(String constantValue) {
		super(FormulaDescription.TERMINAL_BOOLEAN, constantValue);
		initializeBooleanValue(constantValue);
	}

	public FormulaTerminalBoolean(Boolean constantValue) {
		this(constantValue.toString());
	}

	@Transient
	@Override
	public Boolean getBooleanValue() {
		if (booleanValue == null) {
			initializeBooleanValue(getConstantValue());
		}
		return booleanValue;
	}

	private void initializeBooleanValue(String constantValue) {
		if (constantValue != null) {
			if (!(constantValue.equalsIgnoreCase("true") || constantValue.equalsIgnoreCase("false"))) {
				throw new IllegalArgumentException("Contant value must reprensent a boolean value : " + constantValue);
			}
			this.booleanValue = Boolean.valueOf(constantValue);
		}
	}

}
