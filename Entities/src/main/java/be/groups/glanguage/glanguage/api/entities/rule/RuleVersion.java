package be.groups.glanguage.glanguage.api.entities.rule;

import be.groups.common.entities.util.LocalDateConverter;
import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageEvaluationException;
import be.groups.glanguage.glanguage.api.error.rule.RuleVersionUnableToCheckApplicabilityInnerError;
import be.groups.glanguage.glanguage.api.error.rule.RuleVersionUnableToEvaluateInnerError;
import be.groups.glanguage.glanguage.api.error.rule.RuleVersionUnableToEvaluateTypeInnerError;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;
import java.util.SortedSet;

/**
 * A RuleVersion is a version of a RuleDefintion that is effective between two
 * dates inclusively
 *
 * @author michotte
 */
@Table(name = "RULE_VERSION")
@Entity
public class RuleVersion implements Comparable<RuleVersion> {

    /**
     * RuleDescription
     */
    private RuleDescription ruleDescription;

    /**
     * Date until which this is effective inclusively
     */
    private LocalDate effectivityEndDate;

    /**
     * Date from which this is effective inclusively
     */
    private LocalDate effectivityStartDate;

    /**
     * Applicability condition
     */
    private AbstractFormula applicabilityCondition;

    /**
     * Formula
     */
    private AbstractFormula formula;

    /**
     * Technical unique ID
     */
    private int id;

    /**
     * Rounding precision
     */
    private Double roundingPrecision;

    /**
     * Rounding type
     */
    private RoundingType roundingType;

    /**
     * The RuleDefintion of which this is a version of
     */
    private RuleDefinition ruleDefinition;

    /**
     * Rule type
     */
    private RuleType ruleType;

    /**
     * Group items
     */
    private SortedSet<RuleGroupItem> groupItems;

    /**
     * Value
     */
    private Object value;

    /**
     * Version
     */
    private String version;

    /**
     * Set of RuleSetVersion this is included in
     */
    private Set<RuleSetVersion> ruleSetVersions;

    public RuleVersion() {
        super();
    }

    /**
     * @return the ruleDescription
     */
    @ManyToOne
    @JoinColumn(name = "RULE_DESCRIPTION_ID")
    public RuleDescription getRuleDescription() {
        return ruleDescription;
    }

    /**
     * Get the code identifying this Rule
     *
     * @return the code of this Rule
     */
    @Transient
    public String getCode() {
        return ruleDescription.getCode();
    }

    @Column(name = "EFFECTIVITY_START_DATE")
    @Convert(converter = LocalDateConverter.class)
    public LocalDate getEffectivityStartDate() {
        return effectivityStartDate;
    }

    @Column(name = "EFFECTIVITY_END_DATE")
    @Convert(converter = LocalDateConverter.class)
    public LocalDate getEffectivityEndDate() {
        return effectivityEndDate;
    }

    /**
     * @return the applicabilityCondition
     */
    @ManyToOne
    @JoinColumn(name = "APPLICABILITY_CONDITION_ID")
    public AbstractFormula getApplicabilityCondition() {
        return applicabilityCondition;
    }

    /**
     * Get the Formula of this Rule
     *
     * @return the Formula of this rule
     */
    @ManyToOne
    @JoinColumn(name = "FORMULA_ID")
    public AbstractFormula getFormula() {
        return formula;
    }

    /**
     * @return the id
     */
    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    /**
     * Get the precision
     *
     * @return the precision
     */
    @Column(name = "ROUNDING_PRECISION")
    public Double getRoundingPrecision() {
        return roundingPrecision;
    }

    /**
     * Get the rounding type
     *
     * @return the rounding type
     */
    @Column(name = "ROUNDING_TYPE_ID", insertable = false, updatable = false)
    @Convert(converter = RoundingTypeConverter.class)
    public RoundingType getRoundingType() {
        return roundingType;
    }

    /**
     * @return the definition
     */
    @ManyToOne
    @JoinColumn(name = "RULE_DEFINITION_ID")
    public RuleDefinition getRuleDefinition() {
        return ruleDefinition;
    }

    /**
     * @return the ruleType
     */
    @Column(name = "RULE_TYPE")
    @Enumerated(EnumType.ORDINAL)
    public RuleType getRuleType() {
        return ruleType;
    }

    /**
     * @return the groupItems
     */
    @OneToMany(mappedBy = "groupRule", fetch = FetchType.EAGER)
    @OrderBy(value = "SEQUENCE_NUMBER ASC\n")
    public SortedSet<RuleGroupItem> getGroupItems() {
        return groupItems;
    }

    /**
     * @return the version
     */
    @Column(name = "VERSION")
    public String getVersion() {
        return version;
    }

    /**
     * @return the ruleSetVersions
     */
    @ManyToMany(mappedBy = "ruleVersions")
    public Set<RuleSetVersion> getRuleSetVersions() {
        return ruleSetVersions;
    }

    @Transient
    public Object getValue() throws GLanguageEvaluationException {
        return getValue(null);
    }

    @Transient
    public Object getValue(Evaluator evaluator) throws GLanguageEvaluationException {
        try {
            switch (getReturnType(evaluator)) {
                case BOOLEAN:
                    return getBooleanValue(evaluator);
                case DATE:
                    return getDateValue(evaluator);
                case DURATION:
                    return getDurationValue(evaluator);
                case INTEGER:
                    return getIntegerValue(evaluator);
                case NUMERIC:
                    return getNumericValue(evaluator);
                case STRING:
                    return getStringValue(evaluator);
                default:
                    return null;
            }
        } catch (GLanguageEvaluationException e) {
            e.getError().setOuterError(new RuleVersionUnableToEvaluateInnerError(this, evaluator));
            throw e;
        }
    }

    @Transient
    public Boolean getBooleanValue() throws GLanguageEvaluationException {
        return getBooleanValue(null);
    }

    @Transient
    public Boolean getBooleanValue(Evaluator evaluator) throws GLanguageEvaluationException {
        Boolean val;
        if (evaluator != null && evaluator.isRuleVersionEvaluated(this)) {
            val = (Boolean) evaluator.getRuleVersionValue(this);
        } else if (evaluator == null && value != null) {
            val = (Boolean) value;
        } else {
            try {
                val = formula.getBooleanValue(evaluator);
            } catch (GLanguageEvaluationException e) {
                e.getError().setOuterError(new RuleVersionUnableToEvaluateTypeInnerError(this,
                        evaluator,
                        "getBooleanValue"));
                throw e;
            }
        }

        if (evaluator != null || value == null) {
            setValue(val, evaluator);
        }
        return val;
    }

    @Transient
    public LocalDate getDateValue() throws GLanguageEvaluationException {
        return getDateValue(null);
    }

    @Transient
    public LocalDate getDateValue(Evaluator evaluator) throws GLanguageEvaluationException {
        LocalDate val;
        if (evaluator != null && evaluator.isRuleVersionEvaluated(this)) {
            val = (LocalDate) evaluator.getRuleVersionValue(this);
        } else if (evaluator == null && value != null) {
            val = (LocalDate) value;
        } else {
            try {
                val = formula.getDateValue(evaluator);
            } catch (GLanguageEvaluationException e) {
                e.getError().setOuterError(new RuleVersionUnableToEvaluateTypeInnerError(this,
                        evaluator,
                        "getDateValue"));
                throw e;
            }
        }

        if (evaluator != null || value == null) {
            setValue(val, evaluator);
        }
        return val;
    }

    @Transient
    public Duration getDurationValue() throws GLanguageEvaluationException {
        return getDurationValue(null);
    }

    @Transient
    public Duration getDurationValue(Evaluator evaluator) throws GLanguageEvaluationException {
        Duration val;
        if (evaluator != null && evaluator.isRuleVersionEvaluated(this)) {
            val = (Duration) evaluator.getRuleVersionValue(this);
        } else if (evaluator == null && value != null) {
            val = (Duration) value;
        } else {
            try {
                val = formula.getDurationValue(evaluator);
            } catch (GLanguageEvaluationException e) {
                e.getError().setOuterError(new RuleVersionUnableToEvaluateTypeInnerError(this,
                        evaluator,
                        "getDurationValue"));
                throw e;
            }
        }

        if (evaluator != null || value == null) {
            setValue(val, evaluator);
        }
        return val;
    }

    @Transient
    public String getStringValue() throws GLanguageEvaluationException {
        return getStringValue(null);
    }

    @Transient
    public String getStringValue(Evaluator evaluator) throws GLanguageEvaluationException {
        String val;
        if (evaluator != null && evaluator.isRuleVersionEvaluated(this)) {
            val = (String) evaluator.getRuleVersionValue(this);
        } else if (evaluator == null && value != null) {
            val = (String) value;
        } else {
            try {
                val = formula.getStringValue(evaluator);
            } catch (GLanguageEvaluationException e) {
                e.getError().setOuterError(new RuleVersionUnableToEvaluateTypeInnerError(this,
                        evaluator,
                        "getStringValue"));
                throw e;
            }
        }

        if (evaluator != null || value == null) {
            setValue(val, evaluator);
        }
        return val;
    }

    @Transient
    public Integer getIntegerValue() throws GLanguageEvaluationException {
        return getIntegerValue(null);
    }

    @Transient
    public Integer getIntegerValue(Evaluator evaluator) throws GLanguageEvaluationException {
        Integer val;
        if (evaluator != null && evaluator.isRuleVersionEvaluated(this)) {
            val = (Integer) evaluator.getRuleVersionValue(this);
        } else if (evaluator == null && value != null) {
            val = (Integer) value;
        } else {
            try {
                val = doGetIntegerValue(evaluator);
            } catch (GLanguageEvaluationException e) {
                e.getError().setOuterError(new RuleVersionUnableToEvaluateTypeInnerError(this,
                        evaluator,
                        "getIntegerValue"));
                throw e;
            }
        }

        if (evaluator != null || value == null) {
            setValue(val, evaluator);
        }
        return val;
    }

    @Transient
    public Double getNumericValue() throws GLanguageEvaluationException {
        return getNumericValue(null);
    }

    @Transient
    public Double getNumericValue(Evaluator evaluator) throws GLanguageEvaluationException {
        Double val;
        if (evaluator != null && evaluator.isRuleVersionEvaluated(this)) {
            val = (Double) evaluator.getRuleVersionValue(this);
        } else if (evaluator == null && value != null) {
            val = (Double) value;
        } else {
            try {
                val = doGetNumericValue(evaluator);
            } catch (GLanguageEvaluationException e) {
                e.getError().setOuterError(new RuleVersionUnableToEvaluateTypeInnerError(this,
                        evaluator,
                        "getNumericValue"));
                throw e;
            }
        }

        if (evaluator != null || value == null) {
            setValue(val, evaluator);
        }
        return val;
    }

    @Transient
    private Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageEvaluationException {
        Double formulaValue = getDoubleValue(evaluator);

        Integer result = null;
        if (formulaValue != null) {
            if (isRoundable()) {
                result = Rounder.round(formulaValue, roundingType, roundingPrecision).intValue();
            } else {
                result = formulaValue.intValue();
            }
        }

        return result;
    }

    @Transient
    private Double doGetNumericValue(Evaluator evaluator) throws GLanguageEvaluationException {
        Double formulaValue = getDoubleValue(evaluator);

        Double result = null;
        if (formulaValue != null) {
            if (isRoundable()) {
                result = Rounder.round(formulaValue, roundingType, roundingPrecision);
            } else {
                result = formulaValue;
            }
        }

        return result;
    }

    @Transient
    private Double getDoubleValue(Evaluator evaluator) throws GLanguageEvaluationException {
        switch (formula.getReturnType(evaluator)) {
            case INTEGER:
                return formula.getIntegerValue(evaluator).doubleValue();
            case NUMERIC:
                return formula.getNumericValue(evaluator);
            default:
                return null;
        }
    }

    @Transient
    public FormulaReturnType getReturnType() {
        return getReturnType(null);
    }

    @Transient
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        return getFormula().getReturnType(evaluator);
    }

    @Transient
    public boolean isCachable() {
        return true;
    }

    @Transient
    public boolean isCached() {
        return value != null;
    }

    /**
     * Is this roundable ? This is roundable if its rounding type and its
     * precision are not null
     *
     * @return true if this is roundable, false otherwise
     */
    @Transient
    public boolean isRoundable() {
        return roundingType != null && roundingPrecision != null;
    }

    /**
     * Is this applicable ?<br>
     * This is applicable if it hasn't an applicability condition or if its applicability condition is true
     *
     * @return true if this is applicable, false otherwise
     */
    @Transient
    public boolean isApplicable() throws GLanguageEvaluationException {
        return isApplicable(null);
    }

    /**
     * Is this applicable ?<br>
     * This is applicable if it hasn't an applicability condition or if its applicability condition is true
     *
     * @return true if this is applicable, false otherwise
     */
    @Transient
    public boolean isApplicable(Evaluator evaluator) throws GLanguageEvaluationException {
        try {
            return applicabilityCondition != null ? applicabilityCondition.getBooleanValue(evaluator) : true;
        } catch (GLanguageEvaluationException e) {
            e.getError().setOuterError(new RuleVersionUnableToCheckApplicabilityInnerError(this, evaluator));
            throw e;
        }
    }

    @Transient
    public boolean isValuable() {
        return getFormula().isValuable();
    }

    @Transient
    public boolean isEvaluated() {
        return value != null;
    }

    /**
     * Is this effective at a specified date ? <br>
     * This is effective at a specified date if the specified date is between
     * this start date and this end date inclusively
     *
     * @param date The date at which this is effective or not
     * @return true If this is effective at the specified date, false otherwise
     */
    @Transient
    public boolean isEffective(LocalDate date) {
        return !date.isBefore(getEffectivityStartDate()) && (getEffectivityEndDate() == null || !date
                .isAfter(getEffectivityEndDate()));
    }

    @Transient
    public boolean isBranched() {
        return (formula != null && formula.isBranched()) && (applicabilityCondition != null && applicabilityCondition
                .isBranched()) && (groupItems != null && !groupItems.isEmpty() && groupItems.stream()
                .allMatch(RuleGroupItem::isBranched));
    }

    /**
     * @param ruleDescription the ruleDescription to set
     */
    public void setRuleDescription(RuleDescription ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    /**
     * @param effectivityEndDate the endDate to set
     */
    public void setEffectivityEndDate(LocalDate effectivityEndDate) {
        this.effectivityEndDate = effectivityEndDate;
    }

    /**
     * @param effectitvityStartDate the effectitvityStartDate to set
     */
    public void setEffectivityStartDate(LocalDate effectitvityStartDate) {
        this.effectivityStartDate = effectitvityStartDate;
    }

    /**
     * @param applicabilityCondition the applicabilityCondition to set
     */
    public void setApplicabilityCondition(AbstractFormula applicabilityCondition) {
        this.applicabilityCondition = applicabilityCondition;
    }

    /**
     * @param formula the formula to set
     */
    public void setFormula(AbstractFormula formula) {
        this.formula = formula;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param precision the precision to set
     */
    public void setRoundingPrecision(Double precision) {
        this.roundingPrecision = precision;
    }

    /**
     * @param roundingType the roundingType to set
     */
    public void setRoundingType(RoundingType roundingType) {
        this.roundingType = roundingType;
    }

    /**
     * @param ruleDefinition the definition to set
     */
    public void setRuleDefinition(RuleDefinition ruleDefinition) {
        this.ruleDefinition = ruleDefinition;
    }

    /**
     * @param ruleType the ruleType to set
     */
    public void setRuleType(RuleType ruleType) {
        this.ruleType = ruleType;
    }

    /**
     * @param groupItems the groupItems to set
     */
    public void setGroupItems(SortedSet<RuleGroupItem> groupItems) {
        this.groupItems = groupItems;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @param ruleSetVersions the ruleSetVersions to set
     */
    public void setRuleSetVersions(Set<RuleSetVersion> ruleSetVersions) {
        this.ruleSetVersions = ruleSetVersions;
    }

    public void resetCache() {
        value = null;
    }

    public void resetValue() {
        value = null;
    }

    /**
     * Set the value in the cache
     *
     * @param value the value to set
     */
    private void setValue(Object value, Evaluator evaluator) {
        if (evaluator != null) {
            evaluator.addEvaluatedRuleVersion(this, value);
        } else {
            this.value = value;
        }
    }

    /**
     * Set the value with a Boolean value
     *
     * @param value the value to set
     */
    public void setBooleanValue(Boolean value, Evaluator evaluator) {
        setValue(value, evaluator);
    }

    /**
     * Set the value with a LocalDate value
     *
     * @param value the value to set
     */
    public void setDateValue(LocalDate value, Evaluator evaluator) {
        setValue(value, evaluator);
    }

    /**
     * Set the value with a Duration value
     *
     * @param value the value to set
     */
    public void setDurationValue(Duration value, Evaluator evaluator) {
        setValue(value, evaluator);
    }

    /**
     * Set the value with a String value
     *
     * @param value the value to set
     */
    public void setStringValue(String value, Evaluator evaluator) {
        setValue(value, evaluator);
    }

    /**
     * Set the value with a Integer value
     *
     * @param value the value to set
     */
    public void setIntegerValue(Integer value, Evaluator evaluator) {
        Integer result = null;
        if (value != null) {
            if (isRoundable()) {
                result = Rounder.round(value, roundingType, roundingPrecision).intValue();
            } else {
                result = value;
            }
        }
        setValue(result, evaluator);
    }

    /**
     * Set the value with a Numeric value
     *
     * @param value the value to set
     */
    public void setNumericValue(Double value, Evaluator evaluator) {
        Double result = null;
        if (value != null) {
            if (isRoundable()) {
                result = Rounder.round(value, roundingType, roundingPrecision);
            } else {
                result = value;
            }
        }
        setValue(result, evaluator);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        RuleVersion other = (RuleVersion) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public int compareTo(RuleVersion o) {
        return o.effectivityStartDate.compareTo(this.effectivityStartDate);
    }
}
