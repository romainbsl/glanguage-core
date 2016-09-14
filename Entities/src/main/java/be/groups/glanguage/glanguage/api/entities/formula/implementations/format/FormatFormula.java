package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

import java.util.stream.Collectors;

import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

@Entity
public abstract class FormatFormula extends AbstractNonTerminalFormula {

	protected FormatFormula() {
		super();
	}
	
	public FormatFormula(FormulaDescription description) {
		super(description);
	}
	
	@Override
	protected FormulaReturnType computeReturnType() {
		return getDescription().getReturnType();
	}
	
	@Override
	public String asText() {
		String parametersString = getParameters().stream().map(p -> p.asText()).collect(Collectors.toList()).toString();
		parametersString = parametersString.substring(1, parametersString.length() - 1).replace(',', ';');
		
		return operationAsText() + "(" + parametersString + ")";
	}
	
	public abstract String operationAsText();
	
}
