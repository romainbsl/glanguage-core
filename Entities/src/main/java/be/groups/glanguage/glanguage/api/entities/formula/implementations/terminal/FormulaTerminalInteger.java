/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import be.groups.errorframework.core.error.ErrorEnum;
import be.groups.errorframework.core.error.RootError;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal
		.TerminalFormulaUnableToInitializeNullValueInnerError;
import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal.TerminalFormulaUnableToParseValueInnerError;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Formula representing a constant integer value
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.TERMINAL_INTEGER)
public class FormulaTerminalInteger extends AbstractTerminalFormula {

	protected FormulaTerminalInteger() {
		super();
	}

	public FormulaTerminalInteger(FormulaDescription description, String constantValue) {
		super(description, constantValue);
	}

	@JsonIgnore
	@Transient
	@Override
	protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageEvaluationException {
		if (getConstantValue() != null) {
			try {
				return Integer.valueOf(getConstantValue());
			} catch (NumberFormatException nfe) {
				RootError error = new RootError(ErrorEnum.BUSINESS_ERROR);
				error.setInnererror(new TerminalFormulaUnableToParseValueInnerError(getConstantValue(),
						FormulaReturnType.BOOLEAN,
						"(-)?[0-9]*",
						nfe));
				throw new GLanguageEvaluationException(error);
			}
		} else {
			RootError error = new RootError(ErrorEnum.BUSINESS_ERROR);
			error.setInnererror(new TerminalFormulaUnableToInitializeNullValueInnerError());
			throw new GLanguageEvaluationException(error);
		}
	}

	@JsonIgnore
	@Transient
	@Override
	protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageEvaluationException {
		return getIntegerValue(evaluator).doubleValue();
	}

}
