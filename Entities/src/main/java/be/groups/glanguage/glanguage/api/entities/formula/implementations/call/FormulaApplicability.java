package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

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
	public Integer doGetIntegerValue() {
		throw new UnsupportedOperationException(
				"Cannot invoke getIntegerValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public Double doGetNumericValue() {
		throw new UnsupportedOperationException(
				"Cannot invoke getNumericValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public String doGetStringValue() {
		throw new UnsupportedOperationException(
				"Cannot invoke getStringValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public Boolean doGetBooleanValue() {
		return getReferencedRule().getApplicabilityCondition().getBooleanValue();
	}

	@Override
	public LocalDate doGetDateValue() {
		throw new UnsupportedOperationException(
				"Cannot invoke getDateValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public Duration doGetDurationValue() {
		throw new UnsupportedOperationException(
				"Cannot invoke getDurationValue() method on " + this.getClass().getName() + " object");
	}
	
}
