package be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Iterator;
import java.util.List;

/**
 * Formula implementing a mathematical max operation on absolute values of a list of number
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_SMAX)
public class FormulaExtremumSignedMax extends ExtremumFormula {

    public FormulaExtremumSignedMax() {
        super();
    }

    public FormulaExtremumSignedMax(FormulaDescription description,
                                    List<AbstractFormula> parameters,
                                    Evaluator evaluator) throws GLanguageException {
        super(description, parameters, evaluator);
    }

    /**
     * Get the greatest absolute value of the parameters as {@link Double} signed with the sign of the first parameter
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the greatest absolute value of the parameters as {@link Double} signed with the sign of the first
     * parameter
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        Iterator<AbstractFormula> itParameters = getParameters().iterator();
        double temp;
        double sign = 0.0;
        double result = Double.MIN_VALUE;
        do {
            temp = itParameters.next().getNumericValue(evaluator);
            if (sign == 0.0) {
                if (Double.compare(temp, 0.0) >= 0) {
                    sign = 1.0;
                } else {
                    sign = -1.0;
                }
            }
            temp = Math.abs(temp);
            if (result < temp) {
                result = temp;
            }
        } while (itParameters.hasNext());
        return result * sign;
    }

    @Override
    public String operationAsText() {
        return "smax";
    }

}
