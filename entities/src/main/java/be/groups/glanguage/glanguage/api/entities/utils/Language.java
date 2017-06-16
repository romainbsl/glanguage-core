package be.groups.glanguage.glanguage.api.entities.utils;

/**
 * Created by michotte on 4/05/2017.
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
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /*
     * Setters
     */
    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}
