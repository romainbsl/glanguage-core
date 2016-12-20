package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.errorframework.core.error.ErrorEnum;
import be.groups.errorframework.core.error.RootError;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import be.groups.glanguage.glanguage.api.error.formula.implementations.call.RuleCallFormulaReferencedRuleUnavailableInnerError;
import be.groups.glanguage.glanguage.api.error.formula.implementations.call.RuleCallFormulaUnableToEvaluateTypeNotMatchableTypesInnerError;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;

@Entity
public abstract class RuleCallFormula extends CallFormula {
	
	private RuleVersion referencedRule;
	
	public RuleCallFormula() {
		super();
	}
	
	public RuleCallFormula(FormulaDescription description, String ruleId) {
		super(description);
		
		if (ruleId == null || ruleId.isEmpty()) {
			throw new IllegalArgumentException("ruleId must be a non-null non-empty string");
		}
		setConstantValue(ruleId);
	}
	
	@JsonIgnore
	@Transient
	@Override
	public FormulaReturnType getReturnType(Evaluator evaluator) {
		if (getReferencedRule(evaluator) == null) {
			throw new IllegalAccessError("Cannot invoke getReturnType() method on " + this.getClass().getName()
					+ " object while referenced rule (rule id : " + getConstantValue()
					+ ") is not set - while branching is not done - (id : " + this.getId() + ")");
		} else {
			return getReferencedRule(evaluator).getReturnType(evaluator);
		}
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue(Evaluator evaluator) {
		if (getReferencedRule(evaluator) == null) {
			throw new IllegalAccessError("Cannot invoke getIntegerValue() method on " + this.getClass().getName()
					+ " object while referenced rule (rule id : " + getConstantValue()
					+ ") is not set - while branching is not done - (id : " + this.getId() + ")");
		} else if (!(getReturnType(evaluator).equals(FormulaReturnType.INTEGER) || getReturnType(evaluator).equals
				(FormulaReturnType.NUMERIC))) {
			throw new IllegalAccessError("Cannot invoke getIntegerValue() method on " + this.getClass().getName()
					+ " object if referenced rule (rule id : " + getConstantValue() + ") is not of type INTEGER or NUMERIC - (id : "
					+ this.getId() + ")");
		}
		return doGetIntegerValue(evaluator);
	}
	
	abstract Integer doGetIntegerValue(Evaluator evaluator);
	
	@JsonIgnore
	@Transient
	@Override
	public Double getNumericValue(Evaluator evaluator) {
		if (getReferencedRule(evaluator) == null) {
			throw new IllegalAccessError("Cannot invoke getNumericValue() method on " + this.getClass().getName()
					+ " object while referenced rule (rule id : " + getConstantValue()
					+ ") is not set - while branching is not done - (id : " + this.getId() + ")");
		} else if (!(getReturnType(evaluator).equals(FormulaReturnType.INTEGER) || getReturnType(evaluator).equals
				(FormulaReturnType.NUMERIC))) {
			throw new IllegalAccessError("Cannot invoke getNumericValue() method on " + this.getClass().getName()
					+ " object if referenced rule (rule id : " + getConstantValue() + ") is not of type INTEGER or NUMERIC - (id : "
					+ this.getId() + ")");
		}
		return doGetNumericValue(evaluator);
	}
	
	abstract Double doGetNumericValue(Evaluator evaluator);
	
	@JsonIgnore
	@Transient
	@Override
	public String getStringValue(Evaluator evaluator) {
		if (getReferencedRule(evaluator) == null) {
			throw new IllegalAccessError("Cannot invoke getStringValue() method on " + this.getClass().getName()
					+ " object while referenced rule (rule id : " + getConstantValue()
					+ ") is not set - while branching is not done - (id : " + this.getId() + ")");
		} else if (!getReturnType(evaluator).equals(FormulaReturnType.STRING)) {
			throw new IllegalAccessError("Cannot invoke getStringValue() method on " + this.getClass().getName()
					+ " object if referenced rule (rule id : " + getConstantValue() + ") is not of type STRING - (id : "
					+ this.getId() + ")");
		}
		return doGetStringValue(evaluator);
	}
	
	abstract String doGetStringValue(Evaluator evaluator);
	
	@JsonIgnore
	@Transient
	@Override
	public Boolean getBooleanValue(Evaluator evaluator) throws GLanguageEvaluationException {
		if (getReferencedRule(evaluator) == null) {
			RootError error = new RootError(ErrorEnum.BUSINESS_ERROR);
			error.setInnererror(new RuleCallFormulaReferencedRuleUnavailableInnerError(this, evaluator));
			throw new GLanguageEvaluationException(error);
//			throw new IllegalAccessError("Cannot invoke getBooleanValue() method on " + this.getClass().getName()
//					+ " object while referenced rule (rule id : " + getConstantValue()
//					+ ") is not set - while branching is not done - (id : " + this.getId() + ")");
		} else if (!getReturnType(evaluator).equals(FormulaReturnType.BOOLEAN)) {
			RootError error = new RootError(ErrorEnum.BUSINESS_ERROR);
			error.setInnererror(new RuleCallFormulaUnableToEvaluateTypeNotMatchableTypesInnerError(this, evaluator,
					referencedRule, referencedRule.getReturnType(evaluator), FormulaReturnType.BOOLEAN,
					"getBooleanValue"));
			throw new GLanguageEvaluationException(error);
//			throw new IllegalAccessError("Cannot invoke getBooleanValue() method on " + this.getClass().getName()
//					+ " object if referenced rule (rule id : " + getConstantValue() + ") is not of type BOOLEAN - (id : "
//					+ this.getId() + ")");
		}
		return doGetBooleanValue(evaluator);
	}
	
	abstract Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageEvaluationException;
	
	@JsonIgnore
	@Transient
	@Override
	public LocalDate getDateValue(Evaluator evaluator) {
		if (getReferencedRule(evaluator) == null) {
			throw new IllegalAccessError("Cannot invoke getDateValue() method on " + this.getClass().getName()
					+ " object while referenced rule (rule id : " + getConstantValue()
					+ ") is not set - while branching is not done - (id : " + this.getId() + ")");
		} else if (!getReturnType(evaluator).equals(FormulaReturnType.DATE)) {
			throw new IllegalAccessError(
					"Cannot invoke getDateValue() method on " + this.getClass().getName() + " object if referenced rule (rule id : "
							+ getConstantValue() + ") is not of type DATE - (id : " + this.getId() + ")");
		}
		return doGetDateValue(evaluator);
	}
	
	abstract LocalDate doGetDateValue(Evaluator evaluator);
	
	@JsonIgnore
	@Transient
	@Override
	public Duration getDurationValue(Evaluator evaluator) {
		if (getReferencedRule(evaluator) == null) {
			throw new IllegalAccessError("Cannot invoke getDurationValue() method on " + this.getClass().getName()
					+ " object while referenced rule (rule id : " + getConstantValue()
					+ ") is not set - while branching is not done - (id : " + this.getId() + ")");
		} else if (!getReturnType(evaluator).equals(FormulaReturnType.DURATION)) {
			throw new IllegalAccessError("Cannot invoke getDurationValue() method on " + this.getClass().getName()
					+ " object if referenced rule (rule id : " + getConstantValue() + ") is not of type DURATION - (id : "
					+ this.getId() + ")");
		}
		return doGetDurationValue(evaluator);
	}
	
	abstract Duration doGetDurationValue(Evaluator evaluator);
	
	/**
	 * @param evaluator evaluator
	 * @return the referencedRule
	 */
	@JsonIgnore
	@Transient
	protected RuleVersion getReferencedRule(Evaluator evaluator) {
		if (referencedRule == null && evaluator != null) {
			referencedRule = evaluator.getRuleVersion(getConstantValue());
		}
		return referencedRule;
	}

	@JsonIgnore
	@Transient
	public boolean isBranched() {
		return referencedRule != null;
	}

	/**
	 * @param referencedRule the referencedRule to set
	 */
	public void setReferencedRule(RuleVersion referencedRule) {
		this.referencedRule = referencedRule;
		setConstantValue(String.valueOf(referencedRule.getRuleDefinition().getRuleIdentity().getId()));
	}
	
	@Override
	public String asText() {
		return ruleAsText() + operationAsText();
	}
	
	private String ruleAsText() {
		if (getReferencedRule(null) != null) {
			return String.valueOf(getReferencedRule(null).getCode());
		}
		return getConstantValue();
	}
	
	public abstract String operationAsText();
	
}
