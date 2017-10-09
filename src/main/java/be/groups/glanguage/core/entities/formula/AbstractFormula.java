package be.groups.glanguage.core.entities.formula;

import be.groups.glanguage.core.entities.evaluation.Evaluator;
import be.groups.glanguage.core.entities.formula.description.FormulaDescription;
import be.groups.glanguage.core.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.core.entities.formula.description.FormulaType;
import be.groups.glanguage.core.entities.rule.RuleVersion;
import be.groups.glanguage.core.error.exception.GLanguageException;
import be.groups.glanguage.core.error.formula.base.cannot.invoke.evaluation.method.FormulaCannotInvokeEvaluationMethodInnerErrorFactory;
import be.groups.glanguage.core.error.formula.base.unable.evaluate.FormulaEvaluateInnerError;
import be.groups.glanguage.core.error.formula.base.unable.evaluate.FormulaEvaluateTypeInnerError;
import be.groups.glanguage.core.error.utils.ErrorMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Common implementation of a formula <br>
 * <br>
 * An AbstractFormula has : <br>
 * - a {@link FormulaDescription} <br>
 * - a parent formula of which this is a parameter <br>
 * - a sequence number representing the position of this in the parent's parameters sequence <br>
 * - a set of sub-formula's representing the parameters of this formula <br>
 * - an evaluated status <br>
 * - a value <br>
 * <br>
 * An AbstractFormula can be evaluated - can be given a value which type corresponds to its
 * {@link FormulaReturnType}<br>
 * How an AbstractFormula is evaluated depends on its {@link FormulaType}<br>
 * Evaluating an AbstractFormula consists in applying its own evaluation algorithm on the results of the evaluation of
 * its parameters.
 *
 * @author michotte
 */
@Entity
@Table(name = "FORMULA", uniqueConstraints = @UniqueConstraint(columnNames = {"parent_formula_id", "sequence_number"}))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "FORMULA_DESCRIPTION_ID", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorOptions(force = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class AbstractFormula {

    /**
     * Technical unique ID
     */
    private Long id;

    /**
     * Set of {@link RuleVersion} of which this is the formula
     */
    private Set<RuleVersion> ruleVersions;

    /**
     * Formula description
     */
    private FormulaDescription description;

    /**
     * Parent formula
     */
    private AbstractFormula parentFormula;

    /**
     * List of {@link AbstractFormula} parameters
     */
    protected List<AbstractFormula> parameters;

    /**
     * Sequence number of this parameter in parent formula
     */
    private Integer sequenceNumber;

    /**
     * Constant value (if type is terminal)
     */
    private String constantValue;

    protected AbstractFormula() {
        super();
    }

    protected AbstractFormula(FormulaDescription description) {
        super();
        this.description = description;
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
     * Get the set of all {@link RuleVersion} of which this is the formula
     *
     * @return the set of all {@link RuleVersion} of which this is the formula
     */
    @JsonIgnore
    @OneToMany(mappedBy = "formula")
    public Set<RuleVersion> getRuleVersions() {
        return ruleVersions;
    }

    /**
     * Get the description
     *
     * @return the description
     */
    @OneToOne
    @JoinColumn(name = "FORMULA_DESCRIPTION_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    public FormulaDescription getDescription() {
        return description;
    }

    /**
     * Get the parent formula - the one for which this is a parameter, may be null
     *
     * @return the parent formula - the one for which this is a parameter, may be null
     */
    @ManyToOne
    @JoinColumn(name = "PARENT_FORMULA_ID", updatable = false, nullable = true)
    public AbstractFormula getParentFormula() {
        return parentFormula;
    }

    /**
     * Get the list of all {@link AbstractFormula} parameters
     *
     * @return the list of all {@link AbstractFormula} parameters
     */
    @OneToMany(mappedBy = "parentFormula", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("SEQUENCE_NUMBER")
    public List<AbstractFormula> getParameters() {
        return parameters;
    }

    /**
     * Get the sequence number of this in parent formula's parameters list, may be null
     * @return the sequence number of this in parent formula's parameters list, may be null
     */
    @Column(name = "SEQUENCE_NUMBER", updatable = false, nullable = true)
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Get the constant value, may be null
     *
     * @return the constant value, may be null
     */
    @Column(name = "VALUE", nullable = true)
    public String getConstantValue() {
        return constantValue;
    }

    /**
     * Get the discriminator value<br>
     * All formulas are stored in the same structure, the discriminator gives the concrete type of this formula
     *
     * @return the discriminator value
     */
    @JsonIgnore
    @Transient
    public Integer getDiscriminatorValue() {
        return Integer.valueOf(this.getClass().getAnnotation(DiscriminatorValue.class).value());
    }

    /**
     * Is this terminal ?<br>
     * Terminal formulas represent constants values<br>
     * For this kind of formulas, the value is stored directly in {@link AbstractFormula#constantValue}
     *
     * @return true if this is terminal, false otherwise
     */
    @Transient
    public abstract boolean isTerminal();

    /**
     * Is this valid according to its {@link FormulaDescription description} ?
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return true if this is valid according to its {@link FormulaDescription description}, false otherwise
     */
    @JsonIgnore
    @Transient
    public abstract boolean isValid(Evaluator evaluator);


    /**
     * Get the return type
     * A call to this method is equivalent to a call to {@link AbstractFormula#getReturnType(Evaluator)} with null
     * evaluator
     *
     * @return the return type
     * @see AbstractFormula#getReturnType(Evaluator)
     */
    @JsonIgnore
    @Transient
    public FormulaReturnType getReturnType() {
        return getReturnType(null);
    }

    /**
     * Get the return type with an evaluator (can be null)<br>
     * The return type of a {@link AbstractFormula formula} is determined by its {@link FormulaType type} and its
     * list of {@link AbstractFormula} parameters
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return the return type
     */
    @JsonIgnore
    @Transient
    public abstract FormulaReturnType getReturnType(Evaluator evaluator);

    /**
     * Get the value by evaluating the {@link AbstractFormula parameters} and applying this evaluation algorithm to
     * these parameters<br>
     * A call to this method is equivalent to a call to {@link AbstractFormula#getValue(Evaluator)} with null evaluator
     *
     * @return the evaluated value, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see AbstractFormula#getValue(Evaluator)
     */
    @JsonIgnore
    @Transient
    public Object getValue() throws GLanguageException {
        return getValue(null);
    }
    /**
     * Get the value by evaluating the {@link AbstractFormula parameters} and applying this evaluation algorithm to
     * these parameters with an {@code evaluator} (can be null)<br>
     * Evaluation process :
     * <ol>
     *      <li>Check the {@link AbstractFormula#getReturnType(Evaluator)} and delegate to the corresponding typed
     *          evaluation method only for {@link FormulaReturnType#BOOLEAN}, {@link FormulaReturnType#DATE},
     *          {@link FormulaReturnType#DURATION}, {@link FormulaReturnType#INTEGER},
     *          {@link FormulaReturnType#NUMERIC} and {@link FormulaReturnType#STRING}. Otherwise, return null.</li>
     * </ol>
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the evaluated value, may be null if
     * <ul>
     *     <li>{@link AbstractFormula#getReturnType(Evaluator)} is not in {@link FormulaReturnType#BOOLEAN},
     *          {@link FormulaReturnType#DATE}, {@link FormulaReturnType#DURATION}, {@link FormulaReturnType#INTEGER},
     *          {@link FormulaReturnType#NUMERIC} and {@link FormulaReturnType#STRING}</li>
     *     <li>the result of the evaluation is null</li>
     * </ul>
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see AbstractFormula#getReturnType(Evaluator)
     * @see AbstractFormula#getBooleanValue(Evaluator)
     * @see AbstractFormula#getDateValue(Evaluator)
     * @see AbstractFormula#getDurationValue(Evaluator)
     * @see AbstractFormula#getIntegerValue(Evaluator)
     * @see AbstractFormula#getNumericValue(Evaluator)
     * @see AbstractFormula#getStringValue(Evaluator)
     */
    @JsonIgnore
    @Transient
    public Object getValue(Evaluator evaluator) throws GLanguageException {
        try {
            switch (getReturnType(evaluator)) {
                case INTEGER:
                    return getIntegerValue(evaluator);
                case NUMERIC:
                    return getNumericValue(evaluator);
                case STRING:
                    return getStringValue(evaluator);
                case BOOLEAN:
                    return getBooleanValue(evaluator);
                case DATE:
                    return getDateValue(evaluator);
                case DURATION:
                    return getDurationValue(evaluator);
                default:
                    return null;
            }
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateInnerError(this, evaluator));
            throw e;
        }
    }

    /**
     * Get the value as {@link Integer}<br>
     * A call to this method is equivalent to a call to {@link AbstractFormula#getIntegerValue(Evaluator)} with null
     * evaluator
     *
     * @return the value as {@link Integer}, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see AbstractFormula#getIntegerValue(Evaluator)
     */
    @JsonIgnore
    @Transient
    public Integer getIntegerValue() throws GLanguageException {
        return getIntegerValue(null);
    }

    /**
     * Get the value as {@link Integer} with an {@code evaluator} (can be null) by delegating to
     * {@link AbstractFormula#doGetIntegerValue(Evaluator)} method which throws a {@link GLanguageException} unless
     * it is redefined in concrete inheriting classes
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link Integer}, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see AbstractFormula#doGetIntegerValue(Evaluator)
     */
    @JsonIgnore
    @Transient
    public Integer getIntegerValue(Evaluator evaluator) throws GLanguageException {
        try {
            return doGetIntegerValue(evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.INTEGER, null));
            throw e;
        }
    }

    /**
     * Get the value as {@link Integer} with an {@code evaluator} (can be null)<br>
     * Throws a {@link GLanguageException} unless it is redefined in concrete inheriting classes
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link Integer}, may be null
     * @throws GLanguageException unless this method is redefined in concrete inheriting classes
     */
    @JsonIgnore
    @Transient
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(FormulaCannotInvokeEvaluationMethodInnerErrorFactory
                                             .getInteger(this, evaluator));
    }

    /**
     * Get the value as {@link Double}<br>
     * A call to this method is equivalent to a call to {@link AbstractFormula#getNumericValue(Evaluator)} with null
     * evaluator
     *
     * @return the value as {@link Double}, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see AbstractFormula#getNumericValue(Evaluator)
     */
    @JsonIgnore
    @Transient
    public Double getNumericValue() throws GLanguageException {
        return getNumericValue(null);
    }

    /**
     * Get the value as {@link Double} with an {@code evaluator} (can be null) by delegating to
     * {@link AbstractFormula#doGetNumericValue(Evaluator)} method which throws a {@link GLanguageException} unless
     * it is redefined in concrete inheriting classes
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link Double}, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see AbstractFormula#doGetNumericValue(Evaluator)
     */
    @JsonIgnore
    @Transient
    public Double getNumericValue(Evaluator evaluator) throws GLanguageException {
        try {
            return doGetNumericValue(evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.NUMERIC, null));
            throw e;
        }
    }

    /**
     * Get the value as {@link Double} with an {@code evaluator} (can be null)<br>
     * Throws a {@link GLanguageException} unless it is redefined in concrete inheriting classes
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link Double}, may be null
     * @throws GLanguageException unless this method is redefined in concrete inheriting classes
     */
    @JsonIgnore
    @Transient
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(FormulaCannotInvokeEvaluationMethodInnerErrorFactory
                                             .getNumeric(this, evaluator));
    }

    /**
     * Get the value as {@link String}<br>
     * A call to this method is equivalent to a call to {@link AbstractFormula#getStringValue(Evaluator)} with null
     * evaluator
     *
     * @return the value as {@link String}, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see AbstractFormula#getStringValue(Evaluator)
     */
    @JsonIgnore
    @Transient
    public String getStringValue() throws GLanguageException {
        return getStringValue(null);
    }

    /**
     * Get the value as {@link String} with an {@code evaluator} (can be null) by delegating to
     * {@link AbstractFormula#doGetStringValue(Evaluator)} method which throws a {@link GLanguageException} unless
     * it is redefined in concrete inheriting classes
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link String}, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see AbstractFormula#doGetStringValue(Evaluator)
     */
    @JsonIgnore
    @Transient
    public String getStringValue(Evaluator evaluator) throws GLanguageException {
        try {
            return doGetStringValue(evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.STRING, null));
            throw e;
        }
    }

    /**
     * Get the value as {@link String} with an {@code evaluator} (can be null)<br>
     * Throws a {@link GLanguageException} unless it is redefined in concrete inheriting classes
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link String}, may be null
     * @throws GLanguageException unless this method is redefined in concrete inheriting classes
     */
    @JsonIgnore
    @Transient
    protected String doGetStringValue(Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(FormulaCannotInvokeEvaluationMethodInnerErrorFactory
                                             .getString(this, evaluator));
    }

    /**
     * Get the value as {@link Boolean}<br>
     * A call to this method is equivalent to a call to {@link AbstractFormula#getBooleanValue(Evaluator)} with null
     * evaluator
     *
     * @return the value as {@link Boolean}, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see AbstractFormula#getBooleanValue(Evaluator)
     */
    @JsonIgnore
    @Transient
    public Boolean getBooleanValue() throws GLanguageException {
        return getBooleanValue(null);
    }

    /**
     * Get the value as {@link Boolean} with an {@code evaluator} (can be null) by delegating to
     * {@link AbstractFormula#doGetBooleanValue(Evaluator)} method which throws a {@link GLanguageException} unless
     * it is redefined in concrete inheriting classes
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link Boolean}, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see AbstractFormula#doGetBooleanValue(Evaluator)
     */
    @JsonIgnore
    @Transient
    public Boolean getBooleanValue(Evaluator evaluator) throws GLanguageException {
        try {
            return doGetBooleanValue(evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.BOOLEAN, null));
            throw e;
        }
    }

    /**
     * Get the value as {@link Boolean} with an {@code evaluator} (can be null)<br>
     * Throws a {@link GLanguageException} unless it is redefined in concrete inheriting classes
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link Boolean}, may be null
     * @throws GLanguageException unless this method is redefined in concrete inheriting classes
     */
    @JsonIgnore
    @Transient
    protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(FormulaCannotInvokeEvaluationMethodInnerErrorFactory
                                             .getBoolean(this, evaluator));
    }

    /**
     * Get the value as {@link LocalDate}<br>
     * A call to this method is equivalent to a call to {@link AbstractFormula#getDateValue(Evaluator)} with null
     * evaluator
     *
     * @return the value as {@link LocalDate}, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see AbstractFormula#getDateValue(Evaluator)
     */
    @JsonIgnore
    @Transient
    public LocalDate getDateValue() throws GLanguageException {
        return getDateValue(null);
    }

    /**
     * Get the value as {@link LocalDate} with an {@code evaluator} (can be null) by delegating to
     * {@link AbstractFormula#doGetDateValue(Evaluator)} method which throws a {@link GLanguageException} unless
     * it is redefined in concrete inheriting classes
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link LocalDate}, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see AbstractFormula#doGetDateValue(Evaluator)
     */
    @JsonIgnore
    @Transient
    public LocalDate getDateValue(Evaluator evaluator) throws GLanguageException {
        try {
            return doGetDateValue(evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.DATE, null));
            throw e;
        }
    }

    /**
     * Get the value as {@link LocalDate} with an {@code evaluator} (can be null)<br>
     * Throws a {@link GLanguageException} unless it is redefined in concrete inheriting classes
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link LocalDate}, may be null
     * @throws GLanguageException unless this method is redefined in concrete inheriting classes
     */
    @JsonIgnore
    @Transient
    protected LocalDate doGetDateValue(Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(FormulaCannotInvokeEvaluationMethodInnerErrorFactory
                                             .getDate(this, evaluator));
    }

    /**
     * Get the value as {@link Duration}<br>
     * A call to this method is equivalent to a call to {@link AbstractFormula#getDurationValue(Evaluator)} with null
     * evaluator
     *
     * @return the value as {@link Duration}, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see AbstractFormula#getDurationValue(Evaluator)
     */
    @JsonIgnore
    @Transient
    public Duration getDurationValue() throws GLanguageException {
        return getDurationValue(null);
    }

    /**
     * Get the value as {@link Duration} with an {@code evaluator} (can be null) by delegating to
     * {@link AbstractFormula#doGetDurationValue(Evaluator)} method which throws a {@link GLanguageException} unless
     * it is redefined in concrete inheriting classes
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link Duration}, may be null
     * @throws GLanguageException if an error occurs during the evaluation process
     * @see AbstractFormula#doGetDurationValue(Evaluator)
     */
    @JsonIgnore
    @Transient
    public Duration getDurationValue(Evaluator evaluator) throws GLanguageException {
        try {
            return doGetDurationValue(evaluator);
        } catch (GLanguageException e) {
            e.getError().setOuterError(new FormulaEvaluateTypeInnerError(this, evaluator, ErrorMethod.DURATION, null));
            throw e;
        }
    }

    /**
     * Get the value as {@link Duration} with an {@code evaluator} (can be null)<br>
     * Throws a {@link GLanguageException} unless it is redefined in concrete inheriting classes
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link Duration}, may be null
     * @throws GLanguageException unless this method is redefined in concrete inheriting classes
     */
    @JsonIgnore
    @Transient
    protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(FormulaCannotInvokeEvaluationMethodInnerErrorFactory
                                             .getDuration(this, evaluator));
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param ruleVersions the set of {@link RuleVersion} to set
     */
    public void setRuleVersions(Set<RuleVersion> ruleVersions) {
        this.ruleVersions = ruleVersions;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(FormulaDescription description) {
        this.description = description;
    }

    /**
     * @param parentFormula the parent formula to set
     */
    public void setParentFormula(AbstractFormula parentFormula) {
        this.parentFormula = parentFormula;
    }

    /**
     * @param parameters the list of {@link AbstractFormula parameters} to set
     */
    public void setParameters(List<AbstractFormula> parameters) {
        this.parameters = parameters;
    }

    /**
     * @param sequenceNumber the sequence number to set
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * @param constantValue the constant value to set
     */
    public void setConstantValue(String constantValue) {
        this.constantValue = constantValue;
    }

    /**
     * Get the textual representation
     *
     * @return the textual representation
     */
    public abstract String asText();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractFormula)) return false;

        AbstractFormula formula = (AbstractFormula) o;

        return id.equals(formula.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
