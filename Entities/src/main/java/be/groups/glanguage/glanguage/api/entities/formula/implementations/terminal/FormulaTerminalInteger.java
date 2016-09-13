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

	private Integer integerValue;
	
	public FormulaTerminalInteger() {
		super();
	}
		
	public FormulaTerminalInteger(String constantValue) {
		super(constantValue);
		try {
			this.integerValue = Integer.valueOf(constantValue);
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException("Contant value must reprensent an integer value : " + constantValue);
		}
	}

	@Transient
	@Override
	public Integer getIntegerValue() {
		return integerValue;
	}
	
	@Transient
	@Override
	public Double getNumericValue() {
		return integerValue.doubleValue();
	}

}
