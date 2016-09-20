package be.groups.glanguage.glanguage.api.entities.formula.implementations.string;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;

public class FormulaStringLength extends AbstractNonTerminalFormula {
	
	public FormulaStringLength() {
		super();
	}
	
	public FormulaStringLength(List<AbstractFormula> parameters) {
		if (parameters == null) {
			throw new IllegalArgumentException("parameters must be non-null");
		}
		if (parameters.size() != 1) {
			throw new IllegalArgumentException("there should be 1 parameter but there are " + parameters.size());
		}
		if (parameters.get(0) == null) {
			throw new IllegalArgumentException("first parameter must be non-null");
		}
		if (!parameters.get(0).getReturnType().equals(FormulaReturnType.STRING)) {
			throw new IllegalArgumentException("first parameter must of type STRING");
		}
		this.parameters = new ArrayList<>();
		parameters.stream().forEachOrdered(e -> this.parameters.add(e));
	}
	
	@Transient
	@Override
	public String getStringValue() {
		return String.valueOf(getIntegerValue());
	}
	
	@Transient
	@Override
	public Integer getIntegerValue() {
		return getParameters().get(0).getStringValue() != null ? getParameters().get(0).getStringValue().length() : 0;
	}
	
	@Override
	public String asText() {
		return "stringLength(" + getParameters().get(0).asText() + ")";
	}
	
}
