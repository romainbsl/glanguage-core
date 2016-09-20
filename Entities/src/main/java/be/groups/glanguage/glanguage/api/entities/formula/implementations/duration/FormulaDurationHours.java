package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_HOURS)
public class FormulaDurationHours extends DurationFormula {
	
	public static final Set<FormulaReturnType> authorizedParametersTypes =
			new HashSet<>(Arrays.asList(FormulaReturnType.DURATION, FormulaReturnType.INTEGER));
			
	public FormulaDurationHours() {
		super();
	}
	
	public FormulaDurationHours(LinkedList<AbstractFormula> parameters) {
		super( parameters);
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue() {
		return Math.toIntExact(getParameters().get(0).getDurationValue().toHours());
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Duration getDurationValue() {
		return Duration.ofHours(getParameters().get(0).getIntegerValue());
	}
	
	@Override
	public String operationAsText() {
		return "hours";
	}
	
}
