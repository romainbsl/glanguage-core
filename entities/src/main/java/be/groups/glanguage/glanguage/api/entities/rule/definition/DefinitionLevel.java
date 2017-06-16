package be.groups.glanguage.glanguage.api.entities.rule.definition;

/**
 * Definition level's of RuleDefinition's
 * 
 * @author michotte
 */
public enum DefinitionLevel {
	
	DEFAULT(0, "DEFAULT", "definition with no parameters", 0),
	SOCIAL_SECRETARY(1, "SOCIAL_SECRETARY",
			"social secretary level parameter / defintion with social secretary level parameter(s) only", 1),
	EMPLOYER(2, "EMPLOYER", "employer level parameter / defintion with employer level parameter(s) only", 2),
	JOINT_COMMITTEE(3, "JOINT_COMMITTEE", "joint committee level parameter / defintion with joint committee level parameter(s) only",
			3),
	COLLECTIVE_LABOR_AGREEMENT(4, "COLLECTIVE_LABOR_AGREEMENT",
			"collective labor agreement level parameter / defintion with collective labor agreement level parameter(s) only", 4),
	CUSTOM(999, "CUSTOM", "definition with parameters of distinct level", 0);
	
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
