package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.OP_DIVIDE)
public class FormulaDivide extends BinaryFormula {

    public FormulaDivide() {
		super();
	}

	public FormulaDivide(AbstractFormula child1, AbstractFormula child2) {
       super(child1, child2);
    }

	@Override
	public Double getNumericValue() {
		return getParameters().get(0).getNumericValue() / getParameters().get(1).getNumericValue();
	}

	@Override
	public Integer getIntegerValue() {
		throw new IllegalAccessError("Cannot invoke getIntegerValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public String getStringValue() {
		throw new IllegalAccessError("Cannot invoke getStringValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public Boolean getBooleanValue() {
		throw new IllegalAccessError("Cannot invoke getBooleanValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	public LocalDate getDateValue() {
		throw new IllegalAccessError("Cannot invoke getDateValue() method on " + this.getClass().getName() + " object");
	}

	@Override
	protected FormulaReturnType computeReturnType() {
		if (getParameters().get(1).getType().equals(FormulaReturnType.NUMERIC)) {
			return FormulaReturnType.NUMERIC;
		} else {
			return getParameters().get(0).getReturnType();
		}
	}

	@Override
	public String operationAsText() {
		return "/";
	}

}
