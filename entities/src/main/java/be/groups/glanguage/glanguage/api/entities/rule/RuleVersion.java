package be.groups.glanguage.glanguage.api.entities.rule;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.ruleset.RuleSetVersion;
import be.groups.glanguage.glanguage.api.entities.utils.rounding.Rounder;
import be.groups.glanguage.glanguage.api.entities.utils.rounding.RoundingType;
import be.groups.glanguage.glanguage.api.entities.utils.rounding.RoundingTypeConverter;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.rule.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;
import java.util.SortedSet;

/**
 * A RuleVersion is a version of a {@link RuleDefinition} that is effective between two dates inclusively<br>
 * A RuleVersion has
 * <ul>
 * <li>a {@link RuleDescription} with code and labels identifying it</li>
 * <li>a version string identifying it uniquely</li>
 * <li>effective start and end {@link LocalDate dates} defining the period within this is effective, by default end
 * date is {@link LocalDate#MAX}</li>
 * <li>a {@link AbstractFormula formula} that can be evaluated to obtain the value of this</li>
 * <li>a boolean {@link AbstractFormula applicability condition} that defines in which cases this is
 * applicable or not, true by default. When applicable, the formula can be evaluated safely</li>
 * <li>a sorted set of {@link RuleGroupItem sub-rules} sorted by a sequence number</li>
 * </ul>
 *
 * @author michotte
 * @see RuleDefinition
 */
@Table(name = "RULE_VERSION")
@Entity
public class RuleVersion implements Comparable<RuleVersion> {

    /**
     * RuleDescription
     */
    @JsonManagedReference
    private RuleDescription ruleDescription;

    /**
     * Date until which this is effective inclusively
     */
    private LocalDate effectiveEndDate;

    /**
     * Date from which this is effective inclusively
     */
    private LocalDate effectiveStartDate;

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
    private Long id;

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
    @JsonManagedReference
    private RuleDefinition ruleDefinition;

    /**
     * Rule type
     */
    private RuleType ruleType;

    /**
     * Group items
     */
    @JsonIgnore
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
    @JsonIgnore
    private Set<RuleSetVersion> ruleSetVersions;

    public RuleVersion() {
        super();
    }

    /**
     * Get the rule description
     *
     * @return the rule description
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

    /**
     * Get the effective start date
     *
     * @return the effective start date
     */
    @Column(name = "EFFECTIVITY_START_DATE")
    public LocalDate getEffectiveStartDate() {
        return effectiveStartDate;
    }

    /**
     * Get the effective end date
     *
     * @return the effective end date
     */
    @Column(name = "EFFECTIVITY_END_DATE")
    public LocalDate getEffectiveEndDate() {
        return effectiveEndDate;
    }

    /**
     * Get the applicability condition
     *
     * @return the applicability condition
     */
    @ManyToOne
    @JoinColumn(name = "APPLICABILITY_CONDITION_ID")
    public AbstractFormula getApplicabilityCondition() {
        return applicabilityCondition;
    }

    /**
     * Get the formula
     *
     * @return the formula
     */
    @ManyToOne
    @JoinColumn(name = "FORMULA_ID")
    public AbstractFormula getFormula() {
        return formula;
    }

    /**
     * Get the technical id
     *
     * @return the id
     */
    @Id
    @Column(name = "ID")
    public Long getId() {
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
     * Get the definition
     *
     * @return the definition
     */
    @ManyToOne
    @JoinColumn(name = "RULE_DEFINITION_ID")
    public RuleDefinition getRuleDefinition() {
        return ruleDefinition;
    }

    /**
     * Get the rule type
     *
     * @return the rule type
     */
    @Column(name = "RULE_TYPE")
    @Enumerated(EnumType.ORDINAL)
    public RuleType getRuleType() {
        return ruleType;
    }

    /**
     * Get the set of {@link RuleGroupItem} (sub-rules) sorted by {@link RuleGroupItem#sequenceNumber}
     *
     * @return the set of {@link RuleGroupItem} (sub-rules) sorted by {@link RuleGroupItem#sequenceNumber}
     */
    @OneToMany(mappedBy = "groupRule", fetch = FetchType.EAGER)
    @OrderBy(value = "SEQUENCE_NUMBER ASC")
    public SortedSet<RuleGroupItem> getGroupItems() {
        return groupItems;
    }

    /**
     * Get the version
     *
     * @return the version
     */
    @Column(name = "VERSION")
    public String getVersion() {
        return version;
    }

    /**
     * Get the set of {@link RuleSetVersion} in which this is part of it
     *
     * @return the set of {@link RuleSetVersion} in which this is part of it
     */
    @ManyToMany(mappedBy = "ruleVersions")
    public Set<RuleSetVersion> getRuleSetVersions() {
        return ruleSetVersions;
    }

    /**
     * Get the value by evaluating the {@link AbstractFormula formula}<br>
     * A call to this method is equivalent to a call to {@link RuleVersion#getValue(Evaluator)} with null evaluator
     *
     * @return the evaluated value, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see RuleVersion#getValue(Evaluator)
     */
    @Transient
    public Object getValue() throws GLanguageException {
        return getValue(null);
    }

    /**
     * Get the value by evaluating the {@link AbstractFormula formula} with an {@code evaluator} (can be null)<br>
     * Evaluation process :
     * <ol>
     *      <li>Check if this {@link RuleVersion#isApplicable(Evaluator)}, if not return null.</li>
     *      <li>Else check the {@link RuleVersion#getReturnType(Evaluator)} and delegate to the corresponding typed
     *          evaluation method only for {@link FormulaReturnType#BOOLEAN}, {@link FormulaReturnType#DATE},
     *          {@link FormulaReturnType#DURATION}, {@link FormulaReturnType#INTEGER},
     *          {@link FormulaReturnType#NUMERIC} and {@link FormulaReturnType#STRING}. Otherwise, return null.</li>
     * </ol>
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the evaluated value, may be null if
     * <ul>
     *     <li>{@link RuleVersion#isApplicable(Evaluator)} is false</li>
     *     <li>{@link RuleVersion#getReturnType(Evaluator)} is not in {@link FormulaReturnType#BOOLEAN},
     *          {@link FormulaReturnType#DATE}, {@link FormulaReturnType#DURATION}, {@link FormulaReturnType#INTEGER},
     *          {@link FormulaReturnType#NUMERIC} and {@link FormulaReturnType#STRING}</li>
     *     <li>the result of the evaluation of the {@link AbstractFormula formula} is null</li>
     * </ul>
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see RuleVersion#isApplicable(Evaluator)
     * @see RuleVersion#getReturnType(Evaluator)
     * @see RuleVersion#getBooleanValue(Evaluator)
     * @see RuleVersion#getDateValue(Evaluator)
     * @see RuleVersion#getDurationValue(Evaluator)
     * @see RuleVersion#getIntegerValue(Evaluator)
     * @see RuleVersion#getNumericValue(Evaluator)
     * @see RuleVersion#getStringValue(Evaluator)
     */
    @Transient
    public Object getValue(Evaluator evaluator) throws GLanguageException {
        // If not applicable, return null
        if (!isApplicable(evaluator)) return null;

        try {
            // Switch on type
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
                    // Type not handled, return null
                    return null;
            }
        } catch (GLanguageException e) {
            e.getError().setOuterError(new RuleUnableToEvaluateInnerError(this, evaluator));
            throw e;
        }
    }

    /**
     * Get the value as {@link Boolean}<br>
     * A call to this method is equivalent to a call to {@link RuleVersion#getBooleanValue(Evaluator)} with null
     * evaluator
     *
     * @return the value as {@link Boolean}, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see RuleVersion#getBooleanValue(Evaluator)
     */
    @Transient
    public Boolean getBooleanValue() throws GLanguageException {
        return getBooleanValue(null);
    }

    /**
     * Get the value as {@link Boolean} with an {@code evaluator} (can be null)<br>
     * Evaluation process :
     * <ol>
     *     <li>If {@code evaluator} is not null and {@link Evaluator#isRuleVersionEvaluated(RuleVersion)} with {@code
     *          this} as parameter is true, select the value returned by
     *          {@link Evaluator#getRuleVersionValue(RuleVersion)} with {@code this} as parameter</li>
     *     <li>Else, if {@link RuleVersion#value} is not null, select it</li>
     *     <li>Else, evaluate the {@link AbstractFormula formula} by delegating to
     *          {@link AbstractFormula#getBooleanValue(Evaluator)} and select the returned value</li>
     *     <li>Set the selected value by delegating to {@link RuleVersion#setValue(Object, Evaluator)}</li>
     *     <li>Return the selected value</li>
     * </ol>
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link Boolean}, may be null if the result of the evaluation of the
     * {@link AbstractFormula formula} is null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see Evaluator#isRuleVersionEvaluated(RuleVersion)
     * @see Evaluator#getRuleVersionValue(RuleVersion)
     * @see AbstractFormula#getBooleanValue(Evaluator)
     * @see RuleVersion#setValue(Object, Evaluator)
     */
    @Transient
    public Boolean getBooleanValue(Evaluator evaluator) throws GLanguageException {
        Boolean val;
        if (evaluator != null && evaluator.isRuleVersionEvaluated(this)) {
            val = (Boolean) evaluator.getRuleVersionValue(this);
        } else if (evaluator == null && value != null) {
            val = (Boolean) value;
        } else {
            try {
                val = formula.getBooleanValue(evaluator);
            } catch (GLanguageException e) {
                e.getError().setOuterError(new RuleUnableToEvaluateBooleanInnerError(this, evaluator) {
                });
                throw e;
            }
        }

        if (evaluator != null || value == null) {
            setValue(val, evaluator);
        }
        return val;
    }

    /**
     * Get the value as {@link LocalDate}<br>
     * A call to this method is equivalent to a call to {@link RuleVersion#getBooleanValue(Evaluator)} with null
     * evaluator
     *
     * @return the value as {@link LocalDate}, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see RuleVersion#getDateValue(Evaluator)
     */
    @Transient
    public LocalDate getDateValue() throws GLanguageException {
        return getDateValue(null);
    }

    /**
     * Get the value as {@link LocalDate} with an {@code evaluator} (can be null)<br>
     * Evaluation process :
     * <ol>
     *     <li>If {@code evaluator} is not null and {@link Evaluator#isRuleVersionEvaluated(RuleVersion)} with {@code
     *          this} as parameter is true, select the value returned by
     *          {@link Evaluator#getRuleVersionValue(RuleVersion)} with {@code this} as parameter</li>
     *     <li>Else, if {@link RuleVersion#value} is not null, select it</li>
     *     <li>Else, evaluate the {@link AbstractFormula formula} by delegating to
     *          {@link AbstractFormula#getDateValue(Evaluator)} and select the returned value</li>
     *     <li>Set the selected value by delegating to {@link RuleVersion#setValue(Object, Evaluator)}</li>
     *     <li>Return the selected value</li>
     * </ol>
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link LocalDate}, may be null if the result of the evaluation of the
     * {@link AbstractFormula formula} is null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see Evaluator#isRuleVersionEvaluated(RuleVersion)
     * @see Evaluator#getRuleVersionValue(RuleVersion)
     * @see AbstractFormula#getDateValue(Evaluator)
     * @see RuleVersion#setValue(Object, Evaluator)
     */
    @Transient
    public LocalDate getDateValue(Evaluator evaluator) throws GLanguageException {
        LocalDate val;
        if (evaluator != null && evaluator.isRuleVersionEvaluated(this)) {
            val = (LocalDate) evaluator.getRuleVersionValue(this);
        } else if (evaluator == null && value != null) {
            val = (LocalDate) value;
        } else {
            try {
                val = formula.getDateValue(evaluator);
            } catch (GLanguageException e) {
                e.getError().setOuterError(new RuleUnableToEvaluateDateInnerError(this, evaluator) {
                });
                throw e;
            }
        }

        if (evaluator != null || value == null) {
            setValue(val, evaluator);
        }
        return val;
    }

    /**
     * Get the value as {@link Duration}<br>
     * A call to this method is equivalent to a call to {@link RuleVersion#getBooleanValue(Evaluator)} with null
     * evaluator
     *
     * @return the value as {@link Duration}, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see RuleVersion#getDurationValue(Evaluator)
     */
    @Transient
    public Duration getDurationValue() throws GLanguageException {
        return getDurationValue(null);
    }

    /**
     * Get the value as {@link Duration} with an {@code evaluator} (can be null)<br>
     * Evaluation process :
     * <ol>
     *     <li>If {@code evaluator} is not null and {@link Evaluator#isRuleVersionEvaluated(RuleVersion)} with {@code
     *          this} as parameter is true, select the value returned by
     *          {@link Evaluator#getRuleVersionValue(RuleVersion)} with {@code this} as parameter</li>
     *     <li>Else, if {@link RuleVersion#value} is not null, select it</li>
     *     <li>Else, evaluate the {@link AbstractFormula formula} by delegating to
     *          {@link AbstractFormula#getDurationValue(Evaluator)} and select the returned value</li>
     *     <li>Set the selected value by delegating to {@link RuleVersion#setValue(Object, Evaluator)}</li>
     *     <li>Return the selected value</li>
     * </ol>
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link Duration}, may be null if the result of the evaluation of the
     * {@link AbstractFormula formula} is null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see Evaluator#isRuleVersionEvaluated(RuleVersion)
     * @see Evaluator#getRuleVersionValue(RuleVersion)
     * @see AbstractFormula#getDurationValue(Evaluator)
     * @see RuleVersion#setValue(Object, Evaluator)
     */
    @Transient
    public Duration getDurationValue(Evaluator evaluator) throws GLanguageException {
        Duration val;
        if (evaluator != null && evaluator.isRuleVersionEvaluated(this)) {
            val = (Duration) evaluator.getRuleVersionValue(this);
        } else if (evaluator == null && value != null) {
            val = (Duration) value;
        } else {
            try {
                val = formula.getDurationValue(evaluator);
            } catch (GLanguageException e) {
                e.getError().setOuterError(new RuleUnableToEvaluateDurationInnerError(this, evaluator) {
                });
                throw e;
            }
        }

        if (evaluator != null || value == null) {
            setValue(val, evaluator);
        }
        return val;
    }

    /**
     * Get the value as {@link String}<br>
     * A call to this method is equivalent to a call to {@link RuleVersion#getBooleanValue(Evaluator)} with null
     * evaluator
     *
     * @return the value as {@link String}, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see RuleVersion#getStringValue(Evaluator)
     */
    @Transient
    public String getStringValue() throws GLanguageException {
        return getStringValue(null);
    }

    /**
     * Get the value as {@link String} with an {@code evaluator} (can be null)<br>
     * Evaluation process :
     * <ol>
     *     <li>If {@code evaluator} is not null and {@link Evaluator#isRuleVersionEvaluated(RuleVersion)} with {@code
     *          this} as parameter is true, select the value returned by
     *          {@link Evaluator#getRuleVersionValue(RuleVersion)} with {@code this} as parameter</li>
     *     <li>Else, if {@link RuleVersion#value} is not null, select it</li>
     *     <li>Else, evaluate the {@link AbstractFormula formula} by delegating to
     *          {@link AbstractFormula#doGetStringValue(Evaluator)} and select the returned value</li>
     *     <li>Set the selected value by delegating to {@link RuleVersion#setValue(Object, Evaluator)}</li>
     *     <li>Return the selected value</li>
     * </ol>
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link String}, may be null if the result of the evaluation of the
     * {@link AbstractFormula formula} is null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see Evaluator#isRuleVersionEvaluated(RuleVersion)
     * @see Evaluator#getRuleVersionValue(RuleVersion)
     * @see AbstractFormula#getStringValue(Evaluator)
     * @see RuleVersion#setValue(Object, Evaluator)
     */
    @Transient
    public String getStringValue(Evaluator evaluator) throws GLanguageException {
        String val;
        if (evaluator != null && evaluator.isRuleVersionEvaluated(this)) {
            val = (String) evaluator.getRuleVersionValue(this);
        } else if (evaluator == null && value != null) {
            val = (String) value;
        } else {
            try {
                val = formula.getStringValue(evaluator);
            } catch (GLanguageException e) {
                e.getError().setOuterError(new RuleUnableToEvaluateStringInnerError(this, evaluator) {
                });
                throw e;
            }
        }

        if (evaluator != null || value == null) {
            setValue(val, evaluator);
        }
        return val;
    }

    /**
     * Get the value as {@link Integer}<br>
     * A call to this method is equivalent to a call to {@link RuleVersion#getBooleanValue(Evaluator)} with null
     * evaluator
     *
     * @return the value as {@link Integer}, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see RuleVersion#getIntegerValue(Evaluator)
     */
    @Transient
    public Integer getIntegerValue() throws GLanguageException {
        return getIntegerValue(null);
    }

    /**
     * Get the value as {@link Integer} with an {@code evaluator} (can be null)<br>
     * Evaluation process :
     * <ol>
     *     <li>If {@code evaluator} is not null and {@link Evaluator#isRuleVersionEvaluated(RuleVersion)} with {@code
     *          this} as parameter is true, select the value returned by
     *          {@link Evaluator#getRuleVersionValue(RuleVersion)} with {@code this} as parameter</li>
     *     <li>Else, if {@link RuleVersion#value} is not null, select it</li>
     *     <li>Else, evaluate the {@link AbstractFormula formula} by delegating to
     *          {@link RuleVersion#doGetIntegerValue(Evaluator)} and select the returned value</li>
     *     <li>Set the selected value by delegating to {@link RuleVersion#setValue(Object, Evaluator)}</li>
     *     <li>Return the selected value</li>
     * </ol>
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link Integer}, may be null if the result of the evaluation of the
     * {@link AbstractFormula formula} is null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see Evaluator#isRuleVersionEvaluated(RuleVersion)
     * @see Evaluator#getRuleVersionValue(RuleVersion)
     * @see AbstractFormula#getIntegerValue(Evaluator)
     * @see RuleVersion#setValue(Object, Evaluator)
     */
    @Transient
    public Integer getIntegerValue(Evaluator evaluator) throws GLanguageException {
        Integer val;
        if (evaluator != null && evaluator.isRuleVersionEvaluated(this)) {
            val = (Integer) evaluator.getRuleVersionValue(this);
        } else if (evaluator == null && value != null) {
            val = (Integer) value;
        } else {
            try {
                val = doGetIntegerValue(evaluator);
            } catch (GLanguageException e) {
                e.getError().setOuterError(new RuleUnableToEvaluateIntegerInnerError(this, evaluator) {
                    @Override
                    public String getMainMessage() {
                        return super.getMainMessage();
                    }
                });
                throw e;
            }
        }

        if (evaluator != null || value == null) {
            setValue(val, evaluator);
        }
        return val;
    }

    /**
     * Get the value as {@link Double}<br>
     * A call to this method is equivalent to a call to {@link RuleVersion#getBooleanValue(Evaluator)} with null
     * evaluator
     *
     * @return the value as {@link Double}, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see RuleVersion#getNumericValue(Evaluator)
     */
    @Transient
    public Double getNumericValue() throws GLanguageException {
        return getNumericValue(null);
    }

    /**
     * Get the value as {@link Double} with an {@code evaluator} (can be null)<br>
     * Evaluation process :
     * <ol>
     *     <li>If {@code evaluator} is not null and {@link Evaluator#isRuleVersionEvaluated(RuleVersion)} with {@code
     *          this} as parameter is true, select the value returned by
     *          {@link Evaluator#getRuleVersionValue(RuleVersion)} with {@code this} as parameter</li>
     *     <li>Else, if {@link RuleVersion#value} is not null, select it</li>
     *     <li>Else, evaluate the {@link AbstractFormula formula} by delegating to
     *          {@link RuleVersion#doGetNumericValue(Evaluator)} and select the returned value</li>
     *     <li>Set the selected value by delegating to {@link RuleVersion#setValue(Object, Evaluator)}</li>
     *     <li>Return the selected value</li>
     * </ol>
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link Double}, may be null if the result of the evaluation of the
     * {@link AbstractFormula formula} is null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see Evaluator#isRuleVersionEvaluated(RuleVersion)
     * @see Evaluator#getRuleVersionValue(RuleVersion)
     * @see AbstractFormula#getNumericValue(Evaluator)
     * @see RuleVersion#setValue(Object, Evaluator)
     */
    @Transient
    public Double getNumericValue(Evaluator evaluator) throws GLanguageException {
        Double val;
        if (evaluator != null && evaluator.isRuleVersionEvaluated(this)) {
            val = (Double) evaluator.getRuleVersionValue(this);
        } else if (evaluator == null && value != null) {
            val = (Double) value;
        } else {
            try {
                val = doGetNumericValue(evaluator);
            } catch (GLanguageException e) {
                e.getError().setOuterError(new RuleUnableToEvaluateNumericInnerError(this, evaluator) {
                });
                throw e;
            }
        }

        if (evaluator != null || value == null) {
            setValue(val, evaluator);
        }
        return val;
    }

    /**
     * Get the value as {@link Integer}, eventually rounded<br>
     * Evaluation process :
     * <ol>
     *     <li>Get the value as {@link Double} by delegating to {@link RuleVersion#getDoubleValue(Evaluator)}</li>
     *     <li>Apply rounding if needed</li>
     *     <li>Convert to {@link Integer}</li>
     *     <li>Return the value</li>
     * </ol>
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link Integer}, eventually rounded
     * @throws GLanguageException if an error occurs during the evaluation process or if the
     * {@link RuleVersion#getReturnType(Evaluator)} is not {@link FormulaReturnType#INTEGER} or
     * {@link FormulaReturnType#NUMERIC}
     */
    @Transient
    private Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
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

    /**
     * Get the value as {@link Double}, eventually rounded<br>
     * Evaluation process :
     * <ol>
     *     <li>Get the value as {@link Double} by delegating to {@link RuleVersion#getDoubleValue(Evaluator)}</li>
     *     <li>Apply rounding if needed</li>
     *     <li>Return the value</li>
     * </ol>
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link Double}, eventually rounded
     * @throws GLanguageException if an error occurs during the evaluation process or if the
     * {@link RuleVersion#getReturnType(Evaluator)} is not {@link FormulaReturnType#NUMERIC} or
     * {@link FormulaReturnType#INTEGER}
     */
    @Transient
    private Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
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

    /**
     * Get the value as {@link Double}, eventually rounded<br>
     * Evaluation process :
     * <ol>
     *     <li>Check the {@link AbstractFormula#getReturnType(Evaluator)}
     *          <ul>
     *              <li>If {@link FormulaReturnType#INTEGER}, delegate to
     *              {@link AbstractFormula#getIntegerValue(Evaluator)} and transform the returned value to a
     *              {@link Double} if not null</li>
     *              <li>If {@link FormulaReturnType#NUMERIC}, delegate to
     *              {@link AbstractFormula#getIntegerValue(Evaluator)} and select the returned value</li>
     *          </ul>
     *     </li>
     *     <li>Apply rounding if needed</li>
     *     <li>Return the value</li>
     * </ol>
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link Double}, eventually rounded
     * @throws GLanguageException if an error occurs during the evaluation process or if the
     * {@link RuleVersion#getReturnType(Evaluator)} is not {@link FormulaReturnType#NUMERIC} or
     * {@link FormulaReturnType#INTEGER}
     */
    @Transient
    private Double getDoubleValue(Evaluator evaluator) throws GLanguageException {
        switch (formula.getReturnType(evaluator)) {
            case INTEGER:
                Integer result = formula.getIntegerValue(evaluator);
                if (result != null) {
                    return formula.getIntegerValue(evaluator).doubleValue();
                } else {
                    return null;
                }
            case NUMERIC:
                return formula.getNumericValue(evaluator);
            default:
                return null;
        }
    }

    /**
     * Get the return type
     * A call to this method is equivalent to a call to {@link RuleVersion#getReturnType(Evaluator)} with null
     * evaluator
     *
     * @return the return type
     * @see RuleVersion#getReturnType(Evaluator)
     */
    @Transient
    public FormulaReturnType getReturnType() {
        return getReturnType(null);
    }

    /**
     * Get the return type with an evaluator<br>
     * The return type of a {@link RuleVersion} is the return type of its {@link AbstractFormula formula}<br>
     * Ir there is no formula, the return type is {@link FormulaReturnType#UNDEFINED}
     *
     * @return the return type
     */
    @Transient
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        return getFormula() != null ? getFormula().getReturnType(evaluator) : FormulaReturnType.UNDEFINED;
    }

    /**
     * Is this roundable ?<br>
     * This is roundable if its rounding type and its precision are not null
     *
     * @return true if this is roundable, false otherwise
     */
    @Transient
    public boolean isRoundable() {
        return roundingType != null && roundingPrecision != null;
    }

    /**
     * Is this applicable ?<br>
     * A call to this method is equivalent to a call to {@link RuleVersion#isApplicable(Evaluator)} with null
     * evaluator
     *
     * @return true if this is applicable, false otherwise
     * @see RuleVersion#isApplicable(Evaluator)
     */
    @Transient
    public boolean isApplicable() throws GLanguageException {
        return isApplicable(null);
    }

    /**
     * Is this applicable ?<br>
     * This is applicable if it hasn't an applicability condition or if its applicability condition is true
     *
     * @return true if this is applicable, false otherwise
     */
    @Transient
    public boolean isApplicable(Evaluator evaluator) throws GLanguageException {
        try {
            return applicabilityCondition != null ? applicabilityCondition.getBooleanValue(evaluator) : true;
        } catch (GLanguageException e) {
            e.getError().setOuterError(new RuleUnableToCheckApplicabilityInnerError(this, evaluator));
            throw e;
        }
    }

    /**
     * Is this evaluated ?<br>
     * This is evaluated if it's {@link RuleVersion#value} is not null
     *
     * @return true if this is evaluated, false otherwise
     */
    @Transient
    public boolean isEvaluated() {
        return value != null;
    }

    /**
     * Is this effective at a {@code date} ? <br>
     * This is effective at a {@code date} if the {@code date} is between {@link RuleVersion#effectiveStartDate} and
     * {@link RuleVersion#effectiveEndDate} inclusively
     *
     * @param date the date at which this is effective or not
     * @return true if this is effective at the specified {@code date}, false otherwise
     */
    @Transient
    public boolean isEffective(LocalDate date) {
        return !date.isBefore(getEffectiveStartDate()) && (getEffectiveEndDate() == null || !date.isAfter(
                getEffectiveEndDate()));
    }

    /**
     * @param ruleDescription the ruleDescription to set
     */
    public void setRuleDescription(RuleDescription ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    /**
     * @param effectiveEndDate the endDate to set
     */
    public void setEffectiveEndDate(LocalDate effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    /**
     * @param effectiveStartDate the effectiveStartDate to set
     */
    public void setEffectiveStartDate(LocalDate effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
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
    public void setId(Long id) {
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

    public void resetValue() {
        value = null;
    }

    /**
     * Set the value with an evaluator (can be null)
     * Evaluation process :
     * <ul>
     *     <li>If {@code evaluator} is not null, make {@code evaluator} store the {@code value} by delegating to
     *          {@link Evaluator#addEvaluatedRuleVersion(RuleVersion, Object)} with {@code this} and {@code value}
     *          has parameters</li>
     *     <li>If {@code evaluator} is null, set {@code this} {@link RuleVersion#value}</li>
     * </ul>
     *
     * @param value the value to set
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     */
    private void setValue(Object value, Evaluator evaluator) {
        if (evaluator != null) {
            evaluator.addEvaluatedRuleVersion(this, value);
        } else {
            this.value = value;
        }
    }

    /**
     * Set the value as {@link Integer} with an evaluator (can be null)
     * Evaluation process :
     * <ul>
     *     <li>Apply rounding if needed and convert result to {@link Integer}</li>
     *     <li>Set by delegating to {@link RuleVersion#setValue(Object, Evaluator)}</li>
     * </ul>
     *
     * @param value the value to set
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     */
    public void setIntegerValue(Integer value, Evaluator evaluator) {
        Integer result = null;
        if (value != null) {
            if (isRoundable()) {
                try {
                    result = Rounder.round(value, roundingType, roundingPrecision).intValue();
                } catch (GLanguageException e) {
                    e.getError().setOuterError(new RuleUnableToEvaluateIntegerInnerError(this, evaluator));
                }
            } else {
                result = value;
            }
        }
        setValue(result, evaluator);
    }

    /**
     * Set the value as {@link Double} with an evaluator (can be null)
     * Evaluation process :
     * <ul>
     *     <li>Apply rounding if needed</li>
     *     <li>Set by delegating to {@link RuleVersion#setValue(Object, Evaluator)}</li>
     * </ul>
     *
     * @param value the value to set
     */
    public void setNumericValue(Double value, Evaluator evaluator) {
        Double result = null;
        if (value != null) {
            if (isRoundable()) {
                try {
                    result = Rounder.round(value, roundingType, roundingPrecision);
                } catch (GLanguageException e) {
                    e.getError().setOuterError(new RuleUnableToEvaluateNumericInnerError(this, evaluator));
                }
            } else {
                result = value;
            }
        }
        setValue(result, evaluator);
    }

    @Override
    public int compareTo(RuleVersion o) {
        return o.effectiveStartDate.compareTo(this.effectiveStartDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RuleVersion)) return false;

        RuleVersion that = (RuleVersion) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
