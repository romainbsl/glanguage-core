package be.groups.glanguage.glanguage.api.entities.formula.implementations.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

@Entity
public abstract class MathFormula extends AbstractNonTerminalFormula {
	
	public MathFormula() {
		super();
	}
	
	public MathFormula(FormulaDescription description, List<AbstractFormula> parameters) {
		super(description);
		
		if (parameters == null) {
			throw new IllegalArgumentException("parameters must be non-null");
		}
		if (parameters.size() != 1) {
			throw new IllegalArgumentException("there should be 1 parameter but there are " + parameters.size());
		}
		if (!(parameters.get(0).getReturnType().equals(FormulaReturnType.INTEGER)
				|| parameters.get(0).getReturnType().equals(FormulaReturnType.NUMERIC))) {
			throw new IllegalArgumentException("first parameter should be of type INTEGER or NUMERIC");
		}
		this.parameters = new ArrayList<>();
		this.parameters.add(parameters.get(0));
	}
	
	@Transient
	@Override
	protected Integer getIntegerValueImpl() {
		return getNumericValueImpl().intValue();
	}
	
	@Override
	protected FormulaReturnType computeReturnType() {
		return getParameters().get(0).getReturnType();
	}
	
	@Override
	protected Set<FormulaReturnType> getAuthorizedParametersTypes() {
		return new HashSet<>(Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.NUMERIC));
	}
	
	@Override
	protected boolean isParametersCombinationAuthorized() {
		return areParametersAuthorized();
	}
	
	@Override
	public String asText() {
		return operationAsText() + "(" + getParameters().get(0).asText() + ")";
	}
	
	public abstract String operationAsText();
}
