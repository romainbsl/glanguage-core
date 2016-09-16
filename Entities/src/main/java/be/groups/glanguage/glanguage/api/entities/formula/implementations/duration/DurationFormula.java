package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

@Entity
public abstract class DurationFormula extends AbstractNonTerminalFormula {
	
	private static final Set<FormulaReturnType> authorizedParametersTypes =
			new HashSet<>(Arrays.asList(FormulaReturnType.DATE, FormulaReturnType.DURATION, FormulaReturnType.INTEGER));
			
	public DurationFormula() {
		super();
	}
	
	public DurationFormula(FormulaDescription description, LinkedList<AbstractFormula> parameters) {
		super(description);
		if (parameters == null) {
			throw new IllegalArgumentException("parameters must be non-null");
		}
		if (parameters.size() != 1) {
			throw new IllegalArgumentException("there should be 1 parameter but there are " + parameters.size());
		}
		if (!areParametersAuthorized()) {
			throw new IllegalStateException("Cannot use this formula for parameters for which type is not in "
					+ getAuthorizedParametersTypes());
		}
		this.parameters = new ArrayList<>();
		this.parameters.addAll(parameters);
	}
	
	@Override
	protected FormulaReturnType computeReturnType() {
		switch (getParameters().get(0).getReturnType()) {
			case INTEGER:
				return FormulaReturnType.DURATION;
			case DURATION:
				// fall through
			case DATE:
				return FormulaReturnType.INTEGER;
			default:
				throw new IllegalArgumentException("parameter must be of type DATE, DURATION or INTEGER");
		}
	}
	
	@Transient
	@Override
	protected Set<FormulaReturnType> getAuthorizedParametersTypes() {
		return authorizedParametersTypes;
	}
	
	@Transient
	@Override
	protected boolean isParametersCombinationAuthorized() {
		return areParametersAuthorized();
	}
	
	@Override
	public String asText() {
		return operationAsText() + "(" + getParameters().get(0).asText() + ")";
	}
	
	public abstract String operationAsText();
	
}
