package be.groups.glanguage.glanguage.api.entities.formula;

import java.time.LocalDate;
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
	public Integer getIntegerValue() {
		throw new UnsupportedOperationException(
				"Cannot invoke getIntegerValue() method on " + this.getClass().getName() + " object");
	}

	@Transient
	@Override
	public Double getNumericValue() {
		throw new UnsupportedOperationException(
				"Cannot invoke getNumericValue() method on " + this.getClass().getName() + " object");
	}

	@Transient
	@Override
	public String getStringValue() {
		return getConstantValue();
	}

	@Transient
	@Override
	public Boolean getBooleanValue() {
		throw new UnsupportedOperationException(
				"Cannot invoke getBooleanValue() method on " + this.getClass().getName() + " object");
	}

	@Transient
	@Override
	public LocalDate getDateValue() {
		throw new UnsupportedOperationException(
				"Cannot invoke getDateValue() method on " + this.getClass().getName() + " object");
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
