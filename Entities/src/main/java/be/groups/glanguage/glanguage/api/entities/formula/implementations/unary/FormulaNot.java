/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Formula representing a logical not operation<br>
 * This Formula has exactly one (1) parameter<br>
 * The type of the parameter must be boolean
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_NOT)
public class FormulaNot extends UnaryFormula {

	protected FormulaNot() {
		super();
	}

	public FormulaNot(FormulaDescription description, AbstractFormula child) {
		super(description, child);
	}

	@JsonIgnore
	@Transient
	@Override
	public Boolean getBooleanValue(Evaluator evaluator) {
		return !getParameters().get(0).getBooleanValue(evaluator);
	}

	@Override
	public String operationAsText() {
		return "not";
	}

}
