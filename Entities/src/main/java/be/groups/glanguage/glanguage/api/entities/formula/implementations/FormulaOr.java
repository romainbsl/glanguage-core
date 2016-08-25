/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaType;

/**
 * Formula representing a logical or operation<br>
 * This Formula has exactly two (2) parameters<br>
 * The type of the parameters must be boolean
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OR)
public class FormulaOr extends AbstractNonTerminalFormula {
	
	public FormulaOr() {
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
	public String getStringValue() {
		return null;
	}
	
	@Transient
	@Override
	public Boolean getBooleanValue() {
		return getParameters().get(0).getBooleanValue() || getParameters().get(1).getBooleanValue();
	}
	
	@Transient
	@Override
	public LocalDate getDateValue() {
		return null;
	}
	
	@Override
	protected FormulaReturnType computeReturnType() {
		return getDescription().getReturnType();
	}
	
	@Override
	public String asText() {
		return getParameters().get(0).asText() + " || " + getParameters().get(1).asText();
	}
	
}
