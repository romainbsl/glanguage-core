package be.groups.glanguage.core.error.formula.implementations.terminal;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.FormulaInnerError;

/**
 * @author michotte
 */
public class TerminalFormulaUnableToParseValueInnerError extends FormulaInnerError {

    private Exception exception;

    public TerminalFormulaUnableToParseValueInnerError(AbstractFormula formula,
                                                       Evaluator evaluator,
                                                       String methodName,
                                                       String value,
                                                       FormulaReturnType returnType,
                                                       String format,
                                                       Exception exception) {
        super(GLanguageErrorRegistry.FORMULA_TERMINAL_UNABLE_TO_PARSE_VALUE,
              formula,
              evaluator,
              methodName,
              getCause(value, returnType, format));
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    private static String getCause(String value, FormulaReturnType returnType, String format) {
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to parse a " + returnType + " value : " + value + " (format: " + format + ")");
        return sb.toString();
    }
}
