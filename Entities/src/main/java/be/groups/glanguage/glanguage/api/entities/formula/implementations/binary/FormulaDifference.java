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
 * Formula representing a logical not equal operation<br>
 * This Formula has exactly two (2) parameters
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_DIFFERENCE)
public class FormulaDifference extends BinaryFormula {

	protected FormulaDifference() {
		super();
	}

	public FormulaDifference(FormulaDescription description, AbstractFormula child1, AbstractFormula child2) throws GLanguageException {
		super(description, child1, child2);
	}

	@JsonIgnore
	@Transient
	@Override
	protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
		return !getParameters().get(0).getValue(evaluator).equals(getParameters().get(1).getValue(evaluator));
	}

	@Override
	public String operationAsText() {
		return "<>";
	}

}
