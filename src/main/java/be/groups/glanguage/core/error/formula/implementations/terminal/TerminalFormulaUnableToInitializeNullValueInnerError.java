package be.groups.glanguage.core.error.formula.implementations.terminal;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.FormulaInnerError;

/**
 * @author michotte
 */
public class TerminalFormulaUnableToInitializeNullValueInnerError extends FormulaInnerError {

    public TerminalFormulaUnableToInitializeNullValueInnerError(AbstractTerminalFormula formula,
                                                                Evaluator evaluator,
                                                                String methodName) {
        super(GLanguageErrorRegistry.FORMULA_TERMINAL_NULL_VALUE, formula, evaluator, methodName, "Constant value is null");
    }

}
