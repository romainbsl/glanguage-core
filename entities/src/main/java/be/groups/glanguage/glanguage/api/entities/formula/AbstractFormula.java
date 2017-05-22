package be.groups.glanguage.glanguage.api.entities.formula;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.cannot.invoke.evaluation.method.FormulaCannotInvokeEvaluationMethodInnerErrorFactory;
import be.groups.glanguage.glanguage.api.error.formula.base.unable.evaluate.FormulaEvaluateInnerError;
import be.groups.glanguage.glanguage.api.error.formula.base.unable.evaluate.FormulaEvaluateTypeInnerError;
import be.groups.glanguage.glanguage.api.error.utils.ErrorMethod;
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
 * - a FormulaDescription <br>
 * - a parent Formula of which this is a parameter <br>
 * - an sequence number representing the position of this in the parent's parameters sequence <br>
 * - a set of sub-Formula's representing the parameters of this Formula <br>
 * - an evaluated status <br>
 * - a value <br>
 * <br>
 * An AbstractFormula can be evaluated - can be given a value which type corresponds to its
 * {@link FormulaReturnType}. <br>
 * How an AbstractFormula is evaluated depends on its {@link FormulaType}. <br>
 * Evaluating an AbstractFormula consists in applying its own evaluation method on the results of
 * the evaluation of its sub- {@link AbstractFormula}'s parameters.
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
    private int id;

    /**
     * Set of RuleVersion this is the formula
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
     * Parameters
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
     * @return the id
     */
    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    /**
     * @return the ruleVersions
     */
    @JsonIgnore
    @OneToMany(mappedBy = "formula")
    public Set<RuleVersion> getRuleVersions() {
        return ruleVersions;
    }

    /**
     * @return the description
     */
    @OneToOne
    @JoinColumn(name = "FORMULA_DESCRIPTION_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    public FormulaDescription getDescription() {
        return description;
    }

    /**
     * @return the parentFormula
     */
    @ManyToOne
    @JoinColumn(name = "PARENT_FORMULA_ID", updatable = false, nullable = true)
    public AbstractFormula getParentFormula() {
        return parentFormula;
    }

    /**
     * @return the parameters
     */
    @OneToMany(mappedBy = "parentFormula", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("SEQUENCE_NUMBER")
    public List<AbstractFormula> getParameters() {
        return parameters;
    }

    /**
     * @return the sequenceNumber
     */
    @Column(name = "SEQUENCE_NUMBER", updatable = false, nullable = true)
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * @return the constantValue
     */
    @Column(name = "VALUE", nullable = true)
    public String getConstantValue() {
        return constantValue;
    }

    @JsonIgnore
    @Transient
    public Integer getDiscriminatorValue() {
        return Integer.valueOf(this.getClass().getAnnotation(DiscriminatorValue.class).value());
    }

    @Transient
    public abstract boolean isTerminal();

    @JsonIgnore
    @Transient
    public boolean isValid(Evaluator evaluator) {
        return description.isValid(parameters, evaluator);
    }

    @JsonIgnore
    @Transient
    public FormulaReturnType getReturnType() {
        return getReturnType(null);
    }

    @JsonIgnore
    @Transient
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        return description.getReturnType(parameters, evaluator);
    }

    /**
     * @return Default true
     */
    @JsonIgnore
    @Transient
    public boolean isValuable() {
        return true;
    }

    @JsonIgnore
    @Transient
    public Object getValue() throws GLanguageException {
        return getValue(null);
    }

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

    @JsonIgnore
    @Transient
    public Integer getIntegerValue() throws GLanguageException {
        return getIntegerValue(null);
    }

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

    @JsonIgnore
    @Transient
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(FormulaCannotInvokeEvaluationMethodInnerErrorFactory
                                             .getInteger(this, evaluator));
    }

    @JsonIgnore
    @Transient
    public Double getNumericValue() throws GLanguageException {
        return getNumericValue(null);
    }

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

    @JsonIgnore
    @Transient
    protected Double doGetNumericValue(Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(FormulaCannotInvokeEvaluationMethodInnerErrorFactory
                                             .getNumeric(this, evaluator));
    }

    @JsonIgnore
    @Transient
    public String getStringValue() throws GLanguageException {
        return getStringValue(null);
    }

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

    @JsonIgnore
    @Transient
    protected String doGetStringValue(Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(FormulaCannotInvokeEvaluationMethodInnerErrorFactory
                                             .getString(this, evaluator));
    }

    @JsonIgnore
    @Transient
    public Boolean getBooleanValue() throws GLanguageException {
        return getBooleanValue(null);
    }

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

    @JsonIgnore
    @Transient
    protected Boolean doGetBooleanValue(Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(FormulaCannotInvokeEvaluationMethodInnerErrorFactory
                                             .getBoolean(this, evaluator));
    }

    @JsonIgnore
    @Transient
    public LocalDate getDateValue() throws GLanguageException {
        return getDateValue(null);
    }

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

    @JsonIgnore
    @Transient
    protected LocalDate doGetDateValue(Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(FormulaCannotInvokeEvaluationMethodInnerErrorFactory
                                             .getDate(this, evaluator));
    }

    @JsonIgnore
    @Transient
    public Duration getDurationValue() throws GLanguageException {
        return getDurationValue(null);
    }

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

    @JsonIgnore
    @Transient
    protected Duration doGetDurationValue(Evaluator evaluator) throws GLanguageException {
        throw new GLanguageException(FormulaCannotInvokeEvaluationMethodInnerErrorFactory
                                             .getDuration(this, evaluator));
    }

    @JsonIgnore
    @Transient
    public boolean isBranched() {
        return (parameters != null && !parameters.isEmpty() && parameters.stream()
                .allMatch(AbstractFormula::isBranched));
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param ruleVersions the ruleVersions to set
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
     * @param parentFormula the parentFormula to set
     */
    public void setParentFormula(AbstractFormula parentFormula) {
        this.parentFormula = parentFormula;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(List<AbstractFormula> parameters) {
        this.parameters = parameters;
    }

    /**
     * @param sequenceNumber the sequenceNumber to set
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * @param constantValue the constantValue to set
     */
    public void setConstantValue(String constantValue) {
        this.constantValue = constantValue;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AbstractFormula other = (AbstractFormula) obj;
        if (id != other.id) return false;
        return true;
    }

    public abstract String asText();

}