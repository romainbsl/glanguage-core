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
 * Formula implementing a "modulo" operation<br>
 * This formula is of type {@link FormulaType#OP_MODULO}
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_MODULO)
public class FormulaModulo extends BinaryFormula {

    protected FormulaModulo() {
        super();
    }

    public FormulaModulo(FormulaDescription description,
                         AbstractFormula child1,
                         AbstractFormula child2,
                         Evaluator evaluator) throws GLanguageException {
        super(description, child1, child2, evaluator);
    }

    /**
     * Get the result of applying a "modulo" operation on the integer values of the parameters :<br>
     * the result is the remainder of the division of the first parameter by the second one
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the result of applying a a "modulo" operation on the integer values of the parameters
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getIntegerValue(evaluator) % getParameters().get(1).getIntegerValue(evaluator);
    }

    @Override
    public String operationAsText() {
        return "\\";
    }

}
