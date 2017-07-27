package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

/**
 * Formula implementing a reference to a rule
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.C_RULE_REFERENCE)
public class FormulaRuleReference extends RuleCallFormula {

    protected FormulaRuleReference() {
        super();
    }

    public FormulaRuleReference(FormulaDescription description,
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
     * Get the value of a {@link RuleVersion ruleVersion} as {@link Integer}
     *
     * @param ruleVersion the rule of which the value is to be returned
     * @param evaluator   the evaluator to be used in the evaluation process, can be null
     * @return the the value of the {@link RuleVersion ruleVersion} as {@link Integer}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the value of the
     *                            {@link RuleVersion ruleVersion} is not castable to {@link Integer}
     */
    @Override
    Integer doGetIntegerValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        return ruleVersion.getIntegerValue(evaluator);
    }

    /**
     * Get the value of a {@link RuleVersion ruleVersion} as {@link Double}
     *
     * @param ruleVersion the rule of which the value is to be returned
     * @param evaluator   the evaluator to be used in the evaluation process, can be null
     * @return the the value of the {@link RuleVersion ruleVersion} as {@link Double}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the value of the
     *                            {@link RuleVersion ruleVersion} is not castable to {@link Double}
     */
    @Override
    Double doGetNumericValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        return ruleVersion.getNumericValue(evaluator);
    }

    /**
     * Get the value of a {@link RuleVersion ruleVersion} as {@link String}
     *
     * @param ruleVersion the rule of which the value is to be returned
     * @param evaluator   the evaluator to be used in the evaluation process, can be null
     * @return the the value of the {@link RuleVersion ruleVersion} as {@link String}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the value of the
     *                            {@link RuleVersion ruleVersion} is not castable to {@link String}
     */
    @Override
    String doGetStringValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        return ruleVersion.getStringValue(evaluator);
    }

    /**
     * Get the value of a {@link RuleVersion ruleVersion} as {@link Boolean}
     *
     * @param ruleVersion the rule of which the value is to be returned
     * @param evaluator   the evaluator to be used in the evaluation process, can be null
     * @return the the value of the {@link RuleVersion ruleVersion} as {@link Boolean}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the value of the
     *                            {@link RuleVersion ruleVersion} is not castable to {@link Boolean}
     */
    @Override
    Boolean doGetBooleanValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        return ruleVersion.getBooleanValue(evaluator);
    }

    /**
     * Get the value of a {@link RuleVersion ruleVersion} as {@link LocalDate}
     *
     * @param ruleVersion the rule of which the value is to be returned
     * @param evaluator   the evaluator to be used in the evaluation process, can be null
     * @return the the value of the {@link RuleVersion ruleVersion} as {@link LocalDate}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the value of the
     *                            {@link RuleVersion ruleVersion} is not castable to {@link LocalDate}
     */
    @Override
    LocalDate doGetDateValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        return ruleVersion.getDateValue(evaluator);
    }

    /**
     * Get the value of a {@link RuleVersion ruleVersion} as {@link Duration}
     *
     * @param ruleVersion the rule of which the value is to be returned
     * @param evaluator   the evaluator to be used in the evaluation process, can be null
     * @return the the value of the {@link RuleVersion ruleVersion} as {@link Duration}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the value of the
     *                            {@link RuleVersion ruleVersion} is not castable to {@link Duration}
     */
    @Override
    Duration doGetDurationValue(RuleVersion ruleVersion, Evaluator evaluator) throws GLanguageException {
        return ruleVersion.getDurationValue(evaluator);
    }

    @Override
    public String operationAsText() {
        return "";
    }

}
