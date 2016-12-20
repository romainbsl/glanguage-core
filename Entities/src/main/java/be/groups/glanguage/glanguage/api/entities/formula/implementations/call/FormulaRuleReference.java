package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.Duration;
import java.time.LocalDate;

@Entity
@DiscriminatorValue(FormulaType.Values.C_RULE_REFERENCE)
public class FormulaRuleReference extends RuleCallFormula {

	protected FormulaRuleReference() {
		super();
	}

	public FormulaRuleReference(FormulaDescription description, String ruleId) {
		super(description, ruleId);
	}

	@Override
	Integer doGetIntegerValue(Evaluator evaluator) {
		return getReferencedRule(evaluator).getIntegerValue(evaluator);
	}

	@Override
	Double doGetNumericValue(Evaluator evaluator) {
		return getReferencedRule(evaluator).getNumericValue(evaluator);
	}

	@Override
	String doGetStringValue(Evaluator evaluator) {
		return getReferencedRule(evaluator).getStringValue(evaluator);
	}

	@Override
	Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageEvaluationException {
		return getReferencedRule(evaluator).getBooleanValue(evaluator);
	}

	@Override
	LocalDate doGetDateValue(Evaluator evaluator) {
		return getReferencedRule(evaluator).getDateValue(evaluator);
	}

	@Override
	Duration doGetDurationValue(Evaluator evaluator) {
		return getReferencedRule(evaluator).getDurationValue(evaluator);
	}

	@Override
	public String operationAsText() {
		return "";
	}

}
