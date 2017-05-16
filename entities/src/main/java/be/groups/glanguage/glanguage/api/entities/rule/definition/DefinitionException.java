package be.groups.glanguage.glanguage.api.entities.rule.definition;

import be.groups.glanguage.glanguage.api.entities.rule.RuleDefinition;

import java.util.Collection;

public class DefinitionException extends RuntimeException {
	
	private static final long serialVersionUID = -1621711322318220828L;
	
	public DefinitionException(Collection<RuleDefinitionParameter> parameters,
			Collection<RuleDefinition> remainingRuleDefinitions) {
		super(buildMessage(parameters, remainingRuleDefinitions));
	}

	private static String buildMessage(Collection<RuleDefinitionParameter> parameters,
			Collection<RuleDefinition> remainingRuleDefinitions) {
		StringBuilder sb = new StringBuilder();
		sb.append("Unable to select a definition for parameters ");
		sb.append(parameters);
		if (remainingRuleDefinitions != null && !remainingRuleDefinitions.isEmpty()) {
    		sb.append(", remaining definitions : ");
    		remainingRuleDefinitions.stream().forEach(e -> sb.append("\n * " + e));
		}
		return sb.toString();
	}
	
}
