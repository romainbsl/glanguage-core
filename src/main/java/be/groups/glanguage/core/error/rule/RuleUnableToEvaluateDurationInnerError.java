package be.groups.glanguage.core.error.rule;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.rule.RuleVersion;

/**
 * @author michotte
 */
public class RuleUnableToEvaluateDurationInnerError extends RuleUnableToEvaluateTypeInnerError {

    public RuleUnableToEvaluateDurationInnerError(RuleVersion ruleVersion,
                                                  Evaluator evaluator) {
        super(ruleVersion, evaluator, "getDurationValue");
    }

}
