package be.groups.glanguage.glanguage.api.entities.rule.definition;

/**
 * Definition level's of RuleDefinition's
 * 
 * @author michotte
 */
public enum DefinitionLevel {

	DEFAULT (1, "DEFAULT", "no parameters", 0), 
	SOCIAL_SECRETARY (2, "DEFAULT", "no parameters", 1), 
	EMPLOYER (3, "DEFAULT", "no parameters", 2), 
	JOINT_COMMITTEE (4, "DEFAULT", "no parameters", 3), 
	COLLECTIVE_LABOR_AGREEMENT (5, "DEFAULT", "no parameters", 4),
	CUSTOM (99, "DEFAULT", "no parameters", 0); 

	private int id;
	private String name;
	private String description;
	private int priority;
	
	private DefinitionLevel(int id, String name, String description, int priority) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.priority = priority;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
}
