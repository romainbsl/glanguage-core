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
@DiscriminatorValue(FormulaDescription.Values.TERMINAL_STRING)
public class FormulaTerminalString extends AbstractTerminalFormula {

	protected FormulaTerminalString() {
		super();
	}

	public FormulaTerminalString(String constantValue) {
		super(FormulaDescription.TERMINAL_STRING, constantValue);
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
		throw new IllegalAccessError(
				"Cannot invoke getBooleanValue() method on " + this.getClass().getName() + " object");
	}

	@Transient
	@Override
	public LocalDate getDateValue() {
		throw new IllegalAccessError("Cannot invoke getDateValue() method on " + this.getClass().getName() + " object");
	}

}
