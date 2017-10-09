/**
 *
 */
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
 * Formula implementing a logical "equal to" operation<br>
 * This formula is of type {@link FormulaType#OP_EQUAL}
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_EQUAL)
public class FormulaEqual extends BinaryFormula {

    protected FormulaEqual() {
        super();
    }

    public FormulaEqual(FormulaDescription description,
                        AbstractFormula child1,
                        AbstractFormula child2,
                        Evaluator evaluator) throws GLanguageException {
        super(description, child1, child2, evaluator);
    }

     /**
      * Get the result of applying a logical "equal to" operation on the values of the parameters :<br>
      * true if parameters are equal to each other, false otherwise
      *
      * @param evaluator the evaluator to be used in the evaluation process, can be null
      * @return the result of applying a logical "equal to" operation on the boolean values of the parameters
      * @throws GLanguageException if an error occurs during the evaluation process
      */
     @JsonIgnore
    @Transient
    @Override
    protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getValue(evaluator).equals(getParameters().get(1).getValue(evaluator));
    }

    @Override
    public String operationAsText() {
        return "=";
    }

}
