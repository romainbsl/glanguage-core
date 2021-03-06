package be.groups.glanguage.core.error.formula.base.unable.instantiate;

import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.FormulaInnerError;

/**
 * @author michotte
 */
public class FormulaUnableToInstantiateInnerError extends FormulaInnerError {

    public FormulaUnableToInstantiateInnerError(AbstractFormula formula) {
        this (formula, null);
    }

    public FormulaUnableToInstantiateInnerError(AbstractFormula formula, String cause) {
        super(GLanguageErrorRegistry.FORMULA_UNABLE_TO_INSTANTIATE, formula, null, "constructor", cause);
    }
}
