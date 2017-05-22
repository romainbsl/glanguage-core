package be.groups.glanguage.glanguage.api.error.formula.description.conbination;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbinationItem;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionInnerError;
import be.groups.glanguage.glanguage.api.error.utils.ErrorMethod;

import java.util.Iterator;

/**
 * Created by michotte on 11/05/2017.
 */
public class FormulaParameterConbinationItemWrongParameterTypeInnerError extends FormulaDescriptionInnerError {

    public FormulaParameterConbinationItemWrongParameterTypeInnerError(AbstractFormula formula,
                                                                       FormulaUsage usage,
                                                                       FormulaParameterConbinationItem
                                                                               conbinationParameter,
                                                                       String parameterAsText,
                                                                       FormulaReturnType actualType,
                                                                       Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_CONBINATION_ITEM_WRONG_TYPE,
              formula,
              evaluator,
              ErrorMethod.VALIDATE.getName(),
              getCause(usage, conbinationParameter, parameterAsText, actualType));
    }

    private static String getCause(FormulaUsage usage,
                                   FormulaParameterConbinationItem conbinationParameter,
                                   String parameterAsText,
                                   FormulaReturnType actualType) {
        StringBuilder sb = new StringBuilder("Parameter " + usage.getParameterName(conbinationParameter)
                .asText(Language.EN) + " at index [" + conbinationParameter
                .getSequenceNumber() + "] ('" + parameterAsText + "')");
        sb.append(" has a wrong type : " + " actual type = " + (actualType == null ? "[null]" : actualType.name()));
        if (conbinationParameter.getTypes() != null) {
            Iterator<FormulaReturnType> itExpectedTypes = conbinationParameter.getReturnTypes().iterator();
            if (itExpectedTypes.hasNext()) {
                sb.append(" , expected type(s) = {'" + itExpectedTypes.next().name());
                while (itExpectedTypes.hasNext()) {
                    sb.append("', '" + itExpectedTypes.next().name());
                }
                sb.append("'}");
            }
        }
        return sb.toString();
    }
}
