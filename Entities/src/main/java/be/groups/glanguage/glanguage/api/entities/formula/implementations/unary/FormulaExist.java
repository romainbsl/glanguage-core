package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.OP_EXIST)
public class FormulaExist extends UnaryFormula {

	protected FormulaExist() {
		super();
	}

	public FormulaExist(AbstractFormula child) {
		super( child);
	}

	@Transient
	@Override
	public Boolean getBooleanValue() {
		return getParameters().get(0).getValue() != null;
	}

	@Override
	public String operationAsText() {
		return "?";
	}
}
