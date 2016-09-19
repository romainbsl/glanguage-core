package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.rule.RoundingType;

@Entity
@DiscriminatorValue(value = FormulaDescription.Values.F_BANKERS_ROUNDED)
public class FormulaRoundingBankers extends RoundingFormula {

	protected FormulaRoundingBankers() {
		super();
	}

	public FormulaRoundingBankers(AbstractFormula parameter, AbstractFormula precision) {
		super(FormulaDescription.F_BANKERS_ROUNDED, parameter, precision);
	}

	@Override
	public RoundingType getRoundingType() {
		return RoundingType.BANKERS;
	}

	@Override
	public AbstractFormula getDefaultPrecision() {
		return new FormulaTerminalInteger("2");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Set<FormulaReturnType> getAuthorizedParametersTypes() {
		return new HashSet<>(Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.NUMERIC));
	}
	
	@Override
	protected boolean isParametersCombinationAuthorized() {
		return (getParameters().get(0).getReturnType().equals(FormulaReturnType.INTEGER)
				|| getParameters().get(0).getReturnType().equals(FormulaReturnType.NUMERIC))
				&& getParameters().get(1).getReturnType().equals(FormulaReturnType.INTEGER);
	}

	@Override
	public String operationAsText() {
		return "bankers_rounded";
	}

}
