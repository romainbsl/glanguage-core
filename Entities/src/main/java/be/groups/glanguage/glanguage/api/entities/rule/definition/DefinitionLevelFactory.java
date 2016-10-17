package be.groups.glanguage.glanguage.api.entities.rule.definition;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DefinitionLevelFactory {
	
	private static List<DefinitionLevel> parameterDefinitionLevelsOrderedByPriority;
	
	public static List<DefinitionLevel> getParameterDefinitionLevelsOrderedByPriority() {
		if (parameterDefinitionLevelsOrderedByPriority == null) {
			parameterDefinitionLevelsOrderedByPriority = Arrays.asList(DefinitionLevel.values()).stream()
					.filter(dl -> dl.getPriority() != 0)
					.sorted(new Comparator<DefinitionLevel>() {
						@Override
						public int compare(DefinitionLevel o1, DefinitionLevel o2) {
							return o1.getPriority() - o2.getPriority();
						}
					})
					.collect(Collectors.toList());
		}
		return parameterDefinitionLevelsOrderedByPriority;
	}
	
}
