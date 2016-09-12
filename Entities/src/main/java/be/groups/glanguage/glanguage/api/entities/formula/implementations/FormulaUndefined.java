/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;

/**
 * Formula representing a constant integer value
 * 
 * @author dupirefr
 */
@Entity
@DiscriminatorValue(FormulaDescription.Values.UNDEFINED)
public class FormulaUndefined extends AbstractTerminalFormula {

	public FormulaUndefined() {
		super();
	}

	@Transient
	@Override
	public Integer getIntegerValue() {
		return null;
	}

	@Transient
	@Override
	public Double getNumericValue() {
		return null;
	}

	@Transient
	@Override
	public Boolean getBooleanValue() {
		return null;
	}

	@Transient
	@Override
	public LocalDate getDateValue() {
		return null;
	}

}
