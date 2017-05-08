package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.rule.RoundingType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(FormulaType.Values.F_FLOOR)
public class FormulaRoundingFloor extends RoundingFormula {

	protected FormulaRoundingFloor() {
		super();
	}

	public FormulaRoundingFloor(FormulaDescription description, FormulaDescription precisionFormulaDescription, AbstractFormula parameter, AbstractFormula precision) {
		super(description, precisionFormulaDescription, parameter, precision);
	}

	@Override
	@Transient
	public RoundingType getRoundingType() {
		return RoundingType.FLOOR;
	}

	@Override
	public String operationAsText() {
		return "floor";
	}

}
