package be.groups.glanguage.glanguage.api.entities.formula.description.usage;

import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbinationItem;
import be.groups.glanguage.glanguage.api.entities.utils.MultilingualString;

import javax.persistence.*;

/**
 * This class represent an item of a conbination of parameters overridden by a usage
 * Created by michotte on 4/05/2017.
 */
@Entity
@Table(name = "FORMULA_USAGE_PARAM_CONB_ITEM")
public class FormulaUsageParameterConbinationItem {

    /*
     * Fields
     */
    private Integer id;
    private FormulaUsage usage;
    private FormulaParameterConbinationItem conbinationParameter;
    private MultilingualString name;
    private MultilingualString description;

    /*
     * Constructors
     */
    public FormulaUsageParameterConbinationItem() {
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
    @JoinColumn(name = "FORMULA_PARAM_CONB_ITEM_ID", referencedColumnName = "ID")
    public FormulaParameterConbinationItem getConbinationParameter() {
        return conbinationParameter;
    }

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

    public void setConbinationParameter(FormulaParameterConbinationItem conbinationParameter) {
        this.conbinationParameter = conbinationParameter;
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
        return "FormulaUsageParameterConbinationItem{" + "usage=" + usage + ", parameter=" + conbinationParameter +
                ", name=" + name + ", description=" + description + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormulaUsageParameterConbinationItem)) return false;

        FormulaUsageParameterConbinationItem that = (FormulaUsageParameterConbinationItem) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
