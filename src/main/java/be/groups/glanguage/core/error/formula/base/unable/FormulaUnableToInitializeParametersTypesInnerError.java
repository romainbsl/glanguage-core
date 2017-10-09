package be.groups.glanguage.core.error.formula.base.unable;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.FormulaInnerError;

/**
 * @author michotte
 */
public class FormulaUnableToInitializeParametersTypesInnerError extends FormulaInnerError {

    public FormulaUnableToInitializeParametersTypesInnerError(AbstractFormula formula, Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_UNABLE_TO_EVALUATE, formula, evaluator, "initializeParametersType", null);
    }

}
