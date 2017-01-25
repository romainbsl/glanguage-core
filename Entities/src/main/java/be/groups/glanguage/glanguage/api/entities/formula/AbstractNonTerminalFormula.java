package be.groups.glanguage.glanguage.api.entities.formula;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public abstract class AbstractNonTerminalFormula extends AbstractFormula {
	
	protected AbstractNonTerminalFormula() {
		super();
	}
	
	protected AbstractNonTerminalFormula(FormulaDescription description) {
		super(description);
	}

	@JsonIgnore
	@Transient
	@Override
	public boolean isTerminal() {
		return false;
	}
	
}
