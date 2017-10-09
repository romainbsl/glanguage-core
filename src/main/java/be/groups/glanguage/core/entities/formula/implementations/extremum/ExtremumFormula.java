package be.groups.glanguage.core.entities.formula.implementations.extremum;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract formula implementing a mathematical extremal operation on a list of number
 *
 * @author michotte
 */
@Entity
public abstract class ExtremumFormula extends AbstractNonTerminalFormula {

    public ExtremumFormula() {
        super();
    }

    public ExtremumFormula(FormulaDescription description,
                           List<AbstractFormula> parameters,
                           Evaluator evaluator) throws GLanguageException {
		super(description, parameters, evaluator);
    }

    /**
     * Get the return type<br>
     * The return type of this type of formula depends on the return type of the parameters :
     * <ul>
     *     <li>if they are all of type {@link FormulaReturnType#INTEGER} -> the return type is also
     *     {@link FormulaReturnType#INTEGER}</li>
     *     <li>if at least one is of type {@link FormulaReturnType#NUMERIC} -> the return type is also
     *     {@link FormulaReturnType#NUMERIC}</li>
     * </ul>
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return the return type according to the return type of the parameters
     */
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

    /**
     * Get the value as {@link Integer}<br>
     * Calling this method is equivalent to calling {@link ExtremumFormula#doGetNumericValue(Evaluator)} method and
     * applying {@link Double#intValue()} on the result
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link Integer} - the integer part of the value as {@link Double}
     * @throws GLanguageException  if an error occurs during the evaluation process
     */
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
