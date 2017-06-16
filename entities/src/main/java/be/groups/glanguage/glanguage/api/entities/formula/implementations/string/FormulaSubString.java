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
