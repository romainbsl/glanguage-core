package be.groups.glanguage.core.entities.formula.implementations.format;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.utils.format.FormatAlignment;
import be.groups.glanguage.core.entities.utils.format.FormatInteger;
import be.groups.glanguage.core.entities.utils.format.FormatSign;
import be.groups.glanguage.core.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Formula implementing the formatting of an integer value
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_FORMAT_INTEGER)
public class FormulaFormatInteger extends FormatFormula {

    public FormulaFormatInteger() {
        super();
    }

    public FormulaFormatInteger(FormulaDescription description,
                                List<AbstractFormula> parameters,
                                Evaluator evaluator) throws GLanguageException {
        super(description, parameters, evaluator);

        if (parameters == null) {
            throw new IllegalArgumentException("parameters must be non-null");
        }

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
        FormatInteger format = null;
        int i, width;
        String alignment;
        Character fillCharacter = null;
        String sign;

        i = getParameters().get(0).getIntegerValue(evaluator);
        width = getParameters().get(1).getIntegerValue(evaluator);
        alignment = getParameters().get(2).getStringValue(evaluator).toUpperCase();
        if (!alignment.equals(FormatAlignment.Values.NO_JUSTIFY)) {
            if (getParameters().get(3).getStringValue(evaluator).length() == 1) {
                fillCharacter = getParameters().get(3).getStringValue(evaluator).charAt(0);
            } else {
                throw new IllegalArgumentException("Fillin character not valid in " + this.getClass()
                        .getName() + " object : " + getParameters().get(3).getStringValue(evaluator));
            }
            sign = getParameters().get(4).getStringValue(evaluator).toUpperCase();
        } else {
            sign = getParameters().get(3).getStringValue(evaluator).toUpperCase();
        }

        if (width >= 1) {
            format = new FormatInteger(width);

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
                            .getName() + " object : " + getParameters().get(2).getStringValue(evaluator));
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
                            .getName() + " object : " + getParameters().get(4).getStringValue(evaluator));
            }

        } else {
            throw new IllegalArgumentException("Width not valid in " + this.getClass()
                    .getName() + " object : " + getParameters().get(1).getStringValue(evaluator));
        }

        return format.format(i);
    }

    @Override
    public String operationAsText() {
        return "formatInteger";
    }

}
