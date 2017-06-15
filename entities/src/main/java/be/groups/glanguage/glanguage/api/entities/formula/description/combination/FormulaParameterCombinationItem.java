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
 * This class represent an item of a combination of parameters
 * Created by michotte on 4/05/2017.
 */
@Entity
@Table(name = "FORMULA_PARAM_COMB_ITEM")
public class FormulaParameterCombinationItem implements Comparable<FormulaParameterCombinationItem> {

    /*
     * Fields
     */
    private Integer id;
    private FormulaParameterCombination combination;
    private MultilingualString name;
    private MultilingualString description;
    private Integer sequenceNumber;
    private Boolean optional;
    private String defaultValue;
    private Boolean repeatable;
    private Set<FormulaParameterCombinationItemType> types;
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
    @Id
    public Integer getId() {
        return id;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FORMULA_PARAM_COMB_ID", referencedColumnName = "ID")
    public FormulaParameterCombination getCombination() {
        return combination;
    }

    @ManyToOne
    @JoinColumn(name = "NAME_ID", referencedColumnName = "ID")
    public MultilingualString getName() {
        return name;
    }

    @Transient
    public Object getName(Language language) {
        return name.asText(language);
    }

    @ManyToOne
    @JoinColumn(name = "DESC_ID", referencedColumnName = "ID")
    public MultilingualString getDescription() {
        return description;
    }

    @Transient
    public Object getDescription(Language language) {
        return description.asText(language);
    }

    @Column(name = "SEQUENCE_NUMBER")
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    @Column(name = "OPTIONAL")
    public Boolean getOptional() {
        return optional;
    }

    @Column(name = "DEFAULT_VALUE")
    public String getDefaultValue() {
        return defaultValue;
    }

    @Column(name = "REPEATABLE")
    public Boolean getRepeatable() {
        return repeatable;
    }

    @OneToMany(mappedBy = "parameter", fetch = FetchType.EAGER)
    public Set<FormulaParameterCombinationItemType> getTypes() {
        return types;
    }

    @Transient
    public List<FormulaReturnType> getReturnTypes() {
        return getTypes().stream().map(FormulaParameterCombinationItemType::getReturnType).collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "parameter", fetch = FetchType.EAGER)
    public Set<FormulaParameterCombinationItemValue> getValues() {
        return values;
    }

    /*
     * Setters
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public void setCombination(FormulaParameterCombination combination) {
        this.combination = combination;
    }

    public void setName(MultilingualString name) {
        this.name = name;
    }

    public void setDescription(MultilingualString description) {
        this.description = description;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setRepeatable(Boolean repeatable) {
        this.repeatable = repeatable;
    }

    public void setTypes(Set<FormulaParameterCombinationItemType> types) {
        this.types = types;
    }

    public void setValues(Set<FormulaParameterCombinationItemValue> values) {
        this.values = values;
    }

    /*
     * Methods
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

    @Transient
    public boolean isValid(AbstractFormula parameter, Evaluator evaluator) {
        return parameter != null && isValidType(parameter, evaluator) && isValidValue(parameter, evaluator);
    }

    @Transient
    public boolean isValidType(AbstractFormula parameter, Evaluator evaluator) {
        return getReturnTypes().contains(parameter.getReturnType(evaluator));
    }

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
