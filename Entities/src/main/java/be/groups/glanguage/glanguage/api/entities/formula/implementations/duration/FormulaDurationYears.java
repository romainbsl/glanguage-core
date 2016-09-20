package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import java.time.Duration;
import java.time.Period;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_YEARS)
public class FormulaDurationYears extends DurationFormula {
	
	public FormulaDurationYears() {
		super();
	}
	
	public FormulaDurationYears(List<AbstractFormula> parameters) {
		super(parameters);
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue() {
		switch (getParameters().get(0).getReturnType()) {
			case DATE:
				return getParameters().get(0).getDateValue().getYear();
			case DURATION:
				return Math.toIntExact(getParameters().get(0).getDurationValue().toDays()) / YEAR_AVG_DAYS_COUNT;
			default:
				throw new UnsupportedOperationException("Cannot invoke getIntegerValue() method on " + this.getClass().getName()
						+ " object with a parameter of type " + getParameters().get(0).getReturnType());
		}
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Duration getDurationValue() {
		return Duration.ofDays(Period.ofYears(getIntegerValue()).getYears() * YEAR_AVG_DAYS_COUNT);
	}
	
	@Override
	public String operationAsText() {
		return "years";
	}
	
}
