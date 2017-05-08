package be.groups.glanguage.glanguage.api.entities.formula.description;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.utils.MultilingualString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * This class represents a usage of a formula.<br>
 * A usage is a conbination of a {@link FormulaParameterConbination} and a set of {@link FormulaReturnType}'s for a
 * {@link FormulaDescription}.<br>
 * It also has a name and a {@link MultilingualString} description.<br>
 *
 * Created by michotte on 4/05/2017.
 */
@Entity
@Table(name = "FORMULA_USAGE")
public class FormulaUsage {

    /*
     * Fields
     */
    private Integer id;
    private FormulaDescription formulaDescription;
    private FormulaParameterConbination parameterConbination;
    private String name;
    private MultilingualString description;
    private Set<FormulaReturnType> types;

    /*
     * Constructors
     */
    public FormulaUsage() {
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
    @JoinColumn(name = "FIORMULA_DESC_ID", referencedColumnName = "ID")
    public FormulaDescription getFormulaDescription() {
        return formulaDescription;
    }

    @ManyToOne
    @JoinColumn(name = "FIORMULA_PARAM_CONB_ID", referencedColumnName = "ID")
    public FormulaParameterConbination getParameterConbination() {
        return parameterConbination;
    }

    @Column
    public String getName() {
        return name;
    }

    @ManyToOne
    @JoinColumn(name = "DESC_ID", referencedColumnName = "ID")
    public MultilingualString getDescription() {
        return description;
    }

    @Column(table = "FORMULA_PARAM_CONB_ITEM_TYPE", name = "FORMULA_RETURN_TYPE_ID")
    @Convert(converter = FormulaReturnTypeConverter.class)
    public Set<FormulaReturnType> getTypes() {
        return types;
    }

    /*
     * Setters
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public void setFormulaDescription(FormulaDescription formulaDescription) {
        this.formulaDescription = formulaDescription;
    }

    public void setParameterConbination(FormulaParameterConbination parameterConbination) {
        this.parameterConbination = parameterConbination;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(MultilingualString description) {
        this.description = description;
    }

    public void setTypes(Set<FormulaReturnType> types) {
        this.types = types;
    }

    /*
     * Methods
     */
    public boolean isValid(List<AbstractFormula> parameters, Evaluator evaluator) {
        return getParameterConbination().isValid(parameters, evaluator);
    }

    /*
     * Utils
     */
    @Override
    public String toString() {
        return "FormulaUsage{" + ", name='" + name + '\'' + ", " +
                "description=" + description + "parameterConbination=" + parameterConbination + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormulaUsage)) return false;

        FormulaUsage that = (FormulaUsage) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
