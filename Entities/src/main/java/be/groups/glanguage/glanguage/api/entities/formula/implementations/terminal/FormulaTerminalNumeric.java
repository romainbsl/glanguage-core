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
@DiscriminatorValue(FormulaType.Values.TERMINAL_NUMERIC)
public class FormulaTerminalNumeric extends AbstractTerminalFormula {

	protected FormulaTerminalNumeric() {
		super();
	}

	public FormulaTerminalNumeric(String constantValue) {
		super(constantValue);
	}

	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue() {
		return getNumericValue().intValue();
	}

	@JsonIgnore
	@Transient
	@Override
	public Double getNumericValue() {
		try {
			return Double.valueOf(getConstantValue());
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException("Contant value must reprensent a numeric value : " + getConstantValue());
		}
	}
	
}
