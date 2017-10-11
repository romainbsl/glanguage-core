package be.groups.glanguage.core.error.formula.base.parameter;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.description.FormulaDescriptionInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;

/**
 * @author michotte
 */
public class FormulaWrongParameterNumberInnerError extends FormulaDescriptionInnerError {

    private Integer actualParameterNumber;
    private Integer expectedMinimimumParameterNumber;
    private Integer expectedMaximimumParameterNumber;

    public FormulaWrongParameterNumberInnerError(AbstractFormula formula,
                                                 Evaluator evaluator,
                                                 int actualParameterNumber,
                                                 int expectedMinimimumParameterNumber,
                                                 Integer expectedMaximimumParameterNumber) {
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
                                   Integer expectedMaximimumParameterNumber) {
        StringBuilder sb = new StringBuilder("There are " + actualNumberOfParameters + " parameters");
        if (expectedMaximimumParameterNumber != null && expectedMinimimumParameterNumber !=
            expectedMaximimumParameterNumber.intValue()) {
            sb.append(" but there should be at least " + expectedMinimimumParameterNumber);
            sb.append(" and at most " + expectedMaximimumParameterNumber);
        } else {
            sb.append(" but there should be exactly " + expectedMinimimumParameterNumber);
        }
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
