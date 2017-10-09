package be.groups.glanguage.core.error.formula.base.cannot.invoke.evaluation.method;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.FormulaInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;

/**
 * @author michotte
 */
public class FormulaCannotInvokeEvaluationMethodInnerErrorFactory {

    public static FormulaInnerError getType(AbstractFormula formula,
                                            Evaluator evaluator,
                                            ErrorMethod method) {
        return new FormulaInnerError(GLanguageErrorRegistry.FORMULA_CANNOT_INVOKE_EVALUATION_TYPED_METHOD,
                                     formula,
                                     evaluator,
                                     method.getName(),
                                     null);
    }

    public static FormulaInnerError getBoolean(AbstractFormula formula, Evaluator evaluator) {
        return getType(formula, evaluator, ErrorMethod.BOOLEAN);
    }

    public static FormulaInnerError getDate(AbstractFormula formula, Evaluator evaluator) {
        return getType(formula, evaluator, ErrorMethod.DATE);
    }

    public static FormulaInnerError getDuration(AbstractFormula formula, Evaluator evaluator) {
        return getType(formula, evaluator, ErrorMethod.DURATION);
    }

    public static FormulaInnerError getInteger(AbstractFormula formula, Evaluator evaluator) {
        return getType(formula, evaluator, ErrorMethod.INTEGER);
    }

    public static FormulaInnerError getNumeric(AbstractFormula formula, Evaluator evaluator) {
        return getType(formula, evaluator, ErrorMethod.NUMERIC);
    }

    public static FormulaInnerError getString(AbstractFormula formula, Evaluator evaluator) {
        return getType(formula, evaluator, ErrorMethod.STRING);
    }

}
