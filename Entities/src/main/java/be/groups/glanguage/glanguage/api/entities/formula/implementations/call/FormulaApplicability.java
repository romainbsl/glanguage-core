package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.Duration;
import java.time.LocalDate;

@Entity
@DiscriminatorValue(value = FormulaType.Values.C_APPLICABILITY)
public class FormulaApplicability extends RuleCallFormula {
	
	public FormulaApplicability() {
		super();
	}

	public FormulaApplicability(FormulaDescription description, String ruleId) {
		super(description, ruleId);
	}

	@Override
	public String operationAsText() {
		return ".applicable";
	}

	@Override
	protected Integer doGetIntegerValue(RuleVersion ruleVersion, Evaluator evaluator) {
		throw new UnsupportedOperationException(
				"Cannot invoke getIntegerValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	protected Double doGetNumericValue(RuleVersion ruleVersion, Evaluator evaluator) {
		throw new UnsupportedOperationException(
				"Cannot invoke getNumericValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	protected String doGetStringValue(RuleVersion ruleVersion, Evaluator evaluator) {
		throw new UnsupportedOperationException(
				"Cannot invoke getStringValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	protected Boolean doGetBooleanValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageEvaluationException {
		return ruleVersion.getApplicabilityCondition().getBooleanValue(evaluator);
	}

	@Override
	protected LocalDate doGetDateValue(RuleVersion ruleVersion, Evaluator evaluator) {
		throw new UnsupportedOperationException(
				"Cannot invoke getDateValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	protected Duration doGetDurationValue(RuleVersion ruleVersion, Evaluator evaluator) {
		throw new UnsupportedOperationException(
				"Cannot invoke getDurationValue() method on " + this.getClass().getName() + " object");
	}
	
}
