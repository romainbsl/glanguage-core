/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import java.time.Duration;
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
@DiscriminatorValue(FormulaDescription.Values.TERMINAL_NUMERIC)
public class FormulaTerminalNumeric extends AbstractTerminalFormula {

	protected FormulaTerminalNumeric() {
		super();
	}

	public FormulaTerminalNumeric(String constantValue) {
		super(FormulaDescription.TERMINAL_NUMERIC, constantValue);
	}

	@Transient
	@Override
	public Integer getIntegerValue() {
		return getNumericValue().intValue();
	}

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
