package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import java.time.Duration;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_MINUTES)
public class FormulaDurationMinutes extends DurationFormula {
	
	public FormulaDurationMinutes() {
		super();
	}
	
	public FormulaDurationMinutes(List<AbstractFormula> parameters) {
		super(parameters);
	}
	
	@Transient
	@Override
	public Integer getIntegerValue() {
		return Math.toIntExact(getParameters().get(0).getDurationValue().toMinutes());
	}
	
	@Transient
	@Override
	public Duration getDurationValue() {
		return Duration.ofMinutes(getIntegerValue());
	}
	
	@Override
	public String operationAsText() {
		return "minutes";
	}
	
}
