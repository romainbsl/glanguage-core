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
@DiscriminatorValue(value = FormulaDescription.Values.F_ROUNDED)
public class FormulaRoundingArithmetic extends RoundingFormula {

	protected FormulaRoundingArithmetic() {
		super();
	}

	public FormulaRoundingArithmetic(AbstractFormula parameter, AbstractFormula precision) {
		super(FormulaDescription.F_ROUNDED, parameter, precision);
	}

	@Override
	public RoundingType getRoundingType() {
		return RoundingType.ARITHMETIC;
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
		return "round";
	}

}
