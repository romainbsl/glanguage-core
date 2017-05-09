package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@DiscriminatorValue(FormulaType.Values.F_DATE)
public class FormulaDate extends AbstractNonTerminalFormula {

    public FormulaDate() {
        super();
    }

    public FormulaDate(FormulaDescription description, List<AbstractFormula> parameters) throws GLanguageException {
        super(description, parameters);

        if (parameters == null) {
            throw new IllegalArgumentException("parameters must be non-null");
        }

        this.parameters = new ArrayList<>();
        this.parameters.addAll(parameters);
    }

    @Override
    protected LocalDate doGetDateValue(Evaluator evaluator) throws GLanguageException {
        LocalDate date = null;
        if (getParameters().size() == 1) {
            try {
                date = LocalDate.parse(getParameters().get(0).getStringValue(evaluator),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException dtpe) {
                throw new IllegalArgumentException("Parameter must reprensent a date formatted as \"dd/MM/yyyy\" : "
                        + getParameters().get(0).asText());
            }
        } else if (getParameters().size() == 3) {
            try {
                date = LocalDate.of(getParameters().get(2).getIntegerValue(evaluator),
                        getParameters().get(1).getIntegerValue(evaluator),
                        getParameters().get(0).getIntegerValue(evaluator));
            } catch (DateTimeParseException dtpe) {
                throw new IllegalArgumentException("Parameters must reprensent a valid date : " + getParameters().get(0)
                        .asText() + "/" + getParameters().get(1).asText() + "/" + getParameters().get(2).asText());
            }
        } else {
            throw new IllegalArgumentException("there should be 1 or 3 parameters but there are " + parameters.size());
        }
        return date;
    }

    @Override
    public String asText() {
        StringBuilder sb = new StringBuilder();
        sb.append("date(");
        sb.append(getParameters().get(0).asText());
        Iterator<AbstractFormula> itParameters = getParameters().listIterator(1);
        while (itParameters.hasNext()) {
            sb.append("; ");
            sb.append(itParameters.next().asText());
        }
        sb.append(")");
        return sb.toString();
    }

}
