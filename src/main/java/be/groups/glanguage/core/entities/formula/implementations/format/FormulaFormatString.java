package be.groups.glanguage.core.entities.formula.implementations.format;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.utils.format.FormatAlignment;
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
 * Formula implementing the formatting of an string value
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_FORMAT_STRING)
public class FormulaFormatString extends FormatFormula {

    public FormulaFormatString() {
        super();
    }

    public FormulaFormatString(FormulaDescription description,
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
            String result;
            StringBuilder sb = new StringBuilder();
            String str = getParameters().get(0).getStringValue(evaluator);
            int width = getParameters().get(1).getIntegerValue(evaluator);
            String alignment = getParameters().get(2).getStringValue(evaluator).toUpperCase();
            String fillString = getParameters().get(3).getStringValue(evaluator);
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
                        throw new IllegalArgumentException("Alignment not valid : " + getParameters().get(2)
                            .getStringValue(evaluator));
                }
            }
            result = sb.toString();
            return result;
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
        return "formatString";
    }

}
