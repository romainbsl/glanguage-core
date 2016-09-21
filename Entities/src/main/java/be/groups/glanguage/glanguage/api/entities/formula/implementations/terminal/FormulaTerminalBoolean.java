/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

/**
 * Formula representing a constant integer value
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.TERMINAL_BOOLEAN)
public class FormulaTerminalBoolean extends AbstractTerminalFormula {

	private Boolean booleanValue;

	protected FormulaTerminalBoolean() {
		super();
	}

	public FormulaTerminalBoolean(FormulaDescription description, String constantValue) {
		super(description, constantValue);
		
		initializeBooleanValue(constantValue);
	}

	public FormulaTerminalBoolean(FormulaDescription description, Boolean constantValue) {
		this(description, constantValue.toString());
	}

	@JsonIgnore
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
			} else {
				this.booleanValue = Boolean.valueOf(constantValue);
			}
			this.booleanValue = Boolean.valueOf(constantValue);
		}
	}

}
