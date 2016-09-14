package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;

@Entity
@DiscriminatorValue(FormulaDescription.Values.F_RULE_REFERENCE)
public class FormulaRuleReference extends AbstractFormula {

	private RuleVersion referencedRule;

	protected FormulaRuleReference() {
		super();
	}

	public FormulaRuleReference(String ruleId) {
		super(FormulaDescription.F_RULE_REFERENCE);

		setConstantValue(ruleId);
	}

	/**
	 * @return the ruleId
	 */
	public String getRuleId() {
		return getConstantValue();
	}

	/**
	 * @return the referencedRule
	 */
	public RuleVersion getReferencedRule() {
		return referencedRule;
	}

	@Override
	public boolean isTerminal() {
		return false;
	}

	@Override
	public FormulaReturnType getReturnType() {
		if (referencedRule != null && referencedRule.getFormula() != null) {
			return referencedRule.getReturnType();
		} else {
			return FormulaReturnType.UNDEFINED;
		}
	}

	@Override
	public Integer getIntegerValue() {
		if (referencedRule == null) {
			throw new IllegalAccessError("Cannot invoke getIntegerValue() method on " + this.getClass().getName()
					+ " object while referenced rule (version id : " + referencedRule.getId()
					+ ") is not set - while branching is not done");
		} else if (!(getReturnType().equals(FormulaReturnType.INTEGER)
				|| getReturnType().equals(FormulaReturnType.NUMERIC))) {
			throw new IllegalAccessError("Cannot invoke getIntegerValue() method on " + this.getClass().getName()
					+ " object if referenced rule (version id : " + referencedRule.getId()
					+ ") is not of type INTEGER or NUMERIC");
		}
		return referencedRule.getIntegerValue();
	}

	@Override
	public Double getNumericValue() {
		if (referencedRule == null) {
			throw new IllegalAccessError("Cannot invoke getNumericValue() method on " + this.getClass().getName()
					+ " object while referenced rule (version id : " + referencedRule.getId()
					+ ") is not set - while branching is not done");
		} else if (!(getReturnType().equals(FormulaReturnType.INTEGER)
				|| getReturnType().equals(FormulaReturnType.NUMERIC))) {
			throw new IllegalAccessError("Cannot invoke getNumericValue() method on " + this.getClass().getName()
					+ " object if referenced rule (version id : " + referencedRule.getId()
					+ ") is not of type INTEGER or NUMERIC");
		}
		return referencedRule.getNumericValue();
	}

	@Override
	public String getStringValue() {
		if (referencedRule == null) {
			throw new IllegalAccessError("Cannot invoke getStringValue() method on " + this.getClass().getName()
					+ " object while referenced rule (version id : " + referencedRule.getId()
					+ ") is not set - while branching is not done");
		} else if (!getReturnType().equals(FormulaReturnType.STRING)) {
			throw new IllegalAccessError("Cannot invoke getStringValue() method on " + this.getClass().getName()
					+ " object if referenced rule (version id : " + referencedRule.getId() + ") is not of type STRING");
		}
		return referencedRule.getStringValue();
	}

	@Override
	public Boolean getBooleanValue() {
		if (referencedRule == null) {
			throw new IllegalAccessError("Cannot invoke getBooleanValue() method on " + this.getClass().getName()
					+ " object while referenced rule (version id : " + referencedRule.getId()
					+ ") is not set - while branching is not done");
		} else if (!getReturnType().equals(FormulaReturnType.BOOLEAN)) {
			throw new IllegalAccessError("Cannot invoke getBooleanValue() method on " + this.getClass().getName()
					+ " object if referenced rule (version id : " + referencedRule.getId()
					+ ") is not of type BOOLEAN");
		}
		return referencedRule.getBooleanValue();
	}

	@Override
	public LocalDate getDateValue() {
		if (referencedRule == null) {
			throw new IllegalAccessError("Cannot invoke getDateValue() method on " + this.getClass().getName()
					+ " object while referenced rule (version id : " + referencedRule.getId()
					+ ") is not set - while branching is not done");
		} else if (!getReturnType().equals(FormulaReturnType.DATE)) {
			throw new IllegalAccessError("Cannot invoke getDateValue() method on " + this.getClass().getName()
					+ " object if referenced rule (version id : " + referencedRule.getId() + ") is not of type DATE");
		}
		return referencedRule.getDateValue();
	}

	@Override
	public Duration getDurationValue() {
		if (referencedRule == null) {
			throw new IllegalAccessError("Cannot invoke getDurationValue() method on " + this.getClass().getName()
					+ " object while referenced rule (version id : " + referencedRule.getId()
					+ ") is not set - while branching is not done");
		} else if (!getReturnType().equals(FormulaReturnType.DURATION)) {
			throw new IllegalAccessError("Cannot invoke getDurationValue() method on " + this.getClass().getName()
					+ " object if referenced rule (version id : " + referencedRule.getId() + ") is not of type DURATION");
		}
		return referencedRule.getDurationValue();
	}

	/**
	 * @param ruleId
	 *            the ruleId to set
	 */
	public void setRuleId(String ruleId) {
		setConstantValue(ruleId);
	}

	/**
	 * @param referencedRule
	 *            the referencedRule to set
	 */
	public void setReferencedRule(RuleVersion referencedRule) {
		this.referencedRule = referencedRule;
	}

	@Override
	public String asText() {
		return getConstantValue();
	}

}
