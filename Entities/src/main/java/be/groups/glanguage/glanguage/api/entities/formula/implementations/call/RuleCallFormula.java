package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.unable.FormulaReturnTypeInnerError;
import be.groups.glanguage.glanguage.api.error.formula.implementations.call
        .RuleCallFormulaReferencedRuleUnavailableInnerError;
import be.groups.glanguage.glanguage.api.error.formula.implementations.call
        .RuleCallFormulaUnableToEvaluateTypeNotMatchableTypesInnerError;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;

@Entity
public abstract class RuleCallFormula extends CallFormula {

    private RuleVersion referencedRule;

    public RuleCallFormula() {
        super();
    }

    public RuleCallFormula(FormulaDescription description, String ruleId) {
        super(description);

        if (ruleId == null || ruleId.isEmpty()) {
            throw new IllegalArgumentException("ruleId must be a non-null non-empty string");
        }
        setConstantValue(ruleId);
    }

    @JsonIgnore
    @Transient
    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) throws GLanguageException {
        try {
            getReferencedRule(evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaReturnTypeInnerError(this, evaluator));
            throw e;
        }
        return referencedRule.getReturnType(evaluator);
    }

    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        FormulaReturnType formulaReturnType = getReturnType(evaluator);
        if (!formulaReturnType.equals(FormulaReturnType.INTEGER) || formulaReturnType
                .equals(FormulaReturnType.NUMERIC)) {
            throw new GLanguageException(new RuleCallFormulaUnableToEvaluateTypeNotMatchableTypesInnerError(this,
                                                                                                            evaluator,
                                                                                                            "doGetIntegerValue",
                                                                                                            referencedRule,
                                                                                                            referencedRule
                                                                                                                    .getReturnType(
                                                                                                                            evaluator),
                                                                                                            FormulaReturnType.INTEGER,
                                                                                                            FormulaReturnType.NUMERIC));
        }
        return doGetIntegerValue(referencedRule, evaluator);
    }

    abstract Integer doGetIntegerValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException;

    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        FormulaReturnType formulaReturnType = getReturnType(evaluator);
        if (!formulaReturnType.equals(FormulaReturnType.INTEGER) || formulaReturnType
                .equals(FormulaReturnType.NUMERIC)) {
            throw new GLanguageException(new RuleCallFormulaUnableToEvaluateTypeNotMatchableTypesInnerError(this,
                                                                                                            evaluator,
                                                                                                            "doGetNumericValue",
                                                                                                            referencedRule,
                                                                                                            referencedRule
                                                                                                                    .getReturnType(
                                                                                                                            evaluator),
                                                                                                            FormulaReturnType.INTEGER,
                                                                                                            FormulaReturnType.NUMERIC));
        }
        return doGetNumericValue(referencedRule, evaluator);
    }

    abstract Double doGetNumericValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException;

    @JsonIgnore
    @Transient
    @Override
    protected String doGetStringValue(Evaluator evaluator) throws GLanguageException {
        if (!getReturnType(evaluator).equals(FormulaReturnType.STRING)) {
            throw new GLanguageException(new RuleCallFormulaUnableToEvaluateTypeNotMatchableTypesInnerError(this,
                                                                                                            evaluator,
                                                                                                            "doGetStringValue",
                                                                                                            referencedRule,
                                                                                                            referencedRule
                                                                                                                    .getReturnType(
                                                                                                                            evaluator),
                                                                                                            FormulaReturnType.STRING));
        }
        return doGetStringValue(referencedRule, evaluator);
    }

    abstract String doGetStringValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException;

    @JsonIgnore
    @Transient
    @Override
    protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
        if (!getReturnType(evaluator).equals(FormulaReturnType.BOOLEAN)) {
            throw new GLanguageException(new RuleCallFormulaUnableToEvaluateTypeNotMatchableTypesInnerError(this,
                                                                                                            evaluator,
                                                                                                            "doGetBooleanValue",
                                                                                                            referencedRule,
                                                                                                            referencedRule
                                                                                                                    .getReturnType(
                                                                                                                            evaluator),
                                                                                                            FormulaReturnType.BOOLEAN));
        }
        return doGetBooleanValue(referencedRule, evaluator);
    }

    abstract Boolean doGetBooleanValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException;

    @JsonIgnore
    @Transient
    @Override
    public LocalDate doGetDateValue(Evaluator evaluator) throws GLanguageException {
        if (!getReturnType(evaluator).equals(FormulaReturnType.DATE)) {
            throw new GLanguageException(new RuleCallFormulaUnableToEvaluateTypeNotMatchableTypesInnerError(this,
                                                                                                            evaluator,
                                                                                                            "doGetDateValue",
                                                                                                            referencedRule,
                                                                                                            referencedRule
                                                                                                                    .getReturnType(
                                                                                                                            evaluator),
                                                                                                            FormulaReturnType.DATE));
        }
        return doGetDateValue(referencedRule, evaluator);
    }

    abstract LocalDate doGetDateValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException;

    @JsonIgnore
    @Transient
    @Override
    public Duration doGetDurationValue(Evaluator evaluator) throws GLanguageException {
        if (!getReturnType(evaluator).equals(FormulaReturnType.DURATION)) {
            throw new GLanguageException(new RuleCallFormulaUnableToEvaluateTypeNotMatchableTypesInnerError(this,
                                                                                                            evaluator,
                                                                                                            "doGetDateValue",
                                                                                                            referencedRule,
                                                                                                            referencedRule
                                                                                                                    .getReturnType(
                                                                                                                            evaluator),
                                                                                                            FormulaReturnType.DURATION));
        }
        return doGetDurationValue(referencedRule, evaluator);
    }

    abstract Duration doGetDurationValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException;

    RuleVersion getReferencedRule(Evaluator evaluator) throws GLanguageException {
        doGetReferencedRule(evaluator);
        if (referencedRule == null) {
            throw new GLanguageException(new RuleCallFormulaReferencedRuleUnavailableInnerError(this,
                                                                                                evaluator,
                                                                                                "doGetBooleanValue"));
        }
        return referencedRule;
    }

    /**
     * @param evaluator evaluator
     * @return the referencedRule
     */
    @JsonIgnore
    @Transient
    RuleVersion doGetReferencedRule(Evaluator evaluator) {
        if (referencedRule == null && evaluator != null) {
            referencedRule = evaluator.getRuleVersion(getConstantValue());
        }
        return referencedRule;
    }

    @JsonIgnore
    @Transient
    public boolean isBranched() {
        return referencedRule != null;
    }

    /**
     * @param referencedRule the referencedRule to set
     */
    public void setReferencedRule(RuleVersion referencedRule) {
        this.referencedRule = referencedRule;
        setConstantValue(String.valueOf(referencedRule.getRuleDefinition().getRuleIdentity().getId()));
    }

    @Override
    public String asText() {
        return ruleAsText() + operationAsText();
    }

    private String ruleAsText() {
        RuleVersion referencedRule = doGetReferencedRule(null);
        if (referencedRule != null) {
            return String.valueOf(referencedRule.getCode());
        }
        return getConstantValue();
    }

    public abstract String operationAsText();

}
