package be.groups.glanguage.glanguage.api.entities.formula.implementations.format;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(FormulaType.Values.F_FORMAT_DATE)
public class FormulaFormatDate extends FormatFormula {

    public FormulaFormatDate() {
        super();
    }

    public FormulaFormatDate(FormulaDescription description, List<AbstractFormula> parameters) {
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
        return getParameters().get(0).getDateValue(evaluator)
                .format(DateTimeFormatter.ofPattern(getParameters().get(1).getStringValue(null)));
    }

    @Override
    public String operationAsText() {
        return "formatDate";
    }

}
