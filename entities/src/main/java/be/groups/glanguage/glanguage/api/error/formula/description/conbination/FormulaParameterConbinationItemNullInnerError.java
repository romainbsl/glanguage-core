package be.groups.glanguage.glanguage.api.error.formula.description.conbination;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbinationItem;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionInnerError;

/**
 * Created by michotte on 11/05/2017.
 */
public class FormulaParameterConbinationItemNullInnerError extends FormulaDescriptionInnerError {

    public FormulaParameterConbinationItemNullInnerError(AbstractFormula formula,
                                                         FormulaUsage usage,
                                                         FormulaParameterConbinationItem conbinationParameter,
                                                         Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_CONBINATION_ITEM_UNABLE_TO_VALIDATE,
              formula,
              evaluator,
              "validate",
              getCause(usage, conbinationParameter));
    }

    private static String getCause(FormulaUsage usage, FormulaParameterConbinationItem conbinationParameter) {
        return "Parameter " + getParameterName(usage, conbinationParameter) + " at index [" + conbinationParameter
                .getSequenceNumber() + "] is null";
    }

}
