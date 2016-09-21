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
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;

@Entity
@DiscriminatorValue(FormulaType.Values.F_SUBSTRING)
public class FormulaSubString extends AbstractNonTerminalFormula {
	
	public FormulaSubString() {
		super();
	}
	
	public FormulaSubString(FormulaDescription description, List<AbstractFormula> parameters) {
		super(description);
		if (parameters == null) {
			throw new IllegalArgumentException("parameters must be non-null");
		}
		if (parameters.size() != 3) {
			throw new IllegalArgumentException("there should be 3 parameters but there are " + parameters.size());
		}
		if (parameters.get(0) == null) {
			throw new IllegalArgumentException("first parameter must be non-null");
		}
		if (!parameters.get(0).getReturnType().equals(FormulaReturnType.STRING)) {
			throw new IllegalArgumentException("first parameter must of type STRING");
		}
		if (!parameters.get(1).getReturnType().equals(FormulaReturnType.INTEGER)) {
			throw new IllegalArgumentException("second parameter must of type INTEGER");
		}
		if (!parameters.get(2).getReturnType().equals(FormulaReturnType.INTEGER)) {
			throw new IllegalArgumentException("third parameter must of type INTEGER");
		}
		this.parameters = new ArrayList<>();
		this.parameters.addAll(parameters);
	}
	
	@JsonIgnore
	@Transient
	@Override
	public String getStringValue() {
		String str;
		int beginIndex, endIndex;
		
		str = getParameters().get(0).getStringValue();
		beginIndex = getParameters().get(1).getIntegerValue() - 1;
		endIndex = getParameters().get(2).getIntegerValue() - 1;
		
		if ((0 <= beginIndex) && (beginIndex <= endIndex) && (endIndex < str.length())) {
			return str.substring(beginIndex, endIndex + 1);
		} else {
			throw new IllegalArgumentException("Bounds not valid in " + this.getClass().getName() + " object : string = " + str
					+ " (length = " + str.length() + ") , beginIndex = " + beginIndex + ", endIndex = " + endIndex);
		}
	}
	
	@Override
	public String asText() {
		return "subString(" + getParameters().get(0).asText() + "; " + getParameters().get(1).asText() + "; "
				+ getParameters().get(2).asText() + ")";
	}
	
}
