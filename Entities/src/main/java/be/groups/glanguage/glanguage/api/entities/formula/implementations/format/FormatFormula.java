package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

@Entity
public abstract class FormatFormula extends AbstractNonTerminalFormula {

	@Override
	protected FormulaReturnType computeReturnType() {
		return getDescription().getReturnType();
	}
	
	@Override
	public String asText() {
		return operationAsText() + "(" + getParameters().get(0).asText() + ";" + getParameters().get(1).asText() + ")";
	}
	
	public abstract String operationAsText();
	
}
