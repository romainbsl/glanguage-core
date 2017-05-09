package be.groups.glanguage.glanguage.api.error.formula.base.cannot.invoke.evaluation.method;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;
import be.groups.glanguage.glanguage.api.error.utils.EvaluationMethodName;

/**
 * Created by michotte on 25/01/2017.
 */
public class FormulaCannotInvokeEvaluationMethodInnerErrorFactory {

    public static FormulaInnerError getType(AbstractFormula formula,
                                            Evaluator evaluator,
                                            EvaluationMethodName methodName) {
        return new FormulaInnerError(GLanguageErrorRegistry.FORMULA_CANNOT_INVOKE_EVALUATION_TYPED_METHOD,
                                     formula,
                                     evaluator,
                                     methodName.getMethodName(),
                                     null);
    }

    public static FormulaInnerError getBoolean(AbstractFormula formula, Evaluator evaluator) {
        return getType(formula, evaluator, EvaluationMethodName.BOOLEAN);
    }

    public static FormulaInnerError getDate(AbstractFormula formula, Evaluator evaluator) {
        return getType(formula, evaluator, EvaluationMethodName.DATE);
    }

    public static FormulaInnerError getEvaluationDuration(AbstractFormula formula, Evaluator evaluator) {
        return getType(formula, evaluator, EvaluationMethodName.DURATION);
    }

    public static FormulaInnerError getEvaluationInteger(AbstractFormula formula, Evaluator evaluator) {
        return getType(formula, evaluator, EvaluationMethodName.INTEGER);
    }

    public static FormulaInnerError getEvaluationNumeric(AbstractFormula formula, Evaluator evaluator) {
        return getType(formula, evaluator, EvaluationMethodName.NUMERIC);
    }

    public static FormulaInnerError getEvaluationString(AbstractFormula formula, Evaluator evaluator) {
        return getType(formula, evaluator, EvaluationMethodName.STRING);
    }

}
