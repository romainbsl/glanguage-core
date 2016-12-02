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
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnTypeConverter;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(value = FormulaType.Values.C_GET)
public class FormulaGet extends CallFormula {
	
	private Object context;
	
	public FormulaGet() {
		super();
	}
	
	public FormulaGet(FormulaDescription description, FormulaDescription subFormulasdescription, FormulaReturnType returnType,
			List<String> identifiers, List<List<AbstractFormula>> parameters) {
		super(description);
		
		setConstantValue(String.valueOf(returnType.ordinal()));
		this.parameters = new ArrayList<>(identifiers.size());
		for (int i = 0; i < identifiers.size(); i++) {
			this.parameters.add(new FormulaPrimitive(subFormulasdescription, identifiers.get(i), parameters.get(i)));
		}
	}
	
	@Transient
	@Override
	public FormulaReturnType getReturnType() {
		return new FormulaReturnTypeConverter().convertToEntityAttribute(Integer.valueOf(getConstantValue()));
	}
	
	@JsonIgnore
	@Transient
	@Override
	public Boolean getBooleanValue() {
		try {
			return (Boolean) getTargetedObject();
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
			return (LocalDate) getTargetedObject();
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
			return (Duration) getTargetedObject();
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
			return (Integer) getTargetedObject();
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
			return (Double) getTargetedObject();
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
			return (String) getTargetedObject();
		} catch (ClassCastException cce) {
			// TODO report evaluation error
			throw cce;
		}
	}

	@JsonIgnore
	@Transient
	public boolean isBranched() {
		return context != null && super.isBranched();
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
	
	/**
	 * @param context the context to set
	 */
	public void setContext(Object context) {
		this.context = context;
	}
	
	@JsonIgnore
	@Transient
	private Object getTargetedObject() {
		if (context == null) {
			throw new IllegalAccessError("Cannot invoke getTargetetObject() method on " + this.getClass().getName()
					+ " object while context is not set - while branching is not done - (id : " + this.getId() + ")");
		}
		Object result = context;
		for (AbstractFormula primitive : getParameters()) {
			result = ((FormulaPrimitive) primitive).getTargetedObject(result);
		}
		return result;
	}
	
}
