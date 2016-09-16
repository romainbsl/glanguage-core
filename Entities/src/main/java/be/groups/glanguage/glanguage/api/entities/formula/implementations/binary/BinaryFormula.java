package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import java.util.ArrayList;

import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;

@Entity
public abstract class BinaryFormula extends AbstractNonTerminalFormula {
	
	protected BinaryFormula() {
		super();
	}
	
	public BinaryFormula(AbstractFormula child1, AbstractFormula child2) {
		super();
		
		if (child1 == null) {
			throw new IllegalArgumentException("child1 must be non-null");
		}
		if (child2 == null) {
			throw new IllegalArgumentException("child2 must be non-null");
		}
		this.parameters = new ArrayList<>();
		parameters.add(child1);
		parameters.add(child2);
	}
	
	@Override
	public String asText() {
		return getParameters().get(0).asText() + " " + operationAsText() + " " + getParameters().get(1).asText();
	}
	
	public abstract String operationAsText();
	
}
