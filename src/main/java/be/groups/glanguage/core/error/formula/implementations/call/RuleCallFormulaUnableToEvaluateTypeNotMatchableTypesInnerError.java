package be.groups.glanguage.core.error.formula.implementations.call;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.rule.RuleVersion;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.FormulaInnerError;

/**
 * @author michotte
 */
public class RuleCallFormulaUnableToEvaluateTypeNotMatchableTypesInnerError extends FormulaInnerError {

    public RuleCallFormulaUnableToEvaluateTypeNotMatchableTypesInnerError(AbstractFormula formula,
                                                                          Evaluator evaluator,
                                                                          String methodName,
                                                                          RuleVersion referencedRule,
                                                                          FormulaReturnType actualReturnType,
                                                                          FormulaReturnType...
                                                                                            expectedReturnTypes) {
        super(GLanguageErrorRegistry.FORMULA_UNABLE_TO_EVALUATE_TYPE,
              formula,
              evaluator,
              methodName,
              getCause(referencedRule, actualReturnType, expectedReturnTypes));
    }

    private static String getCause(RuleVersion referencedRule,
                                   FormulaReturnType actualReturnType,
                                   FormulaReturnType... expectedReturnTypes) {
        StringBuilder sb = new StringBuilder("Referenced rule version[id: " + referencedRule.getId() + ", code: (" +
                referencedRule.getCode() + "] is of type " + actualReturnType);
        if (expectedReturnTypes != null && expectedReturnTypes.length > 0) {
            sb.append(" instead of " + expectedReturnTypes[0]);
            for (int i = 1 ; i < expectedReturnTypes.length ; i++) {
                sb.append(" or " + expectedReturnTypes[i]);
            }
        }
        return sb.toString();
    }
}
