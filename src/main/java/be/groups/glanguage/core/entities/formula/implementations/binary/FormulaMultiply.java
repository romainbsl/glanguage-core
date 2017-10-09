package be.groups.glanguage.core.entities.formula.implementations.binary;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Formula implementing a "multiplication" operation<br>
 * This formula is of type {@link FormulaType#OP_MULTIPLY}
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_MULTIPLY)
public class FormulaMultiply extends BinaryFormula {

    protected FormulaMultiply() {
        super();
    }

    public FormulaMultiply(FormulaDescription description,
                           AbstractFormula child1,
                           AbstractFormula child2,
                           Evaluator evaluator) throws GLanguageException {
        super(description, child1, child2, evaluator);
    }

    /**
     * Get the truncated result of applying a "multiplication" operation on the numeric value of the parameters<br>
     * A call to this this method is equivalent to a call to {@link Double#intValue()} on the result of the method
     * {@link FormulaMultiply#doGetNumericValue(Evaluator)}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the truncated result of applying a "multiplication" operation on the numeric value of the parameters
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        return getNumericValue(evaluator).intValue();
    }

    /**
     * Get the result of applying a "multiplication" operation on the numeric value of the parameters
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the result of applying a "multiplication" operation on the numeric value of the parameters
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getNumericValue(evaluator) * getParameters().get(1).getNumericValue(evaluator);
    }

    @Override
    public String operationAsText() {
        return "*";
    }

}
