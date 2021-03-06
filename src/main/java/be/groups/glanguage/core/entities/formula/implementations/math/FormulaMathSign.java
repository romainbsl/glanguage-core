package be.groups.glanguage.core.entities.formula.implementations.math;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

/**
 * Formula implementing the operation to determine the sign of a number
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_SIGN)
public class FormulaMathSign extends MathFormula {

    public FormulaMathSign() {
        super();
    }

    public FormulaMathSign(FormulaDescription description,
                           List<AbstractFormula> parameters,
                           Evaluator evaluator) throws GLanguageException {
        super(description, parameters, evaluator);
    }

    /**
     * Get the sign of the value of the parameter as {@link Double}<br>
     * The value will be negative, zero or positive if the value of the parameter is negative, zero or positive
     * respectively
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the sign of the value of the parameter as {@link Double}
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        return Double.valueOf(Double.compare(getParameters().get(0).getNumericValue(evaluator), 0.0));
    }

    @Override
    public String operationAsText() {
        return "sign";
    }

}
