package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

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
 * Formula implementing an "integer division" operation<br>
 * This formula is of type {@link FormulaType#OP_INTEGER_DIVISION}
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_INTEGER_DIVISION)
public class FormulaIntegerDivision extends BinaryFormula {

    protected FormulaIntegerDivision() {
        super();
    }

    public FormulaIntegerDivision(FormulaDescription description,
                                  AbstractFormula child1,
                                  AbstractFormula child2,
                                  Evaluator evaluator) throws GLanguageException {
        super(description, child1, child2, evaluator);
    }

    /**
     * Get the result of applying an "integer division" operation on the integer values of the parameters :<br>
     * the result of the division of the first parameter by the second one discarding the remainder (fractional part)
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the result of applying a an "integer division" operation on the integer values of the parameters
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getIntegerValue(evaluator) / getParameters().get(1).getIntegerValue(evaluator);
    }

    @Override
    public String operationAsText() {
        return "/";
    }

}
