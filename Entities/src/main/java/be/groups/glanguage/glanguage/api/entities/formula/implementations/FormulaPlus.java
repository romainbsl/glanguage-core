package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaType;

/**
 * Formula representing a mathematical addition<br>
 * This formula has exactly two (2) parameters<br>
 * This formula adds its second parameter value to its first parameter value and return the value<br>
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
@DiscriminatorValue(FormulaType.Values.PLUS)
public class FormulaPlus extends AbstractNonTerminalFormula {
	
	public FormulaPlus() {
		super();
	}
	
	@Transient
	@Override
	public Integer getIntegerValue() {
		return getParameters().get(0).getIntegerValue() + getParameters().get(1).getIntegerValue();
	}
	
	@Transient
	@Override
	public Double getNumericValue() {
		return getParameters().get(0).getNumericValue() + getParameters().get(1).getNumericValue();
	}
	
	/**
	 * Appends the second parameter string value to the first parameter string value
	 */
	@Transient
	@Override
	public String getStringValue() {
		return new StringBuffer(getParameters().get(0).getStringValue()).append(getParameters().get(1).getStringValue()).toString();
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
			case STRING:
				if (firstParameterReturnType == FormulaReturnType.STRING) {
					return secondParameterReturnType;
				} else {
					return FormulaReturnType.UNDEFINED;
				}
			case DATE:
				// TODO
			default:
				return FormulaReturnType.UNDEFINED;
		}
	}
	
	@Override
	public String asText() {
		return getParameters().get(0).asText() + " + " + getParameters().get(1).asText();
	}
	
}
