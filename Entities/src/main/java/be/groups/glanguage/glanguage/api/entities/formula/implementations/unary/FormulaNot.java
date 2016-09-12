/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;

/**
 * Formula representing a logical not operation<br>
 * This Formula has exactly one (1) parameter<br>
 * The type of the parameter must be boolean
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaDescription.Values.OP_NOT)
public class FormulaNot extends UnaryFormula {

	protected FormulaNot() {
		super();
	}

	public FormulaNot(AbstractFormula child) {
		super(FormulaDescription.OP_NOT, child);
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
	public String operationAsText() {
		return "not";
	}

}
