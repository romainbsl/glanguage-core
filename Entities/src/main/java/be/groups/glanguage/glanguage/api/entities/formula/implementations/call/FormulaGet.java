package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnTypeConverter;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = FormulaType.Values.C_GET)
public class FormulaGet extends CallFormula {

    private Object context;

    public FormulaGet() {
        super();
    }

    public FormulaGet(FormulaDescription description, FormulaDescription subFormulasDescription, FormulaReturnType
            returnType, List<String> identifiers, List<List<AbstractFormula>> parameters) {
        super(description);

        setConstantValue(String.valueOf(returnType.ordinal()));
        this.parameters = new ArrayList<>(identifiers.size());
        for (int i = 0; i < identifiers.size(); i++) {
            this.parameters.add(new FormulaPrimitive(subFormulasDescription, identifiers.get(i), parameters.get(i)));
        }
    }

    @Transient
    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        return new FormulaReturnTypeConverter().convertToEntityAttribute(Integer.valueOf(getConstantValue()));
    }

    @JsonIgnore
    @Transient
    @Override
    public Boolean getBooleanValue(Evaluator evaluator) {
        try {
            return (Boolean) getTargetedObject(evaluator);
        } catch (ClassCastException cce) {
            // TODO report evaluation error
            throw cce;
        }
    }

    @JsonIgnore
    @Transient
    @Override
    public LocalDate getDateValue(Evaluator evaluator) {
        try {
            return (LocalDate) getTargetedObject(evaluator);
        } catch (ClassCastException cce) {
            // TODO report evaluation error
            throw cce;
        }
    }

    @JsonIgnore
    @Transient
    @Override
    public Duration getDurationValue(Evaluator evaluator) {
        try {
            return (Duration) getTargetedObject(evaluator);
        } catch (ClassCastException cce) {
            // TODO report evaluation error
            throw cce;
        }
    }

    @JsonIgnore
    @Transient
    @Override
    public Integer getIntegerValue(Evaluator evaluator) {
        try {
            return (Integer) getTargetedObject(evaluator);
        } catch (ClassCastException cce) {
            // TODO report evaluation error
            throw cce;
        }
    }

    @JsonIgnore
    @Transient
    @Override
    public Double getNumericValue(Evaluator evaluator) {
        try {
            return (Double) getTargetedObject(evaluator);
        } catch (ClassCastException cce) {
            // TODO report evaluation error
            throw cce;
        }
    }

    @JsonIgnore
    @Transient
    @Override
    public String getStringValue(Evaluator evaluator) {
        try {
            return (String) getTargetedObject(evaluator);
        } catch (ClassCastException cce) {
            // TODO report evaluation error
            throw cce;
        }
    }

    @JsonIgnore
    @Transient
    public boolean isBranched() {
        return context != null && super.isBranched();
    }

    @Override
    public String asText() {
        StringBuilder sb = new StringBuilder();
        sb.append("get ");

        FormulaReturnTypeConverter converter = new FormulaReturnTypeConverter();
        sb.append(converter.convertToEntityAttribute(Integer.valueOf(getConstantValue())));
        sb.append(" ");

        for (int i = 0; i < getParameters().size(); i++) {
            sb.append(getParameters().get(i).asText());
            if (i < getParameters().size() - 1) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    /**
     * @param context the context to set
     */
    public void setContext(Object context) {
        this.context = context;
    }

    @JsonIgnore
    @Transient
    private Object getTargetedObject(Evaluator evaluator) {
        Object result;
        if (evaluator != null) {
            result = evaluator.getContext();
        } else {
            result = context;
        }

        if(result == null) {
            throw new IllegalAccessError("Cannot invoke getTargetetObject() method on " + this.getClass()
                    .getName() + " object while context is not set - while branching is not done - (id : " + this
                    .getId() + ")");
        }

        for (AbstractFormula primitive : getParameters()) {
            result = ((FormulaPrimitive) primitive).getTargetedObject(result, evaluator);
        }
        return result;
    }

}
