package be.groups.glanguage.glanguage.api.error.formula.implementations.terminal;

import be.groups.errorframework.core.error.InnerError;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;

/**
 * Created by michotte on 21/12/2016.
 */
public class TerminalFormulaUnableToParseValueInnerError extends InnerError {

    private Exception exception;

    public TerminalFormulaUnableToParseValueInnerError(String value, FormulaReturnType returnType, String format,
                                                       Exception exception) {
        super(GLanguageErrorRegistry.FORMULA_TERMINAL_UNABLE_TO_PARSE_VALUE.getCode(),
                createMessage(value, returnType, format),
                null);
        this.exception = exception;
    }

    private static String createMessage(String value, FormulaReturnType returnType, String format) {
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to parse a " + returnType + " value : " + value + " (format: " + format + ")");
        return sb.toString();
    }
}
