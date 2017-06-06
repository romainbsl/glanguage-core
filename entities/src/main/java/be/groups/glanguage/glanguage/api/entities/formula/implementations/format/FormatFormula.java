package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public abstract class FormatFormula extends AbstractNonTerminalFormula {
	
	protected FormatFormula() {
		super();
	}
	
	protected FormatFormula(FormulaDescription description,
                            List<AbstractFormula> parameters,
                            Evaluator evaluator) throws GLanguageException {
		super(description, parameters, evaluator);
	}
	
	@JsonIgnore
	@Transient
	@Override
	public FormulaReturnType getReturnType(Evaluator evaluator) {
		return FormulaReturnType.STRING;
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
