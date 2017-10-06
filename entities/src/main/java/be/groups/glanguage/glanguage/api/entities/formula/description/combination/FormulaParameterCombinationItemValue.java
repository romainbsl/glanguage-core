package be.groups.glanguage.glanguage.api.entities.formula.description.combination;

import be.groups.glanguage.glanguage.api.entities.utils.MultilingualString;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * This class represent a possible value for a {@link FormulaParameterCombinationItem} and a description of the
 * meaning of that value
 * @author michotte
 */
@Entity
@Table(name = "FORMULA_PARAM_COMB_ITEM_VALUE")
public class FormulaParameterCombinationItemValue {

    /*
     * Fields
     */

    /**
     * Technical unique ID
     */
    private Long id;

    /**
     * Parameter for which this is a value
     */
    private FormulaParameterCombinationItem parameter;

    /**
     * Value
     */
    private String value;

    /**
     * Description
     */
    private MultilingualString description;

    /*
     * Constructors
     */
    public FormulaParameterCombinationItemValue() {
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
    public Long getId() {
        return id;
    }

    /**
     * Get the parameter for which this is a return type
     *
     * @return the parameter for which this is a return type
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FORMULA_PARAM_COMB_ITEM_ID", referencedColumnName = "ID")
    public FormulaParameterCombinationItem getParameter() {
        return parameter;
    }

    /**
     * Get the value
     *
     * @return the value
     */
    @Column
    public String getValue() {
        return value;
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

    /*
     * Setters
     */

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param parameter the the parameter for which this is a return type to set
     */
    public void setParameter(FormulaParameterCombinationItem parameter) {
        this.parameter = parameter;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(MultilingualString description) {
        this.description = description;
    }

    /*
     * Utils
     */
    @Override
    public String toString() {
        return "FormulaParameterCombinationItemValue{" + "value='" + value + '\'' + ", description=" + description + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormulaParameterCombinationItemValue)) return false;

        FormulaParameterCombinationItemValue that = (FormulaParameterCombinationItemValue) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
