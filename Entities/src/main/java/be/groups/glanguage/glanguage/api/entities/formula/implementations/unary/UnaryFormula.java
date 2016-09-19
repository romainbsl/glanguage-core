package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import java.util.ArrayList;

import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;

@Entity
public abstract class UnaryFormula extends AbstractNonTerminalFormula {

	protected UnaryFormula() {
		super();
	}

	public UnaryFormula(AbstractFormula child) {
		super();

		if (child == null) {
			throw new IllegalArgumentException("child must be non-null");
		}
		this.parameters = new ArrayList<>();
		parameters.add(child);
	}

	@Override
	public String asText() {
		return operationAsText() + " " + getParameters().get(0).asText();
	}

	public abstract String operationAsText();

}
