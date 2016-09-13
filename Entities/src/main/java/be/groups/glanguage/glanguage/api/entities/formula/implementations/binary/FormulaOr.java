/**
 * 
 */
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
 * Formula representing a logical or operation<br>
 * This Formula has exactly two (2) parameters<br>
 * The type of the parameters must be boolean
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaDescription.Values.OP_OR)
public class FormulaOr extends BinaryFormula {

	public FormulaOr() {
		super();
	}

	public FormulaOr(AbstractFormula child1, AbstractFormula child2) {
		super(child1, child2);
	}

	@Transient
	@Override
	public Boolean getBooleanValueImpl() {
		return getParameters().get(0).getBooleanValue() || getParameters().get(1).getBooleanValue();
	}

	@Override
	protected FormulaReturnType computeReturnType() {
		return getDescription().getReturnType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	protected Set<FormulaReturnType> getAuthorizedParametersTypes() {
		return new HashSet<>(Arrays.asList(FormulaReturnType.BOOLEAN));
	}

	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	protected Map<FormulaReturnType, Set<FormulaReturnType>> getParametersCombinationMatrix() {
		Map<FormulaReturnType, Set<FormulaReturnType>> combinations = new HashMap<>();
		combinations.put(FormulaReturnType.BOOLEAN, new HashSet<>(Arrays.asList(FormulaReturnType.BOOLEAN)));

		return combinations;
	}

	@Override
	public String operationAsText() {
		return "or";
	}

}
