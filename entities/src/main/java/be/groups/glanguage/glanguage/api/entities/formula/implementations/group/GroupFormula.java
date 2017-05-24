package be.groups.glanguage.glanguage.api.entities.formula.implementations.group;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.parameter.FormulaNullParameterInnerError;
import be.groups.glanguage.glanguage.api.error.formula.implementations.group.GroupFormulaReferencedRuleUnavailableInnerError;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public abstract class GroupFormula extends AbstractNonTerminalFormula {

    private RuleVersion groupRule;

    public GroupFormula() {
        super();
    }

    public GroupFormula(FormulaDescription description, String groupId) throws GLanguageException {
        super(description, null);
        if (groupId == null || groupId.isEmpty()) {
            throw new GLanguageException(new FormulaNullParameterInnerError(this, null, "constructor", 1));
        }
        setConstantValue(groupId);
    }

    @Override
    public boolean isValid(Evaluator evaluator) {
        if (doGetGroupRule(evaluator) == null) {
            return false;
        } else if (!(getReturnType(evaluator).equals(FormulaReturnType.INTEGER)
                || getReturnType(evaluator).equals(FormulaReturnType.NUMERIC))) {
            return false;
        }
        return true;
    }

    @Override
    public void validate(List<AbstractFormula> parameters, Evaluator evaluator) throws GLanguageException {
        if (getGroupRule() == null) {
            throw new IllegalAccessError("Cannot invoke getRulesInGroup() method on " + this.getClass().getName()
                                                 + " object while referenced rule (version id : " + getConstantValue()
                                                 + ") is not set - while branching is not done");
        } else if (!(getReturnType(evaluator).equals(FormulaReturnType.INTEGER)
                || getReturnType(evaluator).equals(FormulaReturnType.NUMERIC))) {
            throw new IllegalAccessError("Cannot invoke getIntegerValue() method on " + this.getClass().getName()
                                                 + " object if referenced rule group  (version id : " + getConstantValue()
                                                 + ") has rules that are not of type INTEGER or NUMERIC");
        }
    }

    @Transient
    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        if (doGetGroupRule(evaluator) != null) {
            Set<FormulaReturnType> returnTypes = doGetGroupRule(evaluator).getGroupItems().stream().map(i -> i
                    .getReferencedRule(evaluator).getReturnType(evaluator)).distinct().collect(Collectors.toSet());
            if (returnTypes.size() == 1) {
                return returnTypes.iterator().next();
            } else {
                return FormulaReturnType.NUMERIC;
            }
        } else {
            return FormulaReturnType.UNDEFINED;
        }
    }

    /**
     * @return the groupRule
     */
    @JsonIgnore
    @Transient
    public RuleVersion getGroupRule() {
        return groupRule;
    }

    @JsonIgnore
    @Transient
    RuleVersion getGroupRule(Evaluator evaluator) throws GLanguageException {
        doGetGroupRule(evaluator);
        if (getGroupRule() == null) {
            throw new GLanguageException(new GroupFormulaReferencedRuleUnavailableInnerError(this,
                                                                                             evaluator,
                                                                                             "doGetBooleanValue"));
        }
        return getGroupRule();
    }

    /**
     * @param evaluator evaluator
     * @return the referencedRule
     */
    @JsonIgnore
    @Transient
    RuleVersion doGetGroupRule(Evaluator evaluator) {
        if (getGroupRule() == null && evaluator != null) {
            groupRule = evaluator.getRuleVersion(getConstantValue());
        }
        return getGroupRule();
    }

    /**
     * Get the rules contained in the group identified by first parameter
     *
     * @return The list of rules contained in the group identified by first parameter
     * @throws IllegalAccessError if {@code groupRule} is null - branching has not been done
     */
    @Transient
    public List<RuleVersion> getRulesInGroup(Evaluator evaluator) throws GLanguageException {
        if (doGetGroupRule(evaluator) == null) {
            throw new IllegalAccessError("Cannot invoke getRulesInGroup() method on " + this.getClass()
                    .getName() + " object while referenced rule (version id : " + getConstantValue() + ") is not set " +
                                                 "" + "- while branching is not done");
        }
        return doGetGroupRule(evaluator).getGroupItems().stream().map(i -> i.getReferencedRule(evaluator)).collect(
                Collectors.toList());
    }

    @JsonIgnore
    @Transient
    @Override
    protected Integer doGetIntegerValue(Evaluator evaluator) throws GLanguageException {
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

    @Override
    public String asText() {
        return operationAsText() + "(" + getConstantValue() + ")";
    }

    public abstract String operationAsText();

}
