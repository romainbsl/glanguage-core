package be.groups.glanguage.core.error.formula.description;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.utils.ErrorMethod;

import java.util.List;
import java.util.ListIterator;

/**
 * @author michotte
 */
public class FormulaDescriptionUnableToValidateInnerError extends FormulaDescriptionInnerError {

    public FormulaDescriptionUnableToValidateInnerError(AbstractFormula formula,
                                                        List<AbstractFormula> parameters,
                                                        Evaluator evaluator) {
        super(GLanguageErrorRegistry.FORMULA_DESCRIPTION_UNABLE_TO_VALIDATE,
              formula,
              evaluator,
              ErrorMethod.VALIDATE.getName(),
              getCause(parameters, evaluator));
    }

    public static String getCause(List<AbstractFormula> parameters, Evaluator evaluator) {
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to validate for parameter type sequence : <");
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
