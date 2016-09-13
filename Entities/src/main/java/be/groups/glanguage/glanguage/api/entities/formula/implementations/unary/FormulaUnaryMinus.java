package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

@Entity
@DiscriminatorValue(FormulaDescription.Values.OP_UNARY_MINUS)
public class FormulaUnaryMinus extends UnaryFormula {

	protected FormulaUnaryMinus() {
		super();
	}

	public FormulaUnaryMinus(AbstractFormula child) {
		super(FormulaDescription.OP_UNARY_MINUS, child);
	}

	@Override
	public Integer getIntegerValueImpl() {
		return -getParameters().get(0).getIntegerValue();
	}

	@Override
	public Double getNumericValueImpl() {
		return -getParameters().get(0).getNumericValue();
	}

	@Override
	protected FormulaReturnType computeReturnType() {
		return getParameters().get(0).getReturnType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Set<FormulaReturnType> getAuthorizedParametersTypes() {
		return new HashSet<>(Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.NUMERIC));
	}

	@Override
	public String operationAsText() {
		return "-";
	}

}
