package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

@Entity
@DiscriminatorValue(value = FormulaDescription.Values.F_HOURS)
public class FormulaDurationHours extends DurationFormula {
	
	public static final Set<FormulaReturnType> authorizedParametersTypes =
			new HashSet<>(Arrays.asList(FormulaReturnType.DURATION, FormulaReturnType.INTEGER));
			
	public FormulaDurationHours() {
		super();
	}
	
	public FormulaDurationHours(LinkedList<AbstractFormula> parameters) {
		super(FormulaDescription.F_HOURS, parameters);
	}
	
	@Transient
	@Override
	public Integer getIntegerValueImpl() {
		return Math.toIntExact(getParameters().get(0).getDurationValue().toHours());
	}
	
	@Transient
	@Override
	public Duration getDurationValueImpl() {
		return Duration.ofHours(getParameters().get(0).getIntegerValue());
	}
	
	@Transient
	@Override
	protected Set<FormulaReturnType> getAuthorizedParametersTypes() {
		return authorizedParametersTypes;
	}
	
	@Override
	public String operationAsText() {
		return "hours";
	}
	
}
