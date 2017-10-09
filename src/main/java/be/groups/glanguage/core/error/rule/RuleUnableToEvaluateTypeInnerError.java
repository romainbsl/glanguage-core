package be.groups.glanguage.core.error.rule;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.rule.RuleVersion;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;

/**
 * @author michotte
 */
public abstract class RuleUnableToEvaluateTypeInnerError extends RuleInnerError {

    public RuleUnableToEvaluateTypeInnerError(RuleVersion ruleVersion, Evaluator evaluator, String methodName) {
        super(GLanguageErrorRegistry.RULE_VERSION_UNABLE_TO_EVALUATE_TYPE, ruleVersion, evaluator,
              methodName, null);
    }

}
