package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

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
	public Integer doGetIntegerValue(Evaluator evaluator) {
		return getReferencedRule(evaluator).getFormula().getIntegerValue(evaluator);
	}
	
	@Override
	public Double doGetNumericValue(Evaluator evaluator) {
		return getReferencedRule(evaluator).getFormula().getNumericValue(evaluator);
	}
	
	@Override
	public String doGetStringValue(Evaluator evaluator) {
		return getReferencedRule(evaluator).getFormula().getStringValue(evaluator);
	}
	
	@Override
	public Boolean doGetBooleanValue(Evaluator evaluator) {
		return getReferencedRule(evaluator).getFormula().getBooleanValue(evaluator);
	}
	
	@Override
	public LocalDate doGetDateValue(Evaluator evaluator) {
		return getReferencedRule(evaluator).getFormula().getDateValue(evaluator);
	}
	
	@Override
	public Duration doGetDurationValue(Evaluator evaluator) {
		return getReferencedRule(evaluator).getFormula().getDurationValue(evaluator);
	}
	
	@Override
	public String operationAsText() {
		return ".formula";
	}
	
}
