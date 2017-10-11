package be.groups.glanguage.core.error.formula.base;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.formula.FormulaInnerError;

/**
 * Created by michotte on 11/10/2017.
 */
public class FormulaValidateInnerError extends FormulaInnerError {

  public FormulaValidateInnerError(AbstractFormula formula,
                                   Evaluator evaluator,
                                   String methodName,
                                   String cause) {
    super(GLanguageErrorRegistry.FORMULA_VALIDATE_INNER_ERROR, formula, evaluator, methodName, cause);
  }

}
