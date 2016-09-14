package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

public class FormulaAnomaly extends AbstractNonTerminalFormula {
	
	public FormulaAnomaly() {
		super();
	}
	
	public FormulaAnomaly(LinkedList<AbstractFormula> parameters) {
		super(FormulaDescription.F_PUT_TEXT);
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
	
	@Override
	public Integer getIntegerValueImpl() {
		reportAnomaly();
		return 0;
	}
	
	@Override
	public Double getNumericValueImpl() {
		reportAnomaly();
		return 0.0;
	}
	
	@Override
	public String getStringValueImpl() {
		reportAnomaly();
		return "";
	}
	
	@Override
	public LocalDate getDateValueImpl() {
		reportAnomaly();
		return LocalDate.MIN;
	}
	
	@Override
	public Duration getDurationValueImpl() {
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
	
	@Override
	protected FormulaReturnType computeReturnType() {
		return getDescription().getReturnType();
	}
	
	@Override
	protected Set<FormulaReturnType> getAuthorizedParametersTypes() {
		return new HashSet<>(Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.STRING));
	}
	
	@Override
	protected boolean isParametersCombinationAuthorized() {
		return (getParameters().get(0).getReturnType().equals(FormulaReturnType.INTEGER)
				|| getParameters().get(0).getReturnType().equals(FormulaReturnType.STRING))
				&& (getParameters().size() == 1 || getParameters().get(1).getReturnType().equals(FormulaReturnType.STRING));
	}
	
}
