/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.implementations.terminal
        .TerminalFormulaUnableToInitializeNullValueInnerError;
import be.groups.glanguage.glanguage.api.error.utils.ErrorMethod;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Formula representing a constant integer value
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.TERMINAL_STRING)
public class FormulaTerminalString extends AbstractTerminalFormula {

	protected FormulaTerminalString() {
		super();
	}

	public FormulaTerminalString(FormulaDescription description, String constantValue) throws GLanguageException {
		super(description, constantValue);
	}

	@Override
	public boolean isValid(Evaluator evaluator) {
		return getConstantValue() != null;
	}

	@Override
	public void validate(String constantValue) throws GLanguageException {
		if (constantValue == null) {
			throw new GLanguageException(new TerminalFormulaUnableToInitializeNullValueInnerError(this,
																								  null,
																								  ErrorMethod.VALIDATE
																										  .getName()));
		}
	}

	@Override
	public FormulaReturnType getReturnType(Evaluator evaluator) {
		return FormulaReturnType.STRING;
	}

	public String asText() {
		StringBuilder sb = new StringBuilder("\"");
		sb.append(super.asText());
		sb.append("\"");
		return sb.toString();
	}
		
}
