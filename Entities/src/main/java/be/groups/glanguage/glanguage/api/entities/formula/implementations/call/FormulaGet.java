package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnTypeConverter;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(value = FormulaType.Values.C_GET)
public class FormulaGet extends CallFormula {
	
	public FormulaGet() {
		super();
	}
	
	public FormulaGet(FormulaReturnType returnType, List<String> identifiers, List<List<AbstractFormula>> parameters) {
		this();
		
		setConstantValue(String.valueOf(returnType.ordinal()));
		this.parameters = new ArrayList<>(identifiers.size());
		for (int i = 0; i < identifiers.size(); i++) {
			this.parameters.add(new FormulaPrimitive(identifiers.get(i), parameters.get(i)));
		}
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Boolean getBooleanValue() {
		try {
			return (Boolean) callFunctionAny(getTargetedObject(), getParameters().get(getParameters().size() - 1).getConstantValue(),
					getParametersAsArray());
		} catch (ClassCastException cce) {
			// TODO report evaluation error
			throw cce;
		}
	}
	
	@JsonIgnore
	@Transient
	@Override
	public LocalDate getDateValue() {
		try {
			return (LocalDate) callFunctionAny(getTargetedObject(), getConstantValue(), getParametersAsArray());
		} catch (ClassCastException cce) {
			// TODO report evaluation error
			throw cce;
		}
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Duration getDurationValue() {
		try {
			return (Duration) callFunctionAny(getTargetedObject(), getConstantValue(), getParametersAsArray());
		} catch (ClassCastException cce) {
			// TODO report evaluation error
			throw cce;
		}
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Integer getIntegerValue() {
		try {
			return (Integer) callFunctionAny(getTargetedObject(), getConstantValue(), getParametersAsArray());
		} catch (ClassCastException cce) {
			// TODO report evaluation error
			throw cce;
		}
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Double getNumericValue() {
		try {
			return (Double) callFunctionAny(getTargetedObject(), getConstantValue(), getParametersAsArray());
		} catch (ClassCastException cce) {
			// TODO report evaluation error
			throw cce;
		}
	}
	
	@JsonIgnore
	@Transient
	@Override
	public String getStringValue() {
		try {
			return (String) callFunctionAny(getTargetedObject(), getConstantValue(), getParametersAsArray());
		} catch (ClassCastException cce) {
			// TODO report evaluation error
			throw cce;
		}
	}
	
	@Override
	public String asText() {
		StringBuilder sb = new StringBuilder();
		sb.append("get ");
		
		FormulaReturnTypeConverter converter = new FormulaReturnTypeConverter();
		sb.append(converter.convertToEntityAttribute(Integer.valueOf(getConstantValue())));
		sb.append(" ");
		
		for (int i = 0; i < getParameters().size(); i++) {
			sb.append(getParameters().get(i).asText());
			if (i < getParameters().size() - 1) {
				sb.append(".");
			}
		}
		return sb.toString();
	}
	
	@Transient
	private Object getTargetedObject() {
		Object result = null; // TODO get main facade
		for (AbstractFormula primitive : getParameters()) {
			result = ((FormulaPrimitive) primitive).getTargetedObject(result);
		}
		return result;
	}
	
	@Transient
	private AbstractFormula[] getParametersAsArray() {
		AbstractFormula[] parameters = null;
		AbstractFormula lastParameter = getParameters().get(getParameters().size() - 1);
		if (lastParameter.getParameters() != null) {
			parameters = new AbstractFormula[lastParameter.getParameters().size()];
			parameters = lastParameter.getParameters().toArray(parameters);
		}
		return parameters;
	}
	
}
