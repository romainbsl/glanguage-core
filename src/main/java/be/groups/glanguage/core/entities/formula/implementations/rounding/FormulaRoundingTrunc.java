package be.groups.glanguage.core.entities.formula.implementations.rounding;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.AbstractFormula;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.utils.rounding.RoundingType;
import be.groups.glanguage.core.error.exception.GLanguageException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Entity
@DiscriminatorValue(FormulaType.Values.F_TRUNC)
public class FormulaRoundingTrunc extends RoundingFormula {

    protected FormulaRoundingTrunc() {
        super();
    }

    public FormulaRoundingTrunc(FormulaDescription description,
                                FormulaDescription precisionDescription,
                                List<AbstractFormula> parameters,
                                Evaluator evaluator) throws GLanguageException {
        super(description, precisionDescription, parameters, evaluator);
    }

    /**
     * Get the rounding type<br>
     * The rounding type of this type of formula is always {@link RoundingType#TRUNC}
     *
     * @return always {@link RoundingType#TRUNC}
     */
    @Override
    @Transient
    public RoundingType getRoundingType() {
        return RoundingType.TRUNC;
    }

    @Override
    public String operationAsText() {
        return "trunc";
    }

}
