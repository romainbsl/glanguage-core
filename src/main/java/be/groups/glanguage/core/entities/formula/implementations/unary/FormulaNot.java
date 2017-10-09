/**
 *
 */
package be.groups.glanguage.core.entities.formula.implementations.unary;

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
 * Formula implementing the logical not operation
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_NOT)
public class FormulaNot extends UnaryFormula {

    protected FormulaNot() {
        super();
    }

    public FormulaNot(FormulaDescription description,
                      AbstractFormula child,
                      Evaluator evaluator) throws GLanguageException {
        super(description, child, evaluator);
    }

    /**
     * Get the value as {@link Boolean}<br>
     * The value depends on the {@link AbstractFormula#getBooleanValue(Evaluator)} of the parameter<br>
     * If the value of the parameter is true, return false. Else, return true.
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return false if the boolean value of the parameter is true, false otherwise
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    public Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
        return !getParameters().get(0).getBooleanValue(evaluator);
    }

    @Override
    public String operationAsText() {
        return "not";
    }

}
