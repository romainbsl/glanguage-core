package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.OP_MULTIPLY)
public class FormulaMultiply extends BinaryFormula {
	
	protected FormulaMultiply() {
		super();
	}
	
	public FormulaMultiply(AbstractFormula child1, AbstractFormula child2) {
		super( child1, child2);
	}

	@Transient
	@Override
	public Integer getIntegerValue() {
		return getNumericValue().intValue();
	}

	@Transient
	@Override
	public Double getNumericValue() {
		return getParameters().get(0).getNumericValue() * getParameters().get(1).getNumericValue();
	}
	
	@Override
	public String operationAsText() {
		return "*";
	}
	
}
