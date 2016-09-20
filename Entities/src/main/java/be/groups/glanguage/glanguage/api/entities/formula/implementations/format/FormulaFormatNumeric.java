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
import be.groups.glanguage.glanguage.api.entities.utils.FormatDouble;
import be.groups.glanguage.glanguage.api.entities.utils.FormatSign;

@Entity
@DiscriminatorValue(FormulaType.Values.F_FORMAT_NUMERIC)
public class FormulaFormatNumeric extends FormatFormula {
	
	public FormulaFormatNumeric() {
		super();
	}
	
	public FormulaFormatNumeric(List<AbstractFormula> parameters) {
		super();
		
		if (parameters == null) {
			throw new IllegalArgumentException("parameters must be non-null");
		}
		if (parameters.size() != 7) {
			throw new IllegalArgumentException("there should be 7 parameters but there are " + parameters.size());
		}
		if (parameters.get(0) == null) {
			throw new IllegalArgumentException("first parameter must be non-null");
		}
		if (!parameters.get(0).getReturnType().equals(FormulaReturnType.NUMERIC)) {
			throw new IllegalArgumentException("first parameter must of type NUMERIC");
		}
		if (!parameters.get(1).getReturnType().equals(FormulaReturnType.INTEGER)) {
			throw new IllegalArgumentException("second parameter must of type INTEGER");
		}
		if (!parameters.get(2).getReturnType().equals(FormulaReturnType.INTEGER)) {
			throw new IllegalArgumentException("third parameter must of type INTEGER");
		}
		if (!parameters.get(3).getReturnType().equals(FormulaReturnType.STRING)) {
			throw new IllegalArgumentException("forth parameter must of type STRING");
		}
		if (!parameters.get(4).getReturnType().equals(FormulaReturnType.STRING)) {
			throw new IllegalArgumentException("fifth parameter must of type STRING");
		}
		if (!parameters.get(5).getReturnType().equals(FormulaReturnType.STRING)) {
			throw new IllegalArgumentException("sixth parameter must of type STRING");
		}
		if (!parameters.get(6).getReturnType().equals(FormulaReturnType.STRING)) {
			throw new IllegalArgumentException("seventh parameter must of type STRING");
		}
		this.parameters = new ArrayList<>();
		this.parameters.addAll(parameters);
	}

	@JsonIgnore
	@Transient
	@Override
	public String getStringValue() {
		FormatDouble format = null;
		double d;
		int width, decimals;
		String alignment;
		char fillCharacter;
		String sign;
		String decimalMark;
		
		d = getParameters().get(0).getNumericValue();
		width = getParameters().get(1).getIntegerValue();
		decimals = getParameters().get(2).getIntegerValue();
		alignment = getParameters().get(3).getStringValue().toUpperCase();
		sign = getParameters().get(5).getStringValue().toUpperCase();
		decimalMark = getParameters().get(6).getStringValue().toUpperCase();
		if (width >= 1) {
			format = new FormatDouble(width, decimals);
			format.noSeparateAfterDecimal();
			
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
							+ getParameters().get(3).getStringValue());
			}
			
			if (!alignment.equals(FormatAlignment.Values.NO_JUSTIFY)) {
				if (getParameters().get(4).getStringValue().length() == 1) {
					fillCharacter = getParameters().get(4).getStringValue().charAt(0);
					format.setFill(fillCharacter);
				} else {
					throw new IllegalArgumentException("Fillin character not valid in " + this.getClass().getName() + " object : "
							+ getParameters().get(4).getStringValue());
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
							+ getParameters().get(5).getStringValue());
			}
			
			if (decimalMark.length() == 1) {
				if (decimalMark.charAt(0) == '.') {
					format.pointDecimal();
				} else if (decimalMark.charAt(0) == ',') {
					format.commaDecimal();
				}
			} else {
				throw new IllegalArgumentException("Decimal mark not valid in " + this.getClass().getName() + " object : "
						+ getParameters().get(6).getStringValue());
			}
			
			if (decimals == 0) {
				format.hideDecimalCharacter();
			}
			
		} else {
			throw new IllegalArgumentException(
					"Width not valid in " + this.getClass().getName() + " object : " + getParameters().get(1).getStringValue());
		}
		
		return format.formatted(d);
	}
	
	@Override
	public String operationAsText() {
		return "formatNumeric";
	}
	
}
