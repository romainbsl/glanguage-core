package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.util.List;

@Entity
@DiscriminatorValue(FormulaType.Values.F_MINUTES)
public class FormulaDurationMinutes extends DurationFormula {
	
	public FormulaDurationMinutes() {
		super();
	}
	
	public FormulaDurationMinutes(FormulaDescription description, List<AbstractFormula> parameters) {
		super(description, parameters);
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageEvaluationException {
		if (getParameters().get(0).getReturnType(evaluator).equals(FormulaReturnType.DURATION)) {
			return Math.toIntExact(getParameters().get(0).getDurationValue(evaluator).toMinutes());
		} else {
			return getParameters().get(0).getIntegerValue(evaluator);
		}
	}
	
	@JsonIgnore
	@Transient
	@Override
	protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageEvaluationException {
		return Duration.ofMinutes(getIntegerValue(evaluator));
	}
	
	@Override
	public String operationAsText() {
		return "minutes";
	}
	
}
