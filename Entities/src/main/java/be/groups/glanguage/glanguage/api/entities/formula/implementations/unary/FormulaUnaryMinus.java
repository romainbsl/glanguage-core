package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.OP_UNARY_MINUS)
public class FormulaUnaryMinus extends UnaryFormula {

	protected FormulaUnaryMinus() {
		super();
	}

	public FormulaUnaryMinus(AbstractFormula child) {
		super( child);
	}

	@Transient
	@Override
	public Integer getIntegerValue() {
		return -getParameters().get(0).getIntegerValue();
	}

	@Transient
	@Override
	public Double getNumericValue() {
		return -getParameters().get(0).getNumericValue();
	}

	@Override
	public String operationAsText() {
		return "-";
	}

}
