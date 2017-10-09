package be.groups.glanguage.core.error.formula.implementations.call;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.implementations.call.RuleCallFormula;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.FormulaInnerError;

/**
 * @author michotte
 */
public class RuleCallFormulaReferencedRuleUnavailableInnerError extends FormulaInnerError {

    public RuleCallFormulaReferencedRuleUnavailableInnerError(RuleCallFormula formula,
                                                              Evaluator evaluator,
                                                              String methodName) {
        super(GLanguageErrorRegistry.FORMULA_RULE_REFERENCE_REFERENCED_RULE_UNAVAILABLE, formula,
              evaluator, methodName, getCause(formula, evaluator));
    }

    private static String getCause(RuleCallFormula formula, Evaluator evaluator) {
        if (evaluator == null) {
            return "Referenced rule[id: " + formula.getConstantValue() + "] not known -> branch or use an evaluator !";
        } else {
            return "Referenced rule[id: " + formula.getConstantValue() + "] not found";
        }
    }
}
