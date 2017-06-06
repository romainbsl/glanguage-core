package be.groups.glanguage.glanguage.api.error.formula.description.combination;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionInnerError;
import be.groups.glanguage.glanguage.api.error.utils.ErrorMethod;

/**
 * Created by michotte on 17/05/2017.
 */
public class FormulaParameterCombinationUnreachableParametersInnerError extends FormulaDescriptionInnerError {

    public FormulaParameterCombinationUnreachableParametersInnerError(AbstractFormula formula,
                                                                      int numberOfUnreachableParameters,
                                                                      Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_COMBINATION_UNREACHABLE_PARAMETERS,
              formula,
              evaluator,
              ErrorMethod.VALIDATE.getName(),
              getCause(numberOfUnreachableParameters));
    }

    private static String getCause(int numberOfUnreachableParameters) {
        return "The last " + numberOfUnreachableParameters + " parameters are unreachable";
    }
}
