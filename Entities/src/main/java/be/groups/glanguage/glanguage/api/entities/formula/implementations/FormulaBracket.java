package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_BRACKETS)
public class FormulaBracket extends AbstractFormula {
	
	protected FormulaBracket() {
		super();
	}
	
	public FormulaBracket(AbstractFormula child) {
		super();		
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

	@Transient
	@Override
	public Integer getIntegerValue() {
		return getParameters().get(0).getIntegerValue();
	}

	@Transient
	@Override
	public Double getNumericValue() {
		return getParameters().get(0).getNumericValue();
	}

	@Transient
	@Override
	public String getStringValue() {
		return getParameters().get(0).getStringValue();
	}

	@Transient
	@Override
	public Boolean getBooleanValue() {
		return getParameters().get(0).getBooleanValue();
	}

	@Transient
	@Override
	public LocalDate getDateValue() {
		return getParameters().get(0).getDateValue();
	}

	@Transient
	@Override
	public Duration getDurationValue() {
		return getParameters().get(0).getDurationValue();
	}	

	@Transient
	@Override
	public String asText() {
		return "(" + getParameters().get(0).asText() + ")";
	}

}
