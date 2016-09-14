package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

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
@DiscriminatorValue(FormulaDescription.Values.OP_MINUS)
public class FormulaMinus extends BinaryFormula {
	
	protected FormulaMinus() {
		super();
	}
	
	public FormulaMinus(AbstractFormula child1, AbstractFormula child2) {
		super(FormulaDescription.OP_MINUS, child1, child2);
	}
	
	@Transient
	@Override
	public Integer getIntegerValueImpl() {
		AbstractFormula leftParameter = getParameters().get(0);
		AbstractFormula rightParameter = getParameters().get(1);
		
		return Double.valueOf((leftParameter.getReturnType().equals(FormulaReturnType.INTEGER)
				? leftParameter.getIntegerValue().doubleValue() : leftParameter.getNumericValue())
				- (rightParameter.getReturnType().equals(FormulaReturnType.INTEGER) ? rightParameter.getIntegerValue().doubleValue()
						: rightParameter.getNumericValue()))
				.intValue();
	}
	
	@Transient
	@Override
	public Double getNumericValueImpl() {
		AbstractFormula leftParameter = getParameters().get(0);
		AbstractFormula rightParameter = getParameters().get(1);
		
		return Double.valueOf((leftParameter.getReturnType().equals(FormulaReturnType.INTEGER)
				? leftParameter.getIntegerValue().doubleValue() : leftParameter.getNumericValue())
				- (rightParameter.getReturnType().equals(FormulaReturnType.INTEGER) ? rightParameter.getIntegerValue().doubleValue()
						: rightParameter.getNumericValue()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	protected Set<FormulaReturnType> getAuthorizedParametersTypes() {
		return new HashSet<>(Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.NUMERIC));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	protected Map<FormulaReturnType, Set<FormulaReturnType>> getParametersCombinationMatrix() {
		Map<FormulaReturnType, Set<FormulaReturnType>> combinations = new HashMap<>();
		combinations.put(FormulaReturnType.INTEGER,
				new HashSet<>(Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.NUMERIC)));
		combinations.put(FormulaReturnType.NUMERIC,
				new HashSet<>(Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.NUMERIC)));
				
		return combinations;
	}
	
	@Override
	public String operationAsText() {
		return "-";
	}
	
}
