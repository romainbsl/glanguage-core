package be.groups.glanguage.glanguage.api.entities.formula.description.usage;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnTypeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by michotte on 17/05/2017.
 */
@Entity
@Table(name = "FORMULA_USAGE_RETURN_TYPE")
public class FormulaUsageReturnType {

    /*
     * Fields
     */
    private Integer id;
    private FormulaUsage usage;
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
    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FORMULA_USAGE_ID", referencedColumnName = "ID")
    public FormulaUsage getUsage() {
        return usage;
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

    public void setUsage(FormulaUsage usage) {
        this.usage = usage;
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
        if (!(o instanceof FormulaUsageReturnType)) return false;

        FormulaUsageReturnType that = (FormulaUsageReturnType) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
