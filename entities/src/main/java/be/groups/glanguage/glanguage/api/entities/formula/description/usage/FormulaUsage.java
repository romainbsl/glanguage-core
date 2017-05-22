package be.groups.glanguage.glanguage.api.entities.formula.description.usage;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbination;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbinationItem;
import be.groups.glanguage.glanguage.api.entities.utils.MultilingualString;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents a usage of a formula.<br>
 * A usage is a conbination of a {@link FormulaParameterConbination} and a set of {@link FormulaReturnType}'s for a
 * {@link FormulaDescription}.<br>
 * It also has a name and a {@link MultilingualString} description.<br>
 * <p>
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
    private Set<FormulaUsageReturnType> types;
    private List<FormulaUsageParameterConbinationItem> overriddenParameters;

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
    @JoinColumn(name = "FORMULA_DESC_ID", referencedColumnName = "ID")
    public FormulaDescription getFormulaDescription() {
        return formulaDescription;
    }

    @ManyToOne
    @JoinColumn(name = "FORMULA_PARAM_CONB_ID", referencedColumnName = "ID")
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

    @OneToMany(mappedBy = "usage")
    public Set<FormulaUsageReturnType> getTypes() {
        return types;
    }

    @Transient
    public List<FormulaReturnType> getReturnTypes() {
        return getTypes().stream().map(FormulaUsageReturnType::getReturnType).collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "usage")
    public List<FormulaUsageParameterConbinationItem> getOverriddenParameters() {
        return overriddenParameters;
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

    public void setTypes(Set<FormulaUsageReturnType> types) {
        this.types = types;
    }

    public void setOverriddenParameters(List<FormulaUsageParameterConbinationItem> overriddenParameters) {
        this.overriddenParameters = overriddenParameters;
    }

    /*
     * Methods
     */
    public void validate(AbstractFormula formula,
                         List<AbstractFormula> parameters,
                         Evaluator evaluator) throws GLanguageException {
        getParameterConbination().validate(formula, this, parameters, evaluator);
    }

    @Transient
    public boolean isValid(List<AbstractFormula> parameters, Evaluator evaluator) {
        return getParameterConbination().isValid(parameters, evaluator);
    }

    @Transient
    public MultilingualString getParameterName(FormulaParameterConbinationItem conbinationParameter) {
        if (getOverriddenParameters() != null && !getOverriddenParameters().isEmpty()) {
            Optional<FormulaUsageParameterConbinationItem> overriddenParameter = getOverriddenParameter
                    (conbinationParameter);
            if (overriddenParameter.isPresent()) {
                return overriddenParameter.get().getName();
            }
        } return conbinationParameter.getName();
    }

    @Transient
    public MultilingualString getParameterDescription(FormulaParameterConbinationItem conbinationParameter) {
        if (getOverriddenParameters() != null && !getOverriddenParameters().isEmpty()) {
            Optional<FormulaUsageParameterConbinationItem> overriddenParameter = getOverriddenParameter
                    (conbinationParameter);
            if (overriddenParameter.isPresent()) {
                return overriddenParameter.get().getDescription();
            }
        }
        return conbinationParameter.getDescription();
    }

    @Transient
    private Optional<FormulaUsageParameterConbinationItem> getOverriddenParameter(FormulaParameterConbinationItem
                                                                                conbinationParameter) {
        return getOverriddenParameters().stream().filter(p -> p.getConbinationParameter().equals(conbinationParameter))
                .findFirst();

    }

    /*
     * Utils
     */
    @Override
    public String toString() {
        return "FormulaUsage{" + ", name='" + name + '\'' + ", " + "description=" + description +
                "parameterConbination=" + parameterConbination + '}';
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
