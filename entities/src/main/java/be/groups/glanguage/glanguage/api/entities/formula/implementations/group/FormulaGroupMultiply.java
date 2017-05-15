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
@DiscriminatorValue(FormulaType.Values.G_MULT)
public class FormulaGroupMultiply extends GroupFormula {
		
	public FormulaGroupMultiply() {
		super();
	}

	public FormulaGroupMultiply(FormulaDescription description, String groupId) throws GLanguageException {
		super(description, groupId);
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
		double result = 1.0;
		Iterator<RuleVersion> itGroupItems = getRulesInGroup(evaluator).iterator();
		while (itGroupItems.hasNext()) {
			result *= itGroupItems.next().getNumericValue(evaluator);
		}
		return result;
	}
	
	@Override
	public String operationAsText() {
		return "multiply";
	}
	
}
