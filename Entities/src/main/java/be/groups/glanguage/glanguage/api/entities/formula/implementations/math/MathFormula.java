package be.groups.glanguage.glanguage.api.entities.formula.implementations.math;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

@Entity
public abstract class MathFormula extends AbstractNonTerminalFormula {
	
	public MathFormula() {
		super();
	}
	
	public MathFormula(List<AbstractFormula> parameters) {
		super();
		
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
	
	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue() {
		return getNumericValue().intValue();
	}
	
	@Override
	public String asText() {
		return operationAsText() + "(" + getParameters().get(0).asText() + ")";
	}
	
	public abstract String operationAsText();
}
