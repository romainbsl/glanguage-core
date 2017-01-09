/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Formula representing a logical or operation<br>
 * This Formula has exactly two (2) parameters<br>
 * The type of the parameters must be boolean
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_OR)
public class FormulaOr extends BinaryFormula {

	protected FormulaOr() {
		super();
	}

	public FormulaOr(FormulaDescription description, AbstractFormula child1, AbstractFormula child2) {
		super(description, child1, child2);
	}

	@JsonIgnore
	@Transient
	@Override
	protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
		return getParameters().get(0).getBooleanValue(evaluator) || getParameters().get(1).getBooleanValue(evaluator);
	}

	@Override
	public String operationAsText() {
		return "or";
	}

}
