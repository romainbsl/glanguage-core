package be.groups.glanguage.core.entities.formula.implementations.unary;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.formula.base.parameter.FormulaNullParameterInnerError;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Abstract formula implementing an operation on exactly 1 (1) parameter
 *
 * @author michotte
 */
@Entity
public abstract class UnaryFormula extends AbstractNonTerminalFormula {

	protected UnaryFormula() {
		super();
	}

	public UnaryFormula(FormulaDescription description,
                        AbstractFormula child,
                        Evaluator evaluator) throws GLanguageException {
		super(description, Arrays.asList(child), evaluator);

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
