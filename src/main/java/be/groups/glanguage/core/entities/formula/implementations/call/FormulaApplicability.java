package be.groups.glanguage.core.entities.formula.implementations.call;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.rule.RuleVersion;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.formula.base.cannot.invoke.evaluation.method.FormulaCannotInvokeEvaluationMethodInnerErrorFactory;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

/**
 * Formula implementing a call to the {@link RuleVersion#isApplicable(Evaluator)} of a rule
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(value = FormulaType.Values.C_APPLICABILITY)
public class FormulaApplicability extends RuleCallFormula {

    public FormulaApplicability() {
        super();
    }

    public FormulaApplicability(FormulaDescription description,
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
     * Get the return type<br>
     * The return type of this type of formula is always {@link FormulaReturnType#BOOLEAN}
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return {@link FormulaReturnType#BOOLEAN}
     */
    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        return FormulaReturnType.BOOLEAN;
    }

    @Override
    public String operationAsText() {
        return ".applicable";
    }

    /**
     * This method is not available for this type of formula<br>
     * Always throws a {@link GLanguageException}
     *
     * @param ruleVersion the rule of which the applicability is to be returned
     * @param evaluator   the evaluator to be used during the validation process, can be null
     * @return nothing - method unavailable for this type of formula
     * @throws GLanguageException always - method unavailable for this type of formula
     */
    @Override
    protected Integer doGetIntegerValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(FormulaCannotInvokeEvaluationMethodInnerErrorFactory.getInteger(this, evaluator));
    }

    /**
     * This method is not available for this type of formula<br>
     * Always throws a {@link GLanguageException}
     *
     * @param ruleVersion the rule of which the applicability is to be returned
     * @param evaluator   the evaluator to be used during the validation process, can be null
     * @return nothing - method unavailable for this type of formula
     * @throws GLanguageException always - method unavailable for this type of formula
     */
    @Override
    protected Double doGetNumericValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(FormulaCannotInvokeEvaluationMethodInnerErrorFactory.getNumeric(this, evaluator));
    }

    /**
     * This method is not available for this type of formula<br>
     * Always throws a {@link GLanguageException}
     *
     * @param ruleVersion the rule of which the applicability is to be returned
     * @param evaluator   the evaluator to be used during the validation process, can be null
     * @return nothing - method unavailable for this type of formula
     * @throws GLanguageException always - method unavailable for this type of formula
     */
    @Override
    protected String doGetStringValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(FormulaCannotInvokeEvaluationMethodInnerErrorFactory.getString(this, evaluator));
    }

    /**
     * Get the applicability of a {@link RuleVersion ruleVersion}
     *
     * @param ruleVersion the rule of which the applicability is to be returned
     * @param evaluator   the evaluator to be used in the evaluation process, can be null
     * @return the the applicability of the {@link RuleVersion ruleVersion}
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @Override
    protected Boolean doGetBooleanValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        return ruleVersion.getApplicabilityCondition().getBooleanValue(evaluator);
    }

    /**
     * This method is not available for this type of formula<br>
     * Always throws a {@link GLanguageException}
     *
     * @param ruleVersion the rule of which the applicability is to be returned
     * @param evaluator   the evaluator to be used during the validation process, can be null
     * @return nothing - method unavailable for this type of formula
     * @throws GLanguageException always - method unavailable for this type of formula
     */
    @Override
    protected LocalDate doGetDateValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(FormulaCannotInvokeEvaluationMethodInnerErrorFactory.getDate(this, evaluator));
    }

    /**
     * This method is not available for this type of formula<br>
     * Always throws a {@link GLanguageException}
     *
     * @param ruleVersion the rule of which the applicability is to be returned
     * @param evaluator   the evaluator to be used during the validation process, can be null
     * @return nothing - method unavailable for this type of formula
     * @throws GLanguageException always - method unavailable for this type of formula
     */
    @Override
    protected Duration doGetDurationValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(FormulaCannotInvokeEvaluationMethodInnerErrorFactory.getDuration(this, evaluator));
    }

}
