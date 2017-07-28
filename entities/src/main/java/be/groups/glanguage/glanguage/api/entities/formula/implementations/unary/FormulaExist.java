package be.groups.glanguage.glanguage.api.entities.formula.implementations.unary;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Formula implementing the logical exist operation
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.OP_EXIST)
public class FormulaExist extends UnaryFormula {

    protected FormulaExist() {
        super();
    }

    public FormulaExist(FormulaDescription description,
                        AbstractFormula child,
                        Evaluator evaluator) throws GLanguageException {
        super(description, child, evaluator);
    }

    /**
     * Get the value as {@link Boolean}<br>
     * The value depends on the nullability of the value of the parameter<br>
     * If the value of the parameter is not null, return true. Else, return false.
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return true if the value of the parameter is not null, false otherwise
     * @throws GLanguageException if an error occurs during the evaluation process
     */
    @JsonIgnore
    @Transient
    @Override
    public Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
        return getParameters().get(0).getValue(evaluator) != null;
    }

    @Override
    public String operationAsText() {
        return "?";
    }
}
