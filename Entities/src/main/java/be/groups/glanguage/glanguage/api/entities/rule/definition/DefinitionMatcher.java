/**
 * 
 */
package be.groups.glanguage.glanguage.api.entities.rule.definition;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author michotte
 */
public class DefinitionMatcher {
	
	/**
	 * Does {@code first} match {@code second} ?<br>
	 * {@code first} matches {@code second} if :
	 * <ul>
	 * <li>{@code first} is an empty collection</li>
	 * <li>OR</li>
	 * <li>for each different level of {@code first}'s parameters, there is at least one parameter
	 * that matches one of the {@code second}'s parameters</li>
	 * </ul>
	 * 
	 * @param first
	 * @param second
	 * @return true if {@code first} matches {@code second}, false otherwise
	 */
	public static boolean match(Collection<RuleDefinitionParameter> first, Collection<RuleDefinitionParameter> second) {
		if (first.isEmpty()) {
			return true;
		} else {
			boolean match = true;
			List<RuleDefinitionParameter> socialSecretariatParameters =
					first.stream().filter(f -> f.getLevel() == DefinitionLevel.SOCIAL_SECRETARY).collect(Collectors.toList());
			if (!socialSecretariatParameters.isEmpty()) {
				match = socialSecretariatParameters.stream().anyMatch(f -> second.stream().anyMatch(s -> s.match(f)));
			}
			if (match) {
				List<RuleDefinitionParameter> employerParameters =
						first.stream().filter(f -> f.getLevel() == DefinitionLevel.EMPLOYER).collect(Collectors.toList());
				if (!employerParameters.isEmpty()) {
					match = employerParameters.stream().anyMatch(f -> second.stream().anyMatch(s -> s.match(f)));
				}
				if (match) {
					List<RuleDefinitionParameter> jointCommitteeParameters =
							first.stream().filter(f -> f.getLevel() == DefinitionLevel.JOINT_COMMITTEE).collect(Collectors.toList());
					if (!jointCommitteeParameters.isEmpty()) {
						match = jointCommitteeParameters.stream().anyMatch(f -> second.stream().anyMatch(s -> s.match(f)));
					}
					if (match) {
						List<RuleDefinitionParameter> collectiveLaborAgreementParameters =
								first.stream().filter(f -> f.getLevel() == DefinitionLevel.COLLECTIVE_LABOR_AGREEMENT)
										.collect(Collectors.toList());
						if (!collectiveLaborAgreementParameters.isEmpty()) {
							match = collectiveLaborAgreementParameters.stream()
									.anyMatch(f -> second.stream().anyMatch(s -> s.match(f)));
						}
					}
				}
			}
			return match;
		}
	}
	
}
