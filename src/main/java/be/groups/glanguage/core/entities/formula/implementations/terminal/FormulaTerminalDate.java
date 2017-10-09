/**
 *
 */
package be.groups.glanguage.core.entities.formula.implementations.terminal;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.formula.implementations.terminal.TerminalFormulaUnableToInitializeNullValueInnerError;
import be.groups.glanguage.core.error.formula.implementations.terminal.TerminalFormulaUnableToParseValueInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Formula representing a constant date value
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.TERMINAL_DATE)
public class FormulaTerminalDate extends AbstractTerminalFormula {

    protected FormulaTerminalDate() {
        super();
    }

    public FormulaTerminalDate(FormulaDescription description, String constantValue) throws GLanguageException {
        super(description, constantValue);
    }

    public FormulaTerminalDate(FormulaDescription description, LocalDate constantValue) throws GLanguageException {
        this(description, constantValue.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    /**
     * Is this valid ?<br>
     * This is valid if :
     * <ol>
     * <li>the {@link FormulaTerminalDate#getConstantValue()} is not null</li>
     * <li>the {@link LocalDate#parse(CharSequence, DateTimeFormatter)} with a formatter of pattern "dd/MM/yyyy" does
     * not throw an exception</li>
     * </ol>
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return true if this is valid, false otherwise
     */
    @Override
    public boolean isValid(Evaluator evaluator) {
        if (getConstantValue() != null) {
            try {
                LocalDate.parse(getConstantValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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
     * <li>the {@link FormulaTerminalDate#getConstantValue()} is not null</li>
     * <li>the {@link LocalDate#parse(CharSequence, DateTimeFormatter)} with a formatter of pattern "dd/MM/yyyy" does
     * not throw an exception</li>
     * </ol>
     *
     * @param constantValue the value to be validated
     * @throws GLanguageException if the {@code constantValue} is null or if the
     * {@link LocalDate#parse(CharSequence, DateTimeFormatter)} with a formatter of pattern "dd/MM/yyyy" throws an
     * exception
     */
    @Override
    public void validate(String constantValue) throws GLanguageException {
        if (constantValue != null) {
            try {
                LocalDate.parse(constantValue, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException dtpe) {
                throw new GLanguageException(new TerminalFormulaUnableToParseValueInnerError(this,
                                                                                             null,
                                                                                             ErrorMethod.VALIDATE.getName(),
                                                                                             constantValue,
                                                                                             FormulaReturnType.DATE,
                                                                                             "dd/MM/yyyy",
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
     * The return type of this type of formula is always {@link FormulaReturnType#DATE}
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return always {@link FormulaReturnType#DATE}
     */
    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        return FormulaReturnType.DATE;
    }

    /**
     * Get the value as {@link LocalDate}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the date corresponding to the parameter formatted with the pattern "dd/MM/yyyy"
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected LocalDate doGetDateValue(Evaluator evaluator) throws GLanguageException {
        if (getConstantValue() != null) {
            try {
                return LocalDate.parse(getConstantValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException dtpe) {
                throw new GLanguageException(new TerminalFormulaUnableToParseValueInnerError(this,
                                                                                             evaluator,
                                                                                             "doGetDateValue",
                                                                                             getConstantValue(),
                                                                                             FormulaReturnType.DATE,
                                                                                             "dd/MM/yyyy",
                                                                                             dtpe));
            }
        } else {
            throw new GLanguageException(new TerminalFormulaUnableToInitializeNullValueInnerError(this,
                                                                                                  evaluator,
                                                                                                  "doGetDateValue"));
        }
    }

}
