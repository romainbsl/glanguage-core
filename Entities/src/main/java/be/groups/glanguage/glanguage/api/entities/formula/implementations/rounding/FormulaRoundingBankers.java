package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.rule.RoundingType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_BANKERS_ROUNDED)
public class FormulaRoundingBankers extends RoundingFormula {

	protected FormulaRoundingBankers() {
		super();
	}

	public FormulaRoundingBankers(AbstractFormula parameter, AbstractFormula precision) {
		super( parameter, precision);
	}

	@Override
	public RoundingType getRoundingType() {
		return RoundingType.BANKERS;
	}

	@Override
	public AbstractFormula getDefaultPrecision() {
		return new FormulaTerminalInteger("2");
	}

	@Override
	public String operationAsText() {
		return "bankers_rounded";
	}

}
