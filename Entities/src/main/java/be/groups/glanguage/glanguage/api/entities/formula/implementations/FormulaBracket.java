package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@DiscriminatorValue(FormulaType.Values.F_BRACKETS)
public class FormulaBracket extends AbstractFormula {
	
	protected FormulaBracket() {
		super();
	}
	
	public FormulaBracket(FormulaDescription description, AbstractFormula child) {
		super(description);		
		if (child == null) {
			throw new IllegalArgumentException("Child must be non-null");
		}
		this.parameters = new ArrayList<>();
		this.parameters.add(child);
	}

	@Transient
	@Override
	public boolean isTerminal() {
		return false;
	}

	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue(Evaluator evaluator) {
		return getParameters().get(0).getIntegerValue(evaluator);
	}

	@JsonIgnore
	@Transient
	@Override
	public Double getNumericValue(Evaluator evaluator) {
		return getParameters().get(0).getNumericValue(evaluator);
	}

	@JsonIgnore
	@Transient
	@Override
	public String getStringValue(Evaluator evaluator) {
		return getParameters().get(0).getStringValue(evaluator);
	}

	@JsonIgnore
	@Transient
	@Override
	public Boolean getBooleanValue(Evaluator evaluator) {
		return getParameters().get(0).getBooleanValue(evaluator);
	}

	@JsonIgnore
	@Transient
	@Override
	public LocalDate getDateValue(Evaluator evaluator) {
		return getParameters().get(0).getDateValue(evaluator);
	}

	@JsonIgnore
	@Transient
	@Override
	public Duration getDurationValue(Evaluator evaluator) {
		return getParameters().get(0).getDurationValue(evaluator);
	}	

	@Transient
	@Override
	public String asText() {
		return "(" + getParameters().get(0).asText() + ")";
	}

}
