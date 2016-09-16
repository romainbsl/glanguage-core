package be.groups.glanguage.glanguage.api.entities.formula.implementations.binary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.OP_MODULO)
public class FormulaModulo extends BinaryFormula {

	protected FormulaModulo() {
		super();
	}

	public FormulaModulo(AbstractFormula child1, AbstractFormula child2) {
		super( child1, child2);
	}

	@Transient
	@Override
	public Integer getIntegerValue() {
		return getParameters().get(0).getIntegerValue() % getParameters().get(1).getIntegerValue();
	}

	@Override
	public String operationAsText() {
		return "\\";
	}

}
