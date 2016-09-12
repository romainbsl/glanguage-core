/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;

/**
 * Formula representing a logical not equal operation<br>
 * This Formula has exactly two (2) parameters
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaDescription.Values.OP_EQUAL)
public class FormulaDifference extends BinaryFormula {

	protected FormulaDifference() {
		super();
	}

	public FormulaDifference(AbstractFormula child1, AbstractFormula child2) {
		super(FormulaDescription.OP_DIFFERENCE, child1, child2);
	}

	@Transient
	@Override
	public Boolean getBooleanValue() {
		return !getParameters().get(0).getValue().equals(getParameters().get(1).getValue());
	}

	@Transient
	@Override
	public Integer getIntegerValue() {
		throw new IllegalAccessError(
				"Cannot invoke getIntegerValue() method on " + this.getClass().getName() + " object");
	}

	@Transient
	@Override
	public Double getNumericValue() {
		throw new IllegalAccessError(
				"Cannot invoke getNumericValue() method on " + this.getClass().getName() + " object");
	}

	@Transient
	@Override
	public String getStringValue() {
		throw new IllegalAccessError(
				"Cannot invoke getStringValue() method on " + this.getClass().getName() + " object");
	}

	@Transient
	@Override
	public LocalDate getDateValue() {
		throw new IllegalAccessError("Cannot invoke getDateValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	protected FormulaReturnType computeReturnType() {
		return getDescription().getReturnType();
	}

	@Override
	public String operationAsText() {
		return "<>";
	}

}
