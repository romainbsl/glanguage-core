package be.groups.glanguage.glanguage.api.error.formula.description.conbination;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;

/**
 * Created by michotte on 20/12/2016.
 */
public class FormulaParameterConbinationWrongParameterNumberInnerError extends FormulaInnerError {

    private Integer actualParameterNumber;
    private Integer expectedMinimimumParameterNumber;
    private Integer expectedMaximimumParameterNumber;

    public FormulaParameterConbinationWrongParameterNumberInnerError(AbstractFormula formula,
                                                                     Evaluator evaluator,
                                                                     String methodName,
                                                                     int actualParameterNumber,
                                                                     int expectedMinimimumParameterNumber,
                                                                     int expectedMaximimumParameterNumber) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_WRONG_NUMBER,
              formula,
              evaluator,
              methodName,
              getCause(actualParameterNumber, expectedMinimimumParameterNumber, expectedMaximimumParameterNumber));
        this.actualParameterNumber = actualParameterNumber;
        this.expectedMinimimumParameterNumber = expectedMinimimumParameterNumber;
        this.expectedMaximimumParameterNumber = expectedMaximimumParameterNumber;
    }

    private static String getCause(int actualNumberOfParameters, int expectedMinimimumParameterNumber,
                                   int expectedMaximimumParameterNumber) {
        StringBuilder sb = new StringBuilder("There are " + actualNumberOfParameters + " parameters");
        sb.append(" but there should be at least " + expectedMinimimumParameterNumber);
        sb.append(" and at most " + expectedMaximimumParameterNumber);
        return sb.toString();
    }

    public Integer getActualParameterNumber() {
        return actualParameterNumber;
    }

    public Integer getExpectedMinimimumParameterNumber() {
        return expectedMinimimumParameterNumber;
    }

    public Integer getExpectedMaximimumParameterNumber() {
        return expectedMaximimumParameterNumber;
    }
}
