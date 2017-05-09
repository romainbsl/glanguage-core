package be.groups.glanguage.glanguage.api.error.formula.implementations.terminal;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;

/**
 * Created by michotte on 21/12/2016.
 */
public class TerminalFormulaUnableToInitializeNullValueInnerError extends FormulaInnerError {

    public TerminalFormulaUnableToInitializeNullValueInnerError(AbstractTerminalFormula formula,
                                                                Evaluator evaluator,
                                                                String methodName) {
        super(GLanguageErrorRegistry.FORMULA_TERMINAL_NULL_VALUE, formula, evaluator, methodName, getCause());
    }

    private static String getCause() {
        StringBuilder sb = new StringBuilder();
        sb.append("Constant value is null");
        return sb.toString();
    }
}
