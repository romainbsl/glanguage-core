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
 * Formula representing a logical not equal operation<br>
 * This Formula has exactly two (2) parameters
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaDescription.Values.OP_DIFFERENCE)
public class FormulaDifference extends BinaryFormula {

	protected FormulaDifference() {
		super();
	}

	public FormulaDifference(AbstractFormula child1, AbstractFormula child2) {
		super(FormulaDescription.OP_DIFFERENCE, child1, child2);
	}

	@Transient
	@Override
	public Boolean getBooleanValueImpl() {
		return !getParameters().get(0).getValue().equals(getParameters().get(1).getValue());
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
		return new HashSet<>(Arrays.asList(FormulaReturnType.values()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	protected Map<FormulaReturnType, Set<FormulaReturnType>> getParametersCombinationMatrix() {
		Map<FormulaReturnType, Set<FormulaReturnType>> combinations = new HashMap<>();
		combinations.put(FormulaReturnType.INTEGER, new HashSet<>(Arrays.asList(FormulaReturnType.values())));
		combinations.put(FormulaReturnType.NUMERIC, new HashSet<>(Arrays.asList(FormulaReturnType.values())));
		combinations.put(FormulaReturnType.STRING, new HashSet<>(Arrays.asList(FormulaReturnType.values())));
		combinations.put(FormulaReturnType.BOOLEAN, new HashSet<>(Arrays.asList(FormulaReturnType.values())));
		combinations.put(FormulaReturnType.DATE, new HashSet<>(Arrays.asList(FormulaReturnType.values())));

		return combinations;
	}

	@Override
	public String operationAsText() {
		return "<>";
	}

}
