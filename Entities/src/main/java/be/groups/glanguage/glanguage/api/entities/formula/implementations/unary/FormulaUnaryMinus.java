package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;

@Entity
@DiscriminatorValue(FormulaDescription.Values.OP_UNARY_MINUS)
public class FormulaUnaryMinus extends UnaryFormula {
	
	public FormulaUnaryMinus(AbstractFormula child) {
		super(child);
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
	public String operationAsText() {
		return "-";
	}
	
}
