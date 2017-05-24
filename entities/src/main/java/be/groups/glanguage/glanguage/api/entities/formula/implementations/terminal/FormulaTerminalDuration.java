/**
 *
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.duration.DurationFormula;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal.TerminalFormulaUnableToInitializeNullValueInnerError;
import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal.TerminalFormulaUnableToParseValueInnerError;
import be.groups.glanguage.glanguage.api.error.utils.ErrorMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.Period;
import java.time.format.DateTimeParseException;

/**
 * Formula representing a constant integer value
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

    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        return FormulaReturnType.DURATION;
    }

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
