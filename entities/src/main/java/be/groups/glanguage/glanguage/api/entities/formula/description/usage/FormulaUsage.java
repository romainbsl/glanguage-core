package be.groups.glanguage.glanguage.api.entities.formula.description.usage;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.description.combination.FormulaParameterCombination;
import be.groups.glanguage.glanguage.api.entities.formula.description.combination.FormulaParameterCombinationItem;
import be.groups.glanguage.glanguage.api.entities.utils.MultilingualString;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents a usage of a formula.<br>
 * A usage is a combination of a {@link FormulaParameterCombination} and a set of {@link FormulaReturnType}'s for a
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
    private FormulaParameterCombination parameterCombination;
    private String name;
    private MultilingualString description;
    private Set<FormulaUsageReturnType> types;
    private List<FormulaUsageParameterCombinationItem> overriddenParameters;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FORMULA_DESC_ID", referencedColumnName = "ID")
    public FormulaDescription getFormulaDescription() {
        return formulaDescription;
    }

    @ManyToOne
    @JoinColumn(name = "FORMULA_PARAM_COMB_ID", referencedColumnName = "ID")
    public FormulaParameterCombination getParameterCombination() {
        return parameterCombination;
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
    public List<FormulaUsageParameterCombinationItem> getOverriddenParameters() {
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

    public void setParameterCombination(FormulaParameterCombination parameterCombination) {
        this.parameterCombination = parameterCombination;
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

    public void setOverriddenParameters(List<FormulaUsageParameterCombinationItem> overriddenParameters) {
        this.overriddenParameters = overriddenParameters;
    }

    /*
     * Methods
     */
    public void validate(AbstractFormula formula,
                         List<AbstractFormula> parameters,
                         Evaluator evaluator) throws GLanguageException {
        getParameterCombination().validate(formula, this, parameters, evaluator);
    }

    @Transient
    public boolean isValid(List<AbstractFormula> parameters, Evaluator evaluator) {
        return getParameterCombination().isValid(parameters, evaluator);
    }

    @Transient
    public MultilingualString getParameterName(FormulaParameterCombinationItem combinationParameter) {
        if (getOverriddenParameters() != null && !getOverriddenParameters().isEmpty()) {
            Optional<FormulaUsageParameterCombinationItem> overriddenParameter = getOverriddenParameter
                    (combinationParameter);
            if (overriddenParameter.isPresent()) {
                return overriddenParameter.get().getName();
            }
        } return combinationParameter.getName();
    }

    @Transient
    public MultilingualString getParameterDescription(FormulaParameterCombinationItem combinationParameter) {
        if (getOverriddenParameters() != null && !getOverriddenParameters().isEmpty()) {
            Optional<FormulaUsageParameterCombinationItem> overriddenParameter = getOverriddenParameter
                    (combinationParameter);
            if (overriddenParameter.isPresent()) {
                return overriddenParameter.get().getDescription();
            }
        }
        return combinationParameter.getDescription();
    }

    @Transient
    private Optional<FormulaUsageParameterCombinationItem> getOverriddenParameter(FormulaParameterCombinationItem
                                                                                combinationParameter) {
        return getOverriddenParameters().stream().filter(p -> p.getCombinationParameter().equals(combinationParameter))
                .findFirst();

    }

    /*
     * Utils
     */
    @Override
    public String toString() {
        return "FormulaUsage{" + ", name='" + name + '\'' + ", " + "description=" + description +
                "parameterCombination=" + parameterCombination + '}';
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
