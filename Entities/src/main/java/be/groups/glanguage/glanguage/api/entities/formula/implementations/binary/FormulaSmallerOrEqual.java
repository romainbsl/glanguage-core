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
 * Formula representing a logical smaller or equal operation<br>
 * This Formula has exactly two (2) parameters
 * 
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaDescription.Values.OP_SMALLER_OR_EQUAL)
public class FormulaSmallerOrEqual extends BinaryFormula {

	protected FormulaSmallerOrEqual() {
		super();
	}

	public FormulaSmallerOrEqual(AbstractFormula child1, AbstractFormula child2) {
		super(FormulaDescription.OP_SMALLER_OR_EQUAL, child1, child2);
	}

	@Transient
	@Override
	public Boolean getBooleanValueImpl() {
		switch (parameters.get(0).getReturnType()) {
		case DATE:
			return !getParameters().get(0).getDateValue().isAfter(getParameters().get(1).getDateValue());
		case INTEGER:
			if (parameters.get(1).getReturnType().equals(FormulaReturnType.INTEGER)) {
				return getParameters().get(0).getIntegerValue() <= getParameters().get(1).getIntegerValue();
			} else { // TODO use numeric each time?
				return getParameters().get(0).getNumericValue() <= getParameters().get(1).getNumericValue();
			}
		case NUMERIC:
			return getParameters().get(0).getNumericValue() <= getParameters().get(1).getNumericValue();
		case STRING:
			return getParameters().get(0).getStringValue().compareTo(getParameters().get(1).getStringValue()) <= 0;
		default:
			throw new IllegalArgumentException(
					"Cannot compare unknown values in " + this.getClass().getName() + " object");
		}
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
		return new HashSet<>(Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.NUMERIC,
				FormulaReturnType.STRING, FormulaReturnType.DATE));
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
		combinations.put(FormulaReturnType.STRING, new HashSet<>(Arrays.asList(FormulaReturnType.STRING)));
		combinations.put(FormulaReturnType.DATE, new HashSet<>(Arrays.asList(FormulaReturnType.DATE)));

		return combinations;
	}

	@Override
	public String operationAsText() {
		return "<=";
	}

}
