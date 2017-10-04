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

/**
 * Formula implementing the ability to report an anomaly
 *
 * @author michotte
 */
@Entity
@DiscriminatorValue(FormulaType.Values.F_PUT_TEXT)
public class FormulaAnomaly extends AbstractNonTerminalFormula {

    public FormulaAnomaly() {
        super();
    }

    public FormulaAnomaly(FormulaDescription description,
                          List<AbstractFormula> parameters,
                          Evaluator evaluator) throws GLanguageException {
        super(description, parameters, evaluator);

        this.parameters = new ArrayList<>();
        this.parameters.addAll(parameters);
    }

    /**
     * Get the value as {@link Integer} and report the anomaly
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return always 0
     */
    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) {
        reportAnomaly();
        return 0;
    }

    /**
     * Get the value as {@link Integer} and report the anomaly
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return always 0.0
     */
    @JsonIgnore
    @Transient
    @Override
    protected Double doGetNumericValue(Evaluator evaluator) {
        reportAnomaly();
        return 0.0;
    }

    /**
     * Get the value as {@link Integer} and report the anomaly
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return always "" (empty string)
     */
    @JsonIgnore
    @Transient
    @Override
    protected String doGetStringValue(Evaluator evaluator) {
        reportAnomaly();
        return "";
    }

    /**
     * Get the value as {@link Integer} and report the anomaly
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return always {@link LocalDate#MIN}
     */
    @JsonIgnore
    @Transient
    @Override
    protected LocalDate doGetDateValue(Evaluator evaluator) {
        reportAnomaly();
        return LocalDate.MIN;
    }

    /**
     * Get the value as {@link Integer} and report the anomaly
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return always {@link Duration#ZERO}
     */
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
