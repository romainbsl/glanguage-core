package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

@Entity
public abstract class BinaryFormula extends AbstractNonTerminalFormula {

	public BinaryFormula() {
		super();
	}

	public BinaryFormula(AbstractFormula child1, AbstractFormula child2) {
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
	protected FormulaReturnType computeReturnType() {
		if (getParameters().get(1).getReturnType().equals(FormulaReturnType.NUMERIC)) {
			return FormulaReturnType.NUMERIC;
		} else {
			return getParameters().get(0).getReturnType();
		}
	}

	@Transient
	@Override
	protected final boolean isParametersCombinationAuthorized() {
		return getParametersCombinationMatrix().get(parameters.get(0).getReturnType())
				.contains(parameters.get(1).getReturnType());
	}

	/**
	 * Gives this parameters combination matrix (for a parameter type, which
	 * type is accepted for the other parameter)
	 * 
	 * @return this parameters combination matrix
	 */
	@Transient
	protected abstract Map<FormulaReturnType, Set<FormulaReturnType>> getParametersCombinationMatrix();

	@Override
	public String asText() {
		return getParameters().get(0).asText() + " " + operationAsText() + " " + getParameters().get(1).asText();
	}

	public abstract String operationAsText();

}
