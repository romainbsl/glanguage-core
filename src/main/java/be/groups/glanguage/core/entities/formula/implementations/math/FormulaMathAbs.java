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
 * Formula implementing the absolute value operation on a number
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_ABS)
public class FormulaMathAbs extends MathFormula {

    public FormulaMathAbs() {
        super();
    }

    public FormulaMathAbs(FormulaDescription description,
                          List<AbstractFormula> parameters,
                          Evaluator evaluator) throws GLanguageException {
        super(description, parameters, evaluator);
    }

    /**
     * Get the absolute value of the parameter as {@link Double}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the absolute value of the parameter as {@link Double}
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        return Math.abs(getParameters().get(0).getNumericValue(evaluator));
    }

    @Override
    public String operationAsText() {
        return "abs";
    }

}
