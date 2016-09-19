package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.rule.RoundingType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_FLOOR)
public class FormulaRoundingFloor extends RoundingFormula {

	protected FormulaRoundingFloor() {
		super();
	}

	public FormulaRoundingFloor(AbstractFormula parameter, AbstractFormula precision) {
		super( parameter, precision);
	}

	@Override
	public RoundingType getRoundingType() {
		return RoundingType.FLOOR;
	}

	@Override
	public AbstractFormula getDefaultPrecision() {
		return new FormulaTerminalInteger("1");
	}

	@Override
	public String operationAsText() {
		return "floor";
	}

}
