package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.rule.RoundingType;

@Entity
@DiscriminatorValue(value = FormulaDescription.Values.F_FLOOR)
public class FormulaFloorRounding extends RoundingFormula {

	public FormulaFloorRounding() {
		super();
	}

	public FormulaFloorRounding(AbstractFormula parameter, AbstractFormula precision) {
		super(parameter, precision);
	}

	@Override
	public RoundingType getRoundingType() {
		return RoundingType.FLOOR;
	}

	@Override
	public AbstractFormula getDefaultPrecision() {
		return new FormulaTerminalInteger("1");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Set<FormulaReturnType> getAuthorizedParametersTypes() {
		return new HashSet<>(Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.NUMERIC));
	}

	@Override
	public String operationAsText() {
		return "floor";
	}

	@Override
	protected FormulaReturnType computeReturnType() {
		return getParameters().get(1).getReturnType();
	}

}
