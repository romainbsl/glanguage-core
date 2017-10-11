package be.groups.glanguage.core.entities.formula.implementations.group;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.rule.RuleVersion;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.formula.base.unable.evaluate.FormulaEvaluateTypeInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;
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
        try {
            double result = 0.0;
            Iterator<RuleVersion> itGroupItems = getRulesInGroup(evaluator).iterator();
            while (itGroupItems.hasNext()) {
                result += itGroupItems.next().getNumericValue(evaluator);
            }
            return result;
        } catch (GLanguageException gle) {
            gle.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.NUMERIC,
                null));
            throw gle;
        } catch (Exception e) {
            throw new GLanguageException(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.NUMERIC, e
                .getMessage()));
        }
    }

    @Override
    public String operationAsText() {
        return "multiply";
    }

}
