package be.groups.glanguage.core.entities.formula.implementations.format;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Formula implementing the formatting of a date value
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_FORMAT_DATE)
public class FormulaFormatDate extends FormatFormula {

    public FormulaFormatDate() {
        super();
    }

    public FormulaFormatDate(FormulaDescription description,
                             List<AbstractFormula> parameters,
                             Evaluator evaluator) throws GLanguageException {
        super(description, parameters, evaluator);

        this.parameters = new ArrayList<>();
        this.parameters.addAll(parameters);
    }

    /**
     * Get the formatted value as {@link String} according to a pattern
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the formatted value as {@link String} according to a pattern
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected String doGetStringValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getDateValue(evaluator).format(DateTimeFormatter.ofPattern(getParameters().get(1)
                                                                                                         .getStringValue(
                                                                                                                 null)));
    }

    @Override
    public String operationAsText() {
        return "formatDate";
    }

}
