package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.cannot.invoke.*;

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
	protected Integer doGetIntegerValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
		throw new GLanguageException(new FormulaCannotInvokeEvaluationIntegerMethodInnerError(this, evaluator));
	}

	@Override
	protected Double doGetNumericValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
		throw new GLanguageException(new FormulaCannotInvokeEvaluationNumericMethodInnerError(this, evaluator));
	}

	@Override
	protected String doGetStringValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
		throw new GLanguageException(new FormulaCannotInvokeEvaluationStringMethodInnerError(this, evaluator));
	}

	@Override
	protected Boolean doGetBooleanValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
		return ruleVersion.getApplicabilityCondition().getBooleanValue(evaluator);
	}

	@Override
	protected LocalDate doGetDateValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
		throw new GLanguageException(new FormulaCannotInvokeEvaluationDateMethodInnerError(this, evaluator));
	}

	@Override
	protected Duration doGetDurationValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
		throw new GLanguageException(new FormulaCannotInvokeEvaluationDurationMethodInnerError(this, evaluator));
	}
	
}
