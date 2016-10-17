package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.rule.RoundingType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_TRUNC)
public class FormulaRoundingTrunc extends RoundingFormula {
	
	protected FormulaRoundingTrunc() {
		super();
	}
	
	public FormulaRoundingTrunc(FormulaDescription description, FormulaDescription precisionFormulaDescription, AbstractFormula parameter, AbstractFormula precision) {
		super(description, precisionFormulaDescription, parameter, precision);
	}
	
	@Override
	@Transient
	public RoundingType getRoundingType() {
		return RoundingType.TRUNC;
	}
	
	@Override
	@Transient
	public AbstractFormula getDefaultPrecision( FormulaDescription description) {
		return new FormulaTerminalInteger(description, "2");
	}
	
	@Override
	public String operationAsText() {
		return "trunc";
	}
	
}
