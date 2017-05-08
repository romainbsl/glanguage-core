package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.rule.Rounder;
import be.groups.glanguage.glanguage.api.entities.rule.RoundingType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class RoundingFormula extends AbstractNonTerminalFormula {
	
	protected RoundingFormula() {
		super();
	}
	
	public RoundingFormula(FormulaDescription description, FormulaDescription precisionFormulaDescription, AbstractFormula parameter, AbstractFormula precision) {
		super(description, Arrays.asList(parameter, precision == null ? getDefaultPrecision(precisionFormulaDescription) : precision));
		
		this.parameters = new ArrayList<>();
		parameters.add(parameter);
		if (precision == null) {
			setPrecision(getDefaultPrecision(precisionFormulaDescription));
		} else {
			setPrecision(precision);
		}
	}

	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue(Evaluator evaluator) {
		switch (getParameters().get(0).getReturnType(evaluator)) {
			case INTEGER:
				return Rounder.round(getParameters().get(0).getIntegerValue(evaluator), getRoundingType(),
						getParameters().get(1).getNumericValue(evaluator)).intValue();
			case NUMERIC:
				return Rounder.round(getParameters().get(0).getNumericValue(evaluator), getRoundingType(),
						getParameters().get(1).getNumericValue(evaluator)).intValue();
			default:
				throw new IllegalArgumentException("Parameter to be rounded must be of type INTEGER or NUMERIC");
		}
	}

	@JsonIgnore
	@Transient
	@Override
	public Double getNumericValue(Evaluator evaluator) {
		switch (getParameters().get(0).getReturnType(evaluator)) {
			case INTEGER:
				return Rounder.round(getParameters().get(0).getIntegerValue(evaluator), getRoundingType(),
						getParameters().get(1).getNumericValue(evaluator));
			case NUMERIC:
				return Rounder.round(getParameters().get(0).getNumericValue(evaluator), getRoundingType(),
						getParameters().get(1).getNumericValue(evaluator));
			default:
				throw new IllegalArgumentException("Parameter to be rounded must be of type INTEGER or NUMERIC");
		}
	}
	
	public abstract RoundingType getRoundingType();
	
	public static AbstractFormula getDefaultPrecision(FormulaDescription description) {
		switch (description.getType()) {
			case F_BANKERS_ROUNDED:
				//fall through
			case F_TRUNC:
				return new FormulaTerminalInteger(description, "2");
			case F_CEIL:
				//fall through
			case F_FLOOR:
				//fall through
			case F_ROUNDED:
				return new FormulaTerminalInteger(description, "1");
			default:
				//TODO
				//Throw an error
				return null;
		}
	}
	
	private void setPrecision(AbstractFormula precision) {
		this.parameters.add(precision);
	}

	@Override
	public String asText() {
		return operationAsText() + "(" + getParameters().get(0).asText() + "; " + getParameters().get(1).asText()
				+ ")";
	}
	
	public abstract String operationAsText();
	
}
