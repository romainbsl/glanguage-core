package be.groups.glanguage.glanguage.api.entities.formula.implementations.group;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.call.FormulaRuleReference;
import be.groups.glanguage.glanguage.api.entities.formula.implementations.terminal.FormulaTerminalString;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.base.parameter.FormulaNullParameterInnerError;
import be.groups.glanguage.glanguage.api.error.formula.implementations.group.GroupFormulaReferencedRuleUnavailableInnerError;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Abstract formula implementing operations on a group of rules
 *
 * @author michotte
 */
@Entity
public abstract class GroupFormula extends AbstractNonTerminalFormula {

    private RuleVersion groupRule;

    public GroupFormula() {
        super();
    }

    public GroupFormula(FormulaDescription description, String groupId, Evaluator evaluator) throws GLanguageException {
        super(description, getParametersAsList(groupId), evaluator);
        if (groupId == null || groupId.isEmpty()) {
            throw new GLanguageException(new FormulaNullParameterInnerError(this, null, "constructor", 1));
        }
        // revert super
        setParameters(null);
        setConstantValue(groupId);
    }

    @JsonIgnore
    @Transient
    private static List<AbstractFormula> getParametersAsList(String groupId) {
        List<AbstractFormula> list = new ArrayList<>(1);
        FormulaTerminalString formulaTerminalString = new FormulaTerminalString();
        formulaTerminalString.setConstantValue(groupId);
        list.add(formulaTerminalString);
        return list;
    }

    /**
     * Is this valid ?<br>
     * This is valid if :
     * <ol>
     * <li>the {@link GroupFormula#getGroupRule(Evaluator)} is not null</li>
     * <li>the {@link GroupFormula#getReturnType(Evaluator)} is {@link FormulaReturnType#INTEGER} or
     * {@link FormulaReturnType#NUMERIC}</li>
     * </ol>
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return true if this is valid, false otherwise
     * @see FormulaDescription#isValid(List, Evaluator)
     */
    @JsonIgnore
    @Override
    public boolean isValid(Evaluator evaluator) {
        if (doGetGroupRule(evaluator) == null) {
            return false;
        } else if (!(getReturnType(evaluator).equals(FormulaReturnType.INTEGER) || getReturnType(evaluator).equals(
                FormulaReturnType.NUMERIC))) {
            return false;
        }
        return true;
    }

    /**
     * Validate this with a list of {@link AbstractFormula parameters}
     *
     * @param parameters the list of parameters to be validated
     * @param evaluator  the evaluator to be used during the validation process, can be null
     * @throws GLanguageException if this is not valid
     * @see FormulaDescription#validate(AbstractFormula, List, Evaluator)
     */
    @JsonIgnore
    @Override
    public void validate(List<AbstractFormula> parameters, Evaluator evaluator) throws GLanguageException {
        if (parameters == null || parameters.isEmpty()) {
            // TODO throw a GLanguageException
        } else if (parameters.size() > 1) {
            // TODO throw a GLanguageException
        } else if (parameters.get(0) == null) {
            // TODO throw a GLanguageException
        } else {
            String groupId = null;
            try {
                groupId = parameters.get(0).getStringValue();
            } catch (GLanguageException e) {
                // TODO add outer error to the exception and rethrow
            }
            if (groupId == null || groupId.isEmpty()) {
                // TODO throw a GLanguageException
            } else if (evaluator != null) {
                RuleVersion groupRule = evaluator.getRuleVersion(groupId);
                if (groupRule == null) {
                    // TODO throw a GLanguageException
                } else {
                    Set<FormulaReturnType> returnTypes = getGroupSubRulesReturnTypes(groupRule, evaluator);
                    if (returnTypes.contains(FormulaReturnType.BOOLEAN) || returnTypes
                            .contains(FormulaReturnType.STRING) || returnTypes
                            .contains(FormulaReturnType.DATE) || returnTypes
                            .contains(FormulaReturnType.DURATION) || returnTypes
                            .contains(FormulaReturnType.PROCEDURE)) {
                        // TODO throw a GLanguageException
                    }
                }
            }
        }
    }

    /**
     * Get the return type<br>
     * The return type of this type of formula is determined by the type of the referenced rule
     * {@link FormulaRuleReference#getReferencedRule(Evaluator)}
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return the return type of the {@link FormulaRuleReference#getReferencedRule(Evaluator)}, or
     * {@link FormulaReturnType#UNDEFINED} if it does not exist or if an error occurs during the evaluation process
     */
    @Transient
    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) {
        if (doGetGroupRule(evaluator) != null) {
            Set<FormulaReturnType> returnTypes = getGroupSubRulesReturnTypes(doGetGroupRule(evaluator), evaluator);
            if (returnTypes.size() == 1) {
                return returnTypes.iterator().next();
            } else {
                return FormulaReturnType.NUMERIC;
            }
        } else {
            return FormulaReturnType.UNDEFINED;
        }
    }

    @JsonIgnore
    @Transient
    private Set<FormulaReturnType> getGroupSubRulesReturnTypes(RuleVersion groupRule, Evaluator evaluator) {
        return groupRule.getGroupItems().stream().map(i -> i
                .getReferencedRule(evaluator).getReturnType(evaluator)).distinct().collect(Collectors.toSet());
    }

    /**
     * Get the group rule
     *
     * @return the groupRule
     */
    @JsonIgnore
    @Transient
    public RuleVersion getGroupRule() {
        return groupRule;
    }

    /**
     * Get the group rule with an evaluator (can be null)
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return the group rule if it exists
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the group rule doesn't exist
     */
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
     * Get the group rule with an evaluator (can be null)
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return the group rule if it exists, null otherwise
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
     * Get the rules contained in the group identified by first parameter, with an evaluator (can be null)
     *
     * @param evaluator the evaluator to be used during the validation process, can be null
     * @return The list of rules contained in the group identified by first parameter
     * @throws IllegalAccessError if {@code groupRule} is null - branching has not been done
     */
    @JsonIgnore
    @Transient
    public List<RuleVersion> getRulesInGroup(Evaluator evaluator) throws GLanguageException {
        if (doGetGroupRule(evaluator) == null) {
            // TODO replace by a GLanguageException
            throw new IllegalAccessError("Cannot invoke getRulesInGroup() method on " + this.getClass()
                    .getName() + " object while referenced rule (version id : " + getConstantValue() + ") is not set " +
                                                 "" + "" + "- while branching is not done");
        }
        return doGetGroupRule(evaluator).getGroupItems().stream().map(i -> i.getReferencedRule(evaluator)).collect(
                Collectors.toList());
    }

    /**
     * Get the value as {@link Integer}<br>
     * Calling this method is equivalent to calling {@link GroupFormula#doGetNumericValue(Evaluator)} method and
     * applying {@link Double#intValue()} on the result
     *
     * @param evaluator the evaluator to be used in the evaluation process, can be null
     * @return the value as {@link Integer} - the integer part of the value as {@link Double}
     * @throws GLanguageException if an error occurs during the evaluation process, e.g. if the group rule doesn't
     * exist or if the type of at least one sub-rule is not {@link FormulaReturnType#INTEGER} or
     * {@link FormulaReturnType#NUMERIC}
     */
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
