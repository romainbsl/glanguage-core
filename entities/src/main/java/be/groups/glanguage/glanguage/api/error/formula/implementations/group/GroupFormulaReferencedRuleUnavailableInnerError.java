package be.groups.glanguage.glanguage.api.error.formula.implementations.group;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.group.GroupFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;

/**
 * @author michotte
 */
public class GroupFormulaReferencedRuleUnavailableInnerError extends FormulaInnerError {

    public GroupFormulaReferencedRuleUnavailableInnerError(GroupFormula formula,
                                                           Evaluator evaluator,
                                                           String methodName) {
        super(GLanguageErrorRegistry.FORMULA_RULE_REFERENCE_REFERENCED_RULE_UNAVAILABLE, formula,
              evaluator, methodName, getCause(formula, evaluator));
    }

    private static String getCause(GroupFormula formula, Evaluator evaluator) {
        if (evaluator == null) {
            return "Referenced rule[id: " + formula.getConstantValue() + "] not known -> branch or use an evaluator !";
        } else {
            return "Referenced rule[id: " + formula.getConstantValue() + "] not found";
        }
    }
}
