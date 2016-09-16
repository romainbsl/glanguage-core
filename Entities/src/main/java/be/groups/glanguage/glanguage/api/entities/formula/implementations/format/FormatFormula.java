package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

import java.util.List;
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
		List<String> parametersTexts = getParameters().stream().map(p -> p.asText()).collect(Collectors.toList());
		
		StringBuilder builder = new StringBuilder();
		for (String text : parametersTexts) {
			builder.append(text);
			builder.append("; ");
		}
		
		builder.delete(builder.length() - 2, builder.length());
		
		return operationAsText() + "(" + builder.toString() + ")";
	}
	
	public abstract String operationAsText();
	
}
