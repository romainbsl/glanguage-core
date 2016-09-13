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

	public FormulaExist() {
		super();
	}

	public FormulaExist(AbstractFormula child) {
		super(child);
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
		return new HashSet<>(Arrays.asList(FormulaReturnType.values()));
	}

	@Override
	public String operationAsText() {
		return "?";
	}
}
