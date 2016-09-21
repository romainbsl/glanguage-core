package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import java.time.Duration;
import java.time.Period;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_MONTHS)
public class FormulaDurationMonths extends DurationFormula {
	
	public FormulaDurationMonths() {
		super();
	}
	
	public FormulaDurationMonths(FormulaDescription description, List<AbstractFormula> parameters) {
		super(description, parameters);
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue() {
		switch (getParameters().get(0).getReturnType()) {
			case DATE:
				return getParameters().get(0).getDateValue().getMonthValue();
			case DURATION:
				return Math.toIntExact(getParameters().get(0).getDurationValue().toDays()) / MONTH_AVG_DAYS_COUNT;
			default:
				throw new UnsupportedOperationException("Cannot invoke getIntegerValue() method on " + this.getClass().getName()
						+ " object with a parameter of type " + getParameters().get(0).getReturnType());
		}
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Duration getDurationValue() {
		return Duration.ofDays(Period.ofMonths(getIntegerValue()).getMonths() * MONTH_AVG_DAYS_COUNT);
	}
	
	@Override
	public String operationAsText() {
		return "months";
	}
	
}
