package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(FormulaType.Values.F_PUT_TEXT)
public class FormulaAnomaly extends AbstractNonTerminalFormula {
	
	public FormulaAnomaly() {
		super();
	}
	
	public FormulaAnomaly(FormulaDescription description, List<AbstractFormula> parameters) {
		super(description, parameters);
		if (parameters == null) {
			throw new IllegalArgumentException("parameters must be non-null");
		}
		if (parameters.size() > 2) {
			throw new IllegalArgumentException("there should be 1 or 2 parameters but there are " + parameters.size());
		}
		if (!(parameters.get(0).getReturnType(null).equals(FormulaReturnType.INTEGER)
				|| parameters.get(0).getReturnType(null).equals(FormulaReturnType.STRING))) {
			throw new IllegalArgumentException("first parameter should be of type INTEGER or STRING");
		}
		if (parameters.size() > 1 && !parameters.get(1).getReturnType(null).equals(FormulaReturnType.STRING)) {
			throw new IllegalArgumentException("second parameter should be of type STRING");
		}
		this.parameters = new ArrayList<>();
		this.parameters.addAll(parameters);
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue(Evaluator evaluator) {
		reportAnomaly();
		return 0;
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Double getNumericValue(Evaluator evaluator) {
		reportAnomaly();
		return 0.0;
	}
	
	@JsonIgnore
	@Transient
	@Override
	public String getStringValue(Evaluator evaluator) {
		reportAnomaly();
		return "";
	}
	
	@JsonIgnore
	@Transient
	@Override
	public LocalDate getDateValue(Evaluator evaluator) {
		reportAnomaly();
		return LocalDate.MIN;
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Duration getDurationValue(Evaluator evaluator) {
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
			sb.append("; ");
			sb.append(getParameters().get(1).asText());
		}
		sb.append(")");
		return sb.toString();
	}
	
}
