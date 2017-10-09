package be.groups.glanguage.core.error.formula.base.unable.evaluate;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.FormulaInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;

/**
 * @author michotte
 */
public class FormulaEvaluateInnerError extends FormulaInnerError {

    public FormulaEvaluateInnerError(AbstractFormula formula, Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_UNABLE_TO_EVALUATE, formula, evaluator, ErrorMethod.VALUE.getName(), null);
    }

}
