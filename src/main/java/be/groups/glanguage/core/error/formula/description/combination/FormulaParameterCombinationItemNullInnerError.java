package be.groups.glanguage.core.error.formula.description.combination;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.combination.FormulaParameterCombinationItem;
import be.groups.glanguage.core.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.core.entities.utils.Language;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.description.FormulaDescriptionInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;

/**
 * @author michotte
 */
public class FormulaParameterCombinationItemNullInnerError extends FormulaDescriptionInnerError {

    public FormulaParameterCombinationItemNullInnerError(AbstractFormula formula,
                                                         FormulaUsage usage,
                                                         FormulaParameterCombinationItem combinationParameter,
                                                         Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_COMBINATION_ITEM_UNABLE_TO_VALIDATE,
              formula,
              evaluator,
              ErrorMethod.VALIDATE.getName(),
              getCause(usage, combinationParameter));
    }

    private static String getCause(FormulaUsage usage, FormulaParameterCombinationItem combinationParameter) {
        return "Parameter " + usage.getParameterName(combinationParameter)
                .asText(Language.EN) + " at index [" + combinationParameter.getSequenceNumber() + "] is null";
    }

}
