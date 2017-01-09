package be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.parameter.FormulaNullParameterListInnerError;
import be.groups.glanguage.glanguage.api.error.formula.base.parameter.FormulaWrongParameterTypeInnerError;
import be.groups.glanguage.glanguage.api.error.formula.base.unable.FormulaReturnTypeInnerError;
import be.groups.glanguage.glanguage.api.error.formula.base.unable.instantiate.FormulaUnableToInstantiateInnerError;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public abstract class ExtremumFormula extends AbstractNonTerminalFormula {

    public ExtremumFormula() {
        super();
    }

    public ExtremumFormula(FormulaDescription description, List<AbstractFormula> parameters) throws GLanguageException {
        super(description);
        try {
            if (parameters == null) {
                throw new GLanguageException(new FormulaNullParameterListInnerError(this, null, "constructor"));
            }
            this.parameters = new ArrayList<>();
            parameters.stream().forEachOrdered(p -> {
                try {
                    if (p.getReturnType(null).equals(FormulaReturnType.INTEGER) || p.getReturnType(null).equals(
                            FormulaReturnType.NUMERIC)) {
                        this.parameters.add(p);
                    } else {
                        throw new GLanguageException(new FormulaWrongParameterTypeInnerError(this,
                                                                                               null,
                                                                                               "constructor",
                                                                                               parameters
                                                                                                       .indexOf(p) + 1,
                                                                                               p.getReturnType(null),
                                                                                               FormulaReturnType
                                                                                                       .INTEGER,
                                                                                               FormulaReturnType
                                                                                                       .NUMERIC));
                    }
                } catch (GLanguageException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            if (e.getCause() instanceof GLanguageException) {
                GLanguageException gLanguageException = (GLanguageException) e.getCause();
                gLanguageException.getError().setOuterError(new FormulaUnableToInstantiateInnerError(this));
                throw gLanguageException;
            }
            throw e;
        }
    }

    @JsonIgnore
    @Transient
    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) throws GLanguageException {
        try {
            FormulaReturnType returnType = getParameters().get(0).getReturnType(evaluator);
            Iterator<AbstractFormula> itParameters = getParameters().iterator();
            while (!returnType.equals(FormulaReturnType.NUMERIC) && itParameters.hasNext()) {
                returnType = itParameters.next().getReturnType(evaluator);
            }
            return returnType;
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaReturnTypeInnerError(this, evaluator));
            throw e;
        }
    }

    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        return getNumericValue(evaluator).intValue();
    }

    @Override
    public String asText() {
        String parametersString = getParameters().stream().map(p -> p.asText()).collect(Collectors.toList()).toString();
        parametersString = parametersString.substring(1, parametersString.length() - 1);

        return operationAsText() + "(" + parametersString + ")";
    }

    public abstract String operationAsText();
}
