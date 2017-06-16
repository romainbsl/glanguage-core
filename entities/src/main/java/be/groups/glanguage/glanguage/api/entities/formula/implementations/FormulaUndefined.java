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
 * Formula representing a constant integer value
 * 
 * @author dupirefr
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

	@Override
	public FormulaReturnType getReturnType(Evaluator evaluator) {
		return FormulaReturnType.UNDEFINED;
	}

	@JsonIgnore
	@Transient
	@Override
	protected Integer doGetIntegerValue(Evaluator evaluator) {
		return null;
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected Double doGetNumericValue(Evaluator evaluator) {
		return null;
	}

	@JsonIgnore
	@Transient
	@Override
	protected Boolean doGetBooleanValue(Evaluator evaluator) {
		return null;
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected LocalDate doGetDateValue(Evaluator evaluator) {
		return null;
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected Duration doGetDurationValue(Evaluator evaluator) {
		return null;
	}
	
}
