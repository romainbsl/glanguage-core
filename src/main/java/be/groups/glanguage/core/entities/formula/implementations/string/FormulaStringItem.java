package be.groups.glanguage.core.entities.formula.implementations.string;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Formula implementing the operation to get an item of a string specifying a separator character and an index
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_STRING_ITEM)
public class FormulaStringItem extends AbstractNonTerminalFormula {

    public FormulaStringItem() {
        super();
    }

    public FormulaStringItem(FormulaDescription description,
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
     * Get the item of the parameter as {@link String}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the item of the parameter as {@link String}
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    protected String doGetStringValue(Evaluator evaluator) throws GLanguageException {
        String str, separatorString, separatorRegex;
        int index;
        String[] items;

        str = getParameters().get(0).getStringValue(evaluator);
        separatorString = getParameters().get(1).getStringValue(evaluator);
        index = getParameters().get(2).getIntegerValue(evaluator);
        if (separatorString.isEmpty() || index <= 0) {
            return "";
        } else {
            if (separatorString.equals("|")) { // '|' is a special character in regex, it must be
                // escaped
                separatorRegex = "\\" + separatorString.charAt(0);
            } else {
                separatorRegex = "" + separatorString.charAt(0);
            }
            items = str.split(separatorRegex);

            if (items.length <= 0 || !(0 <= (index - 1) && (index - 1) < items.length)) {
                return "";
            } else {
                return items[index - 1];
            }
        }
    }

    @Override
    public String asText() {
        return "stringItem(" + getParameters().get(0).asText() + "; " + getParameters().get(1)
                .asText() + "; " + getParameters().get(2).asText() + ")";
    }

}
