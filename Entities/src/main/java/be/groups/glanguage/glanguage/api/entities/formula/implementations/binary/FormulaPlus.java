package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
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
 * Formula representing a mathematical addition<br>
 * This formula has exactly two (2) parameters<br>
 * This formula adds its second parameter value to its first parameter value and return the value
 * <br>
 * This formula can add :
 * <ul>
 * <li>two integers - returning an integer value</li>
 * <li>two numerics - returning a numeric value</li>
 * <li>an integer and a numeric - returning a numeric value</li>
 * <li>two strings - returning a string value</li>
 * </ul>
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_PLUS)
public class FormulaPlus extends BinaryFormula {
	
	protected FormulaPlus() {
		super();
	}
	
	public FormulaPlus(FormulaDescription description, AbstractFormula child1, AbstractFormula child2) {
		super(description, child1, child2);
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
		return getParameters().get(0).getIntegerValue(evaluator) + getParameters().get(1).getIntegerValue(evaluator);
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
		return getParameters().get(0).getNumericValue(evaluator) + getParameters().get(1).getNumericValue(evaluator);
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected String doGetStringValue(Evaluator evaluator) throws GLanguageException {
		AbstractFormula leftParameter = getParameters().get(0);
		AbstractFormula rightParameter = getParameters().get(1);
		
		return leftParameter.getStringValue(evaluator) + rightParameter.getStringValue(evaluator);
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected LocalDate doGetDateValue(Evaluator evaluator) throws GLanguageException {
		AbstractFormula leftParameter = getParameters().get(0);
		AbstractFormula rightParameter = getParameters().get(1);
		AbstractFormula dateParameter = null;
		AbstractFormula durationParameter = null;
		
		if (leftParameter.getReturnType(evaluator).equals(FormulaReturnType.DATE)) {
			dateParameter = leftParameter;
		} else if (rightParameter.getReturnType(evaluator).equals(FormulaReturnType.DATE)) {
			dateParameter = rightParameter;
		}
		if (leftParameter.getReturnType(evaluator).equals(FormulaReturnType.DURATION)) {
			durationParameter = leftParameter;
		} else if (rightParameter.getReturnType(evaluator).equals(FormulaReturnType.DURATION)) {
			durationParameter = rightParameter;
		}
		
		if (dateParameter == null || durationParameter == null) {
			throw new IllegalStateException(
					"Cannot call getDateValue() method on FormulaPlus with these parameter types : left parameter type : "
							+ leftParameter.getReturnType(evaluator) + ", right parameter type : " + rightParameter
							.getReturnType(evaluator));
		}
		
		return dateParameter.getDateValue(evaluator).plusDays(durationParameter.getDurationValue(evaluator).toDays());
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageException {
		AbstractFormula leftParameter = getParameters().get(0);
		AbstractFormula rightParameter = getParameters().get(1);
		
		return leftParameter.getDurationValue(evaluator).plusDays(rightParameter.getDurationValue(evaluator).toDays());
	}
	
	@Override
	public String operationAsText() {
		return "+";
	}
	
}
