package be.groups.glanguage.glanguage.api.error.formula.description.combination;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.combination.FormulaParameterCombination;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionInnerError;
import be.groups.glanguage.glanguage.api.error.utils.ErrorMethod;

/**
 * @author michotte
 */
public class FormulaParameterCombinationUnableToValidateInnerError extends FormulaDescriptionInnerError {

    public FormulaParameterCombinationUnableToValidateInnerError(AbstractFormula formula,
                                                                 FormulaParameterCombination combination,
                                                                 Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_COMBINATION_UNABLE_TO_VALIDATE,
              formula,
              evaluator,
              ErrorMethod.VALIDATE.getName(),
              getCause(combination));
    }

    private static String getCause(FormulaParameterCombination combination) {
        return "Unable to validate parameter combination [id: + " + combination.getId() + ", name:" + combination
                .getName() + "]";
    }
}
