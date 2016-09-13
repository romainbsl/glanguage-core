package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import java.util.ArrayList;

import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.rule.Rounder;
import be.groups.glanguage.glanguage.api.entities.rule.RoundingType;

public abstract class RoundingFormula extends AbstractNonTerminalFormula {

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
	public Integer getIntegerValueImpl() {
		switch (getParameters().get(0).getReturnType()) {
		case INTEGER:
			return Rounder.round(getParameters().get(0).getIntegerValue(), getRoundingType(),
					getParameters().get(1).getNumericValue()).intValue();
		case NUMERIC:
			return Rounder.round(getParameters().get(0).getNumericValue(), getRoundingType(),
					getParameters().get(1).getNumericValue()).intValue();
		default:
			throw new IllegalArgumentException("Parameter to be rounded must be of type INTEGER or NUMERIC");
		}
	}

	@Override
	public Double getNumericValueImpl() {
		switch (getParameters().get(0).getReturnType()) {
		case INTEGER:
			return Rounder.round(getParameters().get(0).getIntegerValue(), getRoundingType(),
					getParameters().get(1).getNumericValue());
		case NUMERIC:
			return Rounder.round(getParameters().get(0).getNumericValue(), getRoundingType(),
					getParameters().get(1).getNumericValue());
		default:
			throw new IllegalArgumentException("Parameter to be rounded must be of type INTEGER or NUMERIC");
		}
	}

	public abstract RoundingType getRoundingType();

	public abstract AbstractFormula getDefaultPrecision();

	private void setPrecision(AbstractFormula precision) {
		this.parameters.set(1, precision);
	}

	@Transient
	@Override
	public boolean isParametersCombinationAuthorized() {
		return true;
	}

	@Override
	public String asText() {
		return operationAsText() + "( " + getParameters().get(0).asText() + "; " + getParameters().get(1).asText()
				+ " )";
	}

	public abstract String operationAsText();

}
