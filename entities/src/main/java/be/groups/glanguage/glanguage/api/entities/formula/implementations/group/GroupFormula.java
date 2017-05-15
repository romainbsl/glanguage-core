package be.groups.glanguage.glanguage.api.entities.formula.implementations.group;

import be.groups.glanguage.glanguage.api.entities.evaluation.Evaluator;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractFormula;
import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.description.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;
import be.groups.glanguage.glanguage.api.error.GLanguageErrorRegistry;
import be.groups.glanguage.glanguage.api.error.exception.GLanguageException;
import be.groups.glanguage.glanguage.api.error.formula.FormulaInnerError;
import be.groups.glanguage.glanguage.api.error.formula.base.parameter.FormulaNullParameterInnerError;
import be.groups.glanguage.glanguage.api.error.formula.base.unable.FormulaReturnTypeInnerError;
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

    public GroupFormula(FormulaDescription description, String groupId) throws GLanguageException {
        super(description, null);
        if (groupId == null || groupId.isEmpty()) {
            throw new GLanguageException(new FormulaNullParameterInnerError(this, null, "constructor", 1));
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

    @Transient
    @Override
    public boolean isValid(List<AbstractFormula> parameters, Evaluator evaluator) throws GLanguageException {
        /*
         * WORKAROUND
         * It is not allowed to have checked exceptions thrown within a lambda expression without catching it within
         * the lambda expression -> Blame Oracle for that !
         * Therefore, the workaround consists in catching the checked exception inside of the lambda expression,
         * wrapping it into an unchecked exception (e.g. RuntimeException), throwing it, surrounding the whole lambda
         * into another try-catch block, catching the unchecked exception outside of the lambda expression and
         * finally handling it
         */
        try{
            if (groupRule != null) {
                Set<FormulaReturnType> returnTypes = groupRule.getGroupItems().stream()
                        .map(i -> {
                            try {
                                return i.getReferencedRule(null).getReturnType(null);
                            } catch (GLanguageException e) {
                                throw new RuntimeException(e);
                            }
                        }).distinct().collect(Collectors.toSet());
                if (returnTypes.stream()
                        .allMatch(e -> Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.NUMERIC).contains(e))) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            if (e.getCause() instanceof GLanguageException) {
                GLanguageException gLanguageException = (GLanguageException) e.getCause();
                gLanguageException.getError()
                        .setOuterError(new FormulaInnerError(GLanguageErrorRegistry.FORMULA_INNER_ERROR,
                                                             this,
                                                             null,
                                                             "isValid",
                                                             null));
                throw gLanguageException;
            }
            throw e;
        }

        return true;
    }

    @Transient
    @Override
    public FormulaReturnType getReturnType(Evaluator evaluator) throws GLanguageException {
        /*
         * WORKAROUND
         * It is not allowed to have checked exceptions thrown within a lambda expression without catching it within
         * the lambda expression -> Blame Oracle for that !
         * Therefore, the workaround consists in catching the checked exception inside of the lambda expression,
         * wrapping it into an unchecked exception (e.g. RuntimeException), throwing it, surrounding the whole lambda
         * into another try-catch block, catching the unchecked exception outside of the lambda expression and
         * finally handling it
         */
        try {
            if (groupRule != null) {
                Set<FormulaReturnType> returnTypes = groupRule.getGroupItems().stream()
                        .map(i -> {
                            try {
                                return i.getReferencedRule(evaluator).getReturnType(evaluator);
                            } catch (GLanguageException e) {
                                throw new RuntimeException(e);
                            }
                        }).distinct()
                        .collect(Collectors.toSet());
                if (returnTypes.size() == 1) {
                    return returnTypes.iterator().next();
                } else {
                    return FormulaReturnType.NUMERIC;
                }
            } else {
                return FormulaReturnType.UNDEFINED;
            }
        } catch (Exception e) {
            if (e.getCause() instanceof GLanguageException) {
                GLanguageException gLanguageException = (GLanguageException) e.getCause();
                gLanguageException.getError()
                        .setOuterError(new FormulaReturnTypeInnerError(this,
                                                                       null));
                throw gLanguageException;
            }
            throw e;
        }
    }

    @Override
    public String asText() {
        return operationAsText() + "(" + getConstantValue() + ")";
    }

    public abstract String operationAsText();

}
