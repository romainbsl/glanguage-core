package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;

@Entity
@DiscriminatorValue(FormulaDescription.Values.OP_MULTIPLY)
public class FormulaMultiply extends BinaryFormula {

    public FormulaMultiply() {
		super();
	}

	public FormulaMultiply(AbstractFormula child1, AbstractFormula child2) {
		super(child1, child2);
    }

	@Override
	public Integer getIntegerValue() {
		return getParameters().get(0).getIntegerValue() * getParameters().get(1).getIntegerValue();
	}

	@Override
	public Double getNumericValue() {
		return getParameters().get(0).getNumericValue() * getParameters().get(1).getNumericValue();
	}

	@Override
	public String getStringValue() {
		throw new IllegalAccessError("Cannot invoke getStringValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public Boolean getBooleanValue() {
		throw new IllegalAccessError("Cannot invoke getBooleanValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public LocalDate getDateValue() {
		throw new IllegalAccessError("Cannot invoke getDateValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public String operationAsText() {
		return "*";
	}

}
