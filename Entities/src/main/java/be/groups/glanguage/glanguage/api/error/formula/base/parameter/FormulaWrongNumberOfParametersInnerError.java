package be.groups.glanguage.glanguage.api.error.formula.base.parameter;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;

/**
 * Created by michotte on 20/12/2016.
 */
public class FormulaWrongNumberOfParametersInnerError extends FormulaInnerError {

    public FormulaWrongNumberOfParametersInnerError(AbstractFormula formula,
                                                    Evaluator evaluator,
                                                    String methodName,
                                                    int actualNumberOfParameters,
                                                    int... expectedNumberOfParameters) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_WRONG_NUMBER,
              formula,
              evaluator,
              methodName,
              getCause(actualNumberOfParameters, expectedNumberOfParameters));
    }

    private static String getCause(int actualNumberOfParameters, int[] expectedNumberOfParameters) {
        StringBuilder sb = new StringBuilder("There are " + actualNumberOfParameters + " number of parameters");
        if (expectedNumberOfParameters != null && expectedNumberOfParameters.length > 0) {
            sb.append("but there should be " + expectedNumberOfParameters[0]);
            for (int i = 1; i < expectedNumberOfParameters.length; i++) {
                sb.append(" or " + expectedNumberOfParameters[i]);
            }
        }
        return sb.toString();
    }

}
