package be.groups.glanguage.glanguage.api.error.formula.base.parameter;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;

/**
 * @author michotte
 */
public class FormulaNullParameterInnerError extends FormulaInnerError {

    public FormulaNullParameterInnerError(AbstractFormula formula,
                                          Evaluator evaluator,
                                          String methodName,
                                          int parameterIndex) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_NULL, formula, evaluator, methodName, getCause(parameterIndex));
    }

    private static String getCause(int parameterIndex) {
        return "Parameter at index " + parameterIndex + " is null";
    }
}
