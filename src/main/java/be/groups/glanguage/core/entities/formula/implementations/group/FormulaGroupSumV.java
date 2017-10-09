package be.groups.glanguage.core.entities.formula.implementations.group;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.rule.RuleVersion;
import be.groups.glanguage.core.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Iterator;

/**
 * Formula implementing sum operation on a group of possibly rounded rules propagating rounding precision losses
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.G_SUMV)
public class FormulaGroupSumV extends GroupFormula {

    public FormulaGroupSumV() {
        super();
    }

    public FormulaGroupSumV(FormulaDescription description,
                            String groupId,
                            Evaluator evaluator) throws GLanguageException {
        super(description, groupId, evaluator);
    }

    /**
     * Get the sum of {@link RuleVersion#getIntegerValue(Evaluator)} of all sub-rules of the rule group as
     * {@link Integer} propagating rounding precision losses
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the sum of {@link RuleVersion#getIntegerValue(Evaluator)} of all sub-rules of the rule group as
     * {@link Integer} propagating rounding precision losses
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the group rule doesn't
     * exist or if the type of at least one sub-rule is not {@link FormulaReturnType#INTEGER} or
     * {@link FormulaReturnType#NUMERIC}
     */
    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        if (getGroupRule() == null) {
            throw new IllegalAccessError("Cannot invoke getRulesInGroup() method on " + this.getClass()
                    .getName() + " object while referenced rule (version id : " + getConstantValue() + ") is not set " + "- while branching is not done");
        } else if (!(getReturnType(evaluator).equals(FormulaReturnType.INTEGER) || getReturnType(evaluator).equals(
                FormulaReturnType.NUMERIC))) {
            throw new IllegalAccessError("Cannot invoke getIntegerValue() method on " + this.getClass()
                    .getName() + " object if referenced rule group  (version id : " + getConstantValue() + ") has " +
												 "rules that are not of type INTEGER or NUMERIC");
        }
        int result = 0;
        int delta = 0;
        Iterator<RuleVersion> itGroupItems = getRulesInGroup(evaluator).iterator();
        while (itGroupItems.hasNext()) {
            RuleVersion item = itGroupItems.next();
            if (!item.isEvaluated() && item.isApplicable(evaluator)) {
                // Get the value of the formula of the rule (without rounding)
                int value = item.getFormula().getIntegerValue(evaluator) + delta;
                // Set the value of the formula to the rule (applying rounding)
                item.setIntegerValue(value, evaluator);
                // Get the difference between the value of the formula (without rounding) and the value of the rule
                // (after applying rounding)
                delta = value - item.getIntegerValue(evaluator);
            }
            result += item.getIntegerValue(evaluator);
        }
        return result;
    }

    /**
     * Get the sum of {@link RuleVersion#getNumericValue(Evaluator)} of all sub-rules of the rule group as
     * {@link Double} propagating rounding precision losses
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the sum of {@link RuleVersion#getNumericValue(Evaluator)} of all sub-rules of the rule group as
     * {@link Double} propagating rounding precision losses
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the group rule doesn't
     * exist or if the type of at least one sub-rule is not {@link FormulaReturnType#INTEGER} or
     * {@link FormulaReturnType#NUMERIC}
     */
    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        if (getGroupRule() == null) {
            throw new IllegalAccessError("Cannot invoke getRulesInGroup() method on " + this.getClass()
                    .getName() + " object while referenced rule (version id : " + getConstantValue() + ") is not set " +
												 "- while branching is not done");
        } else if (!(getReturnType(evaluator).equals(FormulaReturnType.INTEGER) || getReturnType(evaluator).equals(
                FormulaReturnType.NUMERIC))) {
            throw new IllegalAccessError("Cannot invoke getIntegerValue() method on " + this.getClass()
                    .getName() + " object if referenced rule group  (version id : " + getConstantValue() + ") has " +
												 "rules that are not of type INTEGER or NUMERIC");
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
