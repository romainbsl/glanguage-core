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
 * Formula representing a mathematical addition<br>
 * This formula has exactly two (2) parameters<br>
 * This formula adds its second parameter value to its first parameter value and
 * return the value<br>
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
@DiscriminatorValue(FormulaDescription.Values.OP_PLUS)
public class FormulaPlus extends BinaryFormula {

	protected FormulaPlus() {
		super();
	}

	public FormulaPlus(AbstractFormula child1, AbstractFormula child2) {
		super(FormulaDescription.OP_PLUS, child1, child2);
	}

	@Transient
	@Override
	public Integer getIntegerValueImpl() {
		return getParameters().get(0).getIntegerValue() + getParameters().get(1).getIntegerValue();
	}

	@Transient
	@Override
	public Double getNumericValueImpl() {
		return getParameters().get(0).getNumericValue() + getParameters().get(1).getNumericValue();
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
		return "+";
	}

}
