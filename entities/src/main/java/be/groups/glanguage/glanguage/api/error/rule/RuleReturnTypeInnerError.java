package be.groups.glanguage.glanguage.api.error.rule;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;

/**
 * Created by michotte on 23/12/2016.
 */
public class RuleReturnTypeInnerError extends RuleInnerError {

    public RuleReturnTypeInnerError(RuleVersion ruleVersion, Evaluator evaluator) {
        super(GLanguageErrorRegistry.RULE_UNABLE_TO_GET_RETURN_TYPE,
              ruleVersion,
              evaluator,
              "getReturnType",
              null);
    }

}