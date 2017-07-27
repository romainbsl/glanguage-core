package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.unable.evaluate.FormulaEvaluateTypeInnerError;
import be.groups.glanguage.glanguage.api.error.utils.ErrorMethod;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

/**
 * Formula implementing a call to the {@link RuleVersion#getFormula()} of a rule
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.C_FORMULA)
public class FormulaFormula extends RuleCallFormula {

    protected FormulaFormula() {
        super();
    }

    public FormulaFormula(FormulaDescription description,
                          String ruleId,
                          Evaluator evaluator) throws GLanguageException {
        super(description, ruleId, evaluator);
    }

    @Override
    public boolean isValid(Evaluator evaluator) {
        return true;
    }

    @Override
    public void validate(List<AbstractFormula> parameters, Evaluator evaluator) throws GLanguageException {
        // do nothing
    }

    /**
     * Get the value of the formula of a {@link RuleVersion ruleVersion} as {@link Integer}
     *
     * @param ruleVersion the rule of which the value of the formula is to be returned
     * @param evaluator   the evaluator to be used in the evaluation process, can be null
     * @return the value of the formula of the {@link RuleVersion ruleVersion}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the formula of the
     *                            {@link RuleVersion ruleVersion} doesn't implement the
     *                            {@link AbstractFormula#getIntegerValue(Evaluator)}
     */
    @Override
    protected Integer doGetIntegerValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        try {
            return ruleVersion.getFormula().getIntegerValue(evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.INTEGER, null));
            throw e;
        }
    }

    /**
     * Get the value of the formula of a {@link RuleVersion ruleVersion} as {@link Double}
     *
     * @param ruleVersion the rule of which the value of the formula is to be returned
     * @param evaluator   the evaluator to be used in the evaluation process, can be null
     * @return the value of the formula of the {@link RuleVersion ruleVersion}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the formula of the
     *                            {@link RuleVersion ruleVersion} doesn't implement the
     *                            {@link AbstractFormula#getNumericValue(Evaluator)}
     */
    @Override
    protected Double doGetNumericValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        try {
            return ruleVersion.getFormula().getNumericValue(evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.NUMERIC, null));
            throw e;
        }
    }

    /**
     * Get the value of the formula of a {@link RuleVersion ruleVersion} as {@link String}
     *
     * @param ruleVersion the rule of which the value of the formula is to be returned
     * @param evaluator   the evaluator to be used in the evaluation process, can be null
     * @return the value of the formula of the {@link RuleVersion ruleVersion}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the formula of the
     *                            {@link RuleVersion ruleVersion} doesn't implement the
     *                            {@link AbstractFormula#getStringValue(Evaluator)}
     */
    @Override
    protected String doGetStringValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        try {
            return ruleVersion.getFormula().getStringValue(evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.STRING, null));
            throw e;
        }
    }

    /**
     * Get the value of the formula of a {@link RuleVersion ruleVersion} as {@link Boolean}
     *
     * @param ruleVersion the rule of which the value of the formula is to be returned
     * @param evaluator   the evaluator to be used in the evaluation process, can be null
     * @return the value of the formula of the {@link RuleVersion ruleVersion}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the formula of the
     *                            {@link RuleVersion ruleVersion} doesn't implement the
     *                            {@link AbstractFormula#getBooleanValue(Evaluator)}
     */
    @Override
    protected Boolean doGetBooleanValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        try {
            return ruleVersion.getFormula().getBooleanValue(evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.BOOLEAN, null));
            throw e;
        }
    }

    /**
     * Get the value of the formula of a {@link RuleVersion ruleVersion} as {@link LocalDate}
     *
     * @param ruleVersion the rule of which the value of the formula is to be returned
     * @param evaluator   the evaluator to be used in the evaluation process, can be null
     * @return the value of the formula of the {@link RuleVersion ruleVersion}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the formula of the
     *                            {@link RuleVersion ruleVersion} doesn't implement the
     *                            {@link AbstractFormula#getDateValue(Evaluator)}
     */
    @Override
    protected LocalDate doGetDateValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        try {
            return ruleVersion.getFormula().getDateValue(evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.DATE, null));
            throw e;
        }
    }

    /**
     * Get the value of the formula of a {@link RuleVersion ruleVersion} as {@link Duration}
     *
     * @param ruleVersion the rule of which the value of the formula is to be returned
     * @param evaluator   the evaluator to be used in the evaluation process, can be null
     * @return the value of the formula of the {@link RuleVersion ruleVersion}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the formula of the
     *                            {@link RuleVersion ruleVersion} doesn't implement the
     *                            {@link AbstractFormula#getDurationValue(Evaluator)}
     */
    @Override
    protected Duration doGetDurationValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        try {
            return ruleVersion.getFormula().getDurationValue(evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.DURATION, null));
            throw e;
        }
    }

    @Override
    public String operationAsText() {
        return ".formula";
    }

}
