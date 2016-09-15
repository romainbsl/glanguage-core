package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;

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

	@Transient
	@Override
	public Integer getIntegerValueImpl() {
		if (getReferencedRule() == null) {
			throw new IllegalAccessError("Cannot invoke getIntegerValue() method on " + this.getClass().getName()
					+ " object while referenced rule (version id : " + getConstantValue()
					+ ") is not set - while branching is not done");
		} else if (!(getReturnType().equals(FormulaReturnType.INTEGER)
				|| getReturnType().equals(FormulaReturnType.NUMERIC))) {
			throw new IllegalAccessError("Cannot invoke getIntegerValue() method on " + this.getClass().getName()
					+ " object if referenced rule (version id : " + getConstantValue()
					+ ") is not of type INTEGER or NUMERIC");
		}
		return doGetIntegerValue();
	}

	public abstract Integer doGetIntegerValue();

	@Transient
	@Override
	public Double getNumericValueImpl() {
		if (getReferencedRule() == null) {
			throw new IllegalAccessError("Cannot invoke getNumericValue() method on " + this.getClass().getName()
					+ " object while referenced rule (version id : " + getConstantValue()
					+ ") is not set - while branching is not done");
		} else if (!(getReturnType().equals(FormulaReturnType.INTEGER)
				|| getReturnType().equals(FormulaReturnType.NUMERIC))) {
			throw new IllegalAccessError("Cannot invoke getNumericValue() method on " + this.getClass().getName()
					+ " object if referenced rule (version id : " + getConstantValue()
					+ ") is not of type INTEGER or NUMERIC");
		}
		return doGetNumericValue();
	}

	public abstract Double doGetNumericValue();

	@Transient
	@Override
	public String getStringValueImpl() {
		if (getReferencedRule() == null) {
			throw new IllegalAccessError("Cannot invoke getStringValue() method on " + this.getClass().getName()
					+ " object while referenced rule (version id : " + getConstantValue()
					+ ") is not set - while branching is not done");
		} else if (!getReturnType().equals(FormulaReturnType.STRING)) {
			throw new IllegalAccessError("Cannot invoke getStringValue() method on " + this.getClass().getName()
					+ " object if referenced rule (version id : " + getConstantValue() + ") is not of type STRING");
		}
		return doGetStringValue();
	}

	public abstract String doGetStringValue();

	@Transient
	@Override
	public Boolean getBooleanValueImpl() {
		if (getReferencedRule() == null) {
			throw new IllegalAccessError("Cannot invoke getBooleanValue() method on " + this.getClass().getName()
					+ " object while referenced rule (version id : " + getConstantValue()
					+ ") is not set - while branching is not done");
		} else if (!getReturnType().equals(FormulaReturnType.BOOLEAN)) {
			throw new IllegalAccessError("Cannot invoke getBooleanValue() method on " + this.getClass().getName()
					+ " object if referenced rule (version id : " + getConstantValue()
					+ ") is not of type BOOLEAN");
		}
		return doGetBooleanValue();
	}

	public abstract Boolean doGetBooleanValue();

	@Transient
	@Override
	public LocalDate getDateValueImpl() {
		if (getReferencedRule() == null) {
			throw new IllegalAccessError("Cannot invoke getDateValue() method on " + this.getClass().getName()
					+ " object while referenced rule (version id : " + getConstantValue()
					+ ") is not set - while branching is not done");
		} else if (!getReturnType().equals(FormulaReturnType.DATE)) {
			throw new IllegalAccessError("Cannot invoke getDateValue() method on " + this.getClass().getName()
					+ " object if referenced rule (version id : " + getConstantValue() + ") is not of type DATE");
		}
		return doGetDateValue();
	}

	public abstract LocalDate doGetDateValue();

	@Transient
	@Override
	public Duration getDurationValueImpl() {
		if (getReferencedRule() == null) {
			throw new IllegalAccessError("Cannot invoke getDurationValue() method on " + this.getClass().getName()
					+ " object while referenced rule (version id : " + getConstantValue()
					+ ") is not set - while branching is not done");
		} else if (!getReturnType().equals(FormulaReturnType.DURATION)) {
			throw new IllegalAccessError("Cannot invoke getDurationValue() method on " + this.getClass().getName()
					+ " object if referenced rule (version id : " + getConstantValue() + ") is not of type DURATION");
		}
		return doGetDurationValue();
	}

	public abstract Duration doGetDurationValue();

	/**
	 * @return the ruleId
	 */
	@Transient
	public String getRuleId() {
		return getConstantValue();
	}

	/**
	 * @return the referencedRule
	 */
	@Transient
	public RuleVersion getReferencedRule() {
		return referencedRule;
	}

	/**
	 * @param ruleId the ruleId to set
	 */
	public void setRuleId(String ruleId) {
		setConstantValue(ruleId);
	}

	/**
	 * @param referencedRule the referencedRule to set
	 */
	public void setReferencedRule(RuleVersion referencedRule) {
		this.referencedRule = referencedRule;
	}

	@Override
	protected FormulaReturnType computeReturnType() {
		if (referencedRule != null && referencedRule.getFormula() != null) {
			return referencedRule.getReturnType();
		} else {
			return FormulaReturnType.UNDEFINED;
		}
	}

	@Override
	public String asText() {
		return getConstantValue() + operationAsText();
	}

	public abstract String operationAsText();
	
}
