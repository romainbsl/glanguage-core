package be.groups.glanguage.glanguage.api.entities.formula.implementations.string;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Formula implementing the operation to get the length of a string
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_STRING_LENGTH)
public class FormulaStringLength extends AbstractNonTerminalFormula {

    public FormulaStringLength() {
        super();
    }

    public FormulaStringLength(FormulaDescription description,
                               List<AbstractFormula> parameters,
                               Evaluator evaluator) throws GLanguageException {
        super(description, parameters, evaluator);

        if (parameters == null) {
            throw new IllegalArgumentException("parameters must be non-null");
        }

        this.parameters = new ArrayList<>();
        this.parameters.addAll(parameters);
    }

    /**
     * Get the length - the number of characters - of the parameter as {@link String}<br>
     * Calling this method is equivalent to calling {@link FormulaStringLength#doGetIntegerValue(Evaluator)} method and
     * applying {@link String#valueOf(int)} on the result
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the length - the number of characters - of the parameter as {@link String}
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected String doGetStringValue(Evaluator evaluator) throws GLanguageException {
        return String.valueOf(getIntegerValue(evaluator));
    }

    /**
     * Get the length - the number of characters - of the parameter as {@link Integer}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the length - the number of characters - of the parameter as {@link Integer}
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getStringValue(evaluator) != null ? getParameters().get(0).getStringValue(
                evaluator).length() : 0;
    }

    @Override
    public String asText() {
        return "stringLength(" + getParameters().get(0).asText() + ")";
    }

}
