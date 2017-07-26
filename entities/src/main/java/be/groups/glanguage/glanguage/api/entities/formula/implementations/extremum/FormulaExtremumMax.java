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
 * Formula implementing a mathematical max operation on a list of number
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_MAX)
public class FormulaExtremumMax extends ExtremumFormula {

    public FormulaExtremumMax() {
        super();
    }

    public FormulaExtremumMax(FormulaDescription description,
                              List<AbstractFormula> parameters,
                              Evaluator evaluator) throws GLanguageException {
        super(description, parameters, evaluator);
    }

    /**
     * Get the greatest value of the parameters as {@link Double}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the greatest value of the parameters as {@link Double}
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        Iterator<AbstractFormula> itParameters = getParameters().iterator();
        Double temp;
        Double result = Double.MIN_VALUE;
        do {
            temp = itParameters.next().getNumericValue(evaluator);

            if (result < temp) {
                result = temp;
            }
        } while (itParameters.hasNext());

        return result;
    }

    @Override
    public String operationAsText() {
        return "max";
    }

}
