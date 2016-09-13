package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

@Entity
public abstract class UnaryFormula extends AbstractNonTerminalFormula {

	public UnaryFormula() {
		super();
	}

	public UnaryFormula(AbstractFormula child) {
		if (child == null) {
			throw new IllegalArgumentException("child must be non-null");
		}
		this.parameters = new ArrayList<>();
		parameters.add(child);
	}

	@Override
	protected FormulaReturnType computeReturnType() {
		return getDescription().getReturnType();
	}

	@Transient
	@Override
	public boolean isParametersCombinationAuthorized() {
		return true;
	}

	@Override
	public String asText() {
		return operationAsText() + " " + getParameters().get(0).asText();
	}

	public abstract String operationAsText();

}
