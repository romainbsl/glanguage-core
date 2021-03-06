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
@DiscriminatorValue(FormulaType.Values.F_BANKERS_ROUNDED)
public class FormulaRoundingBankers extends RoundingFormula {

    protected FormulaRoundingBankers() {
        super();
    }

    public FormulaRoundingBankers(FormulaDescription description,
                                  FormulaDescription precisionDescription,
                                  List<AbstractFormula> parameters,
                                  Evaluator evaluator) throws GLanguageException {
        super(description, precisionDescription, parameters, evaluator);
    }

    /**
     * Get the rounding type<br>
     * The rounding type of this type of formula is always {@link RoundingType#BANKERS}
     *
     * @return always {@link RoundingType#BANKERS}
     */
    @Override
    @Transient
    public RoundingType getRoundingType() {
        return RoundingType.BANKERS;
    }

    @Override
    public String operationAsText() {
        return "bankers_rounded";
    }

}
