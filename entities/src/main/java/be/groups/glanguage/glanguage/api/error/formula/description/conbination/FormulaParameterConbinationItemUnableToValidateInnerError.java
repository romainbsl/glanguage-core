package be.groups.glanguage.glanguage.api.error.formula.description.conbination;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbinationItem;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionInnerError;
import be.groups.glanguage.glanguage.api.error.utils.ErrorMethod;

/**
 * Created by michotte on 11/05/2017.
 */
public class FormulaParameterConbinationItemUnableToValidateInnerError extends FormulaDescriptionInnerError {

    public FormulaParameterConbinationItemUnableToValidateInnerError(AbstractFormula formula,
                                                                     FormulaUsage usage,
                                                                     FormulaParameterConbinationItem
                                                                             conbinationParameter,
                                                                     String parameterAsText,
                                                                     Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_CONBINATION_ITEM_UNABLE_TO_VALIDATE,
              formula,
              evaluator,
              ErrorMethod.VALIDATE.getName(),
              getCause(usage, conbinationParameter, parameterAsText));
    }

    private static String getCause(FormulaUsage usage,
                                   FormulaParameterConbinationItem conbinationParameter,
                                   String parameterAsText) {
        return "Unable to validate parameter " + usage.getParameterName(conbinationParameter)
                .asText(Language.EN) + " at index [" + conbinationParameter
                .getSequenceNumber() + "] ('" + parameterAsText + "')";
    }
}
