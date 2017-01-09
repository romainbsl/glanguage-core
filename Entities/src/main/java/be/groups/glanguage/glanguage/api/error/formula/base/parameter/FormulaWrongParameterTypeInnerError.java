package be.groups.glanguage.glanguage.api.error.formula.base.parameter;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;

/**
 * Created by michotte on 6/01/2017.
 */
public class FormulaWrongParameterTypeInnerError extends FormulaInnerError{

    public FormulaWrongParameterTypeInnerError(AbstractFormula formula,
                                               Evaluator evaluator,
                                               String methodName,
                                               int parameterIndex,
                                               FormulaReturnType actualReturnType,
                                               FormulaReturnType... expectedReturnTypes) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_WRONG_TYPE,
              formula,
              evaluator,
              methodName,
              getCause(parameterIndex, actualReturnType, expectedReturnTypes));
    }

    private static String getCause(int parameterIndex,
                                   FormulaReturnType actualReturnType,
                                   FormulaReturnType... expectedReturnTypes) {
        StringBuilder sb = new StringBuilder("Parameter at index " + parameterIndex + " is of type " +
                                                     actualReturnType);
        if (expectedReturnTypes != null && expectedReturnTypes.length > 0) {
            sb.append(" but it should be " + expectedReturnTypes[0]);
            for (int i = 1; i < expectedReturnTypes.length; i++) {
                sb.append(" or " + expectedReturnTypes[i]);
            }
        }
        return sb.toString();
    }
}
