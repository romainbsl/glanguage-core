package be.groups.glanguage.glanguage.api.entities.formula;

import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public abstract class AbstractTerminalFormula extends AbstractFormula {

	protected AbstractTerminalFormula() {
		super();
	}

	public AbstractTerminalFormula(FormulaDescription description, String constantValue) {
		super(description);

		if (constantValue == null) {
			throw new IllegalArgumentException("Constant value must be non-null");
		}
		this.setConstantValue(constantValue);
	}

	@Transient
	@Override
	public FormulaReturnType getReturnType() {
		return getDescription().getReturnType();
	}

	@Transient
	@Override
	public LinkedList<AbstractFormula> getParameters() {
		return null;
	}

	@Transient
	@Override
	public String getStringValue() {
		return getConstantValue();
	}

	@Transient
	@Override
	public boolean isTerminal() {
		return true;
	}

	@Override
	public String asText() {
		return getConstantValue();
	}

}
