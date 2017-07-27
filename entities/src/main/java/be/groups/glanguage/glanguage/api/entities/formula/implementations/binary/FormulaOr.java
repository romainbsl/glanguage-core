/**
 *
 */
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
 * Formula implementing a logical "and" operation<br>
 * This formula is of type {@link FormulaType#OP_OR}
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_OR)
public class FormulaOr extends BinaryFormula {

    protected FormulaOr() {
        super();
    }

    public FormulaOr(FormulaDescription description,
                     AbstractFormula child1,
                     AbstractFormula child2,
                     Evaluator evaluator) throws GLanguageException {
        super(description, child1, child2, evaluator);
    }

    /**
     * Get the result of applying a logical "or" operation on the boolean values of the parameters : <br>
     * true if at least one of the parameters is true, false otherwise
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the result of applying a logical "or" operation on the boolean values of the parameters
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getBooleanValue(evaluator) || getParameters().get(1).getBooleanValue(evaluator);
    }

    @Override
    public String operationAsText() {
        return "or";
    }

}
