package be.groups.glanguage.glanguage.api.entities.formula.description.conbination;

import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnTypeConverter;

import javax.persistence.*;

/**
 * Created by michotte on 30/01/2017.
 */
@Entity
@Table(name = "FORMULA_PARAM_CONB_ITEM_TYPE")
public class FormulaParameterConbinationItemType {

    /*
     * Fields
     */
    private Integer id;
    private FormulaParameterConbinationItem parameter;
    private FormulaReturnType returnType;

    /*
     * Constructors
     */
    public FormulaParameterConbinationItemType() {
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

    @ManyToOne
    @JoinColumn(name = "FORMULA_PARAM_CONB_ITEM_ID", referencedColumnName = "ID")
    public FormulaParameterConbinationItem getParameter() {
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

    public void setParameter(FormulaParameterConbinationItem parameter) {
        this.parameter = parameter;
    }

    public void setReturnType(FormulaReturnType returnType) {
        this.returnType = returnType;
    }
}
