package be.groups.glanguage.core.error.formula.description.combination;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.description.FormulaDescriptionInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;

/**
 * @author michotte
 */
public class FormulaParameterCombinationWrongParameterNumberInnerError extends FormulaDescriptionInnerError {

    private Integer actualParameterNumber;
    private Integer expectedMinimimumParameterNumber;
    private Integer expectedMaximimumParameterNumber;

    public FormulaParameterCombinationWrongParameterNumberInnerError(AbstractFormula formula,
                                                                     Evaluator evaluator,
                                                                     int actualParameterNumber,
                                                                     int expectedMinimimumParameterNumber,
                                                                     int expectedMaximimumParameterNumber) {
        super(GLanguageErrorRegistry.FORMULA_PARAMETER_COMBINATION_WRONG_PARAMETER_NUMBER,
              formula,
              evaluator,
              ErrorMethod.VALIDATE.getName(),
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
