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
