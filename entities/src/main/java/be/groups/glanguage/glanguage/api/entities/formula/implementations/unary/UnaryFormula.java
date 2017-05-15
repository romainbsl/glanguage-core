package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.parameter.FormulaNullParameterInnerError;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
public abstract class UnaryFormula extends AbstractNonTerminalFormula {

	protected UnaryFormula() {
		super();
	}

	public UnaryFormula(FormulaDescription description, AbstractFormula child) throws GLanguageException {
		super(description, Arrays.asList(child));

		if (child == null) {
			throw new GLanguageException(new FormulaNullParameterInnerError(this, null, "constructor", 1));
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
