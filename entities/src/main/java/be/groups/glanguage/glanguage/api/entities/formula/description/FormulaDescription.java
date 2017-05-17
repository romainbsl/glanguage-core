package be.groups.glanguage.glanguage.api.entities.formula.description;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.conbination.FormulaParameterConbinationItem;
import be.groups.glanguage.glanguage.api.entities.formula.description.usage.FormulaUsage;
import be.groups.glanguage.glanguage.api.entities.utils.Language;
import be.groups.glanguage.glanguage.api.entities.utils.MultilingualString;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.description.FormulaDescriptionInnerErrorFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
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

    @Transient
    public String getDescription(Language language) {
        return description.asText(language);
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
    public void validate(AbstractFormula formula,
                         List<AbstractFormula> parameters,
                         Evaluator evaluator) throws GLanguageException {
        try {
            if (!isValid(parameters, evaluator)) {
                FormulaUsage usage = getBestMatchingUsage(parameters, evaluator);
                if (usage != null) {
                    // If a best matching usage is found, validate it in order to get more precise exceptions about
                    // the invalidity
                    usage.validate(formula, parameters, evaluator);
                } else {
                    throw new GLanguageException(FormulaDescriptionInnerErrorFactory
                                                         .getNoMatchingUsage(formula, parameters, evaluator, this));
                }
            }
        } catch (GLanguageException e) {
            e.getError().setOuterError(FormulaDescriptionInnerErrorFactory
                                               .getUnableToValidate(formula, parameters, evaluator, this));
            throw e;
        }
    }

    @Transient
    public boolean isValid(List<AbstractFormula> parameters, Evaluator evaluator) {
        return usages.stream().anyMatch(u -> u.isValid(parameters, evaluator));
    }

    @Transient
    public FormulaReturnType getReturnType(List<AbstractFormula> parameters, Evaluator evaluator) {
        FormulaUsage usage = getValidUsage(parameters, evaluator);
        if (usage != null) {
            if (usage.getTypes().size() == 1) {
                return new ArrayList<>(usage.getReturnTypes()).get(0);
            } else {
                return FormulaReturnType.UNDEFINED;
            }
        } else {
            return FormulaReturnType.UNDEFINED;
        }
    }

    @Transient
    private FormulaUsage getValidUsage(List<AbstractFormula> parameters, Evaluator evaluator) {
        // Only 1 usage -> return it
        if (getUsages().size() == 1) {
            return getUsages().iterator().next();
        } else if (getUsages().size() > 1) {
            // More than 1 usages
            // Select the valid usages
            List<FormulaUsage> validUsages = usages.stream().filter(u -> u.isValid(parameters, evaluator)).collect(
                    Collectors.toList());
            if (validUsages.size() == 1) {
                // Only 1 valid usage -> return it
                return validUsages.get(0);
            } else if (validUsages.size() > 1) {
                // More than 1 valid usages
                // Select the valid usages with minimal parameter number
                validUsages = getUsagesWithMinimalParameterNumber(validUsages);
                if (validUsages.size() == 1) {
                    // Only 1 valid usage -> return it
                    return validUsages.get(0);
                } else if (validUsages.size() > 1) {
                    // More than one smallest maximum parameter number usages
                    // Select the one with the most restrictive parameters types
                    Integer minimumParametersReturnTypesNumber = Integer.MAX_VALUE;
                    FormulaUsage usage = null;
                    for (FormulaUsage currentUsage : validUsages) {
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

    @Transient
    private FormulaUsage getBestMatchingUsage(List<AbstractFormula> parameters, Evaluator evaluator) {
        // Only 1 usage -> return it
        if (getUsages().size() == 1) {
            return getUsages().iterator().next();
        } else {
            // Select the usages with matching number of parameters
            List<FormulaUsage> matchingUsages = usages.stream().filter(u -> u.getParameterConbination()
                    .getParametersMinimumNumber() <= parameters.size() && u.getParameterConbination()
                    .getParametersMaximumNumber() >= parameters.size()).collect(Collectors.toList());
            if (matchingUsages.size() == 1) {
                // Only 1 matching usage -> return it
                return matchingUsages.get(0);
            } else if (matchingUsages.size() > 1) {
                // More than 1 matching usages
                // Select the matching usages with minimal parameter number
                matchingUsages = getUsagesWithMinimalParameterNumber(matchingUsages);
                if (matchingUsages.size() == 1) {
                    // Only 1 matching usage -> return it
                    return matchingUsages.get(0);
                } else if (matchingUsages.size() > 1) {
                    // More than 1 matching usages
                    // Select the matching usages with minimum number of parameter equal to parameters list size
                    ArrayList<FormulaUsage> matchingMinimumParameterNumberUsages = new ArrayList<>();
                    for (FormulaUsage usage : matchingUsages) {
                        if (usage.getParameterConbination().getParametersMinimumNumber() == parameters.size()) {
                            matchingMinimumParameterNumberUsages.add(usage);
                        }
                    }
                    if (matchingMinimumParameterNumberUsages.size() == 1) {
                        // Only 1 matching usage -> return it
                        return matchingMinimumParameterNumberUsages.get(0);
                    } else if (matchingMinimumParameterNumberUsages.size() > 1) {
                        // More than 1 matching usages
                        // Select the matching usages with maximum number of parameter equal to parameters list size
                        ArrayList<FormulaUsage> matchingMaximumParameterNumberUsages = new ArrayList<>();
                        for (FormulaUsage matchingUsage : matchingMinimumParameterNumberUsages) {
                            if (matchingUsage.getParameterConbination().getParametersMaximumNumber() == parameters
                                    .size()) {
                                matchingMaximumParameterNumberUsages.add(matchingUsage);
                            }
                        }
                        if (matchingMaximumParameterNumberUsages.size() == 1) {
                            // Only 1 matching usage -> return it
                            return matchingMaximumParameterNumberUsages.get(0);
                        } else if (matchingMaximumParameterNumberUsages.size() > 1) {
                            // More than 1 matching usages
                            // Select the matching usage with best matching parameters types
                            int maximumNumberOfMatchinParameterTypes = 0;
                            FormulaUsage matchingUsage = null;
                            for (FormulaUsage currentMatchingUsage : matchingMaximumParameterNumberUsages) {
                                List<FormulaParameterConbinationItem> usageParameters = currentMatchingUsage
                                        .getParameterConbination().getParameters().stream()
                                        .sorted(new Comparator<FormulaParameterConbinationItem>() {
                                            @Override
                                            public int compare(FormulaParameterConbinationItem o1,
                                                               FormulaParameterConbinationItem o2) {
                                                return o1.getSequenceNumber().compareTo(o2.getSequenceNumber());
                                            }
                                        }).collect(Collectors.toList());
                                int numberOfMatchingParameterTypes = 0;
                                for (int i = 0; i < parameters.size(); i++) {
                                    if (usageParameters.get(i).isValidType(parameters.get(i), evaluator)) {
                                        numberOfMatchingParameterTypes++;
                                    }
                                }
                                if (numberOfMatchingParameterTypes > maximumNumberOfMatchinParameterTypes) {
                                    maximumNumberOfMatchinParameterTypes = numberOfMatchingParameterTypes;
                                    matchingUsage = currentMatchingUsage;
                                }
                            }
                            return matchingUsage;
                        }
                    }
                }
            }
        }
        return null;
    }

    private List<FormulaUsage> getUsagesWithMinimalParameterNumber(List<FormulaUsage> validUsages) {
        // More than 1 valid usages
        // Select the ones with smallest minimum parameter number
        List<FormulaUsage> validMinimalUsages = null;
        int minimumParameterNumber = Integer.MAX_VALUE;
        for (FormulaUsage currentValidUsage : validUsages) {
            if (currentValidUsage.getParameterConbination().getParametersMinimumNumber() < minimumParameterNumber) {
                validMinimalUsages = new ArrayList<>();
                minimumParameterNumber = currentValidUsage.getParameterConbination().getParametersMinimumNumber();

            }
            if (currentValidUsage.getParameterConbination().getParametersMinimumNumber() == minimumParameterNumber) {
                validMinimalUsages.add(currentValidUsage);
            }
        }
        if (validMinimalUsages.size() == 1) {
            return validMinimalUsages;
        } else if (validMinimalUsages.size() > 1) {
            // More than one smallest minimum parameter number usages
            // Select the ones with smallest maximum parameter number
            List<FormulaUsage> validMaximalUsages = null;
            int maximumParameterNumber = Integer.MAX_VALUE;
            for (FormulaUsage currentValidUsage : validUsages) {
                if (currentValidUsage.getParameterConbination().getParametersMaximumNumber() < maximumParameterNumber) {
                    validMaximalUsages = new ArrayList<>();
                    maximumParameterNumber = currentValidUsage.getParameterConbination().getParametersMaximumNumber();
                }
                if (currentValidUsage.getParameterConbination()
                        .getParametersMaximumNumber() == maximumParameterNumber) {
                    validMaximalUsages.add(currentValidUsage);
                }
            }
            return validMaximalUsages;
        }
        return null;
    }

    /*
     * Utils
     */
    @Override
    public String toString() {
        return "FormulaDescription{" + "type=" + type + ", name='" + name + '\'' + ", description=" + description +
                "," + " priority=" + priority + ", position=" + position + ", usages=" + usages + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormulaDescription)) return false;

        FormulaDescription that = (FormulaDescription) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
