package be.groups.glanguage.glanguage.api.error.formula.implementations.call;

import be.groups.errorframework.core.error.InnerError;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;

/**
 * Created by michotte on 20/12/2016.
 */
public class RuleCallFormulaUnableToEvaluateTypeNotMatchableTypesInnerError extends InnerError {

    public RuleCallFormulaUnableToEvaluateTypeNotMatchableTypesInnerError(AbstractFormula formula, Evaluator evaluator, RuleVersion
            referencedRule, FormulaReturnType actualReturnType, FormulaReturnType expectedReturnType, String
            methodName) {
        super(GLanguageErrorRegistry.FORMULA_UNABLE_TO_EVALUATE_TYPE.getCode(),
                createMessage(formula, evaluator, referencedRule, actualReturnType, expectedReturnType, methodName),
                null);
    }

    private static String createMessage(AbstractFormula formula, Evaluator evaluator, RuleVersion referencedRule,
                                        FormulaReturnType actualReturnType, FormulaReturnType expectedReturnType,
                                        String methodName) {
        StringBuilder sb = new StringBuilder();
        sb.append("Cannot invoke " + methodName + " method on " + formula.getClass().getName() + "[id: " + formula
                .getId() + "] if rule version[id: " + referencedRule.getId() + ", code: " + referencedRule
                .getCode() + "] is of type " + actualReturnType + " instead of " + expectedReturnType);
        sb.append(" (evaluator: ");
        if (evaluator == null) {
            sb.append("null)");
        } else {
            sb.append("not null)");
        }
        return sb.toString();
    }
}
