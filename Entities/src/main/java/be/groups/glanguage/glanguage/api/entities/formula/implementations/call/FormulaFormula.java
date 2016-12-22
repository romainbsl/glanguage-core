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
@DiscriminatorValue(FormulaType.Values.C_FORMULA)
public class FormulaFormula extends RuleCallFormula {
	
	protected FormulaFormula() {
		super();
	}
	
	public FormulaFormula(FormulaDescription description, String ruleId) {
		super(description, ruleId);
	}
	
	@Override
	protected Integer doGetIntegerValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageEvaluationException {
		return ruleVersion.getFormula().getIntegerValue(evaluator);
	}
	
	@Override
	protected Double doGetNumericValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageEvaluationException {
		return ruleVersion.getFormula().getNumericValue(evaluator);
	}
	
	@Override
	protected String doGetStringValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageEvaluationException {
		return ruleVersion.getFormula().getStringValue(evaluator);
	}
	
	@Override
	protected Boolean doGetBooleanValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageEvaluationException {
		return ruleVersion.getFormula().getBooleanValue(evaluator);
	}
	
	@Override
	protected LocalDate doGetDateValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageEvaluationException {
		return ruleVersion.getFormula().getDateValue(evaluator);
	}
	
	@Override
	protected Duration doGetDurationValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageEvaluationException {
		return ruleVersion.getFormula().getDurationValue(evaluator);
	}
	
	@Override
	public String operationAsText() {
		return ".formula";
	}
	
}
