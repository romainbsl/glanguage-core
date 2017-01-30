package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.utils.rounding.Rounder;
import be.groups.glanguage.glanguage.api.entities.utils.rounding.RoundingType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.parameter.FormulaWrongParameterTypeInnerError;
import be.groups.glanguage.glanguage.api.error.formula.base.unable.evaluate.FormulaUnableToEvaluateIntegerInnerError;
import be.groups.glanguage.glanguage.api.error.formula.base.unable.evaluate.FormulaUnableToEvaluateNumericInnerError;
import be.groups.glanguage.glanguage.api.error.formula.base.unable.instantiate.FormulaUnableToInstantiateInnerError;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class RoundingFormula extends AbstractNonTerminalFormula {

    protected RoundingFormula() {
        super();
    }

    public RoundingFormula(FormulaDescription description,
                           FormulaDescription defaultPrecisionFormulaDescription,
                           List<AbstractFormula> parameters) throws GLanguageException {
        super(description);

        try {
            this.parameters = new ArrayList<>();
            this.parameters.addAll(parameters);
            if (parameters.size() < 2) {
                getParameters().add(getDefaultPrecision(defaultPrecisionFormulaDescription));
            }
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaUnableToInstantiateInnerError(this, null));
            throw e;
        }
    }

    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        try {
            return getNumericValue().intValue();
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaUnableToEvaluateIntegerInnerError(this, evaluator));
            throw e;
        }
    }

    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        try {
            FormulaReturnType returnType = getParameters().get(0).getReturnType(evaluator);
            switch (returnType) {
                case INTEGER:
                    return Rounder.round(getParameters().get(0).getIntegerValue(evaluator),
                                         getRoundingType(),
                                         getParameters().get(1).getNumericValue(evaluator));
                case NUMERIC:
                    return Rounder.round(getParameters().get(0).getNumericValue(evaluator),
                                         getRoundingType(),
                                         getParameters().get(1).getNumericValue(evaluator));
                default:
                    throw new GLanguageException(new FormulaWrongParameterTypeInnerError(this,
                                                                                         evaluator,
                                                                                         "doGetNumericValue",
                                                                                         "",
                                                                                         1,
                                                                                         returnType,
                                                                                         Arrays.asList
                                                                                                 (FormulaReturnType
                                                                                                          .INTEGER,
                                                                                                  FormulaReturnType
                                                                                                          .NUMERIC)));
            }
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaUnableToEvaluateNumericInnerError(this, evaluator));
            throw e;
        }
    }

    public abstract RoundingType getRoundingType();

    public abstract AbstractFormula getDefaultPrecision(FormulaDescription description) throws GLanguageException;

    @Override
    public String asText() {
        return operationAsText() + "(" + getParameters().get(0).asText() + "; " + getParameters().get(1).asText() + ")";
    }

    public abstract String operationAsText();

}
