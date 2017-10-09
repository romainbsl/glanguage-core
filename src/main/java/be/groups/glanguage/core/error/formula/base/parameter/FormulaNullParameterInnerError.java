package be.groups.glanguage.core.error.formula.base.parameter;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.FormulaInnerError;

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
