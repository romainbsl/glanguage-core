package be.groups.glanguage.glanguage.api.entities.formula.description;

/**
 * Created by michotte on 5/05/2017.
 */
public enum FormulaPosition {

    /*
     * Enum constants
     */
    NONE (0, "NONE", "no textual representation"),
    PREFIX_BRACKET (1, "PREFIX_BRACKET", "before the parameters with parameters under brackets and separated by semicolons"),
    PREFIX (2, "PREFIX", "before parameters"),
    INFIX (3, "INFIX", "between parameters"),
    POSTFIX (4, "POSTFIX", "after parameters"),
    POSTFIX_DOT (5, "POSTFIX_DOT", "after parameters with the dot (.) notation"),
    CUSTOM (6, "CUSTOM", "parameters shown at <paramx> positions");

    /*
     * Fields
     */
    private int id;
    private String name;
    private String description;

    /*
     * Constructors
     */
    FormulaPosition(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /*
     * Getters
     */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
