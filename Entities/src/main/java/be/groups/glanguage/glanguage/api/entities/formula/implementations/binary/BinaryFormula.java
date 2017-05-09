package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.parameter.FormulaNullParameterInnerError;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
public abstract class BinaryFormula extends AbstractNonTerminalFormula {
	
	protected BinaryFormula() {
		super();
	}
	
	public BinaryFormula(FormulaDescription description, AbstractFormula child1, AbstractFormula child2) throws GLanguageException {
		super(description, Arrays.asList(child1, child2));
		
		if (child1 == null) {
			throw new GLanguageException(new FormulaNullParameterInnerError(this, null, "constructor", 1));
		}
		if (child2 == null) {
			throw new GLanguageException(new FormulaNullParameterInnerError(this, null, "constructor", 2));
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
