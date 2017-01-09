package be.groups.glanguage.glanguage.api.error.formula.base.unable.instantiate;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;

/**
 * Created by michotte on 20/12/2016.
 */
public class FormulaUnableToInstantiateInnerError extends FormulaInnerError {

    public FormulaUnableToInstantiateInnerError(AbstractFormula formula) {
        this (formula, null);
    }

    public FormulaUnableToInstantiateInnerError(AbstractFormula formula, String cause) {
        super(GLanguageErrorRegistry.FORMULA_UNABLE_TO_INSTANTIATE, formula, null, "constructor", cause);
    }
}
