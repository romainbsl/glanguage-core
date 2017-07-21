package be.groups.glanguage.glanguage.api.entities.utils;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * This class represents a multilingual string.
 * It has a set of {@link MultilingualStringItem}'s
 * Created by michotte on 4/05/2017.
 */
@Entity
@Table(name = "MULTILINGUAL_STRING")
public class MultilingualString {

     /*
     * Fields
     */
    private Integer id;
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
    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "Perform", sequenceName = "SQ_TB_MULTILINGUAL_STRING_ID", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "Perform")
    public Integer getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @OneToMany(mappedBy = "multilingualString", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<MultilingualStringItem> getItems() {
        return items;
    }

    /*
     * Setters
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItems(Set<MultilingualStringItem> items) {
        this.items = items;
    }

    /*
     * Methods
     */
    /**
     * Returns the text of the {@link MultilingualStringItem} whose {@link MultilingualStringItem#getLanguage()} is
     * {@link Language#EN} if it exists.<br>If not, returns the text of the first item arbitrarily if it exists. <br>
     * If not, returns an empty string.
     *
     * @return the text of the {@link MultilingualStringItem} whose {@link MultilingualStringItem#getLanguage()} is
     * {@link Language#EN} if it exists.<br>If not, returns the text of the first item arbitrarily if it exists. <br>
     * If not, returns an empty string.
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
     * Returns the text of the {@link MultilingualStringItem} whose {@link MultilingualStringItem#getLanguage()} is
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

    /*
     * Utils
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MultilingualString{" + "name='" + name + '\'' + ", items=");
        for (MultilingualStringItem item : items) {
            sb.append("\n\t" + item.toString());
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MultilingualString)) return false;

        MultilingualString that = (MultilingualString) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return items != null ? items.equals(that.items) : that.items == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }
}
