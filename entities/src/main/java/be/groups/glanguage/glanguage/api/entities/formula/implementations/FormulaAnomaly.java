package be.groups.glanguage.glanguage.api.entities.formula.implementations;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(FormulaType.Values.F_PUT_TEXT)
public class FormulaAnomaly extends AbstractNonTerminalFormula {

    public FormulaAnomaly() {
        super();
    }

    public FormulaAnomaly(FormulaDescription description, List<AbstractFormula> parameters) throws GLanguageException {
		super(description, parameters);
//        try {
//            if (parameters == null) {
//                throw new GLanguageException(new FormulaNullParameterListInnerError(this, null, "constructor"));
//            }
//            if (!(parameters.size() == 1 || parameters.size() == 2)) {
//                throw new GLanguageException(new FormulaParameterConbinationWrongParameterNumberInnerError(this,
//                                                                                                           null,
//                                                                                                           "constructor",
//                                                                                                           parameters.size(),
//                                                                                                           Arrays.asList(1, 2)));
//            }
//            if (parameters.get(0) == null) {
//                throw new GLanguageException(new FormulaNullParameterInnerError(this, null, "constructor", 1));
//            }
//            if (parameters.size() == 2 && parameters.get(1) == null) {
//                throw new GLanguageException(new FormulaNullParameterInnerError(this, null, "constructor", 2));
//            }
//            if (!(parameters.get(0).getReturnType(null).equals(FormulaReturnType.INTEGER) || parameters.get(0)
//                    .getReturnType(null).equals(FormulaReturnType.STRING))) {
//                throw new GLanguageException(new FormulaWrongParameterTypeInnerError(this,
//                                                                                     null,
//                                                                                     "constructor",
//                                                                                     "code",
//                                                                                     1,
//                                                                                     parameters.get(0)
//                                                                                             .getReturnType(null),
//                                                                                     Arrays.asList(FormulaReturnType
//                                                                                                           .INTEGER,
//                                                                                                   FormulaReturnType
//                                                                                                           .STRING)));
//            }
//            if (parameters.size() > 1 && !parameters.get(1).getReturnType(null).equals(FormulaReturnType.STRING)) {
//                throw new GLanguageException(new FormulaWrongParameterTypeInnerError(this,
//                                                                                     null,
//                                                                                     "constructor",
//                                                                                     "message",
//                                                                                     2,
//                                                                                     parameters.get(0)
//                                                                                             .getReturnType(null),
//                                                                                     Arrays.asList(FormulaReturnType
//                                                                                                           .STRING)));
//            }
//        } catch (GLanguageException e) {
//            e.getError().setOuterError(new FormulaUnableToInstantiateInnerError(this));
//            throw e;
//        }
        this.parameters = new ArrayList<>();
        this.parameters.addAll(parameters);
    }

    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) {
        reportAnomaly();
        return 0;
    }

    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) {
        reportAnomaly();
        return 0.0;
    }

    @JsonIgnore
    @Transient
    @Override
    protected String doGetStringValue(Evaluator evaluator) {
        reportAnomaly();
        return "";
    }

    @JsonIgnore
    @Transient
    @Override
    protected LocalDate doGetDateValue(Evaluator evaluator) {
        reportAnomaly();
        return LocalDate.MIN;
    }

    @JsonIgnore
    @Transient
    @Override
    protected Duration doGetDurationValue(Evaluator evaluator) {
        reportAnomaly();
        return Duration.ZERO;
    }

    /**
     * Report anomaly
     */
    private void reportAnomaly() {
        // TODO
    }

    @Override
    public String asText() {
        StringBuilder sb = new StringBuilder();
        sb.append("putText(");
        if (getParameters() != null && !getParameters().isEmpty()) {
            sb.append(getParameters().get(0).asText());
            if (getParameters().size() > 1) {
                sb.append("; ");
                sb.append(getParameters().get(1).asText());
            }
        } else {
            sb.append("NO_KNOWN_PARAMETER");
        }
        sb.append(")");
        return sb.toString();
    }

}