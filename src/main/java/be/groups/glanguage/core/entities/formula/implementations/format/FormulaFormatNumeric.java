package be.groups.glanguage.core.entities.formula.implementations.format;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.utils.format.FormatAlignment;
import be.groups.glanguage.core.entities.utils.format.FormatDouble;
import be.groups.glanguage.core.entities.utils.format.FormatSign;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.formula.base.unable.evaluate.FormulaEvaluateTypeInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Formula implementing the formatting of a numeric value
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_FORMAT_NUMERIC)
public class FormulaFormatNumeric extends FormatFormula {

    public FormulaFormatNumeric() {
        super();
    }

    public FormulaFormatNumeric(FormulaDescription description,
                                List<AbstractFormula> parameters,
                                Evaluator evaluator) throws GLanguageException {
        super(description, parameters, evaluator);

        this.parameters = new ArrayList<>();
        this.parameters.addAll(parameters);
    }

    /**
     * Get the formatted value as {@link String} according to format parameters
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the formatted value as {@link String} according to a format parameters
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected String doGetStringValue(Evaluator evaluator) throws GLanguageException {
        try {
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
                    throw new IllegalArgumentException("Fillin character not valid : " + getParameters().get(4)
                        .getStringValue(evaluator));
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
                        throw new IllegalArgumentException("Alignment not valid : " + getParameters().get(3)
                            .getStringValue(evaluator));
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
                        throw new IllegalArgumentException("Sign format not valid : " + getParameters().get(5)
                            .getStringValue(evaluator));
                }

                if (decimalMark.length() == 1) {
                    if (decimalMark.charAt(0) == '.') {
                        format.pointDecimal();
                    } else if (decimalMark.charAt(0) == ',') {
                        format.commaDecimal();
                    }
                } else {
                    throw new IllegalArgumentException("Decimal mark not valid : " + getParameters().get(6)
                        .getStringValue(evaluator));
                }

                if (decimals == 0) {
                    format.hideDecimalCharacter();
                }

            } else {
                throw new IllegalArgumentException("Width not valid : " + getParameters().get(1).getStringValue
                    (evaluator));
            }

            return format.formatted(d);
        } catch (GLanguageException gle) {
            gle.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.STRING, null));
            throw gle;
        } catch (Exception e) {
            throw new GLanguageException(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.STRING, e
                .getMessage()));
        }
    }

    @Override
    public String operationAsText() {
        return "formatNumeric";
    }

}
