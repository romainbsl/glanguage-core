package be.groups.glanguage.glanguage.api.entities.formula.description.combination;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import be.groups.glanguage.glanguage.api.entities.utils.MultilingualString;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.description.combination
        .FormulaParameterCombinationItemInnerErrorFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represent an item of a combination of parameters<br>
 * It defines :
 * <ul>
 * <li>the {@link FormulaReturnType types}</li>
 * <li>the optional status</li>
 * <li>the repeatable status</li>
 * <li>the eventual default value</li>
 * <li>the eventual set of possible values</li>
 * </ul>
 *
 * @author michotte
 */
@Entity
@Table(name = "FORMULA_PARAM_COMB_ITEM")
public class FormulaParameterCombinationItem implements Comparable<FormulaParameterCombinationItem> {

    /*
     * Fields
     */

    /**
     * Technical unique ID
     */
    private Integer id;

    /**
     * The {@link FormulaParameterCombination} of which this is an item
     */
    private FormulaParameterCombination combination;

    /**
     * Name
     */
    private MultilingualString name;

    /**
     * Description
     */
    private MultilingualString description;

    /**
     * Sequence number indicating the index of this item in the combination
     */
    private Integer sequenceNumber;

    /**
     * Optional status
     */
    private Boolean optional;

    /**
     * Default value
     */
    private String defaultValue;

    /**
     * Repeatable status
     */
    private Boolean repeatable;

    /**
     * The set of {@link FormulaParameterCombinationItemType possible types}
     */
    private Set<FormulaParameterCombinationItemType> types;

    /**
     * The set of {@link FormulaParameterCombinationItemValue possible values}
     */
    private Set<FormulaParameterCombinationItemValue> values;

    /*
     * Constructors
     */
    public FormulaParameterCombinationItem() {
        super();
    }

    /*
     * Getters
     */

    /**
     * Get the technical id
     *
     * @return the id
     */
    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FORMULA_PARAM_COMB_ID", referencedColumnName = "ID")
    public FormulaParameterCombination getCombination() {
        return combination;
    }

    /**
     * Get the name
     *
     * @return the name
     */
    @ManyToOne
    @JoinColumn(name = "NAME_ID", referencedColumnName = "ID")
    public MultilingualString getName() {
        return name;
    }

    /**
     * Get the name text in {@link Language language}
     *
     * @param language the language of the name text to be returned
     * @return the name text in {@link Language language} if it exists, an empty {@link String} otherwise
     */
    @Transient
    public Object getName(Language language) {
        return name.asText(language);
    }

    /**
     * Get the description
     *
     * @return the description
     */
    @ManyToOne
    @JoinColumn(name = "DESC_ID", referencedColumnName = "ID")
    public MultilingualString getDescription() {
        return description;
    }

    /**
     * Get the description text in {@link Language language}
     *
     * @param language the language of the description text to be returned
     * @return the description text in {@link Language language} if it exists, an empty {@link String} otherwise
     */
    @Transient
    public Object getDescription(Language language) {
        return description.asText(language);
    }

    /**
     * Get the sequence number
     *
     * @return the sequence number
     */
    @Column(name = "SEQUENCE_NUMBER")
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Get the optional status
     *
     * @return the optional status
     */
    @Column(name = "OPTIONAL")
    public Boolean getOptional() {
        return optional;
    }

    /**
     * Get the default value
     *
     * @return the default value, can be null
     */
    @Column(name = "DEFAULT_VALUE")
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Get the repeatable status
     *
     * @return the repeatable status
     */
    @Column(name = "REPEATABLE")
    public Boolean getRepeatable() {
        return repeatable;
    }

    /**
     * Get the set of {@link FormulaParameterCombinationItemType possible types}
     *
     * @return the set of {@link FormulaParameterCombinationItemType possible types}
     */
    @OneToMany(mappedBy = "parameter", fetch = FetchType.EAGER)
    public Set<FormulaParameterCombinationItemType> getTypes() {
        return types;
    }

    /**
     * Get the list of {@link FormulaReturnType possible types} corresponding to
     * {@link FormulaParameterCombinationItem#getTypes()}
     *
     * @return the list of {@link FormulaReturnType possible types}  corresponding to
     * {@link FormulaParameterCombinationItem#getTypes()}
     */
    @Transient
    public List<FormulaReturnType> getReturnTypes() {
        return getTypes().stream().map(FormulaParameterCombinationItemType::getReturnType).collect(Collectors.toList());
    }

    /**
     * Get the set of {@link FormulaParameterCombinationItemValue possible values}
     *
     * @return the set of {@link FormulaParameterCombinationItemValue possible values}
     */
    @OneToMany(mappedBy = "parameter", fetch = FetchType.EAGER)
    public Set<FormulaParameterCombinationItemValue> getValues() {
        return values;
    }

    /*
     * Setters
     */

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param combination the {@link FormulaParameterCombination} of which this is an item to set
     */
    public void setCombination(FormulaParameterCombination combination) {
        this.combination = combination;
    }

    /**
     * @param name the name to set
     */
    public void setName(MultilingualString name) {
        this.name = name;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(MultilingualString description) {
        this.description = description;
    }

    /**
     * @param sequenceNumber the sequence number to set
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * @param optional the id optional status to set
     */
    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    /**
     * @param defaultValue the default value to set
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * @param repeatable the id repeatable status to set
     */
    public void setRepeatable(Boolean repeatable) {
        this.repeatable = repeatable;
    }

    /**
     * @param types the set of {@link FormulaParameterCombinationItemType possible types} to set
     */
    public void setTypes(Set<FormulaParameterCombinationItemType> types) {
        this.types = types;
    }

    /**
     * @param values the set of {@link FormulaParameterCombinationItemValue possible values} to set
     */
    public void setValues(Set<FormulaParameterCombinationItemValue> values) {
        this.values = values;
    }

    /*
     * Methods
     */

    /**
     * Validate this for a {@link AbstractFormula parameter} of a {@link AbstractFormula formula} for a particular
     * {@link FormulaUsage usage} with an {@link Evaluator evaluator} (can be null)<br>
     * Validation process :
     * <ol>
     * <li>Check if it is valid by delegating to
     * {@link FormulaParameterCombinationItem#isValid(AbstractFormula, Evaluator)}</li>
     * <li>If valid, do nothing</li>
     * <li>Else if not valid, check if {@link AbstractFormula parameter} is null
     * <ol>
     * <li>If {@link AbstractFormula parameter} is null, throw a {@link GLanguageException}</li>
     * <li>Else, check if {@link AbstractFormula parameter} has a valid type by delegating to
     * {@link FormulaParameterCombinationItem#isValidType(AbstractFormula, Evaluator)}
     * <ol>
     * <li>If {@link AbstractFormula parameter} does not have a valid type, throw a
     * {@link GLanguageException}</li>
     * <li>Else, check if {@link AbstractFormula parameter} has a valid value by delegating to
     * {@link FormulaParameterCombinationItem#isValidValue(AbstractFormula, Evaluator)}
     * <ol>
     * <li>If {@link AbstractFormula parameter} does not have a valid value, throw a
     * {@link GLanguageException}</li>
     * </ol>
     * </li>
     * </ol>
     * </li>
     * </ol>
     * </li>
     * <li>If a {@link GLanguageException} is thrown, set an outer error and throw it</li>
     * </ol>
     *
     * @param formula    the {@link AbstractFormula formula} to be validated
     * @param usage      the {@link FormulaUsage} from which {@code this} is a parameter combination item
     * @param parameter  the {@link AbstractFormula parameter} to be validated
     * @param evaluator  the {@link Evaluator evaluator} to be used during the validation process, can be null
     * @throws GLanguageException if an error occurs during the validation process or if the
     * {@link AbstractFormula parameter} is not valid
     */
    public void validate(AbstractFormula formula,
                         FormulaUsage usage,
                         AbstractFormula parameter,
                         Evaluator evaluator) throws GLanguageException {
        try {
            if (!isValid(parameter, evaluator)) {
                if (parameter == null) {
                    throw new GLanguageException(FormulaParameterCombinationItemInnerErrorFactory
                                                         .getNullParameter(formula, usage, this, evaluator));
                }
                if (!isValidType(parameter, evaluator)) {
                    throw new GLanguageException(FormulaParameterCombinationItemInnerErrorFactory.getWrongParameterType(
                            formula,
                            usage,
                            this,
                            parameter.asText(),
                            parameter.getReturnType(evaluator),
                            evaluator));
                } else if (!isValidValue(parameter, evaluator)) {
                    throw new GLanguageException(FormulaParameterCombinationItemInnerErrorFactory
                                                         .getWrongParameterValue(formula,
                                                                                 usage,
                                                                                 this,
                                                                                 parameter.asText(),
                                                                                 parameter.getValue(evaluator),
                                                                                 evaluator));
                }
            }
        } catch (GLanguageException e) {
            e.getError().setOuterError(FormulaParameterCombinationItemInnerErrorFactory.getUnableToValidate(formula,
                                                                                                            usage,
                                                                                                            this,
                                                                                                            parameter
                                                                                                                    == null ? "[null]" : parameter
                                                                                                                    .asText(),
                                                                                                            evaluator));
            throw e;
        }
    }

    /**
     * Is this valid for a {@link AbstractFormula parameter} with an {@link Evaluator evaluator} (can be
     * null) ?<br>
     * Validation process :<br>
     * Check if :
     * <ol>
     *     <li>the {@link AbstractFormula parameter} is not null</li>
     *     <li>the {@link AbstractFormula parameter} has a valid type by delegating to
     * {@link FormulaParameterCombinationItem#isValidType(AbstractFormula, Evaluator)}</li>
     *     <li>t{@link AbstractFormula parameter} has a valid value by delegating to
     * {@link FormulaParameterCombinationItem#isValidValue(AbstractFormula, Evaluator)}</li>
     * </ol>
     * If all checks are true, return true, false otherwise
     *
     * @param parameter  the {@link AbstractFormula parameter} to be validated
     * @param evaluator  the {@link Evaluator evaluator} to be used during the validation process, can be null
     * @return true if this is valid for the{@link AbstractFormula parameter}, false otherwise
     */
    @Transient
    public boolean isValid(AbstractFormula parameter, Evaluator evaluator) {
        return parameter != null && isValidType(parameter, evaluator) && isValidValue(parameter, evaluator);
    }

    /**
     * Is this valid for a {@link AbstractFormula parameter} with an {@link Evaluator evaluator} (can be
     * null) only according to the {@link FormulaReturnType type} ?<br>
     * Validation process :
     * <ul>
     *     <li>Return true if the {@link FormulaReturnType type} of the {@link AbstractFormula parameter} is contained in the
     * list of {@link FormulaParameterCombinationItem#getReturnTypes() possible types} of {@code this}, false
     * otherwise</li>
     * </ul>
     *
     * @param parameter  the {@link AbstractFormula parameter} to be validated
     * @param evaluator  the {@link Evaluator evaluator} to be used during the validation process, can be null
     * @return true if this is valid for the{@link AbstractFormula parameter} only according to the type, false
     * otherwise
     */
    @Transient
    public boolean isValidType(AbstractFormula parameter, Evaluator evaluator) {
        return getReturnTypes().contains(parameter.getReturnType(evaluator));
    }

    /**
     * Is this valid for a {@link AbstractFormula parameter} with an {@link Evaluator evaluator} (can be
     * null) only according to the value ?<br>
     * Validation process :
     * <ol>
     *     <li>If the list of {@link FormulaParameterCombinationItem#getValues() possible values} is null or empty,
     *     return true
     *     </li>
     *     <li>Else, if the value of the {@link AbstractFormula parameter} is containes in the list of
     *     {@link FormulaParameterCombinationItem#getValues() possible values}, return true, false otherwise</li>
     * </ol>
     *
     * @param parameter  the {@link AbstractFormula parameter} to be validated
     * @param evaluator  the {@link Evaluator evaluator} to be used during the validation process, can be null
     * @return true if this is valid for the{@link AbstractFormula parameter} only according to the value, false
     * otherwise
     */
    @Transient
    public boolean isValidValue(AbstractFormula parameter, Evaluator evaluator) {
        if (getValues() == null || getValues().isEmpty()) {
            return true;
        } else {
            try {
                switch (parameter.getReturnType(evaluator)) {
                    case INTEGER:
                        return getValues().stream().map(v -> Integer.valueOf(v.getValue())).collect(Collectors.toList())
                                .contains(parameter.getIntegerValue(evaluator));
                    case NUMERIC:
                        return getValues().stream().map(v -> Double.valueOf(v.getValue())).collect(Collectors.toList())
                                .contains(parameter.getNumericValue(evaluator));
                    case STRING:
                        String value = parameter.getStringValue(evaluator);
                        return getValues().stream().map(v -> v.getValue()).anyMatch(v -> v.equalsIgnoreCase(value));
                    case BOOLEAN:
                        return getValues().stream().map(v -> Boolean.valueOf(v.getValue())).collect(Collectors.toList())
                                .contains(parameter.getBooleanValue(evaluator));
                    case DATE:
                        return getValues().stream().map(v -> LocalDate
                                .parse(v.getValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))).collect(Collectors
                                                                                                                 .toList())
                                .contains(parameter.getDateValue(evaluator));
                    case DURATION:
                        return getValues().stream().map(v -> Duration.parse(v.getValue())).collect(Collectors.toList())
                                .contains(parameter.getDurationValue(evaluator));
                    case LIST:
                        //fall through
                    case PROCEDURE:
                        //fall through
                    default:
                        return true;
                }
            } catch (GLanguageException e) {
                return false;
            }
        }
    }

    /*
     * Utils
     */
    @Override
    public String toString() {
        return "FormulaParameterCombinationItem{" + "name=" + name + ", description=" + description + ", " +
                "sequenceNumber=" + sequenceNumber + ", optional=" + optional + ", defaultValue='" + defaultValue +
                '\'' + ", repeatable=" + repeatable + ", types=" + types + ", values=" + values + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormulaParameterCombinationItem)) return false;

        FormulaParameterCombinationItem that = (FormulaParameterCombinationItem) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int compareTo(FormulaParameterCombinationItem o) {
        return getSequenceNumber().compareTo(o.getSequenceNumber());
    }
}
