package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.utils.FormatAlignment;

@Entity
@DiscriminatorValue(FormulaType.Values.F_FORMAT_STRING)
public class FormulaFormatString extends FormatFormula {
	
	public FormulaFormatString() {
		super();
	}
	
	public FormulaFormatString(List<AbstractFormula> parameters) {
		super();
		
		if (parameters == null) {
			throw new IllegalArgumentException("parameters must be non-null");
		}
		if (parameters.size() != 4) {
			throw new IllegalArgumentException("there should be 4 parameters but there are " + parameters.size());
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
		if (!parameters.get(2).getReturnType().equals(FormulaReturnType.STRING)) {
			throw new IllegalArgumentException("third parameter must of type STRING");
		}
		if (!parameters.get(3).getReturnType().equals(FormulaReturnType.STRING)) {
			throw new IllegalArgumentException("forth parameter must of type STRING");
		}
		this.parameters = new ArrayList<>();
		this.parameters.addAll(parameters);
	}

	@JsonIgnore
	@Transient
	@Override
	public String getStringValue() {
		String result;
		StringBuilder sb = new StringBuilder();
		String str = getParameters().get(0).getStringValue();
		int width = getParameters().get(1).getIntegerValue();
		String alignment = getParameters().get(2).getStringValue().toUpperCase();
		String fillString = getParameters().get(3).getStringValue();
		String left;
		String right;
		char fillCharacter;
		
		if (!fillString.isEmpty()) {
			fillCharacter = fillString.charAt(0);
		} else {
			fillCharacter = ' ';
		}
		
		if (width <= 0) {
			sb.append("");
		} else if (width == str.length()) {
			sb.append(str);
		} else if (width < str.length()) {
			sb.append(str.substring(0, width));
		} else {
			switch (alignment) {
				case FormatAlignment.Values.LEFT_JUSTIFY:
					sb.append(str);
					right = "" + fillCharacter;
					for (int i = 1; i < width - str.length(); i++) {
						right = right + fillCharacter;
					}
					sb.append(right);
					break;
				case FormatAlignment.Values.RIGHT_JUSTIFY:
					left = "" + fillCharacter;
					for (int i = 1; i < width - str.length(); i++) {
						left = left + fillCharacter;
					}
					sb.append(left);
					sb.append(str);
					break;
				case FormatAlignment.Values.CENTER_JUSTIFY:
					left = "" + fillCharacter;
					for (int i = 1; i < (width - str.length()) / 2; i++) {
						left = left + fillCharacter;
					}
					right = left;
					sb.append(left);
					sb.append(str);
					sb.append(right);
					if (sb.toString().length() < width) {
						sb.append(fillCharacter);
					}
					break;
				default:
					throw new IllegalArgumentException("Alignment not valid in " + this.getClass().getName() + " object : "
							+ getParameters().get(2).getStringValue());
			}
		}
		result = sb.toString();
		return result;
	}
	
	@Override
	public String operationAsText() {
		return "formatString";
	}
	
}
