package be.groups.glanguage.glanguage.api.error.formula.base.unable;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;

/**
 * @author michotte
 */
public class FormulaReturnTypeInnerError extends FormulaInnerError {

    public FormulaReturnTypeInnerError(AbstractFormula formula, Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_UNABLE_TO_GET_RETURN_TYPE, formula, evaluator, "getReturnType", null);
    }

    public String getMainMessage() {
        return "Unable to get return type of formula";
    }

}