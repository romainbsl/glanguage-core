package be.groups.glanguage.glanguage.api.entities.formula.description.conbination;

import be.groups.glanguage.glanguage.api.entities.utils.MultilingualString;

import javax.persistence.*;

/**
 * This class represent a possible value for a {@link FormulaParameterConbinationItem} and a description of the
 * meaning of that value
 * Created by michotte on 4/05/2017.
 */
@Entity
@Table(name = "FORMULA_PARAM_CONB_ITEM_VALUE")
public class FormulaParameterConbinationItemValue {

    /*
     * Fields
     */
    private Integer id;
    private FormulaParameterConbinationItem parameter;
    private String value;
    private MultilingualString description;

    /*
     * Constructors
     */
    public FormulaParameterConbinationItemValue() {
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
    public FormulaParameterConbinationItem getParameter() {
        return parameter;
    }

    @Column
    public String getValue() {
        return value;
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

    public void setParameter(FormulaParameterConbinationItem parameter) {
        this.parameter = parameter;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDescription(MultilingualString description) {
        this.description = description;
    }

    /*
     * Utils
     */
    @Override
    public String toString() {
        return "FormulaParameterConbinationItemValue{" + "value='" + value + '\'' + ", description=" + description + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormulaParameterConbinationItemValue)) return false;

        FormulaParameterConbinationItemValue that = (FormulaParameterConbinationItemValue) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
