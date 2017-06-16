package be.groups.glanguage.glanguage.api.error.formula.base.parameter;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;

import java.util.Iterator;
import java.util.List;

/**
 * Created by michotte on 6/01/2017.
 */
public class FormulaWrongParameterTypeInnerError extends FormulaInnerError {

    public FormulaWrongParameterTypeInnerError(AbstractFormula formula,
                                               Evaluator evaluator,
                                               String methodName,
                                               String parameterName,
                                               int parameterIndex,
                                               FormulaReturnType actualReturnType,
                                               List<FormulaReturnType> expectedReturnTypes) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_WRONG_TYPE,
              formula,
              evaluator,
              methodName,
              getCause(parameterName, parameterIndex, actualReturnType, expectedReturnTypes));
    }

    private static String getCause(String parameterName,
                                   int parameterIndex,
                                   FormulaReturnType actualReturnType,
                                   List<FormulaReturnType> expectedReturnTypes) {
        StringBuilder sb = new StringBuilder("Parameter '" + parameterName + "' at index [" + parameterIndex + "] is" +
                                                     " of type" + actualReturnType);
        if (expectedReturnTypes != null) {
            Iterator<FormulaReturnType> itExpectedReturnTypes = expectedReturnTypes.iterator();
            if (itExpectedReturnTypes.hasNext()) {
                sb.append(" but it should be of type " + itExpectedReturnTypes.next());
                while (itExpectedReturnTypes.hasNext()) {
                    sb.append(" or " + itExpectedReturnTypes.next());
                }
            }
        }
        return sb.toString();
    }
}
