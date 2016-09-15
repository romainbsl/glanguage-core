package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;

@Entity
@DiscriminatorValue(value = FormulaDescription.Values.C_GET)
public class FormulaGet extends CallFormula {
					
	public FormulaGet() {
		super();
	}
	
	public FormulaGet(FormulaReturnType returnType, LinkedList<String> identifiers,
			LinkedList<LinkedList<AbstractFormula>> parameters) {
		super(FormulaDescription.C_GET);
		setConstantValue(String.valueOf(returnType.ordinal()));
		this.parameters = new ArrayList<>(identifiers.size());
		for (int i = 0; i < identifiers.size(); i++) {
			this.parameters.add(new FormulaPrimitive(identifiers.get(i), parameters.get(1)));
		}
	}
	
	@Override
	public Boolean getBooleanValueImpl() {
		try {
			return (Boolean) callFunctionAny(getTargetedObject(), getParameters().get(getParameters().size() - 1).getConstantValue(),
					getParametersAsArray());
		} catch (ClassCastException cce) {
			// TODO report evaluation error
			throw cce;
		}
	}
	
	@Override
	public LocalDate getDateValueImpl() {
		try {
			return (LocalDate) callFunctionAny(getTargetedObject(), getConstantValue(), getParametersAsArray());
		} catch (ClassCastException cce) {
			// TODO report evaluation error
			throw cce;
		}
	}
	
	@Override
	public Duration getDurationValueImpl() {
		try {
			return (Duration) callFunctionAny(getTargetedObject(), getConstantValue(), getParametersAsArray());
		} catch (ClassCastException cce) {
			// TODO report evaluation error
			throw cce;
		}
	}
	
	@Override
	public Integer getIntegerValueImpl() {
		try {
			return (Integer) callFunctionAny(getTargetedObject(), getConstantValue(), getParametersAsArray());
		} catch (ClassCastException cce) {
			// TODO report evaluation error
			throw cce;
		}
	}
	
	@Override
	public Double getNumericValueImpl() {
		try {
			return (Double) callFunctionAny(getTargetedObject(), getConstantValue(), getParametersAsArray());
		} catch (ClassCastException cce) {
			// TODO report evaluation error
			throw cce;
		}
	}
	
	@Override
	public String getStringValueImpl() {
		try {
			return (String) callFunctionAny(getTargetedObject(), getConstantValue(), getParametersAsArray());
		} catch (ClassCastException cce) {
			// TODO report evaluation error
			throw cce;
		}
	}
	
	@Override
	protected FormulaReturnType computeReturnType() {
		return getParameters().get(0).getReturnType();
	}

	@Override
	public String asText() {
		StringBuilder sb = new StringBuilder();
		sb.append("get ");
		sb.append(getConstantValue());
		for (int i = 0; i < getParameters().size(); i++) {
			sb.append(getParameters().get(i).asText());
			if (i < getParameters().size() - 1) {
				sb.append(".");
			}
		}
		return sb.toString();
	}
	
	private Object getTargetedObject() {
		Object result = null; // TODO get main facade
		for (AbstractFormula primitive : getParameters()) {
			result = ((FormulaPrimitive) primitive).getTargetedObject(result);
		}
		return result;
	}
	
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
