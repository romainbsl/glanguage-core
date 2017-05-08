package be.groups.glanguage.glanguage.api.entities.formula.implementations.group;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public abstract class GroupFormula extends AbstractNonTerminalFormula {

    private RuleVersion groupRule;

    public GroupFormula() {
        super();
    }

    public GroupFormula(FormulaDescription description, String groupId) {
        super(description, null);
        if (groupId == null || groupId.isEmpty()) {
            throw new IllegalArgumentException("groupId must be a non-null non-empty string");
        }
        setConstantValue(groupId);
    }

    /**
     * @return the groupRule
     */
    @Transient
    public RuleVersion getGroupRule() {
        return groupRule;
    }

    /**
     * Get the rules contained in the group identified by first parameter
     *
     * @return The list of rules contained in the group identified by first parameter
     * @throws IllegalAccessError if {@code groupRule} is null - branching has not been done
     */
    @Transient
    public List<RuleVersion> getRulesInGroup(Evaluator evaluator) {
        if (groupRule == null) {
            throw new IllegalAccessError("Cannot invoke getRulesInGroup() method on " + this.getClass()
                    .getName() + " object while referenced rule (version id : " + getConstantValue() + ") is not set " + "- while branching is not done");
        }
        return groupRule.getGroupItems().stream().map(i -> i.getReferencedRule(evaluator)).collect(Collectors.toList());
    }

    @JsonIgnore
    @Transient
    @Override
    public Integer getIntegerValue(Evaluator evaluator) {
        return getNumericValue(null).intValue();
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(String groupId) {
        setConstantValue(groupId);
    }

    /**
     * @param groupRule the groupRule to set
     */
    public void setGroupRule(RuleVersion groupRule) {
        this.groupRule = groupRule;
    }

    @Transient
    @Override
    public boolean isValid(List<AbstractFormula> parameters, Evaluator evaluator) {
        if (groupRule != null) {
            Set<FormulaReturnType> returnTypes = groupRule.getGroupItems().stream()
                    .map(i -> i.getReferencedRule(evaluator).getReturnType(evaluator)).distinct().collect(Collectors.toSet());

            if (returnTypes.stream()
                    .allMatch(e -> Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.NUMERIC).contains(e))) {
                return true;
            } else {
                return false;
            }
        }

        return true;
    }

    @Transient
    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        if (groupRule != null) {
            Set<FormulaReturnType> returnTypes = groupRule.getGroupItems().stream()
                    .map(i -> i.getReferencedRule(evaluator).getReturnType(evaluator)).distinct()
                    .collect(Collectors.toSet());
            if (returnTypes.size() == 1) {
                return returnTypes.iterator().next();
            } else {
                return FormulaReturnType.NUMERIC;
            }
        } else {
            return FormulaReturnType.UNDEFINED;
        }
    }

    @Override
    public String asText() {
        return operationAsText() + "(" + getConstantValue() + ")";
    }

    public abstract String operationAsText();

}
