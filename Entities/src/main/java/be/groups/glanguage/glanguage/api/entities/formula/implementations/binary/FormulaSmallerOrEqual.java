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
 * Formula representing a logical smaller or equal operation<br>
 * This Formula has exactly two (2) parameters
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaDescription.Values.OP_SMALLER_OR_EQUAL)
public class FormulaSmallerOrEqual extends BinaryFormula {
	
	public FormulaSmallerOrEqual() {
		super();
	}

	public FormulaSmallerOrEqual(AbstractFormula child1, AbstractFormula child2) {
		super(child1, child2);
	}
	
	@Transient
	@Override
	public Boolean getBooleanValue() {
		switch(getReturnType()) {
			case BOOLEAN:
				throw new IllegalArgumentException("Cannot compare boolean values in " + this.getClass().getName() + " object");
			case DATE:
				return !getParameters().get(0).getDateValue().isAfter(getParameters().get(1).getDateValue());
			case INTEGER:
				return getParameters().get(0).getIntegerValue() <= getParameters().get(1).getIntegerValue();
			case NUMERIC:
				return getParameters().get(0).getNumericValue() <= getParameters().get(1).getNumericValue();
			case STRING:
				return getParameters().get(0).getStringValue().compareTo(getParameters().get(1).getStringValue()) <= 0;
			case UNDEFINED:
				throw new IllegalArgumentException("Cannot compare undefined values in " + this.getClass().getName() + " object");
			default:
				throw new IllegalArgumentException("Cannot compare unknown values in " + this.getClass().getName() + " object");
		}
	}

	@Transient
	@Override
	public Integer getIntegerValue() {
		throw new IllegalAccessError("Cannot invoke getIntegerValue() method on " + this.getClass().getName() + " object");
	}
	
	@Transient
	@Override
	public Double getNumericValue() {
		throw new IllegalAccessError("Cannot invoke getNumericValue() method on " + this.getClass().getName() + " object");
	}
	
	@Transient
	@Override
	public String getStringValue() {
		throw new IllegalAccessError("Cannot invoke getStringValue() method on " + this.getClass().getName() + " object");
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
		return "<=";
	}

}
