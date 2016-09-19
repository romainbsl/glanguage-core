package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

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
	
	public FormulaPlus(AbstractFormula child1, AbstractFormula child2) {
		super( child1, child2);
	}
	
	@Transient
	@Override
	public Integer getIntegerValue() {
		return getNumericValue().intValue();
	}
	
	@Transient
	@Override
	public Double getNumericValue() {
		return getParameters().get(0).getNumericValue() + getParameters().get(1).getNumericValue();
	}
	
	@Transient
	@Override
	public String getStringValue() {
		AbstractFormula leftParameter = getParameters().get(0);
		AbstractFormula rightParameter = getParameters().get(1);
		
		return leftParameter.getStringValue() + rightParameter.getStringValue();
	}
	
	@Override
	public String operationAsText() {
		return "+";
	}
	
}
