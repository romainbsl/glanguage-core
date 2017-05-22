package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.Duration;
import java.time.LocalDate;

@Entity
@DiscriminatorValue(FormulaType.Values.C_RULE_REFERENCE)
public class FormulaRuleReference extends RuleCallFormula {

    protected FormulaRuleReference() {
        super();
    }

    public FormulaRuleReference(FormulaDescription description, String ruleId) throws GLanguageException {
        super(description, ruleId);
    }

    @Override
    Integer doGetIntegerValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        return ruleVersion.getIntegerValue(evaluator);
    }

    @Override
    Double doGetNumericValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        return ruleVersion.getNumericValue(evaluator);
    }

    @Override
    String doGetStringValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        return ruleVersion.getStringValue(evaluator);
    }

    @Override
    Boolean doGetBooleanValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        return ruleVersion.getBooleanValue(evaluator);
    }

    @Override
    LocalDate doGetDateValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        return ruleVersion.getDateValue(evaluator);
    }

    @Override
    Duration doGetDurationValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        return ruleVersion.getDurationValue(evaluator);
    }

    @Override
    public String operationAsText() {
        return "";
    }

}