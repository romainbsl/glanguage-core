/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;

/**
 * Formula representing an undefined formula
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.UNDEFINED)
public class FormulaUndefined extends AbstractTerminalFormula {
	
	public FormulaUndefined() {
		super();
	}
	
	protected FormulaUndefined(FormulaDescription description) {
		super(description);
	}

	@Override
	public boolean isValid(Evaluator evaluator) {
		return true;
	}

	@Override
	public void validate(String constantValue) throws GLanguageException {
		// do nothing
	}

	/**
	 * Get the return type<br>
	 * The return type of this type of formula is always {@link FormulaReturnType#UNDEFINED}
	 *
	 * @param evaluator the evaluator to be used during the validation process, can be null
	 * @return always {@link FormulaReturnType#UNDEFINED}
	 */
	@Override
	public FormulaReturnType getReturnType(Evaluator evaluator) {
		return FormulaReturnType.UNDEFINED;
	}

	/**
	 * Get the value as {@link Integer} - always null
	 *
	 * @param evaluator the evaluator to be used in the evaluation process, can be null
	 * @return always null
	 */
	@JsonIgnore
	@Transient
	@Override
	protected Integer doGetIntegerValue(Evaluator evaluator) {
		return null;
	}

	/**
	 * Get the value as {@link Double} - always null
	 *
	 * @param evaluator the evaluator to be used in the evaluation process, can be null
	 * @return always null
	 */
	@JsonIgnore
	@Transient
	@Override
	protected Double doGetNumericValue(Evaluator evaluator) {
		return null;
	}

	/**
	 * Get the value as {@link Boolean} - always null
	 *
	 * @param evaluator the evaluator to be used in the evaluation process, can be null
	 * @return always null
	 */
	@JsonIgnore
	@Transient
	@Override
	protected Boolean doGetBooleanValue(Evaluator evaluator) {
		return null;
	}

	/**
	 * Get the value as {@link LocalDate} - always null
	 *
	 * @param evaluator the evaluator to be used in the evaluation process, can be null
	 * @return always null
	 */
	@JsonIgnore
	@Transient
	@Override
	protected LocalDate doGetDateValue(Evaluator evaluator) {
		return null;
	}

	/**
	 * Get the value as {@link Duration} - always null
	 *
	 * @param evaluator the evaluator to be used in the evaluation process, can be null
	 * @return always null
	 */
	@JsonIgnore
	@Transient
	@Override
	protected Duration doGetDurationValue(Evaluator evaluator) {
		return null;
	}
	
}
