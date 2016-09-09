package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import java.time.LocalDate;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;

public class FormulaExist extends UnaryFormula {

	public FormulaExist() {
		super();
	}

	public FormulaExist(AbstractFormula child) {
		super(child);
    }    

	@Override
	public Integer getIntegerValue() {
		throw new IllegalAccessError("Cannot invoke getIntegerValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public Double getNumericValue() {
		throw new IllegalAccessError("Cannot invoke getNumericValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public String getStringValue() {
		throw new IllegalAccessError("Cannot invoke getStringValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public Boolean getBooleanValue() {
		return getParameters().get(0).getValue() != null;
	}

	@Override
	public LocalDate getDateValue() {
		throw new IllegalAccessError("Cannot invoke getDateValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public String operationAsText() {
		return "?";
	}
}
