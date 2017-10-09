package be.groups.glanguage.core.error.formula.description.combination;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.description.FormulaDescriptionInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;

/**
 * @author michotte
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
