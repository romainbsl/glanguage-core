package be.groups.glanguage.glanguage.api.entities.formula.description;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.utils.MultilingualString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "FORMULA_DESC")
public class FormulaDescription {

    /*
     * Fields
     */
    private Integer id;

    private FormulaType type;

    private String name;

    private MultilingualString description;

    private FormulaPriority priority;

    private FormulaPosition position;

    private Set<FormulaUsage> usages;


    /*
     * Constructors
     */
    public FormulaDescription() {
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

    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    @Convert(converter = FormulaTypeConverter.class)
    public FormulaType getType() {
        return type;
    }

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    @ManyToOne
    @JoinColumn(name = "DESC_ID", referencedColumnName = "ID")
    public MultilingualString getDescription() {
        return description;
    }

    @Column(name = "PRIORITY_ID")
    @Convert(converter = FormulaPriorityConverter.class)
    public FormulaPriority getPriority() {
        return priority;
    }

    @Column(name = "POSITION")
    @Convert(converter = FormulaPositionConverter.class)
    public FormulaPosition getPosition() {
        return position;
    }

    @OneToMany(mappedBy = "formulaDescription", fetch = FetchType.EAGER)
    public Set<FormulaUsage> getUsages() {
        return usages;
    }

    /*
     * Setters
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public void setType(FormulaType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(MultilingualString description) {
        this.description = description;
    }

    public void setPriority(FormulaPriority priority) {
        this.priority = priority;
    }

    public void setPosition(FormulaPosition position) {
        this.position = position;
    }

    public void setUsages(Set<FormulaUsage> usages) {
        this.usages = usages;
    }

    /*
     * Methods
     */
    @Transient
    public boolean isValid(List<AbstractFormula> parameters, Evaluator evaluator) {
        return usages.stream().anyMatch(u -> u.isValid(parameters, evaluator));
    }

    @Transient
    public FormulaReturnType getReturnType(List<AbstractFormula> parameters, Evaluator evaluator) {
        FormulaUsage usage = getUsage(parameters, evaluator);
        if (usage != null) {
            if (usage.getTypes().size() == 1) {
                return new ArrayList<>(usage.getTypes()).get(0);
            } else {
                return FormulaReturnType.UNDEFINED;
            }
        } else {
            return FormulaReturnType.UNDEFINED;
        }
    }

    private FormulaUsage getUsage(List<AbstractFormula> parameters, Evaluator evaluator) {
        // Select the valid usages
        List<FormulaUsage> validUsages = usages.stream().filter(u -> u.isValid(parameters, evaluator)).collect(
                Collectors.toList());
        if (validUsages.size() == 1) {
            return validUsages.get(0);
        } else if (validUsages.size() > 1) {
            // More than 1 valid usages
            // Select the ones with smallest minimum parameter number
            List<FormulaUsage> validMinimalUsages = null;
            int minimumParameterNumber = Integer.MAX_VALUE;
            for (FormulaUsage currentValidUsage : validUsages) {
                if (currentValidUsage.getParameterConbination().getParamtersMinimumNumber() < minimumParameterNumber) {
                    validMinimalUsages = new ArrayList<>();
                    minimumParameterNumber = currentValidUsage.getParameterConbination().getParamtersMinimumNumber();
                }
                if (currentValidUsage.getParameterConbination().getParamtersMinimumNumber() == minimumParameterNumber) {
                    validMinimalUsages.add(currentValidUsage);
                }
            }
            if (validMinimalUsages.size() == 1) {
                return validMinimalUsages.get(0);
            } else if (validMinimalUsages.size() > 1) {
                // More than one smallest minimum parameter number usages
                // Select the ones with smallest maximum parameter number
                List<FormulaUsage> validMaximalUsages = null;
                int maximumParameterNumber = Integer.MAX_VALUE;
                for (FormulaUsage currentValidUsage : validUsages) {
                    if (currentValidUsage.getParameterConbination()
                            .getParamtersMaximumNumber() < maximumParameterNumber) {
                        validMaximalUsages = new ArrayList<>();
                        maximumParameterNumber = currentValidUsage.getParameterConbination()
                                .getParamtersMaximumNumber();
                    }
                    if (currentValidUsage.getParameterConbination()
                            .getParamtersMaximumNumber() == maximumParameterNumber) {
                        validMaximalUsages.add(currentValidUsage);
                    }
                }
                if (validMaximalUsages.size() == 1) {
                    return validMaximalUsages.get(0);
                } else if (validMaximalUsages.size() > 1) {
                    // More than one smallest maximum parameter number usages
                    // Select the one with the most restrictive parameters types
                    Integer minimumParametersReturnTypesNumber = Integer.MAX_VALUE;
                    FormulaUsage usage = null;
                    for (FormulaUsage currentUsage : validMaximalUsages) {
                        Integer currentUsageParametersReturnTypesNumber = currentUsage.getParameterConbination()
                                .getParameters().stream().map(p -> p.getTypes().size()).reduce((i1, i2) -> i1 + i2)
                                .orElse(0);
                        if (currentUsageParametersReturnTypesNumber < minimumParametersReturnTypesNumber) {
                            minimumParametersReturnTypesNumber = currentUsageParametersReturnTypesNumber;
                            usage = currentUsage;
                        }
                    }
                    return usage;
                }
            }
        }
        return null;
    }

    /*
     * Utils
     */
    @Override public String toString () {
        return "FormulaDescription{" + "type=" + type + ", name='" + name + '\'' + ", description=" + description + "," + " priority=" + priority + ", position=" + position + ", usages=" + usages + '}';
    }

    @Override public boolean equals (Object o){
        if (this == o) return true;
        if (!(o instanceof FormulaDescription)) return false;

        FormulaDescription that = (FormulaDescription) o;

        return id.equals(that.id);
    }

    @Override public int hashCode () {
        return id.hashCode();
    }

}
