package be.groups.glanguage.core.entities.rule.definition;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Factory class for {@link DefinitionLevel} enum
 */
public class DefinitionLevelFactory {
	
	private static List<DefinitionLevel> parameterDefinitionLevelsOrderedByPriority;
	
	public static List<DefinitionLevel> getParameterDefinitionLevelsOrderedByPriority() {
		if (parameterDefinitionLevelsOrderedByPriority == null) {
			parameterDefinitionLevelsOrderedByPriority = Arrays.asList(DefinitionLevel.values()).stream()
					.filter(dl -> dl.getPriority() != 0)
					.sorted(Comparator.comparingInt(DefinitionLevel::getPriority))
					.collect(Collectors.toList());
		}
		return parameterDefinitionLevelsOrderedByPriority;
	}
	
}
