package be.groups.glanguage.glanguage.api.entities.formula.implementations.group;

import java.util.Iterator;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;

@Entity
@DiscriminatorValue(FormulaType.Values.G_SUMV)
public class FormulaGroupSumV extends GroupFormula {
		
	public FormulaGroupSumV() {
		super();
	}

	public FormulaGroupSumV(FormulaDescription description, String groupId) {
		super(description, groupId);
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue() {
		if (getGroupRule() == null) {
			throw new IllegalAccessError("Cannot invoke getRulesInGroup() method on " + this.getClass().getName()
					+ " object while referenced rule (version id : " + getConstantValue()
					+ ") is not set - while branching is not done");
		} else if (!(getReturnType().equals(FormulaReturnType.INTEGER)
				|| getReturnType().equals(FormulaReturnType.NUMERIC))) {
			throw new IllegalAccessError("Cannot invoke getIntegerValue() method on " + this.getClass().getName()
					+ " object if referenced rule group  (version id : " + getConstantValue()
					+ ") has rules that are not of type INTEGER or NUMERIC");
		}
		int result = 0;
		int delta = 0;
		Iterator<RuleVersion> itGroupItems = getRulesInGroup().iterator();
		while (itGroupItems.hasNext()) {
			RuleVersion item = itGroupItems.next();
			if (!item.isEvaluated() && item.isApplicable()) {
    			int value = item.getFormula().getIntegerValue() + delta;
    			item.setIntegerValue(value);
    			delta = value - item.getIntegerValue();
			}
			result += item.getIntegerValue();
		}
		return result;
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Double getNumericValue() {
		if (getGroupRule() == null) {
			throw new IllegalAccessError("Cannot invoke getRulesInGroup() method on " + this.getClass().getName()
					+ " object while referenced rule (version id : " + getConstantValue()
					+ ") is not set - while branching is not done");
		} else if (!(getReturnType().equals(FormulaReturnType.INTEGER)
				|| getReturnType().equals(FormulaReturnType.NUMERIC))) {
			throw new IllegalAccessError("Cannot invoke getIntegerValue() method on " + this.getClass().getName()
					+ " object if referenced rule group  (version id : " + getConstantValue()
					+ ") has rules that are not of type INTEGER or NUMERIC");
		}
		double result = 0.0;
		double delta = 0.0;
		Iterator<RuleVersion> itGroupItems = getRulesInGroup().iterator();
		while (itGroupItems.hasNext()) {
			RuleVersion item = itGroupItems.next();
			if (!item.isEvaluated() && item.isApplicable()) {
    			double value = item.getFormula().getNumericValue() + delta;
    			item.setNumericValue(value);
    			delta = value - item.getNumericValue();
			}	
			result += item.getNumericValue();
		}
		return result;
	}
	
	@Override
	public String operationAsText() {
		return "sumv";
	}
	
}
