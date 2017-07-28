package be.groups.glanguage.glanguage.api.entities.formula.implementations.string;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Formula implementing substring operation on a string
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_SUBSTRING)
public class FormulaSubString extends AbstractNonTerminalFormula {

    public FormulaSubString() {
        super();
    }

    public FormulaSubString(FormulaDescription description,
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
     * Get the substring of the first parameter beginning at the second parameter index and ending at the third
     * parameter index, or at the end if the third parameter does not exist, as {@link String}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the substring of the first parameter beginning at the second parameter index and ending at the third
     * parameter index, or at the end if the third parameter does not exist, as {@link String}
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected String doGetStringValue(Evaluator evaluator) throws GLanguageException {
        String str;
        int beginIndex, endIndex;

        str = getParameters().get(0).getStringValue(evaluator);
        beginIndex = getParameters().get(1).getIntegerValue(evaluator) - 1;
        endIndex = getParameters().size() > 2 ? getParameters().get(2).getIntegerValue(evaluator) - 1 : str
                .length() - 1;

        if ((0 <= beginIndex) && (beginIndex <= endIndex) && (endIndex < str.length())) {
            return str.substring(beginIndex, endIndex + 1);
        } else {
            // TODO replace by GLanguageException
            throw new IllegalArgumentException("Bounds not valid in " + this.getClass()
                    .getName() + " object : string = " + str + " (length = " + str
                    .length() + ") , beginIndex = " + beginIndex + ", endIndex = " + endIndex);
        }
    }

    @Override
    public String asText() {
        return "subString(" + getParameters().get(0).asText() + "; " + getParameters().get(1)
                .asText() + "; " + getParameters().get(2).asText() + ")";
    }

}
