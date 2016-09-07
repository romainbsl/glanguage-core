package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import java.time.LocalDate;
import java.util.ArrayList;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

public class FormulaExist extends AbstractFormula {

	public FormulaExist() {
		super();
	}

	public FormulaExist(AbstractFormula child) {
		if (child == null) {
        	throw new IllegalArgumentException("Child must be non-null");
        }
        this.parameters = new ArrayList<>();
		parameters.add(child);
    }    

	@Override
	public boolean isTerminal() {
		return false;
	}

	@Override
	public FormulaReturnType getReturnType() {
		return getDescription().getReturnType();
	}

	@Override
	public Integer getIntegerValue() {
		throw new IllegalAccessError("Cannot invoke getIntegerValue() method on FormulaExist object");
	}

	@Override
	public Double getNumericValue() {
		throw new IllegalAccessError("Cannot invoke getNumericValue() method on FormulaExist object");
	}

	@Override
	public String getStringValue() {
		throw new IllegalAccessError("Cannot invoke getStringValue() method on FormulaExist object");
	}

	@Override
	public Boolean getBooleanValue() {
		return getParameters().get(0).getValue() != null;
	}

	@Override
	public LocalDate getDateValue() {
		throw new IllegalAccessError("Cannot invoke getDateValue() method on FormulaExist object");
	}

	@Override
	public String asText() {
		return "?";
	}
}
// $Source: F:/CVSRoot5/COMPOSANTS/src/composants/se2000/support/regle/formules/implementation/FormuleExiste.java,v $