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

    @Override
    protected Integer doGetIntegerValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        try {
            return ruleVersion.getFormula().getIntegerValue(evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.INTEGER, null));
            throw e;
        }
    }

    @Override
    protected Double doGetNumericValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        try {
            return ruleVersion.getFormula().getNumericValue(evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.NUMERIC, null));
            throw e;
        }
    }

    @Override
    protected String doGetStringValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        try {
            return ruleVersion.getFormula().getStringValue(evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.STRING, null));
            throw e;
        }
    }

    @Override
    protected Boolean doGetBooleanValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        try {
            return ruleVersion.getFormula().getBooleanValue(evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.BOOLEAN, null));
            throw e;
        }
    }

    @Override
    protected LocalDate doGetDateValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        try {
            return ruleVersion.getFormula().getDateValue(evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.DATE, null));
            throw e;
        }
    }

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
