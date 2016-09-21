package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.rule.RoundingType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_CEIL)
public class FormulaRoundingCeil extends RoundingFormula {

	protected FormulaRoundingCeil() {
		super();
	}

	public FormulaRoundingCeil(FormulaDescription description, FormulaDescription precisionFormulaDescription, AbstractFormula parameter, AbstractFormula precision) {
		super(description, precisionFormulaDescription, parameter, precision);
	}

	@Override
	public RoundingType getRoundingType() {
		return RoundingType.CEIL;
	}

	@Override
	public AbstractFormula getDefaultPrecision(FormulaDescription description) {
		return new FormulaTerminalInteger(description, "1");
	}

	@Override
	public String operationAsText() {
		return "ceil";
	}

}
