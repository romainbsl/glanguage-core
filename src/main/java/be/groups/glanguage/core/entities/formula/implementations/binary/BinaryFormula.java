package be.groups.glanguage.core.entities.formula.implementations.binary;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.error.exception.GLanguageException;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Abstract formula implementing an operation on exactly two (2) parameters
 *
 * @author michotte
 */
@Entity
public abstract class BinaryFormula extends AbstractNonTerminalFormula {
	
	protected BinaryFormula() {
		super();
	}

	public BinaryFormula(FormulaDescription description, AbstractFormula child1, AbstractFormula child2, Evaluator evaluator)
			throws
      GLanguageException {
		super(description, Arrays.asList(child1, child2), evaluator);
		this.parameters = new ArrayList<>(2);
		parameters.add(child1);
		parameters.add(child2);
	}

	@Override
	public String asText() {
		return getParameters().get(0).asText() + " " + operationAsText() + " " + getParameters().get(1).asText();
	}
	
	public abstract String operationAsText();
	
}
