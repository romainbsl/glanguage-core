/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import java.time.LocalDate;

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
	public Integer getIntegerValue() {
		throw new IllegalAccessError(
				"Cannot invoke getIntegerValue() method on " + this.getClass().getName() + " object");
	}

	@Transient
	@Override
	public Double getNumericValue() {
		throw new IllegalAccessError(
				"Cannot invoke getNumericValue() method on " + this.getClass().getName() + " object");
	}

	@Transient
	@Override
	public Boolean getBooleanValue() {
		if (booleanValue == null) {
			initializeBooleanValue(getConstantValue());
		}

		return booleanValue;
	}

	@Transient
	@Override
	public LocalDate getDateValue() {
		throw new IllegalAccessError("Cannot invoke getDateValue() method on " + this.getClass().getName() + " object");
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
