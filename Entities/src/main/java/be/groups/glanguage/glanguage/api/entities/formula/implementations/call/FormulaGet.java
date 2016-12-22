package be.groups.glanguage.glanguage.api.entities.formula.implementations.call;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnTypeConverter;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
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
    protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageEvaluationException {
        try {
            return (Boolean) getTargetedObject(evaluator);
        } catch (ClassCastException cce) {
            // TODO report evaluation error
            throw cce;
        } catch (GLanguageEvaluationException e) {
            // TODO report evaluation error
            throw e;
        }
    }

    @JsonIgnore
    @Transient
    @Override
    protected LocalDate doGetDateValue(Evaluator evaluator) throws GLanguageEvaluationException {
        try {
            return (LocalDate) getTargetedObject(evaluator);
        } catch (ClassCastException cce) {
            // TODO report evaluation error
            throw cce;
        } catch (GLanguageEvaluationException e) {
            // TODO report evaluation error
            throw e;
        }
    }

    @JsonIgnore
    @Transient
    @Override
    protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageEvaluationException {
        try {
            return (Duration) getTargetedObject(evaluator);
        } catch (ClassCastException cce) {
            // TODO report evaluation error
            throw cce;
        } catch (GLanguageEvaluationException e) {
            // TODO report evaluation error
            throw e;
        }
    }

    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageEvaluationException {
        try {
            return (Integer) getTargetedObject(evaluator);
        } catch (ClassCastException cce) {
            // TODO report evaluation error
            throw cce;
        } catch (GLanguageEvaluationException e) {
            // TODO report evaluation error
            throw e;
        }
    }

    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageEvaluationException {
        try {
            return (Double) getTargetedObject(evaluator);
        } catch (ClassCastException cce) {
            // TODO report evaluation error
            throw cce;
        } catch (GLanguageEvaluationException e) {
            // TODO report evaluation error
            throw e;
        }
    }

    @JsonIgnore
    @Transient
    @Override
    protected String doGetStringValue(Evaluator evaluator) throws GLanguageEvaluationException {
        try {
            return (String) getTargetedObject(evaluator);
        } catch (ClassCastException cce) {
            // TODO report evaluation error
            throw cce;
        } catch (GLanguageEvaluationException e) {
            // TODO report evaluation error
            throw e;
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
    private Object getTargetedObject(Evaluator evaluator) throws GLanguageEvaluationException {
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
