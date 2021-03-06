package be.groups.glanguage.core.error.rule;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.rule.RuleVersion;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;

/**
 * @author michotte
 */
public class RuleUnableToCheckApplicabilityInnerError extends RuleInnerError {

    public RuleUnableToCheckApplicabilityInnerError(RuleVersion ruleVersion, Evaluator evaluator) {
        super(GLanguageErrorRegistry.RULE_VERSION_UNABLE_TO_CHECK_APPLICABILITY, ruleVersion, evaluator,
              "isApplicable", getCause());
    }

    private static String getCause() {
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to check applicability of rule version");
        return sb.toString();
    }

}
