package be.groups.glanguage.glanguage.api.entities.formula.description.combination;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnTypeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by michotte on 30/01/2017.
 */
@Entity
@Table(name = "FORMULA_PARAM_COMB_ITEM_TYPE")
public class FormulaParameterCombinationItemType {

    /*
     * Fields
     */
    private Integer id;
    private FormulaParameterCombinationItem parameter;
    private FormulaReturnType returnType;

    /*
     * Constructors
     */
    public FormulaParameterCombinationItemType() {
        super();
    }

    /*
     * Getters
     */
    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FORMULA_PARAM_COMB_ITEM_ID", referencedColumnName = "ID")
    public FormulaParameterCombinationItem getParameter() {
        return parameter;
    }

    @Column(name = "FORMULA_RETURN_TYPE_ID", nullable = false)
    @Convert(converter = FormulaReturnTypeConverter.class)
    public FormulaReturnType getReturnType() {
        return returnType;
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

    public void setReturnType(FormulaReturnType returnType) {
        this.returnType = returnType;
    }

    /*
     * Utils
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormulaParameterCombinationItemType)) return false;

        FormulaParameterCombinationItemType that = (FormulaParameterCombinationItemType) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
