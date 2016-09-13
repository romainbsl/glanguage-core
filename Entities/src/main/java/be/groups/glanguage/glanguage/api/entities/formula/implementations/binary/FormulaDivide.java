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
@DiscriminatorValue(FormulaDescription.Values.OP_DIVIDE)
public class FormulaDivide extends BinaryFormula {

	public FormulaDivide() {
		super();
	}

	public FormulaDivide(AbstractFormula child1, AbstractFormula child2) {
		super(child1, child2);
	}

	@Override
	public Double getNumericValueImpl() {
		return getParameters().get(0).getNumericValue() / getParameters().get(1).getNumericValue();
	}

	@Override
	protected FormulaReturnType computeReturnType() {
		if (getParameters().get(1).getReturnType().equals(FormulaReturnType.NUMERIC)) {
			return FormulaReturnType.NUMERIC;
		} else {
			return getParameters().get(0).getReturnType();
		}
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
		return "/";
	}

}
