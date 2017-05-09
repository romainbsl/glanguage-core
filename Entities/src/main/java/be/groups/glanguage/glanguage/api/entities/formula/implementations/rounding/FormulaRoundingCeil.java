package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.utils.rounding.RoundingType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.unable.instantiate.FormulaUnableToInstantiateInnerError;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(FormulaType.Values.F_CEIL)
public class FormulaRoundingCeil extends RoundingFormula {

    protected FormulaRoundingCeil() {
        super();
    }

    public FormulaRoundingCeil(FormulaDescription description,
                               FormulaDescription precisionFormulaDescription,
                               List<AbstractFormula> parameters) throws GLanguageException {
        super(description, precisionFormulaDescription, parameters);
    }

    @Override
    @Transient
    public RoundingType getRoundingType() {
        return RoundingType.CEIL;
    }

    @Override
    public String operationAsText() {
        return "ceil";
    }

}
