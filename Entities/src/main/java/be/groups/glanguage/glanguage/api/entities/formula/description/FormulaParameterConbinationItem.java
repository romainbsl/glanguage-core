package be.groups.glanguage.glanguage.api.entities.formula.description;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.utils.MultilingualString;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represent an item of a conbination of parameters
 * Created by michotte on 4/05/2017.
 */
@Entity
@Table(name = "FORMULA_PARAM_CONB_ITEM")
public class FormulaParameterConbinationItem {

    /*
     * Fields
     */
    private Integer id;
    private FormulaParameterConbination conbination;
    private MultilingualString name;
    private MultilingualString description;
    private Integer sequenceNumber;
    private Boolean optional;
    private String defaultValue;
    private Boolean repeatable;
    private Set<FormulaParameterConbinationItemType> types;
    private Set<FormulaParameterConbinationItemValue> values;

    /*
     * Constructors
     */
    public FormulaParameterConbinationItem() {
        super();
    }

    /*
     * Getters
     */
    @Id
    public Integer getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "FORMULA_PARAM_CONB_ID", referencedColumnName = "ID")
    public FormulaParameterConbination getConbination() {
        return conbination;
    }

    @ManyToOne
    @JoinColumn(name = "NAME_ID", referencedColumnName = "ID")
    public MultilingualString getName() {
        return name;
    }

    @ManyToOne
    @JoinColumn(name = "DESC_ID", referencedColumnName = "ID")
    public MultilingualString getDescription() {
        return description;
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

    @OneToMany(mappedBy = "parameter")
    public Set<FormulaParameterConbinationItemType> getTypes() {
        return types;
    }

    @OneToMany(mappedBy = "parameter")
    public Set<FormulaParameterConbinationItemValue> getValues() {
        return values;
    }

    /*
     * Setters
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public void setConbination(FormulaParameterConbination conbination) {
        this.conbination = conbination;
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

    public void setTypes(Set<FormulaParameterConbinationItemType> types) {
        this.types = types;
    }

    public void setValues(Set<FormulaParameterConbinationItemValue> values) {
        this.values = values;
    }

    /*
     * Methods
     */
    public boolean isValid(AbstractFormula formula, Evaluator evaluator) {
        return isValidType(formula, evaluator) && isValidValue(formula, evaluator);
    }

    public boolean isValidType(AbstractFormula formula, Evaluator evaluator) {
        return getTypes().contains(formula.getReturnType(evaluator));
    }

    private boolean isValidValue(AbstractFormula formula, Evaluator evaluator) {
        if (getValues() != null && !getValues().isEmpty()) {
            switch (formula.getReturnType(evaluator)) {
                case INTEGER:
                    return getValues().stream().map(v -> Integer.valueOf(v.getValue())).collect(Collectors.toList())
                            .contains(formula.getIntegerValue(evaluator));
                case NUMERIC:
                    return getValues().stream().map(v -> Double.valueOf(v.getValue())).collect(Collectors.toList())
                            .contains(formula.getNumericValue(evaluator));
                case STRING:
                    return getValues().stream().map(v -> v.getValue()).collect(Collectors.toList())
                            .contains(formula.getStringValue(evaluator));
                case BOOLEAN:
                    return getValues().stream().map(v -> Boolean.valueOf(v.getValue())).collect(Collectors.toList())
                            .contains(formula.getBooleanValue(evaluator));
                case DATE:
                    return getValues().stream().map(v -> LocalDate
                            .parse(v.getValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))).collect(Collectors.toList())
                            .contains(formula.getDateValue(evaluator));
                case DURATION:
                    return getValues().stream().map(v -> Duration.parse(v.getValue())).collect(Collectors.toList())
                            .contains(formula.getDurationValue(evaluator));
                case LIST:
                    //fall through
                case PROCEDURE:
                    //fall through
                default:
                    return true;
            }
        }
        return true;
    }

    /*
     * Utils
     */
        @Override public String toString () {
            return "FormulaParameterConbinationItem{" + "name=" + name + ", description=" + description + ", " +
                    "sequenceNumber=" + sequenceNumber + ", optional=" + optional + ", defaultValue='" + defaultValue
                    + '\'' + ", repeatable=" + repeatable + ", types=" + types + ", values=" + values + '}';
        }

        @Override public boolean equals (Object o){
            if (this == o) return true;
            if (!(o instanceof FormulaParameterConbinationItem)) return false;

            FormulaParameterConbinationItem that = (FormulaParameterConbinationItem) o;

            return id.equals(that.id);
        }

        @Override public int hashCode () {
            return id.hashCode();
        }

    }
