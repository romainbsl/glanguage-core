package be.groups.glanguage.glanguage.api.entities.formula;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal.TerminalFormulaUnableToInitializeNullValueInnerError;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.LinkedList;

/**
 * Common implementation of a terminal formula <br>
 * An AbstractTerminalFormula always has a not null {@link AbstractTerminalFormula#constantValue}<br>
 * An AbstractTerminalFormula never has {@link AbstractTerminalFormula#parameters}<br>
 * Evaluating an AbstractTerminalFormula consists in returning its {@link AbstractTerminalFormula#constantValue}
 *
 * @author michotte
 */
@Entity
public abstract class AbstractTerminalFormula extends AbstractFormula {

    protected AbstractTerminalFormula() {
        super();
    }

    public AbstractTerminalFormula(FormulaDescription description) {
        super(description);
    }

    public AbstractTerminalFormula(FormulaDescription description, String constantValue) throws GLanguageException {
        this(description);

        validate(constantValue);

        this.setConstantValue(constantValue);
    }

    /**
     * Is this valid ?
     * This is valid if {@link AbstractTerminalFormula#constantValue} corresponds to the
     * {@link AbstractTerminalFormula#getReturnType(Evaluator)}
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return true if this is valid, false otherwise
     */
    @JsonIgnore
    @Transient
    public abstract boolean isValid(Evaluator evaluator);

    /**
     * Validate this with a constant value<br>
     * The algorithm to validate is the same as the one in {@link AbstractTerminalFormula#isValid(Evaluator)} but
     * throwing {@link GLanguageException} instead of returning false if not valid
     *
     * @param constantValue
     * @throws GLanguageException if this is not valid
     */
    @JsonIgnore
    public abstract void validate(String constantValue) throws GLanguageException;

    /**
     * Terminal formulas never have parameters
     *
     * @return null
     */
    @Transient
    @Override
    public LinkedList<AbstractFormula> getParameters() {
        return null;
    }

    /**
     * Get the value as {@link String} with an {@code evaluator} (can be null)
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link String}
     * @throws GLanguageException if {@link AbstractTerminalFormula#constantValue} is null (should never happen)
     */
    @JsonIgnore
    @Transient
    @Override
    protected String doGetStringValue(Evaluator evaluator) throws GLanguageException {
        if (getConstantValue() != null) {
            return getConstantValue();
        } else {
            throw new GLanguageException(new TerminalFormulaUnableToInitializeNullValueInnerError(this,
                                                                                                  evaluator,
                                                                                                  "doGetStringValue"));
        }
    }

    /**
     * Is this terminal ?<br>
     * AbstractTerminalFormula is always terminal
     *
     * @return true
     */
    @JsonIgnore
    @Transient
    @Override
    public boolean isTerminal() {
        return true;
    }

    @Override
    public String asText() {
        return getConstantValue();
    }

}
