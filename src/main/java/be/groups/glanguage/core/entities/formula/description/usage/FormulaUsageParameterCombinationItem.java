package be.groups.glanguage.core.entities.formula.description.usage;

import be.groups.glanguage.core.entities.formula.description.combination.FormulaParameterCombinationItem;
import be.groups.glanguage.core.entities.utils.MultilingualString;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * This class represent an item of a combination of parameters overridden by a usage
 *
 * @author michotte
 */
@Entity
@Table(name = "FORMULA_USAGE_PARAM_COMB_ITEM")
public class FormulaUsageParameterCombinationItem {

    /*
     * Fields
     */

    /**
     * Technical unique ID
     */
    private Long id;

    /**
     * Usage for which this is an overriding parameter
     */
    private FormulaUsage usage;

    /**
     * Combination parameter for which this is an overriding parameter
     */
    private FormulaParameterCombinationItem combinationParameter;

    /**
     * Name
     */
    private MultilingualString name;

    /**
     * Description
     */
    private MultilingualString description;

    /*
     * Constructors
     */
    public FormulaUsageParameterCombinationItem() {
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
     * Get the combination parameter for which this is an overriding parameter
     *
     * @return the combination parameter for which this is an overriding parameter
     */
    @ManyToOne
    @JoinColumn(name = "FORMULA_PARAM_COMB_ITEM_ID", referencedColumnName = "ID")
    public FormulaParameterCombinationItem getCombinationParameter() {
        return combinationParameter;
    }

    /**
     * Get the usage for which this is an overriding parameter
     *
     * @return the usage for which this is an overriding parameter
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FORMULA_USAGE_ID", referencedColumnName = "ID")
    public FormulaUsage getUsage() {
        return usage;
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
     * @param usage the usage to set
     */
    public void setUsage(FormulaUsage usage) {
        this.usage = usage;
    }

    /**
     * @param combinationParameter the combination parameter to set
     */
    public void setCombinationParameter(FormulaParameterCombinationItem combinationParameter) {
        this.combinationParameter = combinationParameter;
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

    /*
     * Utils
     */
    @Override
    public String toString() {
        return "FormulaUsageParameterCombinationItem{" + "usage=" + usage + ", parameter=" + combinationParameter +
                ", name=" + name + ", description=" + description + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormulaUsageParameterCombinationItem)) return false;

        FormulaUsageParameterCombinationItem that = (FormulaUsageParameterCombinationItem) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
