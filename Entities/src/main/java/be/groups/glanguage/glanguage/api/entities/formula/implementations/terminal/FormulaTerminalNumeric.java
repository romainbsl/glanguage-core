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
@DiscriminatorValue(FormulaDescription.Values.TERMINAL_NUMERIC)
public class FormulaTerminalNumeric extends AbstractTerminalFormula {
	
	private Double doubleValue;
	
	public FormulaTerminalNumeric() {
		super();
	}
	
	public FormulaTerminalNumeric(String constantValue) {
		super(constantValue);
		try {
			this.doubleValue = Double.valueOf(constantValue);
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException("Contant value must reprensent a numeric value : " + constantValue);
		}
	}
	
	@Transient
	@Override
	public Integer getIntegerValue() {
		return doubleValue.intValue();
	}
	
	@Transient
	@Override
	public Double getNumericValue() {
		return doubleValue;
	}
	
}
