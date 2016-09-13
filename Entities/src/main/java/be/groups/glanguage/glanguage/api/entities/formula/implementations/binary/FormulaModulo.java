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
@DiscriminatorValue(FormulaDescription.Values.OP_MODULO)
public class FormulaModulo extends BinaryFormula {

	public FormulaModulo() {
		super();
	}

	public FormulaModulo(AbstractFormula child1, AbstractFormula child2) {
		super(child1, child2);
	}

	@Override
	protected FormulaReturnType computeReturnType() {
		if (getParameters().get(1).getReturnType().equals(FormulaReturnType.NUMERIC)) {
			return FormulaReturnType.NUMERIC;
		} else {
			return getParameters().get(0).getReturnType();
		}
	}

	@Override
	public Integer getIntegerValueImpl() {
		return getParameters().get(0).getIntegerValue() % getParameters().get(1).getIntegerValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	protected Set<FormulaReturnType> getAuthorizedParametersTypes() {
		return new HashSet<>(Arrays.asList(FormulaReturnType.INTEGER));
	}

	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	protected Map<FormulaReturnType, Set<FormulaReturnType>> getParametersCombinationMatrix() {
		Map<FormulaReturnType, Set<FormulaReturnType>> combinations = new HashMap<>();
		combinations.put(FormulaReturnType.INTEGER, new HashSet<>(Arrays.asList(FormulaReturnType.INTEGER)));

		return combinations;
	}

	@Override
	public String operationAsText() {
		return "\\";
	}

}
