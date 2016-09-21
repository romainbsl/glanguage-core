package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.OP_MULTIPLY)
public class FormulaMultiply extends BinaryFormula {
	
	protected FormulaMultiply() {
		super();
	}
	
	public FormulaMultiply(FormulaDescription description, AbstractFormula child1, AbstractFormula child2) {
		super(description, child1, child2);
	}

	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue() {
		return getNumericValue().intValue();
	}

	@JsonIgnore
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
