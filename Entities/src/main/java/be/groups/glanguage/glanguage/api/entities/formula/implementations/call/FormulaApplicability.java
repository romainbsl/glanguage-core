package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

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
	public Integer doGetIntegerValue(Evaluator evaluator) {
		throw new UnsupportedOperationException(
				"Cannot invoke getIntegerValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public Double doGetNumericValue(Evaluator evaluator) {
		throw new UnsupportedOperationException(
				"Cannot invoke getNumericValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public String doGetStringValue(Evaluator evaluator) {
		throw new UnsupportedOperationException(
				"Cannot invoke getStringValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public Boolean doGetBooleanValue(Evaluator evaluator) {
		return getReferencedRule(evaluator).getApplicabilityCondition().getBooleanValue(evaluator);
	}

	@Override
	public LocalDate doGetDateValue(Evaluator evaluator) {
		throw new UnsupportedOperationException(
				"Cannot invoke getDateValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public Duration doGetDurationValue(Evaluator evaluator) {
		throw new UnsupportedOperationException(
				"Cannot invoke getDurationValue() method on " + this.getClass().getName() + " object");
	}
	
}
