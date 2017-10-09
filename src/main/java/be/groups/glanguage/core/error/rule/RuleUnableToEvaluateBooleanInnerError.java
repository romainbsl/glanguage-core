package be.groups.glanguage.core.error.rule;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.rule.RuleVersion;

/**
 * @author michotte
 */
public class RuleUnableToEvaluateBooleanInnerError extends RuleUnableToEvaluateTypeInnerError {

    public RuleUnableToEvaluateBooleanInnerError(RuleVersion ruleVersion,
                                                 Evaluator evaluator) {
        super(ruleVersion, evaluator, "getBooleanValue");
    }

}
