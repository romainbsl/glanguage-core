package be.groups.glanguage.core.entities.utils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * This class represents a multilingual string.
 * It has a set of {@link MultilingualStringItem}'s
 * @author michotte
 */
@Entity
@Table(name = "MULTILINGUAL_STRING")
public class MultilingualString {

     /*
     * Fields
     */
    private Long id;
    private String name;
    private Set<MultilingualStringItem> items;

    /*
     * Constructors
     */
    public MultilingualString() {
        super();
    }

    /*
     * Getters
     */

    /**
     * Get the technical id
     *
     * @return the technical id
     */
    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "Perform", sequenceName = "SQ_TB_MULTILINGUAL_STRING_ID", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "Perform")
    public Long getId() {
        return id;
    }

    /**
     * Get the name
     *
     * @return teh name
     */
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    /**
     * Get the set of items
     *
     * @return the set of items
     */
    @OneToMany(mappedBy = "multilingualString", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<MultilingualStringItem> getItems() {
        return items;
    }

    /**
     * Get the item for which the {@link MultilingualStringItem#getLanguage()} equals the {@code language} passed if
     * it exists, null otherwise
     *
     * @param language the language of the item to be returned
     * @return the item for which the {@link MultilingualStringItem#getLanguage()} equals the {@code language} passed if
     * it exists, null otherwise
     */
    @Transient
    public MultilingualStringItem getItem(Language language) {
        return items.stream().filter(i -> i.getLanguage().equals(language)).findFirst().orElse(null);
    }

    /*
     * Setters
     */

    /**
     * Set the technical id <br>
     * This method is for Hibernate needs. It should never be used. <br>
     * Technical ids are auto-generated via a database sequence
     *
     * @param id the technical id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Set the name
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the set of items
     *
     * @param items the set of items to set
     */
    public void setItems(Set<MultilingualStringItem> items) {
        this.items = items;
    }

    /*
     * Methods
     */
    /**
     * Get the text of the {@link MultilingualStringItem} whose {@link MultilingualStringItem#getLanguage()} is
     * {@link Language#EN} if it exists.<br>If not, returns the text of the first item arbitrarily if it exists. <br>
     * If not, returns an empty string.
     *
     * @return the text of the {@link MultilingualStringItem} whose {@link MultilingualStringItem#getLanguage()} is
     * {@link Language#EN} if it exists.<br>If not, returns the text of the first item arbitrarily if it exists. <br>
     * If not, an empty string.
     */
    public String asText() {
        String textEN = asText(Language.EN);
        if (textEN.isEmpty()) {
            Optional<MultilingualStringItem> item = items.stream().findFirst();
            if (item.isPresent()) {
                return item.get().getText();
            }
        }
        return "";
    }

    /**
     * Get the {@link MultilingualStringItem#text} whose {@link MultilingualStringItem#getLanguage()} is
     * equal to {@code language} parameter if it exists.<br>If not, returns an empty string.
     *
     * @return the text of the {@link MultilingualStringItem} whose {@link MultilingualStringItem#getLanguage()} is
     * equal to {@code language} parameter if it exists.<br>If not, an empty string.
     */
    public String asText(Language language) {
        Optional<MultilingualStringItem> item = items.stream().filter(i -> i.getLanguage() == language)
                .findFirst();
        if (item.isPresent()) {
            return item.get().getText();
        }
        return "";
    }

    /**
     * Add a {@link MultilingualStringItem} in {@link MultilingualString#items} and add the
     * reference {@link MultilingualString} as parent into
     * {@link MultilingualStringItem#multilingualString}
     *
     * @param language the language of the item to be inserted
     * @param text the test of the item to be inserted
     */
    @Transient
    public void addItem(Language language, String text) {
        MultilingualStringItem item = new MultilingualStringItem();
        item.setLanguage(language);
        item.setText(text);
        item.setMultilingualString(this);

        if (items == null) items = new HashSet<>();

        items.add(item);
    }

    /*
     * Utils
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MultilingualString{" + "name='" + name + '\'' + ", items=");
        for (MultilingualStringItem item : items) {
            sb.append("\n\t")
              .append(item.toString());
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MultilingualString)) return false;

        MultilingualString that = (MultilingualString) o;

        return id.equals(that.id);
    }

  @Override
  public int hashCode() {
    int result = 31;
    if(id != null) result += id.hashCode();
    return result;
  }
}
