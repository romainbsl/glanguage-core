package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(FormulaType.Values.OP_EXIST)
public class FormulaExist extends UnaryFormula {

	protected FormulaExist() {
		super();
	}

	public FormulaExist(FormulaDescription description, AbstractFormula child) {
		super(description, child);
	}

	@JsonIgnore
	@Transient
	@Override
	public Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageEvaluationException {
		return getParameters().get(0).getValue(evaluator) != null;
	}

	@Override
	public String operationAsText() {
		return "?";
	}
}
