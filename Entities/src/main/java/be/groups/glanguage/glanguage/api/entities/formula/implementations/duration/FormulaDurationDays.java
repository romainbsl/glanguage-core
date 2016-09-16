package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import java.time.Duration;
import java.util.LinkedList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;

@Entity
@DiscriminatorValue(value = FormulaDescription.Values.F_DAYS)
public class FormulaDurationDays extends DurationFormula {
	
	public FormulaDurationDays() {
		super();
	}
	
	public FormulaDurationDays(LinkedList<AbstractFormula> parameters) {
		super(FormulaDescription.F_DAYS, parameters);
	}
	
	@Transient
	@Override
	public Integer getIntegerValueImpl() {
		switch (getParameters().get(0).getReturnType()) {
			case DATE:
				getParameters().get(0).getDateValue().getDayOfMonth();
			case DURATION:
				return Math.toIntExact(getParameters().get(0).getDurationValue().toDays());
			default:
				throw new UnsupportedOperationException(
						"Cannot invoke getIntegerValue() method on " + this.getClass().getName() + " object with a parameter of type "
								+ getParameters().get(0).getReturnType());
		}
	}
	
	@Transient
	@Override
	public Duration getDurationValueImpl() {
		return Duration.ofDays(getParameters().get(0).getIntegerValue());
	}
	
	@Override
	public String operationAsText() {
		return "days";
	}
	
}
