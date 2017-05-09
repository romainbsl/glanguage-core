package be.groups.glanguage.glanguage.api.error.formula.description;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;
import be.groups.glanguage.glanguage.api.error.formula.base.parameter.FormulaWrongParameterNumberInnerError;
import be.groups.glanguage.glanguage.api.error.formula.base.parameter.FormulaWrongParameterTypeInnerError;

import java.util.List;

/**
 * Created by michotte on 26/01/2017.
 */
public class FormulaDescriptionInnerErrorFactory {

    public static FormulaInnerError getWrongParameterNumber(AbstractFormula formula,
                                                            int actualNumberOfParameters,
                                                            List<Integer> expectedNumberOfParameters) {
        return new FormulaWrongParameterNumberInnerError(formula,
                                                         null,
                                                         "checkIsValid",
                                                         actualNumberOfParameters,
                                                         expectedNumberOfParameters);
    }

    public static FormulaInnerError getWrongParameterType(AbstractFormula formula,
                                                          String parameterName,
                                                          int parameterIndex,
                                                          FormulaReturnType actualReturnType,
                                                          List<FormulaReturnType> expectedReturnTypes) {
        return new FormulaWrongParameterTypeInnerError(formula,
                                                       null,
                                                       "checkIsValid",
                                                       parameterName,
                                                       parameterIndex,
                                                       actualReturnType,
                                                       expectedReturnTypes);
    }

}
