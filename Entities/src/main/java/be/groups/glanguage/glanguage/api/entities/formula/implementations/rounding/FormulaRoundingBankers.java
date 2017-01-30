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
import java.util.List;

@Entity
@DiscriminatorValue(FormulaType.Values.F_BANKERS_ROUNDED)
public class FormulaRoundingBankers extends RoundingFormula {

    protected FormulaRoundingBankers() {
        super();
    }

    public FormulaRoundingBankers(FormulaDescription description,
                                  FormulaDescription precisionFormulaDescription,
                                  List<AbstractFormula> parameters) throws GLanguageException {
        super(description, precisionFormulaDescription, parameters);
    }

    @Override
    @Transient
    public RoundingType getRoundingType() {
        return RoundingType.BANKERS;
    }

    @Override
    @Transient
    public AbstractFormula getDefaultPrecision(FormulaDescription description) throws GLanguageException {
        try {
            return new FormulaTerminalInteger(description, "2");
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaUnableToInstantiateInnerError(this, "Unable to get precision"));
            throw e;
        }
    }

    @Override
    public String operationAsText() {
        return "bankers_rounded";
    }

}
