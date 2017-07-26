package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.parameter.FormulaNullParameterInnerError;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Formula implementing the brackets - to regroup several expressions in one
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_BRACKETS)
public class FormulaBracket extends AbstractFormula {

    protected FormulaBracket() {
        super();
    }

    public FormulaBracket(FormulaDescription description, AbstractFormula child) throws GLanguageException {
        super(description);
        if (child == null) {
            throw new GLanguageException(new FormulaNullParameterInnerError(this, null, "constructor", 1));
        }
        this.parameters = new ArrayList<>();
        this.parameters.add(child);
    }

    @Transient
    @Override
    public boolean isTerminal() {
        return false;
    }

    /**
     * Is this valid according to its {@link FormulaDescription description} ? (by delegating to
     * {@link FormulaDescription#isValid(List, Evaluator)} with {@link AbstractNonTerminalFormula#parameters}
     * as first parameter)
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return true if this is valid according to its {@link FormulaDescription description}, false otherwise
     * @see FormulaDescription#isValid(List, Evaluator)
     */
    @Override
    public boolean isValid(Evaluator evaluator) {
        return getDescription().isValid(getParameters(), evaluator);
    }

    /**
     * Get the return type with an evaluator (can be null) (by delegating to
     * {@link FormulaDescription#getReturnType(List, Evaluator)} with {@link AbstractNonTerminalFormula#parameters}
     * as first parameter)
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return the return type
     */
    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        return getDescription().getReturnType(getParameters(), evaluator);
    }

    /**
     * Get the value of the enclosed formula as {@link Integer}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value of the enclosed formula as {@link Integer}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the enclosed formula
     *                            doesn't implement the {@link AbstractFormula#getIntegerValue(Evaluator)}
     */
    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getIntegerValue(evaluator);
    }

    /**
     * Get the value of the enclosed formula as {@link Double}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value of the enclosed formula as {@link Double}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the enclosed formula
     *                            doesn't implement the {@link AbstractFormula#getNumericValue(Evaluator)}
     */
    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getNumericValue(evaluator);
    }

    /**
     * Get the value of the enclosed formula as {@link String}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value of the enclosed formula as {@link String}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the enclosed formula
     *                            doesn't implement the {@link AbstractFormula#getStringValue(Evaluator)}
     */
    @JsonIgnore
    @Transient
    @Override
    protected String doGetStringValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getStringValue(evaluator);
    }

    /**
     * Get the value of the enclosed formula as {@link Boolean}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value of the enclosed formula as {@link Boolean}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the enclosed formula
     *                            doesn't implement the {@link AbstractFormula#getBooleanValue(Evaluator)}
     */
    @JsonIgnore
    @Transient
    @Override
    protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getBooleanValue(evaluator);
    }

    /**
     * Get the value of the enclosed formula as {@link LocalDate}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value of the enclosed formula as {@link LocalDate}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the enclosed formula
     *                            doesn't implement the {@link AbstractFormula#getDateValue(Evaluator)}
     */
    @JsonIgnore
    @Transient
    @Override
    protected LocalDate doGetDateValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getDateValue(evaluator);
    }

    /**
     * Get the value of the enclosed formula as {@link Duration}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value of the enclosed formula as {@link Duration}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the enclosed formula
     *                            doesn't implement the {@link AbstractFormula#getDurationValue(Evaluator)}
     */
    @JsonIgnore
    @Transient
    @Override
    protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getDurationValue(evaluator);
    }

    @Transient
    @Override
    public String asText() {
        return "(" + getParameters().get(0).asText() + ")";
    }

}
