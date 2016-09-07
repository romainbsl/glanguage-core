/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
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
@DiscriminatorValue(FormulaType.Values.OP_NOT)
public class FormulaNot extends AbstractNonTerminalFormula {
	
	public FormulaNot() {
		super();
	}

	public FormulaNot(AbstractFormula child) {
		if (child == null) {
        	throw new IllegalArgumentException("Child must be non-null");
        }
        this.parameters = new ArrayList<>();
		parameters.add(child);
	}

	@Override
	public Integer getIntegerValue() {
		throw new IllegalAccessError("Cannot invoke getIntegerValue() method on FormulaNot object");
	}

	@Override
	public Double getNumericValue() {
		throw new IllegalAccessError("Cannot invoke getNumericValue() method on FormulaNot object");
	}

	@Override
	public String getStringValue() {
		throw new IllegalAccessError("Cannot invoke getStringValue() method on FormulaNot object");
	}
	
	@Transient
	@Override
	public Boolean getBooleanValue() {
		return !getParameters().get(0).getBooleanValue();
	}

	@Override
	public LocalDate getDateValue() {
		throw new IllegalAccessError("Cannot invoke getDateValue() method on FormulaNot object");
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
