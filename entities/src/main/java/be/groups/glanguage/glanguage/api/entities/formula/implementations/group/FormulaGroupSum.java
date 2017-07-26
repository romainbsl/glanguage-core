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

/**
 * Formula implementing sum operation on a group of rules
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.G_SUM)
public class FormulaGroupSum extends GroupFormula {

    public FormulaGroupSum() {
        super();
    }

    public FormulaGroupSum(FormulaDescription description,
                           String groupId,
                           Evaluator evaluator) throws GLanguageException {
        super(description, groupId, evaluator);
    }

    /**
     * Get the sum of {@link RuleVersion#getNumericValue(Evaluator)} of all sub-rules of the rule group as
     * {@link Double}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the sum of {@link RuleVersion#getNumericValue(Evaluator)} of all sub-rules of the rule group as
     * {@link Double}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the group rule doesn't
     * exist or if the type of at least one sub-rule is not {@link FormulaReturnType#INTEGER} or
     * {@link FormulaReturnType#NUMERIC}
     */
    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        if (getGroupRule() == null) {
            // TODO replace by a GLanguageException
            throw new IllegalAccessError("Cannot invoke getRulesInGroup() method on " + this.getClass()
                    .getName() + " object while referenced rule (version id : " + getConstantValue() + ") is not set " +
                                                 "- while branching is not done");
        } else if (!(getReturnType(evaluator).equals(FormulaReturnType.INTEGER) || getReturnType(evaluator).equals(
                FormulaReturnType.NUMERIC))) {
            // TODO replace by a GLanguageException
            throw new IllegalAccessError("Cannot invoke getIntegerValue() method on " + this.getClass()
                    .getName() + " object if referenced rule group  (version id : " + getConstantValue() + ") has " +
												 "rules that are not of type INTEGER or NUMERIC");
        }
        double result = 0.0;
        Iterator<RuleVersion> itGroupItems = getRulesInGroup(evaluator).iterator();
        while (itGroupItems.hasNext()) {
            result += itGroupItems.next().getNumericValue(evaluator);
        }
        return result;
    }

    @Override
    public String operationAsText() {
        return "multiply";
    }

}
