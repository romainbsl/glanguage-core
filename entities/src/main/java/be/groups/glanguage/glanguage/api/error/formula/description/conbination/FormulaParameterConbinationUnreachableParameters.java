package be.groups.glanguage.glanguage.api.error.formula.description.conbination;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionInnerError;
import be.groups.glanguage.glanguage.api.error.utils.ErrorMethod;

/**
 * Created by michotte on 17/05/2017.
 */
public class FormulaParameterConbinationUnreachableParameters extends FormulaDescriptionInnerError {

    public FormulaParameterConbinationUnreachableParameters(AbstractFormula formula,
                                                            int numberOfUnreachableParameters,
                                                            Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_CONBINATION_UNREACHABLE_PARAMETERS,
              formula,
              evaluator,
              ErrorMethod.VALIDATE.getName(),
              getCause(numberOfUnreachableParameters));
    }

    private static String getCause(int numberOfUnreachableParameters) {
        return "The last " + numberOfUnreachableParameters + " parameters are unreachable";
    }
}
