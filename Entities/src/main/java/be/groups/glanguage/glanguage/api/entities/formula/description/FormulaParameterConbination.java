package be.groups.glanguage.glanguage.api.entities.formula.description;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;

import javax.persistence.*;
import java.util.*;

/**
 * This class represent a conbination of parameters
 * Created by michotte on 4/05/2017.
 */
@Entity
@Table(name = "FORMULA_PARAM_CONB")
public class FormulaParameterConbination {

    /*
     * Fields
     */
    private Integer id;
    private String name;
    private String description;
    private SortedSet<FormulaParameterConbinationItem> parameters;

    /*
     * Constructors
     */
    public FormulaParameterConbination() {
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

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "DESCRIPTION", nullable = false)
    public String getDescription() {
        return description;
    }

    @OneToMany(mappedBy = "conbination")
    @OrderBy("sequence_number")
    public SortedSet<FormulaParameterConbinationItem> getParameters() {
        return parameters;
    }

    /*
     * Setters
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setParameters(SortedSet<FormulaParameterConbinationItem> parameters) {
        this.parameters = parameters;
    }

    /*
     * Methods
     */
    public boolean isValid(List<AbstractFormula> parameters, Evaluator evaluator) throws GLanguageException {
        try {
            if (parameters == null || parameters.size() == 0) {
                if (getParamtersMinimumNumber() != 0) {
                    return false;
                }
            } else {
                if (parameters.size() >= getParamtersMinimumNumber() && parameters.size() <= getParamtersMaximumNumber()) {
                    int i = 0;
                    int j = 0;
                    List<FormulaParameterConbinationItem> conbinationParameters = new ArrayList<>(getParameters());
                    ListIterator<FormulaParameterConbinationItem> itConbinationParameters = conbinationParameters
                            .listIterator();
                    while (itConbinationParameters.hasNext()) {
                        FormulaParameterConbinationItem conbinationParameter = itConbinationParameters.next();
                        if (!conbinationParameter.isValid(parameters.get(i), evaluator) && !conbinationParameter.getOptional
                                    ()) {
                                return false;
                            }
                        i++;
                        if (conbinationParameter.getRepeatable()) {
                            if (conbinationParameters.size() >= j + 1) {
                                int numberOfNonOptionalParameterAfterThis =
                                        Math.toIntExact(conbinationParameters.subList(j + 1, conbinationParameters.size())
                                                                .stream().filter(p -> !p.getOptional()).count());
                                ListIterator<AbstractFormula> itParameters = parameters.listIterator(i);
                                while (itParameters.hasNext() &&
                                        conbinationParameter.isValid(itParameters.next(), evaluator) && i <
                                            parameters.size() - numberOfNonOptionalParameterAfterThis) {
                                    i++;
                                }
                            }
                            j++;
                        }
                    }
                }
            }
        } catch (GLanguageException e) {
            // TODO
            throw e;
        }
        return true;
    }

    public Integer getParamtersMinimumNumber() {
        return Math.toIntExact(parameters.stream().filter(p -> !p.getOptional()).count());
    }

    public Integer getParamtersMaximumNumber() {
        if (parameters.stream().anyMatch(p -> p.getRepeatable())) {
            return Integer.MAX_VALUE;
        } else {
            return parameters.size();
        }
    }

    /*
     * Utils
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FormulaParameterConbination{" + "name='" + name + '\'' + ", " +
                                                     "description='" + description + '\'' + ", parameters=");
        for (FormulaParameterConbinationItem parameter : parameters) {
            sb.append("\n\t" + parameter.toString());
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormulaParameterConbination)) return false;

        FormulaParameterConbination that = (FormulaParameterConbination) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
