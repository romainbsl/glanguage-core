package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import java.time.LocalDate;
import java.util.ArrayList;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

public class FormulaUnaryMinus extends AbstractNonTerminalFormula{

    public FormulaUnaryMinus(AbstractFormula child) {
    	if (child == null) {
        	throw new IllegalArgumentException("Child must be non-null");
        }
        this.parameters = new ArrayList<>();
		parameters.add(child);
    }

	@Override
	public Integer getIntegerValue() {
		return -getParameters().get(0).getIntegerValue();
	}

	@Override
	public Double getNumericValue() {
		return -getParameters().get(0).getNumericValue();
	}

	@Override
	public String getStringValue() {
		throw new IllegalAccessError("Cannot invoke getStringValue() method on FormulaUnaryMinus object");
	}

	@Override
	public Boolean getBooleanValue() {
		throw new IllegalAccessError("Cannot invoke getBooleanValue() method on FormulaUnaryMinus object");
	}

	@Override
	public LocalDate getDateValue() {
		throw new IllegalAccessError("Cannot invoke getDateValue() method on FormulaUnaryMinus object");
	}

	@Override
	public String asText() {
		return "-" + getParameters().get(0).asText();
	}

	@Override
	protected FormulaReturnType computeReturnType() {
		return getParameters().get(0).getReturnType();
	}

}