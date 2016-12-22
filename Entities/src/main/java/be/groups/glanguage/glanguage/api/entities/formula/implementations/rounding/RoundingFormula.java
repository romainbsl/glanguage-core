package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.rule.Rounder;
import be.groups.glanguage.glanguage.api.entities.rule.RoundingType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Transient;
import java.util.ArrayList;

public abstract class RoundingFormula extends AbstractNonTerminalFormula {
	
	protected RoundingFormula() {
		super();
	}
	
	public RoundingFormula(FormulaDescription description, FormulaDescription precisionFormulaDescription, AbstractFormula parameter, AbstractFormula precision) {
		super(description);
		
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
	protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageEvaluationException {
		return getNumericValue().intValue();
	}

	@JsonIgnore
	@Transient
	@Override
	protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageEvaluationException {
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
	
	public abstract AbstractFormula getDefaultPrecision(FormulaDescription description);
	
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
