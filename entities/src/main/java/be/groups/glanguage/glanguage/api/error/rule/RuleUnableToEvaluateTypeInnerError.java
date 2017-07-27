package be.groups.glanguage.glanguage.api.error.rule;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;

/**
 * @author michotte
 */
public abstract class RuleUnableToEvaluateTypeInnerError extends RuleInnerError {

    public RuleUnableToEvaluateTypeInnerError(RuleVersion ruleVersion, Evaluator evaluator, String methodName) {
        super(GLanguageErrorRegistry.RULE_VERSION_UNABLE_TO_EVALUATE_TYPE, ruleVersion, evaluator,
              methodName, null);
    }

}
