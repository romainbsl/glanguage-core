/**
 *
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Formula implementing a logical "greater than or equal to" operation<br>
 * This formula is of type {@link FormulaType#OP_GREATER_OR_EQUAL}
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_GREATER_OR_EQUAL)
public class FormulaGreaterOrEqual extends BinaryFormula {

    protected FormulaGreaterOrEqual() {
        super();
    }

    public FormulaGreaterOrEqual(FormulaDescription description,
                                 AbstractFormula child1,
                                 AbstractFormula child2,
                                 Evaluator evaluator) throws GLanguageException {
        super(description, child1, child2, evaluator);
    }

    /**
     * Get the result of applying a logical "greater than or equal to" operation on the values of the parameters :<br>
     * true if first parameter is greater than or equal to the second one, false otherwise
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the result of applying a logical "greater than or equal to" operation on the values of the parameters
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
        switch (getParameters().get(0).getReturnType(evaluator)) {
            case DATE:
                return !getParameters().get(0).getDateValue(evaluator).isBefore(getParameters().get(1)
                                                                                        .getDateValue(evaluator));
            case INTEGER:
                if (getParameters().get(1).getReturnType(evaluator).equals(FormulaReturnType.INTEGER)) {
                    return getParameters().get(0).getIntegerValue(evaluator) >= getParameters().get(1).getIntegerValue(
                            evaluator);
                } else { // TODO use numeric each time?
                    return getParameters().get(0).getNumericValue(evaluator) >= getParameters().get(1).getNumericValue(
                            evaluator);
                }
            case NUMERIC:
                return getParameters().get(0).getNumericValue(evaluator) >= getParameters().get(1).getNumericValue(
                        evaluator);
            case STRING:
                return getParameters().get(0).getStringValue(evaluator).compareTo(getParameters().get(1)
                                                                                          .getStringValue(evaluator)) >= 0;
            case DURATION:
                return getParameters().get(0).getDurationValue(evaluator).compareTo(getParameters().get(1)
                                                                                            .getDurationValue(evaluator)) >= 0;
            default:
                throw new IllegalArgumentException("Cannot compare unknown values in " + this.getClass()
                        .getName() + " object");
        }
    }

    @Override
    public String operationAsText() {
        return ">=";
    }

}
