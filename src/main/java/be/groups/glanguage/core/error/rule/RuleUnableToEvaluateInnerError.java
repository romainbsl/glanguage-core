package be.groups.glanguage.core.error.rule;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.rule.RuleVersion;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;

/**
 * @author michotte
 */
public class RuleUnableToEvaluateInnerError extends RuleInnerError {

    public RuleUnableToEvaluateInnerError(RuleVersion ruleVersion, Evaluator evaluator) {
        super(GLanguageErrorRegistry.RULE_VERSION_UNABLE_TO_EVALUATE, ruleVersion, evaluator, "getValue", null);
    }

}
