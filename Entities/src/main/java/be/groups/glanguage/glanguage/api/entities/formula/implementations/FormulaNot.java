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
 * Formula representing a logical not operation<br>
 * This Formula has exactly one (1) parameter<br>
 * The type of the parameter must be boolean
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.NOT)
public class FormulaNot extends AbstractNonTerminalFormula {
	
	public FormulaNot() {
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
		return !getParameters().get(0).getBooleanValue();
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
		return "not " + getParameters().get(0).asText();
	}
	
}
