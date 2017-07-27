package be.groups.glanguage.glanguage.api.error.formula.base.cannot.invoke.targets;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;

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
