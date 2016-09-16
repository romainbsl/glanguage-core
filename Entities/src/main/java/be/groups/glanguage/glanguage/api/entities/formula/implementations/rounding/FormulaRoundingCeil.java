package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.rule.RoundingType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_CEIL)
public class FormulaRoundingCeil extends RoundingFormula {

	protected FormulaRoundingCeil() {
		super();
	}

	public FormulaRoundingCeil(AbstractFormula parameter, AbstractFormula precision) {
		super( parameter, precision);
	}

	@Override
	public RoundingType getRoundingType() {
		return RoundingType.CEIL;
	}

	@Override
	public AbstractFormula getDefaultPrecision() {
		return new FormulaTerminalInteger("1");
	}

	@Override
	public String operationAsText() {
		return "ceil";
	}

}
