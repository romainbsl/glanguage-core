package be.groups.glanguage.glanguage.api.error.formula.description;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.utils.ErrorMethod;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by michotte on 18/05/2017.
 */
public class FormulaDescriptionNoMatchingUsageInnerError extends FormulaDescriptionInnerError {

    public FormulaDescriptionNoMatchingUsageInnerError(AbstractFormula formula,
                                                       List<AbstractFormula> parameters,
                                                       Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_DESCRIPTION_NO_MATCHING_USAGE,
              formula,
              evaluator,
              ErrorMethod.VALIDATE.getName(),
              getCause(parameters, evaluator));
    }

    public static String getCause(List<AbstractFormula> parameters, Evaluator evaluator) {
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to find a matching usage");
        if (parameters != null && !parameters.isEmpty()) {
            sb.append(" for parameter type sequence: <");
            ListIterator<AbstractFormula> itParameters = parameters.listIterator();
            if (itParameters.hasNext()) {
                sb.append(itParameters.next().getReturnType(evaluator));
                while (itParameters.hasNext()) {
                    sb.append(", " + itParameters.next().getReturnType(evaluator));
                }
            }
        }
        sb.append(">");
        return sb.toString();
    }
}
