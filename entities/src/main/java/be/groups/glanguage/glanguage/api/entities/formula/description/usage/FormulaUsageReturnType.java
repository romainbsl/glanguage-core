package be.groups.glanguage.glanguage.api.entities.formula.description.usage;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnTypeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * This class represents a {@link FormulaUsage} return type<br>
 * Each {@link FormulaUsage} can have multiple return types, this class represent one of them
 *
 * @author michotte
 */
@Entity
@Table(name = "FORMULA_USAGE_RETURN_TYPE")
public class FormulaUsageReturnType {

    /*
     * Fields
     */

    /**
     * Technical unique ID
     */
    private Long id;

    /**
     * Usage for which this is a return type
     */
    private FormulaUsage usage;

    /**
     * Return type
     */
    private FormulaReturnType returnType;

    /*
     * Constructors
     */
    public FormulaUsageReturnType() {
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
     * Get the usage for which this is a return type
     *
     * @return the usage for which this is a return type
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FORMULA_USAGE_ID", referencedColumnName = "ID")
    public FormulaUsage getUsage() {
        return usage;
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
     * @param usage the usage for which this is a return type to set
     */
    public void setUsage(FormulaUsage usage) {
        this.usage = usage;
    }

    /**
     * @param returnType the return types to set
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
        if (!(o instanceof FormulaUsageReturnType)) return false;

        FormulaUsageReturnType that = (FormulaUsageReturnType) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
