package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;

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
	public FormulaReturnType getReturnType() {
		if (getReferencedRule() == null) {
			throw new IllegalAccessError("Cannot invoke getReturnType() method on " + this.getClass().getName()
					+ " object while referenced rule (version id : " + getConstantValue()
					+ ") is not set - while branching is not done");
		} else {
			return getReferencedRule().getReturnType();
		}
	}

	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue() {
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

	@JsonIgnore
	@Transient
	@Override
	public Double getNumericValue() {
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

	@JsonIgnore
	@Transient
	@Override
	public String getStringValue() {
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

	@JsonIgnore
	@Transient
	@Override
	public Boolean getBooleanValue() {
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

	@JsonIgnore
	@Transient
	@Override
	public LocalDate getDateValue() {
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

	@JsonIgnore
	@Transient
	@Override
	public Duration getDurationValue() {
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
	public String asText() {
		return getConstantValue() + operationAsText();
	}

	public abstract String operationAsText();
	
}
