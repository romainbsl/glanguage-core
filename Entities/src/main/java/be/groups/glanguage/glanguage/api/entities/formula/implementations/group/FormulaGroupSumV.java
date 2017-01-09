package be.groups.glanguage.glanguage.api.entities.formula.implementations.group;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Iterator;

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
	protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
		if (getGroupRule() == null) {
			throw new IllegalAccessError("Cannot invoke getRulesInGroup() method on " + this.getClass().getName()
					+ " object while referenced rule (version id : " + getConstantValue()
					+ ") is not set - while branching is not done");
		} else if (!(getReturnType(evaluator).equals(FormulaReturnType.INTEGER)
				|| getReturnType(evaluator).equals(FormulaReturnType.NUMERIC))) {
			throw new IllegalAccessError("Cannot invoke getIntegerValue() method on " + this.getClass().getName()
					+ " object if referenced rule group  (version id : " + getConstantValue()
					+ ") has rules that are not of type INTEGER or NUMERIC");
		}
		int result = 0;
		int delta = 0;
		Iterator<RuleVersion> itGroupItems = getRulesInGroup(evaluator).iterator();
		while (itGroupItems.hasNext()) {
			RuleVersion item = itGroupItems.next();
			if (!item.isEvaluated() && item.isApplicable(evaluator)) {
    			int value = item.getFormula().getIntegerValue(evaluator) + delta;
    			item.setIntegerValue(value, evaluator);
    			delta = value - item.getIntegerValue(evaluator);
			}
			result += item.getIntegerValue(evaluator);
		}
		return result;
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
		if (getGroupRule() == null) {
			throw new IllegalAccessError("Cannot invoke getRulesInGroup() method on " + this.getClass().getName()
					+ " object while referenced rule (version id : " + getConstantValue()
					+ ") is not set - while branching is not done");
		} else if (!(getReturnType(evaluator).equals(FormulaReturnType.INTEGER)
				|| getReturnType(evaluator).equals(FormulaReturnType.NUMERIC))) {
			throw new IllegalAccessError("Cannot invoke getIntegerValue() method on " + this.getClass().getName()
					+ " object if referenced rule group  (version id : " + getConstantValue()
					+ ") has rules that are not of type INTEGER or NUMERIC");
		}
		double result = 0.0;
		double delta = 0.0;
		Iterator<RuleVersion> itGroupItems = getRulesInGroup(evaluator).iterator();
		while (itGroupItems.hasNext()) {
			RuleVersion item = itGroupItems.next();
			if (!item.isEvaluated() && item.isApplicable(evaluator)) {
    			double value = item.getFormula().getNumericValue(evaluator) + delta;
    			item.setNumericValue(value, evaluator);
				delta = value - item.getNumericValue(evaluator);
			}	
			result += item.getNumericValue(evaluator);
		}
		return result;
	}
	
	@Override
	public String operationAsText() {
		return "sumv";
	}
	
}
