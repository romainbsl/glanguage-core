package be.groups.glanguage.glanguage.api.entities.formula.implementations.group;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Transient;

import be.groups.glanguage.glanguage.api.entities.formula.AbstractNonTerminalFormula;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaDescription;
import be.groups.glanguage.glanguage.api.entities.formula.FormulaReturnType;
import be.groups.glanguage.glanguage.api.entities.rule.RuleVersion;

@Entity
public abstract class GroupFormula extends AbstractNonTerminalFormula {
	
	private static final Set<FormulaReturnType> authorizedParametersTypes =
			new HashSet<>(Arrays.asList(FormulaReturnType.INTEGER, FormulaReturnType.NUMERIC));
			
	private RuleVersion groupRule;
	
	public GroupFormula() {
		super();
	}
	
	public GroupFormula(FormulaDescription description, String groupId) {
		super(description);
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
	public List<RuleVersion> getRulesInGroup() {
		if (groupRule == null) {
			throw new IllegalAccessError("Cannot invoke getRulesInGroup() method on " + this.getClass().getName()
					+ " object while referenced rule (version id : " + getConstantValue()
					+ ") is not set - while branching is not done");
		}
		return groupRule.getGroupItems().stream().map(i -> i.getEffectiveRule()).collect(Collectors.toList());
	}
	
	@Transient
	@Override
	public Integer getIntegerValueImpl() {
		return getNumericValue().intValue();
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
	protected FormulaReturnType computeReturnType() {
		if (groupRule != null) {
			Set<FormulaReturnType> returnTypes =
					groupRule.getGroupItems().stream().map(i -> i.getEffectiveRule().getReturnType()).distinct()
							.collect(Collectors.toSet());
			if (returnTypes.stream().allMatch(e -> getAuthorizedParametersTypes().contains(e))) {
				if (returnTypes.size() == 1) {
					return returnTypes.iterator().next();
				} else {
					return FormulaReturnType.NUMERIC;
				}
			} else {
				throw new IllegalArgumentException("All rules in referenced rule group  (version id : " + getConstantValue()
						+ ") must be of type INTEGER or NUMERIC");
			}
		} else {
			return FormulaReturnType.UNDEFINED;
		}
	}
	
	@Transient
	@Override
	protected Set<FormulaReturnType> getAuthorizedParametersTypes() {
		return authorizedParametersTypes;
	}
	
	@Transient
	@Override
	protected boolean isParametersCombinationAuthorized() {
		return areParametersAuthorized();
	}
	
	@Override
	public String asText() {
		return operationAsText() + "(" + getParameters().get(0).asText() + ")";
	}
	
	public abstract String operationAsText();
	
}
