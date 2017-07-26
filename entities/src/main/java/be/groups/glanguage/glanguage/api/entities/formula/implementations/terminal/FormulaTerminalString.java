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
 * Formula representing a constant string value
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.TERMINAL_STRING)
public class FormulaTerminalString extends AbstractTerminalFormula {

	public FormulaTerminalString() {
		super();
	}

	public FormulaTerminalString(FormulaDescription description, String constantValue) throws GLanguageException {
		super(description, constantValue);
	}

	/**
	 * Is this valid ?<br>
	 * This is valid if :
	 * <ol>
	 * <li>the {@link FormulaTerminalString#getConstantValue()} is not null</li>
	 * </ol>
	 *
	 * @param evaluator the evaluator to be used during the validation process, can be null
	 * @return true if this is valid, false otherwise
	 */
	@Override
	public boolean isValid(Evaluator evaluator) {
		return getConstantValue() != null;
	}

	/**
	 * Validate this with a {@code constantValue}<br>
	 * This is valid if :
	 * <ol>
	 * <li>the {@link FormulaTerminalString#getConstantValue()} is not null</li>
	 * </ol>
	 *
	 * @param constantValue the value to be validated
	 * @throws GLanguageException if the {@code constantValue} is null
	 */
	@Override
	public void validate(String constantValue) throws GLanguageException {
		if (constantValue == null) {
			throw new GLanguageException(new TerminalFormulaUnableToInitializeNullValueInnerError(this,
																								  null,
																								  ErrorMethod.VALIDATE
																										  .getName()));
		}
	}

	/**
	 * Get the return type<br>
	 * The return type of this type of formula is always {@link FormulaReturnType#STRING}
	 *
	 * @param evaluator the evaluator to be used during the validation process, can be null
	 * @return always {@link FormulaReturnType#STRING}
	 */
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
