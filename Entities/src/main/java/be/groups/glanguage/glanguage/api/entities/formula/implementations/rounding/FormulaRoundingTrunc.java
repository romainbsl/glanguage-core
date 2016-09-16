package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.rule.RoundingType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_TRUNC)
public class FormulaRoundingTrunc extends RoundingFormula {
	
	protected FormulaRoundingTrunc() {
		super();
	}
	
	public FormulaRoundingTrunc(AbstractFormula parameter, AbstractFormula precision) {
		super( parameter, precision);
	}
	
	@Override
	public RoundingType getRoundingType() {
		return RoundingType.TRUNC;
	}
	
	@Override
	public AbstractFormula getDefaultPrecision() {
		return new FormulaTerminalInteger("2");
	}
	
	@Override
	public String operationAsText() {
		return "trunc";
	}
	
}
