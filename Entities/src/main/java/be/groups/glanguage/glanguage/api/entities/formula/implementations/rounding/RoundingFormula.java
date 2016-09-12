package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import java.time.LocalDate;
import java.util.ArrayList;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.rule.Rounder;
import be.groups.glanguage.glanguage.api.entities.rule.RoundingType;

public abstract class RoundingFormula extends AbstractNonTerminalFormula{

	public RoundingFormula() {
		super();
	}

	public RoundingFormula(AbstractFormula parameter, AbstractFormula precision) {
		super();
		this.parameters = new ArrayList<>();
		parameters.add(parameter);
		if (precision != null) {
			setPrecision(getDefaultPrecision());
		} else {
			setPrecision(precision);
		}
	}

	@Override
	public Integer getIntegerValue() {
		switch (getParameters().get(0).getReturnType()) {
			case INTEGER:
				return Rounder.round(getParameters().get(0).getIntegerValue(), getRoundingType(), getParameters().get(1).getNumericValue()).intValue();
			case NUMERIC:
				return Rounder.round(getParameters().get(0).getNumericValue(), getRoundingType(), getParameters().get(1).getNumericValue()).intValue();
			default:
				throw new IllegalArgumentException("Parameter to be rounded must be of type INTEGER or NUMERIC");
		}		
	}

	@Override
	public Double getNumericValue() {
		switch (getParameters().get(0).getReturnType()) {
			case INTEGER:
				return Rounder.round(getParameters().get(0).getIntegerValue(), getRoundingType(), getParameters().get(1).getNumericValue());
			case NUMERIC:
				return Rounder.round(getParameters().get(0).getNumericValue(), getRoundingType(), getParameters().get(1).getNumericValue());
			default:
				throw new IllegalArgumentException("Parameter to be rounded must be of type INTEGER or NUMERIC");
		}	
	}

	@Override
	public String getStringValue() {
		throw new IllegalAccessError("Cannot invoke getBooleanValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public Boolean getBooleanValue() {
		throw new IllegalAccessError("Cannot invoke getBooleanValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public LocalDate getDateValue() {
		throw new IllegalAccessError("Cannot invoke getDateValue() method on " + this.getClass().getName() + " object");
	}
	
	public abstract RoundingType getRoundingType();
	
	public abstract AbstractFormula getDefaultPrecision();

	private void setPrecision(AbstractFormula precision) {
		this.parameters.set(1, precision);
	}

	@Override
	public String asText() {
		return operationAsText() + "( " + getParameters().get(0).asText() + "; " + getParameters().get(1).asText() + " )";
	}
	
	public abstract String operationAsText();

}
