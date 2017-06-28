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
    private Integer id;
    private FormulaParameterCombinationItem parameter;
    private String value;
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
    @Id
    public Integer getId() {
        return id;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FORMULA_PARAM_COMB_ITEM_ID", referencedColumnName = "ID")
    public FormulaParameterCombinationItem getParameter() {
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

    public void setParameter(FormulaParameterCombinationItem parameter) {
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
