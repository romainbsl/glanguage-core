package be.groups.glanguage.glanguage.api.error.formula.base.parameter;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;

import java.util.Iterator;
import java.util.List;

/**
 * Created by michotte on 20/12/2016.
 */
public class FormulaWrongParameterNumberInnerError extends FormulaInnerError {

    private Integer actualParameterNumber;
    private List<Integer> expectedParameterNumbers;

    public FormulaWrongParameterNumberInnerError(AbstractFormula formula,
                                                 Evaluator evaluator,
                                                 String methodName,
                                                 int actualParameterNumber,
                                                 List<Integer> expectedParameterNumbers) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_WRONG_NUMBER,
              formula,
              evaluator,
              methodName,
              getCause(actualParameterNumber, expectedParameterNumbers));
        this.actualParameterNumber = actualParameterNumber;
        this.expectedParameterNumbers = expectedParameterNumbers;
    }

    private static String getCause(int actualNumberOfParameters, List<Integer> expectedParameterNumbers) {
        StringBuilder sb = new StringBuilder("There are " + actualNumberOfParameters + " parameters");
        if (expectedParameterNumbers != null) {
            Iterator<Integer> itExpectedParameterNumbers = expectedParameterNumbers.iterator();
            if (itExpectedParameterNumbers.hasNext()) {
                sb.append(" but there should be " + itExpectedParameterNumbers.next());
                while (itExpectedParameterNumbers.hasNext()) {
                    sb.append(" or " + itExpectedParameterNumbers.next());
                }
            }
        }
        return sb.toString();
    }

}
