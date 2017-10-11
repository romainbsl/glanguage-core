package be.groups.glanguage.core.entities.formula.implementations;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.formula.base.unable.evaluate.FormulaEvaluateTypeInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Formula implementing the ability to construct a date from a string or from integers representing the day, the
 * month and the year
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_DATE)
public class FormulaDate extends AbstractNonTerminalFormula {

    public FormulaDate() {
        super();
    }

    public FormulaDate(FormulaDescription description,
                       List<AbstractFormula> parameters,
                       Evaluator evaluator) throws GLanguageException {
        super(description, parameters, evaluator);

        this.parameters = new ArrayList<>();
        this.parameters.addAll(parameters);
    }

    /**
     * Get the value as {@link LocalDate}<br>
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link LocalDate}
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @Override
    protected LocalDate doGetDateValue(Evaluator evaluator) throws GLanguageException {
        try {
            LocalDate date = null;
            if (getParameters().size() == 1) {
                try {
                    date = LocalDate.parse(getParameters().get(0).getStringValue(evaluator),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                } catch (DateTimeParseException dtpe) {
                    throw new IllegalArgumentException("Parameter must reprensent a date formatted as \"dd/MM/yyyy\" : "
                        + getParameters().get(0).asText());
                }
            } else if (getParameters().size() == 3) {
                try {
                    date = LocalDate.of(getParameters().get(2).getIntegerValue(evaluator),
                        getParameters().get(1).getIntegerValue(evaluator),
                        getParameters().get(0).getIntegerValue(evaluator));
                } catch (DateTimeParseException dtpe) {
                    throw new IllegalArgumentException(
                        "Parameters must reprensent a valid date : " + getParameters().get(0)
                            .asText() + "/" + getParameters().get(1).asText() + "/" + getParameters().get(2).asText());
                }
            }
            return date;
        } catch (GLanguageException gle) {
            gle.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.DATE, null));
            throw  gle;
        } catch (Exception e) {
            throw new GLanguageException(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.DATE, e
                .getMessage()));
        }
    }

    @Override
    public String asText() {
        StringBuilder sb = new StringBuilder();
        sb.append("date(");
        sb.append(getParameters().get(0).asText());
        Iterator<AbstractFormula> itParameters = getParameters().listIterator(1);
        while (itParameters.hasNext()) {
            sb.append("; ");
            sb.append(itParameters.next().asText());
        }
        sb.append(")");
        return sb.toString();
    }

}
