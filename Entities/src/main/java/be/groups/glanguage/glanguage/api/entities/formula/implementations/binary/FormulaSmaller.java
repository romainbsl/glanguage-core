/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

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

	public FormulaSmaller(FormulaDescription description, AbstractFormula child1, AbstractFormula child2) {
		super(description, child1, child2);
	}

	@JsonIgnore
	@Transient
	@Override
	protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageEvaluationException {
		switch (parameters.get(0).getReturnType(evaluator)) {
		case DATE:
			return getParameters().get(0).getDateValue(evaluator).isBefore(getParameters().get(1).getDateValue
					(evaluator));
		case INTEGER:
			if (parameters.get(1).getReturnType(evaluator).equals(FormulaReturnType.INTEGER)) {
				return getParameters().get(0).getIntegerValue(evaluator) < getParameters().get(1).getIntegerValue(evaluator);
			} else { // TODO use numeric each time?
				return getParameters().get(0).getNumericValue(evaluator) < getParameters().get(1).getNumericValue(evaluator);
			}
		case NUMERIC:
			return getParameters().get(0).getNumericValue(evaluator) < getParameters().get(1).getNumericValue(evaluator);
		case STRING:
			return getParameters().get(0).getStringValue(evaluator).compareTo(getParameters().get(1).getStringValue(evaluator)) < 0;
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
