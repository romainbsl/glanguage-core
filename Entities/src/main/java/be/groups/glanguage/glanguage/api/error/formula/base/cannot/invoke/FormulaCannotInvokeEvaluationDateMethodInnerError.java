package be.groups.glanguage.glanguage.api.error.formula.base.cannot.invoke;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;

/**
 * Created by michotte on 20/12/2016.
 */
public class FormulaCannotInvokeEvaluationDateMethodInnerError extends FormulaCannotInvokeEvaluationTypedMethodInnerError {

    public FormulaCannotInvokeEvaluationDateMethodInnerError(AbstractFormula formula,
                                                             Evaluator evaluator) {
        super(formula, evaluator, "getDateValue");
    }

}
