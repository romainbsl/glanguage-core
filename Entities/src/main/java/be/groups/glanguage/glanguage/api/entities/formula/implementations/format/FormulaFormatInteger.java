package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.utils.FormatAlignment;
import be.groups.glanguage.glanguage.api.entities.utils.FormatInteger;
import be.groups.glanguage.glanguage.api.entities.utils.FormatSign;

@Entity
@DiscriminatorValue(FormulaType.Values.F_FORMAT_INTEGER)
public class FormulaFormatInteger extends FormatFormula {
	
	public FormulaFormatInteger() {
		super();
	}
	
	public FormulaFormatInteger(List<AbstractFormula> parameters) {
		super();
		
		if (parameters == null) {
			throw new IllegalArgumentException("parameters must be non-null");
		}
		if (parameters.size() != 5) {
			throw new IllegalArgumentException("there should be 5 parameters but there are " + parameters.size());
		}
		if (parameters.get(0) == null) {
			throw new IllegalArgumentException("first parameter must be non-null");
		}
		if (!parameters.get(0).getReturnType().equals(FormulaReturnType.INTEGER)) {
			throw new IllegalArgumentException("first parameter must of type INTEGER");
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
		if (!parameters.get(4).getReturnType().equals(FormulaReturnType.STRING)) {
			throw new IllegalArgumentException("fifth parameter must of type STRING");
		}
		this.parameters = new ArrayList<>();
		this.parameters.addAll(parameters);
	}

	@Transient
	@Override
	public String getStringValue() {
		FormatInteger format = null;
		int i, width;
		String alignment;
		char fillCharacter;
		String sign;
		
		i = getParameters().get(0).getIntegerValue();
		width = getParameters().get(1).getIntegerValue();
		alignment = getParameters().get(2).getStringValue().toUpperCase();
		sign = getParameters().get(4).getStringValue().toUpperCase();
		if (width >= 1) {
			format = new FormatInteger(width);
			
			switch (alignment) {
				case FormatAlignment.Values.NO_JUSTIFY:
					format.noJustified();
					break;
				case FormatAlignment.Values.LEFT_JUSTIFY:
					format.leftJustify();
					break;
				case FormatAlignment.Values.RIGHT_JUSTIFY:
					format.rightJustify();
					break;
				case FormatAlignment.Values.CENTER_JUSTIFY:
					format.centerJustify();
					break;
				default:
					throw new IllegalArgumentException("Alignment not valid in " + this.getClass().getName() + " object : "
							+ getParameters().get(2).getStringValue());
			}
			
			if (!alignment.equals(FormatAlignment.Values.NO_JUSTIFY)) {
				if (getParameters().get(3).getStringValue().length() == 1) {
					fillCharacter = getParameters().get(3).getStringValue().charAt(0);
					format.setFill(fillCharacter);
				} else {
					throw new IllegalArgumentException("Fillin character not valid in " + this.getClass().getName() + " object : "
							+ getParameters().get(3).getStringValue());
				}
			}
			
			switch (sign) {
				case FormatSign.Values.NONE:
					format.signIgnore();
					break;
				case FormatSign.Values.NEGATIVE_ONLY:
					format.signNegativeOnly();
					break;
				case FormatSign.Values.POSITIVE_ONLY:
					format.signPositiveOnly();
					break;
				case FormatSign.Values.BOTH:
					format.signShow();
					break;
				default:
					throw new IllegalArgumentException("Sign format not valid in " + this.getClass().getName() + " object : "
							+ getParameters().get(4).getStringValue());
			}
			
		} else {
			throw new IllegalArgumentException(
					"Width not valid in " + this.getClass().getName() + " object : " + getParameters().get(1).getStringValue());
		}
		
		return format.format(i);
	}
	
	@Override
	public String operationAsText() {
		return "formatInteger";
	}
	
}
