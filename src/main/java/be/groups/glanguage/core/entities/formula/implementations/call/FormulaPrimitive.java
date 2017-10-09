package be.groups.glanguage.core.entities.formula.implementations.call;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.formula.base.cannot.invoke.targets.FormulaCannotInvokeTargetObjectInnerError;
import be.groups.glanguage.core.error.formula.base.parameter.FormulaNullParameterInnerError;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Formula implementing a part of a call to a facade method<br>
 * Each part of a call chain implemented by {@link FormulaGet} results in one {@link FormulaPrimitive} :<br>
 * A call chain like this one : "a().b(c).d" will result in 3 {@link FormulaPrimitive} :
 * <ol>
 * <li>1 for "a()"</li>
 * <li>1 for "b(c)"</li>
 * <li>1 for "d()"</li>
 * </ol>
 * This formula is of type {@link FormulaType#C_PRIMITIVE}
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(value = FormulaType.Values.C_PRIMITIVE)
public class FormulaPrimitive extends CallFormula {

    public FormulaPrimitive() {
        super();
    }

    public FormulaPrimitive(FormulaDescription description,
                            String primitive,
                            List<AbstractFormula> parameters,
                            Evaluator evaluator) throws GLanguageException {
        super(description, evaluator);

        if (primitive == null || primitive.isEmpty()) {
            throw new GLanguageException(new FormulaNullParameterInnerError(this, evaluator, "constructor", 1));
        }
        setConstantValue(primitive);
        if (parameters != null) {
            this.parameters = new ArrayList<>();
            this.parameters.addAll(parameters);
        }
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
     * Get the object resulting of the call to a method
     *
     * @param object    the object on which to call the method
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the object resulting of the call to the method
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the method does not exist
     */
    protected Object getTargetedObject(Object object, Evaluator evaluator) throws GLanguageException {
        AbstractFormula[] parameters = null;
        if (getParameters() != null) {
            parameters = new AbstractFormula[getParameters().size()];
            parameters = getParameters().toArray(parameters);
        }
        try {
            return callFunctionAny(object, getConstantValue(), parameters, evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaCannotInvokeTargetObjectInnerError(this,
                                                                                     evaluator,
                                                                                     "Method is " + "undefined"));
            throw e;
        }
    }

    @Override
    public String asText() {
        StringBuilder sb = new StringBuilder();
        sb.append(getConstantValue());
        if (getParameters().size() > 0) {
            sb.append("(");
            sb.append(getParameters().get(0).asText());
            Iterator<AbstractFormula> itParameters = getParameters().listIterator(1);
            while (itParameters.hasNext()) {
                sb.append("; ");
                sb.append(itParameters.next().asText());
            }
            sb.append(")");
        }
        return sb.toString();
    }

}
