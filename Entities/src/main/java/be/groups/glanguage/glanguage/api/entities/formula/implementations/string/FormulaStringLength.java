package be.groups.glanguage.glanguage.api.entities.formula.implementations.string;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_STRING_LENGTH)
public class FormulaStringLength extends AbstractNonTerminalFormula {
	
	public FormulaStringLength() {
		super();
	}
	
	public FormulaStringLength(FormulaDescription description, List<AbstractFormula> parameters) {
		super(description);
		
		if (parameters == null) {
			throw new IllegalArgumentException("parameters must be non-null");
		}
		
		this.parameters = new ArrayList<>();
		this.parameters.addAll(parameters);
	}
	
	@JsonIgnore
	@Transient
	@Override
	public String getStringValue() {
		return String.valueOf(getIntegerValue());
	}
	
	@JsonIgnore
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
