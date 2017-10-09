/**
 *
 */
package be.groups.glanguage.core.entities.formula.implementations.terminal;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.formula.implementations.duration.DurationFormula;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.formula.implementations.terminal.TerminalFormulaUnableToInitializeNullValueInnerError;
import be.groups.glanguage.core.error.formula.implementations.terminal.TerminalFormulaUnableToParseValueInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.Period;
import java.time.format.DateTimeParseException;

/**
 * Formula representing a constant duration value
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.TERMINAL_DURATION)
public class FormulaTerminalDuration extends AbstractTerminalFormula {

    public FormulaTerminalDuration() {
        super();
    }

    public FormulaTerminalDuration(FormulaDescription description, String constantValue) throws GLanguageException {
        super(description, constantValue);
    }

    /**
     * Is this valid ?<br>
     * This is valid if :
     * <ol>
     * <li>the {@link FormulaTerminalDuration#getConstantValue()} is not null</li>
     * <li>no exception is thrown during the parsing of the value</li>
     * </ol>
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return true if this is valid, false otherwise
     */
    @Override
    public boolean isValid(Evaluator evaluator) {
        if (getConstantValue() != null) {
            try {
                parseValue(getConstantValue());
            } catch (DateTimeParseException dtpe) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Validate this with a {@code constantValue}<br>
     * This is valid if :
     * <ol>
     * <li>the {@link FormulaTerminalDuration#getConstantValue()} is not null</li>
     * <li>no exception is thrown during the parsing of the parameter</li>
     * </ol>
     *
     * @param constantValue the value to be validated
     * @throws GLanguageException if the {@code constantValue} is null or if an exception is thrown during the
     * parsing of the parameter
     */
    @Override
    public void validate(String constantValue) throws GLanguageException {
        if (constantValue != null) {
            try {
                parseValue(constantValue);
            } catch (DateTimeParseException dtpe) {
                throw new GLanguageException(new TerminalFormulaUnableToParseValueInnerError(this,
                                                                                             null,
                                                                                             ErrorMethod.VALIDATE.getName(),
                                                                                             constantValue,
                                                                                             FormulaReturnType.BOOLEAN,
                                                                                             "P[nY][nM][nD][T[nH][nM][n][.nS]]",
                                                                                             dtpe));
            }
        } else {
            throw new GLanguageException(new TerminalFormulaUnableToInitializeNullValueInnerError(this,
                                                                                                  null,
                                                                                                  ErrorMethod.VALIDATE.getName()));
        }
    }

    /**
     * Get the return type<br>
     * The return type of this type of formula is always {@link FormulaReturnType#DURATION}
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return always {@link FormulaReturnType#DURATION}
     */
    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        return FormulaReturnType.DURATION;
    }

    /**
     * Get the value as {@link Duration}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the duration corresponding to the parameter formatted with the pattern "P[nY][nM][nD][T[nH][nM][n][.nS]]"
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageException {
        if (getConstantValue() != null) {
            try {
                return parseValue(getConstantValue());
            } catch (DateTimeParseException dtpe) {
                throw new GLanguageException(new TerminalFormulaUnableToParseValueInnerError(this,
                                                                                    evaluator,
                                                                                    "doGetDurationValue",
                                                                                    getConstantValue(),
                                                                                    FormulaReturnType.BOOLEAN,
                                                                                    "P[nY][nM][nD][T[nH][nM][n][.nS]]",
                                                                                    dtpe));
            }
        } else {
            throw new GLanguageException(new TerminalFormulaUnableToInitializeNullValueInnerError(this,
                                                                                         evaluator,
                                                                                         "doGetDurationValue"));
        }
    }

    private Duration parseValue(String value) throws DateTimeParseException {
        String text = new String(value);
        text = text.substring(1, text.length() - 1);
        if (text.contains("T")) {
            if (text.contains("Y") || (text.contains("M") && text.indexOf("M") < text.indexOf("T"))) {
                String[] split = text.split("T");
                assert (split.length == 2);
                Period period = Period.parse(split[0]);
                int days = (period.getYears() * DurationFormula.YEAR_AVG_DAYS_COUNT) + (period
                        .getMonths() * DurationFormula.MONTH_AVG_DAYS_COUNT) + period.getDays();
                String tmp = "P" + days + "DT" + split[1];
                return Duration.parse(tmp);
            } else {
                return Duration.parse(text);
            }
        } else {
            Period period = Period.parse(text);
            int days = (period.getYears() * DurationFormula.YEAR_AVG_DAYS_COUNT) + (period
                    .getMonths() * DurationFormula.MONTH_AVG_DAYS_COUNT) + period.getDays();
            return Duration.ofDays(days);
        }
    }

}
