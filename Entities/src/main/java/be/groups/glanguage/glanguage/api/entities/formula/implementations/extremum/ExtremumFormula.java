package be.groups.glanguage.glanguage.api.entities.formula.implementations.extremum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

@Entity
public abstract class ExtremumFormula extends AbstractNonTerminalFormula {
	
	public ExtremumFormula() {
		super();
	}
	
	public ExtremumFormula(List<AbstractFormula> parameters) {
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
	
	@Override
	public Integer getIntegerValueImpl() {
		return getNumericValue().intValue();
	}
	
	@Override
	protected FormulaReturnType computeReturnType() {
		FormulaReturnType returnType = FormulaReturnType.INTEGER;
		Iterator<AbstractFormula> itParameters = getParameters().iterator();
		while (returnType.equals(FormulaReturnType.INTEGER) && itParameters.hasNext()) {
			returnType = itParameters.next().getReturnType();
		}
		return returnType;
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
		String parametersString = getParameters().stream().map(p -> p.asText()).collect(Collectors.toList()).toString();
		parametersString = parametersString.substring(1, parametersString.length() - 1);
		
		return operationAsText() + "(" + parametersString + ")";
	}
	
	public abstract String operationAsText();
}
