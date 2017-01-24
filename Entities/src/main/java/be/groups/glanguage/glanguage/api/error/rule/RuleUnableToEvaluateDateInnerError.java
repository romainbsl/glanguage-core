package be.groups.glanguage.glanguage.api.error.rule;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;

/**
 * Created by michotte on 20/12/2016.
 */
public class RuleUnableToEvaluateDateInnerError extends RuleUnableToEvaluateTypeInnerError {

    public RuleUnableToEvaluateDateInnerError(RuleVersion ruleVersion,
                                              Evaluator evaluator) {
        super(ruleVersion, evaluator, "getDateValue");
    }

}
