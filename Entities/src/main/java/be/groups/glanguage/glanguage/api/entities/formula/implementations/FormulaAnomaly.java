package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

public class FormulaAnomaly extends AbstractNonTerminalFormula {
	
	public FormulaAnomaly() {
		super();
	}
	
	public FormulaAnomaly(LinkedList<AbstractFormula> parameters) {
		super();
		if (parameters == null) {
			throw new IllegalArgumentException("parameters must be non-null");
		}
		if (parameters.size() > 2) {
			throw new IllegalArgumentException("there schould be 1 or 2 parameters but there are " + parameters.size());
		}
		if (!(parameters.get(0).getReturnType().equals(FormulaReturnType.INTEGER)
				|| parameters.get(0).getReturnType().equals(FormulaReturnType.STRING))) {
			throw new IllegalArgumentException("first parameter should be of type INTEGER or STRING");
		}
		if (parameters.size() > 1 && !parameters.get(1).getReturnType().equals(FormulaReturnType.STRING)) {
			throw new IllegalArgumentException("second parameter should be of type STRING");
		}
		this.parameters = new ArrayList<>();
		this.parameters.addAll(parameters);
	}

	@Transient
	@Override
	public Integer getIntegerValue() {
		reportAnomaly();
		return 0;
	}

	@Transient
	@Override
	public Double getNumericValue() {
		reportAnomaly();
		return 0.0;
	}

	@Transient
	@Override
	public String getStringValue() {
		reportAnomaly();
		return "";
	}

	@Transient
	@Override
	public LocalDate getDateValue() {
		reportAnomaly();
		return LocalDate.MIN;
	}

	@Transient
	@Override
	public Duration getDurationValue() {
		reportAnomaly();
		return Duration.ZERO;
	}
	
	/**
	 * Report anomaly
	 */
	private void reportAnomaly() {
		// TODO
	}
	
	@Override
	public String asText() {
		StringBuilder sb = new StringBuilder();
		sb.append("putText(");
		sb.append(getParameters().get(0).asText());
		if (getParameters().size() > 1) {
			sb.append(" ; ");
			sb.append(getParameters().get(1).asText());
		}
		sb.append(")");
		return sb.toString();
	}
	
}
