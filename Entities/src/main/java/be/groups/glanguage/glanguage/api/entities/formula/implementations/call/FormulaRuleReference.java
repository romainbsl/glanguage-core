package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;

@Entity
@DiscriminatorValue(FormulaDescription.Values.C_RULE_REFERENCE)
public class FormulaRuleReference extends RuleCallFormula {

	protected FormulaRuleReference() {
		super();
	}

	public FormulaRuleReference(String ruleId) {
		super(FormulaDescription.C_RULE_REFERENCE, ruleId);
	}

	@Override
	public Integer doGetIntegerValue() {
		return getReferencedRule().getIntegerValue();
	}

	@Override
	public Double doGetNumericValue() {
		return getReferencedRule().getNumericValue();
	}

	@Override
	public String doGetStringValue() {
		return getReferencedRule().getStringValue();
	}

	@Override
	public Boolean doGetBooleanValue() {
		return getReferencedRule().getBooleanValue();
	}

	@Override
	public LocalDate doGetDateValue() {
		return getReferencedRule().getDateValue();
	}

	@Override
	public Duration doGetDurationValue() {
		return getReferencedRule().getDurationValue();
	}

	@Override
	public String operationAsText() {
		return "";
	}

}
