package be.groups.glanguage.core.error.rule;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.rule.RuleVersion;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;

/**
 * @author michotte
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