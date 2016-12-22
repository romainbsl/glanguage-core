package be.groups.glanguage.glanguage.api.error.formula.implementations.terminal;

import be.groups.errorframework.core.error.InnerError;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;

/**
 * Created by michotte on 21/12/2016.
 */
public class TerminalFormulaUnableToInitializeNullValueInnerError extends InnerError {

    public TerminalFormulaUnableToInitializeNullValueInnerError() {
        super(GLanguageErrorRegistry.FORMULA_TERMINAL_NULL_VALUE.getCode(), createMessage(), null);
    }

    private static String createMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Constant value is null");
        return sb.toString();
    }
}
