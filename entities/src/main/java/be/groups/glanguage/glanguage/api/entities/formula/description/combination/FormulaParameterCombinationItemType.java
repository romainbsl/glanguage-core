package be.groups.glanguage.glanguage.api.entities.formula.description.combination;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnTypeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * This class represents a {@link FormulaParameterCombinationItem} return type<br>
 * Each {@link FormulaParameterCombinationItem} can have multiple return types, this class represent one of them
 *
 * @author michotte
 */
@Entity
@Table(name = "FORMULA_PARAM_COMB_ITEM_TYPE")
public class FormulaParameterCombinationItemType {

    /*
     * Fields
     */

    /**
     * Technical unique ID
     */
    private Long id;

    /**
     * Parameter for which this is a return type
     */
    private FormulaParameterCombinationItem parameter;

    /**
     * Return type
     */
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
    /**
     * Get the technical id
     *
     * @return the id
     */
    @Id
    @Column(name = "ID", nullable = false)
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
     * Get the return type
     *
     * @return the return type
     */
    @Column(name = "FORMULA_RETURN_TYPE_ID", nullable = false)
    @Convert(converter = FormulaReturnTypeConverter.class)
    public FormulaReturnType getReturnType() {
        return returnType;
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
     * @param returnType the return type to set
     */
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
