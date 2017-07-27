package be.groups.glanguage.glanguage.api.error.formula.description.combination;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.combination.FormulaParameterCombinationItem;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionInnerError;
import be.groups.glanguage.glanguage.api.error.utils.ErrorMethod;

/**
 * @author michotte
 */
public class FormulaParameterCombinationItemUnableToValidateInnerError extends FormulaDescriptionInnerError {

    public FormulaParameterCombinationItemUnableToValidateInnerError(AbstractFormula formula,
                                                                     FormulaUsage usage,
                                                                     FormulaParameterCombinationItem
                                                                             combinationParameter,
                                                                     String parameterAsText,
                                                                     Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_COMBINATION_ITEM_UNABLE_TO_VALIDATE,
              formula,
              evaluator,
              ErrorMethod.VALIDATE.getName(),
              getCause(usage, combinationParameter, parameterAsText));
    }

    private static String getCause(FormulaUsage usage,
                                   FormulaParameterCombinationItem combinationParameter,
                                   String parameterAsText) {
        return "Unable to validate parameter " + usage.getParameterName(combinationParameter)
                .asText(Language.EN) + " at index [" + combinationParameter
                .getSequenceNumber() + "] ('" + parameterAsText + "')";
    }
}
