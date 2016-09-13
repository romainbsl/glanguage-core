package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

@Entity
@DiscriminatorValue(FormulaDescription.Values.OP_EXIST)
public class FormulaExist extends UnaryFormula {

	protected FormulaExist() {
		super();
	}

	public FormulaExist(AbstractFormula child) {
		super(FormulaDescription.OP_EXIST, child);
	}

	@Override
	public Boolean getBooleanValueImpl() {
		return getParameters().get(0).getValue() != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Set<FormulaReturnType> getAuthorizedParametersTypes() {
		return new HashSet<>(Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.NUMERIC,
				FormulaReturnType.STRING, FormulaReturnType.BOOLEAN, FormulaReturnType.DATE));
	}

	@Override
	public String operationAsText() {
		return "?";
	}
}
