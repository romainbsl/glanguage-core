package be.groups.glanguage.glanguage.api.entities.formula.implementations.duration;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;

@Entity
public abstract class DurationFormula extends AbstractNonTerminalFormula {
	
	public DurationFormula() {
		super();
	}
	
	public DurationFormula(List<AbstractFormula> parameters) {
		super();
		
		if (parameters == null) {
			throw new IllegalArgumentException("parameters must be non-null");
		}
		if (parameters.size() != 1) {
			throw new IllegalArgumentException("there should be 1 parameter but there are " + parameters.size());
		}
		this.parameters = new ArrayList<>();
		this.parameters.addAll(parameters);
	}
	
	@Override
	public String asText() {
		return operationAsText() + "(" + getParameters().get(0).asText() + ")";
	}
	
	public abstract String operationAsText();
	
}
