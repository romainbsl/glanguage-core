package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

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
	
	public FormulaMinus(FormulaDescription description, AbstractFormula child1, AbstractFormula child2) {
		super(description, child1, child2);
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue() {
		return getParameters().get(0).getIntegerValue() - getParameters().get(1).getIntegerValue();
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Double getNumericValue() {
		return getParameters().get(0).getNumericValue() - getParameters().get(1).getNumericValue();
	}
	
	@JsonIgnore
	@Transient
	@Override
	public LocalDate getDateValue() {
		return getParameters().get(0).getDateValue().minusDays(getParameters().get(1).getDurationValue().toDays());
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Duration getDurationValue() {		
		return getParameters().get(0).getDurationValue().minusDays(getParameters().get(1).getDurationValue().toDays());
	}
	
	@Override
	public String operationAsText() {
		return "-";
	}
	
}
