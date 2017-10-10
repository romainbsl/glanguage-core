package be.groups.glanguage.core.entities.formula.implementations.call;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnTypeConverter;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.GLanguageErrorRegistry;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.formula.FormulaInnerError;
import be.groups.glanguage.core.error.formula.base.cannot.invoke.targets.FormulaCannotInvokeTargetObjectInnerError;
import be.groups.glanguage.core.error.formula.base.unable.evaluate.FormulaEvaluateTypeInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Formula implementing a call to a facade method<br>
 * This formula is of type {@link FormulaType#C_GET}
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(value = FormulaType.Values.C_GET)
public class FormulaGet extends CallFormula {

    private Object context;

    public FormulaGet() {
        super();
    }

    public FormulaGet(FormulaDescription description,
                      FormulaDescription subFormulasDescription,
                      FormulaReturnType returnType,
                      List<String> identifiers,
                      List<List<AbstractFormula>> parameters,
                      Evaluator evaluator) throws GLanguageException {
        super(description, evaluator);

        setConstantValue(String.valueOf(returnType.ordinal()));
        this.parameters = new ArrayList<>(identifiers.size());
        for (int i = 0; i < identifiers.size(); i++) {
            try {
                this.parameters.add(new FormulaPrimitive(subFormulasDescription,
                                                         identifiers.get(i),
                                                         parameters.get(i),
                                                         evaluator));
            } catch (GLanguageException e) {
                e.getError().setOuterError(new FormulaInnerError(GLanguageErrorRegistry.FORMULA_INNER_ERROR,
                                                                 this,
                                                                 evaluator,
                                                                 "constructor",
                                                                 "Bad call chain"));
                throw e;
            }
        }
    }

    @JsonIgnore
    @Transient
    public Object getContext() {
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(Object context) {
        this.context = context;
    }

    @Override
    public boolean isValid(Evaluator evaluator) {
        return true;
    }

    @Override
    public void validate(List<AbstractFormula> parameters, Evaluator evaluator) throws GLanguageException {
        // do nothing
    }

    /**
     * Get the return type<br>
     * The return type of this type of formula is determined its constant value
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return the return type corresponding to its constant value
     */
    @Transient
    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        return new FormulaReturnTypeConverter().convertToEntityAttribute(Integer.valueOf(getConstantValue()));
    }

    /**
     * Get the result of calling a facade method of type {@link Boolean}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the result of calling a facade method of type {@link Boolean}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the method is not of type
     *                            {@link Boolean}
     */
    @JsonIgnore
    @Transient
    @Override
    protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
        try {
            return (Boolean) getTargetedObject(evaluator);
        } catch (ClassCastException cce) {
            throw new GLanguageException(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.BOOLEAN, cce
                .getMessage()));
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.BOOLEAN, null));
            throw e;
        }
    }

    /**
     * Get the result of calling a facade method of type {@link LocalDate}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the result of calling a facade method of type {@link LocalDate}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the method is not of type
     *                            {@link LocalDate}
     */
    @JsonIgnore
    @Transient
    @Override
    protected LocalDate doGetDateValue(Evaluator evaluator) throws GLanguageException {
        try {
            return (LocalDate) getTargetedObject(evaluator);
        } catch (ClassCastException cce) {
            throw new GLanguageException(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.DATE, cce
                .getMessage()));
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.DATE, null));
            throw e;
        }
    }

    /**
     * Get the result of calling a facade method of type {@link Duration}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the result of calling a facade method of type {@link Duration}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the method is not of type
     *                            {@link Duration}
     */
    @JsonIgnore
    @Transient
    @Override
    protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageException {
        try {
            return (Duration) getTargetedObject(evaluator);
        } catch (ClassCastException cce) {
            throw new GLanguageException(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.DURATION, cce
                .getMessage()));
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.DURATION, null));
            throw e;
        }
    }

    /**
     * Get the result of calling a facade method of type {@link Integer}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the result of calling a facade method of type {@link Integer}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the method is not of type
     *                            {@link Integer}
     */
    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        try {
            return (Integer) getTargetedObject(evaluator);
        } catch (ClassCastException cce) {
            throw new GLanguageException(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.INTEGER, cce
                .getMessage()));
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.INTEGER, null));
            throw e;
        }
    }

    /**
     * Get the result of calling a facade method of type {@link Double}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the result of calling a facade method of type {@link Double}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the method is not of type
     *                            {@link Double}
     */
    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        try {
            return (Double) getTargetedObject(evaluator);
        } catch (ClassCastException cce) {
            throw new GLanguageException(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.NUMERIC, cce
                .getMessage()));
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.NUMERIC, null));
            throw e;
        }
    }

    /**
     * Get the result of calling a facade method of type {@link String}
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the result of calling a facade method of type {@link String}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the method is not of type
     *                            {@link String}
     */
    @JsonIgnore
    @Transient
    @Override
    protected String doGetStringValue(Evaluator evaluator) throws GLanguageException {
        try {
            return (String) getTargetedObject(evaluator);
        } catch (ClassCastException cce) {
            throw new GLanguageException(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.STRING, cce
                .getMessage()));
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.STRING, null));
            throw e;
        }
    }

    @Override
    public String asText() {
        StringBuilder sb = new StringBuilder();
        sb.append("get ");

        FormulaReturnTypeConverter converter = new FormulaReturnTypeConverter();
        sb.append(converter.convertToEntityAttribute(Integer.valueOf(getConstantValue())));
        sb.append(" ");

        sb.append(getTargetChain());

        return sb.toString();
    }

    @JsonIgnore
    @Transient
    private String getTargetChain() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getParameters().size(); i++) {
            sb.append(getParameters().get(i).asText());
            if (i < getParameters().size() - 1) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    /**
     * Get the object resulting of the call to a facade method
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the object resulting of the call to the facade method
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the context is not known
     */
    @JsonIgnore
    @Transient
    private Object getTargetedObject(Evaluator evaluator) throws GLanguageException {
        Object result;
        if (evaluator != null) {
            result = evaluator.getContext();
        } else {
            result = getContext();
        }

        if (result == null) {
            throw new GLanguageException(new FormulaCannotInvokeTargetObjectInnerError(this,
                                                                                       evaluator,
                                                                                       "Context is unknown"));
        }

        for (AbstractFormula primitive : getParameters()) {
            try {
                result = ((FormulaPrimitive) primitive).getTargetedObject(result, evaluator);
            } catch (GLanguageException e) {
                e.getError().setOuterError(new FormulaCannotInvokeTargetObjectInnerError(this,
                                                                                         evaluator,
                                                                                         "Unable to compute " +
                                                                                                 "target chain : "
                                                                                             + getTargetChain()));
                throw e;
            }
        }
        return result;
    }

}
