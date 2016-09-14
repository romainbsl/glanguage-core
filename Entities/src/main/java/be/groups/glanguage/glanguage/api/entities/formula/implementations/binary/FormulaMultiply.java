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

@Entity
@DiscriminatorValue(FormulaDescription.Values.OP_MULTIPLY)
public class FormulaMultiply extends BinaryFormula {
	
	protected FormulaMultiply() {
		super();
	}
	
	public FormulaMultiply(AbstractFormula child1, AbstractFormula child2) {
		super(FormulaDescription.OP_MULTIPLY, child1, child2);
	}
	
	@Override
	public Integer getIntegerValueImpl() {
		AbstractFormula leftParameter = getParameters().get(0);
		AbstractFormula rightParameter = getParameters().get(1);
		
		return Double.valueOf((leftParameter.getReturnType().equals(FormulaReturnType.INTEGER)
				? leftParameter.getIntegerValue().doubleValue() : leftParameter.getNumericValue())
				* (rightParameter.getReturnType().equals(FormulaReturnType.INTEGER) ? rightParameter.getIntegerValue().doubleValue()
						: rightParameter.getNumericValue()))
				.intValue();
	}
	
	@Override
	public Double getNumericValueImpl() {
		AbstractFormula leftParameter = getParameters().get(0);
		AbstractFormula rightParameter = getParameters().get(1);
		
		return Double.valueOf((leftParameter.getReturnType().equals(FormulaReturnType.INTEGER)
				? leftParameter.getIntegerValue().doubleValue() : leftParameter.getNumericValue())
				* (rightParameter.getReturnType().equals(FormulaReturnType.INTEGER) ? rightParameter.getIntegerValue().doubleValue()
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
		return "*";
	}
	
}
