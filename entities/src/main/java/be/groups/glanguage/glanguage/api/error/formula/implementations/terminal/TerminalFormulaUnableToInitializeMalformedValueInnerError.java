package be.groups.glanguage.glanguage.api.error.formula.implementations.terminal;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;

/**
 * @author michotte
 */
public class TerminalFormulaUnableToInitializeMalformedValueInnerError extends FormulaInnerError {

    public TerminalFormulaUnableToInitializeMalformedValueInnerError(AbstractFormula formula,
                                                                     Evaluator evaluator,
                                                                     String methodName,
                                                                     String value,
                                                                     FormulaReturnType returnType,
                                                                     String format) {
        super(GLanguageErrorRegistry.FORMULA_TERMINAL_MALFORMED_VALUE,
              formula,
              evaluator,
              methodName,
              getCause(value, returnType, format));
    }

    private static String getCause(String value, FormulaReturnType returnType, String format) {
        StringBuilder sb = new StringBuilder();
        sb.append("Constant value must represent a " + returnType + " value : " + value + " (format: " + format + ")");
        return sb.toString();
    }
}
