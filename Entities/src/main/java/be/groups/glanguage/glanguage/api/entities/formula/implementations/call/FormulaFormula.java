package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

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
	public Integer doGetIntegerValue() {
		return getReferencedRule().getFormula().getIntegerValue();
	}
	
	@Override
	public Double doGetNumericValue() {
		return getReferencedRule().getFormula().getNumericValue();
	}
	
	@Override
	public String doGetStringValue() {
		return getReferencedRule().getFormula().getStringValue();
	}
	
	@Override
	public Boolean doGetBooleanValue() {
		return getReferencedRule().getFormula().getBooleanValue();
	}
	
	@Override
	public LocalDate doGetDateValue() {
		return getReferencedRule().getFormula().getDateValue();
	}
	
	@Override
	public Duration doGetDurationValue() {
		return getReferencedRule().getFormula().getDurationValue();
	}
	
	@Override
	public String operationAsText() {
		return ".formula";
	}
	
}
