/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

/**
 * Formula representing a logical smaller operation<br>
 * This Formula has exactly two (2) parameters
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_SMALLER)
public class FormulaSmaller extends BinaryFormula {

	protected FormulaSmaller() {
		super();
	}

	public FormulaSmaller(AbstractFormula child1, AbstractFormula child2) {
		super( child1, child2);
	}

	@JsonIgnore
	@Transient
	@Override
	public Boolean getBooleanValue() {
		switch (parameters.get(0).getReturnType()) {
		case DATE:
			return getParameters().get(0).getDateValue().isBefore(getParameters().get(1).getDateValue());
		case INTEGER:
			if (parameters.get(1).getReturnType().equals(FormulaReturnType.INTEGER)) {
				return getParameters().get(0).getIntegerValue() < getParameters().get(1).getIntegerValue();
			} else { // TODO use numeric each time?
				return getParameters().get(0).getNumericValue() < getParameters().get(1).getNumericValue();
			}
		case NUMERIC:
			return getParameters().get(0).getNumericValue() < getParameters().get(1).getNumericValue();
		case STRING:
			return getParameters().get(0).getStringValue().compareTo(getParameters().get(1).getStringValue()) < 0;
		default:
			throw new IllegalArgumentException(
					"Cannot compare unknown values in " + this.getClass().getName() + " object");
		}
	}

	@Override
	public String operationAsText() {
		return "<";
	}

}
