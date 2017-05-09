package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;

/**
 * Formula representing a mathematical subtraction<br>
 * This Formula has exactly two (2) parameters<br>
 * This Formula subtracts its second parameter value from its first parameter value and return the
 * resulting value<br>
 * This Formula can subtract :
 * <ul>
 * <li>two integers - returning an integer value</li>
 * <li>an integer and a numeric - returning a numeric value</li>
 * <li>two numerics - returning a numeric value</li>
 * </ul>
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_MINUS)
public class FormulaMinus extends BinaryFormula {
	
	protected FormulaMinus() {
		super();
	}
	
	public FormulaMinus(FormulaDescription description, AbstractFormula child1, AbstractFormula child2) throws GLanguageException {
		super(description, child1, child2);
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
		return getParameters().get(0).getIntegerValue(evaluator) - getParameters().get(1).getIntegerValue(evaluator);
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
		return getParameters().get(0).getNumericValue(evaluator) - getParameters().get(1).getNumericValue(evaluator);
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected LocalDate doGetDateValue(Evaluator evaluator) throws GLanguageException {
		return getParameters().get(0).getDateValue(evaluator).minusDays(getParameters().get(1).getDurationValue(evaluator).toDays());
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageException {
		return getParameters().get(0).getDurationValue(evaluator).minusDays(getParameters().get(1).getDurationValue(evaluator).toDays());
	}
	
	@Override
	public String operationAsText() {
		return "-";
	}
	
}
