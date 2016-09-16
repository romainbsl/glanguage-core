package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import java.time.Duration;
import java.time.Period;
import java.util.LinkedList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;

@Entity
@DiscriminatorValue(value = FormulaDescription.Values.F_MONTHS)
public class FormulaDurationMonths extends DurationFormula {
	
	public FormulaDurationMonths() {
		super();
	}
	
	public FormulaDurationMonths(LinkedList<AbstractFormula> parameters) {
		super(FormulaDescription.F_MONTHS, parameters);
	}
	
	@Transient
	@Override
	public Integer getIntegerValueImpl() {
		switch (getParameters().get(0).getReturnType()) {
			case DATE:
				getParameters().get(0).getDateValue().getMonthValue();
			case DURATION:
				return Period.ofDays(Math.toIntExact(getParameters().get(0).getDurationValue().toDays())).getMonths();
			default:
				throw new UnsupportedOperationException(
						"Cannot invoke getIntegerValue() method on " + this.getClass().getName() + " object with a parameter of type "
								+ getParameters().get(0).getReturnType());
		}
	}
	
	@Transient
	@Override
	public Duration getDurationValueImpl() {
		return Duration.ofDays(Period.ofMonths(getParameters().get(0).getIntegerValue()).getDays());
	}
	
	@Override
	public String operationAsText() {
		return "months";
	}
	
}
