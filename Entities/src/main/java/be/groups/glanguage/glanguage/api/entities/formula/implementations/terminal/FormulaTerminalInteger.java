/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

/**
 * Formula representing a constant integer value
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.TERMINAL_INTEGER)
public class FormulaTerminalInteger extends AbstractTerminalFormula {

	protected FormulaTerminalInteger() {
		super();
	}

	public FormulaTerminalInteger(String constantValue) {
		super(constantValue);
	}

	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue() {
		try {
			return Integer.valueOf(getConstantValue());
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException("Contant value must reprensent an integer value : " + getConstantValue());
		}
	}

	@JsonIgnore
	@Transient
	@Override
	public Double getNumericValue() {
		return getIntegerValue().doubleValue();
	}

}
