package be.groups.glanguage.glanguage.api.entities.formula;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public abstract class AbstractNonTerminalFormula extends AbstractFormula {
	
	/**
	 * Return type
	 */
	private FormulaReturnType returnType;

	public AbstractNonTerminalFormula() {
		super();
	}
	
	@Transient
	@Override
	public FormulaReturnType getReturnType() {
		if (returnType == null) {
			returnType = computeReturnType();
		}
		return returnType;
	}
	
	protected abstract FormulaReturnType computeReturnType();
	
	@Transient	
	@Override
	public boolean isTerminal() {
		return false;
	}

}
