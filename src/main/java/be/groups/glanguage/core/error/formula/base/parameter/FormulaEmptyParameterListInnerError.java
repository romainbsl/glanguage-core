package be.groups.glanguage.core.error.formula.base.parameter;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.FormulaInnerError;

/**
 * @author michotte
 */
public class FormulaEmptyParameterListInnerError extends FormulaInnerError {

    public FormulaEmptyParameterListInnerError(AbstractFormula formula,
                                               Evaluator evaluator,
                                               String methodName) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_LIST_EMPTY, formula, evaluator, methodName, null);
    }

}
