package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.util.List;

@Entity
@DiscriminatorValue(FormulaType.Values.F_HOURS)
public class FormulaDurationHours extends DurationFormula {
	
	public FormulaDurationHours() {
		super();
	}
	
	public FormulaDurationHours(FormulaDescription description, List<AbstractFormula> parameters) {
		super(description, parameters);
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue(Evaluator evaluator) {
		if (getParameters().get(0).getReturnType(evaluator).equals(FormulaReturnType.DURATION)) {
			return Math.toIntExact(getParameters().get(0).getDurationValue(evaluator).toHours());
		} else {
			return getParameters().get(0).getIntegerValue(evaluator);
		}
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Duration getDurationValue(Evaluator evaluator) {
		return Duration.ofHours(getIntegerValue(evaluator));
	}
	
	@Override
	public String operationAsText() {
		return "hours";
	}
	
}
