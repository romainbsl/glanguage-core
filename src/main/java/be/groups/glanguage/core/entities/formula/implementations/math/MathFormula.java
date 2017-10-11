package be.groups.glanguage.core.entities.formula.implementations.math;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract formula implementing mathematical operations on a number
 *
 * @author michotte
 */
@Entity
public abstract class MathFormula extends AbstractNonTerminalFormula {
	
	public MathFormula() {
		super();
	}
	
	public MathFormula(FormulaDescription description,
					   List<AbstractFormula> parameters,
					   Evaluator evaluator) throws GLanguageException {
		super(description, parameters, evaluator);

		this.parameters = new ArrayList<>();
		this.parameters.addAll(parameters);
	}

	/**
	 * Get the value as {@link Integer}<br>
	 * Calling this method is equivalent to calling {@link MathFormula#doGetNumericValue(Evaluator)} method and
	 * applying {@link Double#intValue()} on the result
	 *
	 * @param evaluator the evaluator to be used in the evaluation process, can be null
	 * @return the value as {@link Integer} - the integer part of the value as {@link Double}
	 * @throws GLanguageException if an error occurs during the evaluation process
	 */
	@JsonIgnore
	@Transient
	@Override
	protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
		return getNumericValue(evaluator).intValue();
	}
	
	@Override
	public String asText() {
		return operationAsText() + "(" + getParameters().get(0).asText() + ")";
	}
	
	public abstract String operationAsText();
}
