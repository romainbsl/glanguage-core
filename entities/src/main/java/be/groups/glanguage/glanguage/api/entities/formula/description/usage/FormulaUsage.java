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
 * This class represents a usage of a formula<br>
 * A usage is a combination of a {@link FormulaParameterCombination} and a set of {@link FormulaReturnType}'s for a
 * {@link FormulaDescription}<br>
 * It also defines a list of {@link FormulaUsageParameterCombinationItem overridenParameters} that override the name
 * and description of {@link FormulaParameterCombinationItem}'s of
 * {@link FormulaParameterCombination parameterCombination}
 *
 * @author michotte
 */
@Entity
@Table(name = "FORMULA_USAGE")
public class FormulaUsage {

    /*
     * Fields
     */

    /**
     * Technical unique ID
     */
    private Integer id;

    /**
     * Formula description for which this is a usage
     */
    private FormulaDescription formulaDescription;

    /**
     * Parameter combination
     */
    private FormulaParameterCombination parameterCombination;

    /**
     * Name
     */
    private String name;

    /**
     * Description
     */
    private MultilingualString description;

    /**
     * Set of {@link FormulaUsageReturnType}
     */
    private Set<FormulaUsageReturnType> types;

    /**
     * List of {@link FormulaUsageParameterCombinationItem} overridden parameters
     */
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

    /**
     * Get the technical id
     *
     * @return the id
     */
    @Id
    public Integer getId() {
        return id;
    }

    /**
     * Get the formula description for which this is a usage
     *
     * @return the formula description for which this is a usage
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FORMULA_DESC_ID", referencedColumnName = "ID")
    public FormulaDescription getFormulaDescription() {
        return formulaDescription;
    }

    /**
     * Get the parameter combination
     *
     * @return the parameter combination
     */
    @ManyToOne
    @JoinColumn(name = "FORMULA_PARAM_COMB_ID", referencedColumnName = "ID")
    public FormulaParameterCombination getParameterCombination() {
        return parameterCombination;
    }

    /**
     * Get the name
     *
     * @return the name
     */
    @Column
    public String getName() {
        return name;
    }

    /**
     * Get the description
     *
     * @return the description
     */
    @ManyToOne
    @JoinColumn(name = "DESC_ID", referencedColumnName = "ID")
    public MultilingualString getDescription() {
        return description;
    }

    /**
     * Get the set of {@link FormulaUsageReturnType}
     *
     * @return the the set of {@link FormulaUsageReturnType}
     */
    @OneToMany(mappedBy = "usage", fetch = FetchType.EAGER)
    public Set<FormulaUsageReturnType> getTypes() {
        return types;
    }

    /**
     * Get the list of {@link FormulaReturnType} corresponding to {@link FormulaUsage#getTypes()}
     *
     * @return the the set of {@link FormulaReturnType} corresponding to {@link FormulaUsage#getTypes()}
     */
    @Transient
    public List<FormulaReturnType> getReturnTypes() {
        return getTypes().stream().map(FormulaUsageReturnType::getReturnType).collect(Collectors.toList());
    }

    /**
     * Get the list of overridden parameters
     *
     * @return the list of overridden parameters
     */
    @OneToMany(mappedBy = "usage", fetch = FetchType.EAGER)
    public List<FormulaUsageParameterCombinationItem> getOverriddenParameters() {
        return overriddenParameters;
    }

    /*
     * Setters
     */

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param formulaDescription the formula description to set
     */
    public void setFormulaDescription(FormulaDescription formulaDescription) {
        this.formulaDescription = formulaDescription;
    }

    /**
     * @param parameterCombination the parameter combination to set
     */
    public void setParameterCombination(FormulaParameterCombination parameterCombination) {
        this.parameterCombination = parameterCombination;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(MultilingualString description) {
        this.description = description;
    }

    /**
     * @param types the set of {@link FormulaUsageReturnType} to set
     */
    public void setTypes(Set<FormulaUsageReturnType> types) {
        this.types = types;
    }

    /**
     * @param overriddenParameters the list of {@link FormulaUsageParameterCombinationItem} to set
     */
    public void setOverriddenParameters(List<FormulaUsageParameterCombinationItem> overriddenParameters) {
        this.overriddenParameters = overriddenParameters;
    }

    /*
     * Methods
     */

    /**
     * Validate a list of {@link AbstractFormula parameters} of a {@link AbstractFormula formula} with an
     * {@link Evaluator evaluator} (can be null)<br>
     * by delegating to {@link FormulaParameterCombination#validate(AbstractFormula, FormulaUsage, List, Evaluator)}
     * with {@code this} as second parameter
     *
     * @param formula    the {@link AbstractFormula formula} to be validated
     * @param parameters the list of {@link AbstractFormula parameters} to be validated
     * @param evaluator  the {@link Evaluator evaluator} to be used during the validation process, can be null
     * @throws GLanguageException if an error occurs during the validation process or if the list of
     *                            {@link AbstractFormula parameters} are not valid
     */
    public void validate(AbstractFormula formula,
                         List<AbstractFormula> parameters,
                         Evaluator evaluator) throws GLanguageException {
        getParameterCombination().validate(formula, this, parameters, evaluator);
    }

    /**
     * Is this valid Validate a list of {@link AbstractFormula parameters} of a
     * {@link FormulaParameterCombination formula} with an evaluator (can be null) ?<br>
     * by delegating to {@link FormulaParameterCombination#isValid(List, Evaluator)}
     *
     * @param parameters the list of {@link AbstractFormula parameters} to be validated
     * @param evaluator  the evaluator to be used during the validation process, can be null
     * @return true if it is valid, false otherwise
     */
    @Transient
    public boolean isValid(List<AbstractFormula> parameters, Evaluator evaluator) {
        return getParameterCombination().isValid(parameters, evaluator);
    }

    /**
     * Get the name of a {@code combinationParameter} according to this usage<br>
     * If the {@code combinationParameter} is overridden, return the overridden name
     * {@link FormulaUsageParameterCombinationItem#getName()}<br>
     * Else, return the name of the {@code combinationParameter} {@link FormulaParameterCombinationItem#getName()}
     *
     * @param combinationParameter the {@link FormulaParameterCombinationItem} for which the (overridden) name has to
     *                             be returned
     * @return the name of a {@code combinationParameter} according to this usage
     */
    @Transient
    public MultilingualString getParameterName(FormulaParameterCombinationItem combinationParameter) {
        if (getOverriddenParameters() != null && !getOverriddenParameters().isEmpty()) {
            Optional<FormulaUsageParameterCombinationItem> overriddenParameter = getOverriddenParameter(
                    combinationParameter);
            if (overriddenParameter.isPresent()) {
                return overriddenParameter.get().getName();
            }
        }
        return combinationParameter.getName();
    }

    /**
     * Get the description of a {@code combinationParameter} according to this usage<br>
     * If the {@code combinationParameter} is overridden, return the overridden description
     * {@link FormulaUsageParameterCombinationItem#getDescription()}<br>
     * Else, return the description of the {@code combinationParameter}
     * {@link FormulaParameterCombinationItem#getDescription()}
     *
     * @param combinationParameter the {@link FormulaParameterCombinationItem} for which the (overridden) description
     *                             has to be returned
     * @return the description of a {@code combinationParameter} according to this usage
     */
    @Transient
    public MultilingualString getParameterDescription(FormulaParameterCombinationItem combinationParameter) {
        if (getOverriddenParameters() != null && !getOverriddenParameters().isEmpty()) {
            Optional<FormulaUsageParameterCombinationItem> overriddenParameter = getOverriddenParameter(
                    combinationParameter);
            if (overriddenParameter.isPresent()) {
                return overriddenParameter.get().getDescription();
            }
        }
        return combinationParameter.getDescription();
    }

    /**
     * Get the overridden parameter of a {@code combinationParameter}
     *
     * @param combinationParameter the {@link FormulaParameterCombinationItem} for which the overridden parameter
     *                             has to be returned
     * @return an {@link Optional} referencing the overridden parameter of a {@code combinationParameter} if it exists
     */
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
