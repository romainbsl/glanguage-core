package be.groups.glanguage.glanguage.api.error.formula.implementations.terminal;

import be.groups.errorframework.core.error.InnerError;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;

/**
 * Created by michotte on 21/12/2016.
 */
public class TerminalFormulaUnableToInitializeMalformedValueInnerError extends InnerError {

    public TerminalFormulaUnableToInitializeMalformedValueInnerError(String value, FormulaReturnType returnType,
                                                                     String format) {
        super(GLanguageErrorRegistry.FORMULA_TERMINAL_MALFORMED_VALUE.getCode(),
                createMessage(value, returnType, format),
                null);
    }

    private static String createMessage(String value, FormulaReturnType returnType, String format) {
        StringBuilder sb = new StringBuilder();
        sb.append("Constant value must represent a " + returnType + " value : " + value + " (format: " + format + ")");
        return sb.toString();
    }
}
