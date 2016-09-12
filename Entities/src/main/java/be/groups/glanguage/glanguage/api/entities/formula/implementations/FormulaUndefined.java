/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaType;

/**
 * Formula representing a constant integer value
 * 
 * @author dupirefr
 */
@Entity
@DiscriminatorValue(FormulaType.Values.UNDEFINED)
public class FormulaUndefined extends AbstractTerminalFormula {

	public FormulaUndefined() {
		super();
	}

	@Override
	public Integer getIntegerValue() {
		return null;
	}

	@Override
	public Double getNumericValue() {
		return null;
	}

	@Override
	public Boolean getBooleanValue() {
		return null;
	}

	@Override
	public LocalDate getDateValue() {
		return null;
	}
	
}
