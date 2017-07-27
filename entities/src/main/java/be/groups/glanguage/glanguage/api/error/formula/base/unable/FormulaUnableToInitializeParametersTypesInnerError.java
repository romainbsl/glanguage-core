package be.groups.glanguage.glanguage.api.error.formula.base.unable;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;

/**
 * @author michotte
 */
public class FormulaUnableToInitializeParametersTypesInnerError extends FormulaInnerError {

    public FormulaUnableToInitializeParametersTypesInnerError(AbstractFormula formula, Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_UNABLE_TO_EVALUATE, formula, evaluator, "initializeParametersType", null);
    }

}
