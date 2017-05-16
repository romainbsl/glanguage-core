package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.utils.rounding.Rounder;
import be.groups.glanguage.glanguage.api.entities.utils.rounding.RoundingType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.parameter.FormulaWrongParameterTypeInnerError;
import be.groups.glanguage.glanguage.api.error.formula.base.unable.evaluate.FormulaEvaluateTypeInnerError;
import be.groups.glanguage.glanguage.api.error.formula.base.unable.instantiate.FormulaUnableToInstantiateInnerError;
import be.groups.glanguage.glanguage.api.error.utils.EvaluationMethod;
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
        super(description,
              getParametersAsList(parameters, defaultPrecisionFormulaDescription));

        try {
            this.parameters = getParametersAsList(parameters, defaultPrecisionFormulaDescription);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaUnableToInstantiateInnerError(this, null));
            throw e;
        }
    }

    @Transient
    @JsonIgnore
    private static List<AbstractFormula> getParametersAsList(List<AbstractFormula> parameters,
                                                             FormulaDescription defaultPrecisionFormulaDescription)
            throws GLanguageException {
        List<AbstractFormula> list = new ArrayList<>();
        list.addAll(parameters);
        if (parameters.size() < 2) {
            list.add(getDefaultPrecision(defaultPrecisionFormulaDescription));
        }
        return list;
    }

    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        try {
            return getNumericValue().intValue();
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, EvaluationMethod.INTEGER, null));
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
                                                                                                       FormulaReturnType.NUMERIC)));
            }
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, EvaluationMethod.NUMERIC,
                    null));
            throw e;
        }
    }

    public abstract RoundingType getRoundingType();

    public static AbstractFormula getDefaultPrecision(FormulaDescription description) throws GLanguageException {
        try {
            switch (description.getType()) {
                case F_BANKERS_ROUNDED:
                    //fall through
                case F_TRUNC:
                    return new FormulaTerminalInteger(description, "2");
                case F_CEIL:
                    //fall through
                case F_FLOOR:
                    //fall through
                case F_ROUNDED:
                    return new FormulaTerminalInteger(description, "1");
                default:
                    //TODO
                    //Throw an error
                    return null;
            }
        } catch (GLanguageException e) {
            // TODO
            // e.getError().setOuterError(new FormulaEvaluateNumericInnerError(this, evaluator));
            throw e;
        }
    }

    @Override
    public String asText() {
        return operationAsText() + "(" + getParameters().get(0).asText() + "; " + getParameters().get(1).asText() + ")";
    }

    public abstract String operationAsText();

}
