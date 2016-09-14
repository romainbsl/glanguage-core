package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

@Entity
@DiscriminatorValue(FormulaDescription.Values.F_BRACKETS)
public class FormulaBracket extends AbstractFormula {
	
	protected FormulaBracket() {
		super();
	}
	
	public FormulaBracket(AbstractFormula child) {
		super(FormulaDescription.F_BRACKETS);		
		if (child == null) {
			throw new IllegalArgumentException("Child must be non-null");
		}
		this.parameters = new ArrayList<>();
		this.parameters.add(child);
	}
	
	@Override
	public boolean isTerminal() {
		return false;
	}
	
	@Override
	public FormulaReturnType getReturnType() {
		return getParameters().get(0).getReturnType();
	}
	
	@Override
	public Integer getIntegerValue() {
		return getParameters().get(0).getIntegerValue();
	}
	
	@Override
	public Double getNumericValue() {
		return getParameters().get(0).getNumericValue();
	}
	
	@Override
	public String getStringValue() {
		return getParameters().get(0).getStringValue();
	}
	
	@Override
	public Boolean getBooleanValue() {
		return getParameters().get(0).getBooleanValue();
	}
	
	@Override
	public LocalDate getDateValue() {
		return getParameters().get(0).getDateValue();
	}

	@Override
	public Duration getDurationValue() {
		return getParameters().get(0).getDurationValue();
	}	
	
	@Override
	public String asText() {
		return "(" + getParameters().get(0).asText() + ")";
	}

}
