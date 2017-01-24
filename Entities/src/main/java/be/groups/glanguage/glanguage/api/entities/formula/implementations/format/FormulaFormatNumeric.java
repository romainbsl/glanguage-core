package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.utils.format.FormatAlignment;
import be.groups.glanguage.glanguage.api.entities.utils.format.FormatDouble;
import be.groups.glanguage.glanguage.api.entities.utils.format.FormatSign;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(FormulaType.Values.F_FORMAT_NUMERIC)
public class FormulaFormatNumeric extends FormatFormula {

    public FormulaFormatNumeric() {
        super();
    }

    public FormulaFormatNumeric(FormulaDescription description, List<AbstractFormula> parameters) {
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
    protected String doGetStringValue(Evaluator evaluator) throws GLanguageException {
        FormatDouble format = null;
        double d;
        int width, decimals;
        String alignment;
        Character fillCharacter = null;
        String sign;
        String decimalMark;

        d = getParameters().get(0).getNumericValue(evaluator);
        width = getParameters().get(1).getIntegerValue(evaluator);
        decimals = getParameters().get(2).getIntegerValue(evaluator);
        alignment = getParameters().get(3).getStringValue(evaluator).toUpperCase();
        if (!alignment.equals(FormatAlignment.Values.NO_JUSTIFY)) {
            if (getParameters().get(4).getStringValue(evaluator).length() == 1) {
                fillCharacter = getParameters().get(4).getStringValue(evaluator).charAt(0);
            } else {
                throw new IllegalArgumentException("Fillin character not valid in " + this.getClass()
                        .getName() + " object : " + getParameters().get(4).getStringValue(evaluator));
            }
            sign = getParameters().get(5).getStringValue(evaluator).toUpperCase();
            decimalMark = getParameters().get(6).getStringValue(evaluator).toUpperCase();
        } else {
            sign = getParameters().get(4).getStringValue(evaluator).toUpperCase();
            decimalMark = getParameters().get(5).getStringValue(evaluator).toUpperCase();
        }

        if (width >= 1) {
            format = new FormatDouble(width, decimals);
            format.noSeparateAfterDecimal();

            switch (alignment) {
                case FormatAlignment.Values.NO_JUSTIFY:
                    format.noJustify();
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
                    throw new IllegalArgumentException("Alignment not valid in " + this.getClass()
                            .getName() + " object : " + getParameters().get(3).getStringValue(evaluator));
            }

            if (fillCharacter != null) {
                format.setFill(fillCharacter);
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
                    throw new IllegalArgumentException("Sign format not valid in " + this.getClass()
                            .getName() + " object : " + getParameters().get(5).getStringValue(evaluator));
            }

            if (decimalMark.length() == 1) {
                if (decimalMark.charAt(0) == '.') {
                    format.pointDecimal();
                } else if (decimalMark.charAt(0) == ',') {
                    format.commaDecimal();
                }
            } else {
                throw new IllegalArgumentException("Decimal mark not valid in " + this.getClass()
                        .getName() + " object : " + getParameters().get(6).getStringValue(evaluator));
            }

            if (decimals == 0) {
                format.hideDecimalCharacter();
            }

        } else {
            throw new IllegalArgumentException("Width not valid in " + this.getClass()
                    .getName() + " object : " + getParameters().get(1).getStringValue(evaluator));
        }

        return format.formatted(d);
    }

    @Override
    public String operationAsText() {
        return "formatNumeric";
    }

}
