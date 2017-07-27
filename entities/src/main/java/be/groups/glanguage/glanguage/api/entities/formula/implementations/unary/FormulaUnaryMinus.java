package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Formula implementing the mathematical minus operation
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_UNARY_MINUS)
public class FormulaUnaryMinus extends UnaryFormula {

    protected FormulaUnaryMinus() {
        super();
    }

    public FormulaUnaryMinus(FormulaDescription description,
                             AbstractFormula child,
                             Evaluator evaluator) throws GLanguageException {
        super(description, child, evaluator);
    }

    /**
     * Get the opposite value as {@link Integer}<br>
     * The value is the opposite of the {@link AbstractFormula#getIntegerValue(Evaluator)} of the parameter
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the opposite value as {@link Integer}
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    public Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        return -getParameters().get(0).getIntegerValue(evaluator);
    }

    /**
     * Get the opposite value as {@link Double}<br>
     * The value is the opposite of the {@link AbstractFormula#getNumericValue(Evaluator)} of the parameter
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the opposite value as {@link Double}
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    public Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        return -getParameters().get(0).getNumericValue(evaluator);
    }

    @Override
    public String operationAsText() {
        return "-";
    }

}
