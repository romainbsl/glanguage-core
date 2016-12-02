package be.groups.glanguage.glanguage.api.entities.rule;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedSet;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import be.groups.common.entities.util.LocalDateTimeConverter;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion;

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
    private LocalDateTime effectivityEndDate;

    /**
     * Date from which this is effective inclusively
     */
    private LocalDateTime effectivityStartDate;

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
    @Convert(converter = LocalDateTimeConverter.class)
    public LocalDateTime getEffectivityStartDate() {
        return effectivityStartDate;
    }

    @Column(name = "EFFECTIVITY_END_DATE")
    @Convert(converter = LocalDateTimeConverter.class)
    public LocalDateTime getEffectivityEndDate() {
        return effectivityEndDate;
    }

    /**
     * @return the applicabilityCondition
     */
    @OneToOne
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
    @OneToMany(mappedBy = "groupRule")
    @OrderBy(value = "SEQUENCE_NUMBER ASC")
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
    public Object getValue() {
        try {
            switch (getReturnType()) {
                case BOOLEAN:
                    return getBooleanValue();
                case DATE:
                    return getDateValue();
                case DURATION:
                    return getDurationValue();
                case INTEGER:
                    return getIntegerValue();
                case NUMERIC:
                    return getNumericValue();
                case STRING:
                    return getStringValue();
                default:
                    return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to evaluate rule version [id: " + getId() + ", code: " +
                    getRuleDescription()
                    .getCode() + "]", e);
        }
    }

    @Transient
    public Boolean getBooleanValue() {
        if (value == null) {
            setValue(formula.getBooleanValue());
        }
        return (Boolean) value;
    }

    @Transient
    public LocalDate getDateValue() {
        if (value == null) {
            setValue(formula.getDateValue());
        }
        return (LocalDate) value;
    }

    @Transient
    public Duration getDurationValue() {
        if (value == null) {
            setValue(formula.getDurationValue());
        }
        return (Duration) value;
    }

    @Transient
    public Integer getIntegerValue() {
        if (value == null) {
            Double formulaValue = null;
            switch (formula.getReturnType()) {
                case INTEGER:
                    formulaValue = formula.getIntegerValue().doubleValue();
                    break;
                case NUMERIC:
                    formulaValue = formula.getNumericValue();
                    break;
                default:
                    assert false;
            }

            Integer result = null;
            if (formulaValue != null) {
                if (isRoundable()) {
                    result = Rounder.round(formulaValue, roundingType, roundingPrecision).intValue();
                } else {
                    result = new Integer(formulaValue.intValue());
                }
            }
            setValue(result);
        }
        return (Integer) value;
    }

    @Transient
    public Double getNumericValue() {
        if (value == null) {
            Double formulaValue = null;
            switch (formula.getReturnType()) {
                case INTEGER:
                    formulaValue = formula.getIntegerValue().doubleValue();
                    break;
                case NUMERIC:
                    formulaValue = formula.getNumericValue();
                    break;
                default:
                    assert false;
            }

            Double result = null;
            if (formulaValue != null) {
                if (isRoundable()) {
                    result = Rounder.round(formulaValue, roundingType, roundingPrecision);
                } else {
                    result = formulaValue;
                }
            }
            setValue(result);
        }
        if (value instanceof Integer) {
            return ((Integer) value).doubleValue();
        }
        return (Double) value;
    }

    @Transient
    public String getStringValue() {
        if (value == null) {
            setValue(formula.getStringValue());
        }
        return (String) value;
    }

    @Transient
    public FormulaReturnType getReturnType() {
        return getFormula().getReturnType();
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
     * @return
     */
    @Transient
    public boolean isApplicable() {
        return applicabilityCondition != null ? applicabilityCondition.getBooleanValue() : true;
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
    public boolean isEffective(LocalDateTime date) {
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
    public void setEffectivityEndDate(LocalDateTime effectivityEndDate) {
        this.effectivityEndDate = effectivityEndDate;
    }

    /**
     * @param effectitvityStartDate the effectitvityStartDate to set
     */
    public void setEffectivityStartDate(LocalDateTime effectitvityStartDate) {
        this.effectivityStartDate = effectitvityStartDate;
    }

    /**
     * @param applicabilityCondition the applicabilityCondition to set
     */
    public void setApplicabilityCondition(AbstractFormula applicabilityCondition) {
        this.applicabilityCondition = applicabilityCondition;
    }

    /**
     * @param code the code to set
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
     * @param code the code to set
     */
    public void setRoundingPrecision(Double precision) {
        this.roundingPrecision = precision;
    }

    /**
     * @param code the code to set
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
     * @param subRulesIdentity the subRulesIdentity to set
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
     * @param code the code to set
     */
    private void setValue(Object value) {
        this.value = value;
    }

    /**
     * Set the value with a Boolean value
     *
     * @param value the value to set
     */
    public void setBooleanValue(Boolean value) {
        setValue(value);
    }

    /**
     * Set the value with a LocalDate value
     *
     * @param value the value to set
     */
    public void setDateValue(LocalDate value) {
        setValue(value);
    }

    /**
     * Set the value with a Duration value
     *
     * @param value the value to set
     */
    public void setDurationValue(Duration value) {
        setValue(value);
    }

    /**
     * Set the value with a Integer value
     *
     * @param value the value to set
     */
    public void setIntegerValue(Integer value) {
        Integer result = null;
        if (value != null) {
            if (isRoundable()) {
                result = new Integer(Rounder.round(value, roundingType, roundingPrecision).intValue());
            } else {
                result = new Integer(value.intValue());
            }
        }
        setValue(result);
    }

    /**
     * Set the value with a Numeric value
     *
     * @param value the value to set
     */
    public void setNumericValue(Double value) {
        Double result = null;
        if (value != null) {
            if (isRoundable()) {
                result = Rounder.round(value, roundingType, roundingPrecision);
            } else {
                result = value;
            }
        }
        setValue(result);
    }

    /**
     * Set the value with a String value
     *
     * @param value the value to set
     */
    public void setStringValue(String value) {
        setValue(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        RuleVersion other = (RuleVersion) obj;
        if (id != other.id) return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public int compareTo(RuleVersion o) {
        return o.effectivityStartDate.compareTo(this.effectivityStartDate);
    }
}
