package be.groups.glanguage.glanguage.api.entities.formula;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

/**
 * Common implementation of a non-terminal formula <br>
 * An AbstractNonTerminalFormula always has a non-empty list of {@link AbstractTerminalFormula#parameters}<br>
 * Evaluating an AbstractNonTerminalFormula consists in applying its own evaluation algorithm on the results of the
 * evaluation of its parameters.
 *
 * @author michotte
 */
@Entity
public abstract class AbstractNonTerminalFormula extends AbstractFormula {
	
	protected AbstractNonTerminalFormula() {
		super();
	}
	
	protected AbstractNonTerminalFormula(FormulaDescription description, List<AbstractFormula> parameters, Evaluator evaluator
										 ) throws
																										   GLanguageException {
		super(description);
		validate(parameters, evaluator);
		this.parameters = parameters;
	}

	/**
	 * Validate this with a list of {@link AbstractFormula parameters} (by delegating to
	 * {@link FormulaDescription#validate(AbstractFormula, List, Evaluator)} with {@code this} as first parameter)
	 *
	 * @param parameters the list of parameters to be validated
	 * @param evaluator the evaluator to be used during the validation process, can be null
	 * @throws GLanguageException if this is not valid
	 * @see FormulaDescription#validate(AbstractFormula, List, Evaluator)
	 */
	@JsonIgnore
	public void validate(List<AbstractFormula> parameters, Evaluator evaluator) throws GLanguageException {
		this.getDescription().validate(this, parameters, evaluator);
	}

	/**
	 * Is this valid according to its {@link FormulaDescription description} ? (by delegating to
	 * {@link FormulaDescription#isValid(List, Evaluator)})
	 *
	 * @param evaluator the evaluator to be used during the validation process, can be null
	 * @return true if this is valid according to its {@link FormulaDescription description}, false otherwise
	 * @see FormulaDescription#isValid(List, Evaluator)
	 */
	@JsonIgnore
	@Transient
	public boolean isValid(Evaluator evaluator) {
		return getDescription().isValid(getParameters(), evaluator);
	}

	/**
	 * Get the return type with an evaluator (can be null) (by delegating to
	 * {@link FormulaDescription#getReturnType(List, Evaluator)} with {@link AbstractNonTerminalFormula#parameters}
	 * as first parameter)
	 *
	 * @param evaluator the evaluator to be used during the validation process, can be null
	 * @return the return type
	 */
	@JsonIgnore
	@Transient
	public FormulaReturnType getReturnType(Evaluator evaluator) {
		return getDescription().getReturnType(getParameters(), evaluator);
	}

	/**
	 * Is this terminal ?<br>
	 * AbstractNonTerminalFormula is never terminal
	 *
	 * @return false
	 */
	@JsonIgnore
	@Transient
	@Override
	public boolean isTerminal() {
		return false;
	}

}
