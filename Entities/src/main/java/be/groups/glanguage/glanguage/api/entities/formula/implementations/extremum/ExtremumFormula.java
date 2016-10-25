package be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

@Entity
public abstract class ExtremumFormula extends AbstractNonTerminalFormula {
	
	public ExtremumFormula() {
		super();
	}
	
	public ExtremumFormula(FormulaDescription description, List<AbstractFormula> parameters) {
		super(description);
		if (parameters == null) {
			throw new IllegalArgumentException("parameters must be non-null");
		}
		this.parameters = new ArrayList<>();
		parameters.stream().forEachOrdered(e -> {
			if (e.getReturnType().equals(FormulaReturnType.INTEGER) || e.getReturnType().equals(FormulaReturnType.NUMERIC)) {
				this.parameters.add(e);
			} else {
				throw new IllegalArgumentException(
						(parameters.indexOf(e) + 1) + "-th parameter must be of type INTEGER or NUMERIC : " + e.asText());
			}
		});
	}
	
	@JsonIgnore
	@Transient
	@Override
	public FormulaReturnType getReturnType() {
		FormulaReturnType returnType = getParameters().get(0).getReturnType();
		Iterator<AbstractFormula> itParameters = getParameters().iterator();
		while (!returnType.equals(FormulaReturnType.NUMERIC) && itParameters.hasNext()) {
			returnType = itParameters.next().getReturnType();
		}
		return returnType;
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue() {
		return getNumericValue().intValue();
	}
	
	@Override
	public String asText() {
		String parametersString = getParameters().stream().map(p -> p.asText()).collect(Collectors.toList()).toString();
		parametersString = parametersString.substring(1, parametersString.length() - 1);
		
		return operationAsText() + "(" + parametersString + ")";
	}
	
	public abstract String operationAsText();
}
