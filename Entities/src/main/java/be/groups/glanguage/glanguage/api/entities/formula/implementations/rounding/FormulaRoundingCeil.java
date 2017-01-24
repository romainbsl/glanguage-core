package be.groups.glanguage.glanguage.api.entities.formula.implementations.rounding;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalInteger;
import be.groups.glanguage.glanguage.api.entities.utils.rounding.RoundingType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.unable.instantiate.FormulaUnableToInstantiateInnerError;

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
                               AbstractFormula parameter,
                               AbstractFormula precision) throws GLanguageException {
        super(description, precisionFormulaDescription, parameter, precision);
    }

    @Override
    @Transient
    public RoundingType getRoundingType() {
        return RoundingType.CEIL;
    }

    @Override
    @Transient
    public AbstractFormula getDefaultPrecision(FormulaDescription description) throws GLanguageException {
        try {
            return new FormulaTerminalInteger(description, "1");
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaUnableToInstantiateInnerError(this, "Unable to get precision"));
            throw e;
        }
    }

    @Override
    public String operationAsText() {
        return "ceil";
    }

}
