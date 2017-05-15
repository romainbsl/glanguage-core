package be.groups.glanguage.glanguage.api.entities.formula;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Entity
public abstract class AbstractNonTerminalFormula extends AbstractFormula {
	
	protected AbstractNonTerminalFormula() {
		super();
	}
	
	protected AbstractNonTerminalFormula(FormulaDescription description, List<AbstractFormula> parameters) throws
																										   GLanguageException {
		super(description);
		// FIXME pass an evaluator with the whole plan in it, initialized by parser
		isValid(parameters, null);
	}

	@JsonIgnore
	@Transient
	public boolean isValid(List<AbstractFormula> parameters, Evaluator evaluator) throws GLanguageException {
		return this.getDescription().isValid(parameters, evaluator);
	}

	@JsonIgnore
	@Transient
	@Override
	public boolean isTerminal() {
		return false;
	}

}
