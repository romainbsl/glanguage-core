package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaType;

/**
 * Formula representing a mathematical subtraction<br>
 * This Formula has exactly two (2) parameters<br>
 * This Formula subtracts its second parameter value from its first parameter value and return the resulting value<br>
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
@DiscriminatorValue(FormulaType.Values.MINUS)
public class FormulaMinus extends AbstractNonTerminalFormula {
	
	public FormulaMinus() {
		super();
	}
	
	@Transient
	@Override
	public Integer getIntegerValue() {
		return getParameters().get(0).getIntegerValue() - getParameters().get(1).getIntegerValue();
	}
	
	@Transient
	@Override
	public Double getNumericValue() {
		return getParameters().get(0).getNumericValue() - getParameters().get(1).getNumericValue();
	}
	
	@Transient
	@Override
	public String getStringValue() {
		return null;
	}
	
	@Transient
	@Override
	public Boolean getBooleanValue() {
		return null;
	}
	
	@Transient
	@Override
	public LocalDate getDateValue() {
		return null;
	}
	
	@Transient
	@Override
	protected FormulaReturnType computeReturnType() {
		FormulaReturnType firstParameterReturnType = getParameters().get(0).getReturnType();
		FormulaReturnType secondParameterReturnType = getParameters().get(1).getReturnType();
		
		switch (secondParameterReturnType) {
			case INTEGER:
				if (firstParameterReturnType == FormulaReturnType.INTEGER
						|| firstParameterReturnType == FormulaReturnType.NUMERIC) {
					return firstParameterReturnType;
				} else {
					return FormulaReturnType.UNDEFINED;
				}
			case NUMERIC:
				if (firstParameterReturnType == FormulaReturnType.INTEGER
						|| firstParameterReturnType == FormulaReturnType.NUMERIC) {
					return secondParameterReturnType;
				} else {
					return FormulaReturnType.UNDEFINED;
				}
			default:
				return FormulaReturnType.UNDEFINED;
		}
	}
	
	@Override
	public String asText() {
		return getParameters().get(0).asText() + " - " + getParameters().get(1).asText();
	}
	
}
