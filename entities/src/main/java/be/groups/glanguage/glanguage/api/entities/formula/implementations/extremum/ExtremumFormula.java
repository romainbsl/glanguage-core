package be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public abstract class ExtremumFormula extends AbstractNonTerminalFormula {

    public ExtremumFormula() {
        super();
    }

    public ExtremumFormula(FormulaDescription description, List<AbstractFormula> parameters) throws GLanguageException {
		super(description, parameters);
    }

    @JsonIgnore
    @Transient
    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        FormulaReturnType returnType = getParameters().get(0).getReturnType(evaluator);
        Iterator<AbstractFormula> itParameters = getParameters().iterator();
        while (!returnType.equals(FormulaReturnType.NUMERIC) && itParameters.hasNext()) {
            returnType = itParameters.next().getReturnType(evaluator);
        }
        return returnType;
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
