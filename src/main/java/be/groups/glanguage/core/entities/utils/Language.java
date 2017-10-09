package be.groups.glanguage.core.entities.utils;

/**
 * Enum representing the languages
 *
 * @author michotte
 */
public enum Language {

    /*
	 * Enum constants
	 */
    EN("EN", "English"),
    FR("FR", "Français"),
    NL("NL", "Nederlands"),
    X("X", "Other");

    /*
     * Fields
     */
    private String code;
    private String name;

    /*
     * Constructors
     */
    private Language(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /*
     * Getters
     */

    /**
     * Get the code
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Get the name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /*
     * Setters
     */

    /**
     * Set the code
     *
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Set the name
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
