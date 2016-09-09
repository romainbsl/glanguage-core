/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaType;

/**
 * Formula representing a constant integer value
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.TERMINAL_INTEGER)
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
	
	@Transient
	@Override
	public Boolean getBooleanValue() {
		throw new IllegalAccessError("Cannot invoke getBooleanValue() method on " + this.getClass().getName() + " object");
	}
	
	@Transient
	@Override
	public LocalDate getDateValue() {
		throw new IllegalAccessError("Cannot invoke getDateValue() method on " + this.getClass().getName() + " object");
	}

}
