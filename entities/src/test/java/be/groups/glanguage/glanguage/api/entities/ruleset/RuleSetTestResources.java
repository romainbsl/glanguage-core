package be.groups.glanguage.glanguage.api.entities.ruleset;

import java.util.HashSet;

public class RuleSetTestResources {

	protected static final RuleSet rs1;
	
	static {
		/* Creation */
		rs1 = new RuleSet();
		rs1.setId(1L);
		rs1.setVersions(new HashSet<>());
		rs1.getVersions().add(RuleSetVersionTestResources.rs1v1);
		rs1.getVersions().add(RuleSetVersionTestResources.rs1v2);
	}
	
}
