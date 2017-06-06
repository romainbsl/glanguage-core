package be.groups.glanguage.glanguage.api.entities.formula.description.usage;

import be.groups.glanguage.glanguage.api.entities.formula.description.combination.FormulaParameterCombinationItem;
import be.groups.glanguage.glanguage.api.entities.utils.MultilingualString;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * This class represent an item of a combination of parameters overridden by a usage
 * Created by michotte on 4/05/2017.
 */
@Entity
@Table(name = "FORMULA_USAGE_PARAM_COMB_ITEM")
public class FormulaUsageParameterCombinationItem {

    /*
     * Fields
     */
    private Integer id;
    private FormulaUsage usage;
    private FormulaParameterCombinationItem combinationParameter;
    private MultilingualString name;
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
    @Id
    public Integer getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "FORMULA_PARAM_COMB_ITEM_ID", referencedColumnName = "ID")
    public FormulaParameterCombinationItem getCombinationParameter() {
        return combinationParameter;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FORMULA_USAGE_ID", referencedColumnName = "ID")
    public FormulaUsage getUsage() {
        return usage;
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

    /*
     * Setters
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsage(FormulaUsage usage) {
        this.usage = usage;
    }

    public void setCombinationParameter(FormulaParameterCombinationItem combinationParameter) {
        this.combinationParameter = combinationParameter;
    }

    public void setName(MultilingualString name) {
        this.name = name;
    }

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
