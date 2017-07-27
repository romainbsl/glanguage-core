package be.groups.glanguage.glanguage.api.error.rule;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;

/**
 * @author michotte
 */
public class RuleUnableToEvaluateInnerError extends RuleInnerError {

    public RuleUnableToEvaluateInnerError(RuleVersion ruleVersion, Evaluator evaluator) {
        super(GLanguageErrorRegistry.RULE_VERSION_UNABLE_TO_EVALUATE, ruleVersion, evaluator, "getValue", null);
    }

}
