package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.utils.rounding.RoundingType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

/**
 * Abstract formula implementing arithmetic rounding operation on a number
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_ROUNDED)
public class FormulaRoundingArithmetic extends RoundingFormula {

    protected FormulaRoundingArithmetic() {
        super();
    }

    public FormulaRoundingArithmetic(FormulaDescription description,
                                     FormulaDescription precisionDescription,
                                     List<AbstractFormula> parameters,
                                     Evaluator evaluator) throws GLanguageException {
        super(description, precisionDescription, parameters, evaluator);
    }

    /**
     * Get the rounding type<br>
     * The rounding type of this type of formula is always {@link RoundingType#ARITHMETIC}
     *
     * @return always {@link RoundingType#ARITHMETIC}
     */
    @Override
    @Transient
    public RoundingType getRoundingType() {
        return RoundingType.ARITHMETIC;
    }

    @Override
    public String operationAsText() {
        return "round";
    }

}
