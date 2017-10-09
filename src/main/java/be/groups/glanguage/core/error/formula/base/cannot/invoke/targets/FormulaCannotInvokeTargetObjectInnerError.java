package be.groups.glanguage.core.error.formula.base.cannot.invoke.targets;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.FormulaInnerError;

/**
 * @author michotte
 */
public class FormulaCannotInvokeTargetObjectInnerError extends FormulaInnerError {

    public FormulaCannotInvokeTargetObjectInnerError(AbstractFormula formula,
                                                     Evaluator evaluator,
                                                     String cause) {
        super(GLanguageErrorRegistry.FORMULA_CANNOT_INVOKE_TARGET_OBJECT_METHOD, formula, evaluator,
              "getTargetedObject", cause);
    }

}
