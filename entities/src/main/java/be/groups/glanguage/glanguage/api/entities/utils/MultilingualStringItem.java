package be.groups.glanguage.glanguage.api.entities.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * This class represents an item of a {@link MultilingualString} <br>
 * It has a {@link Language} and a text representing the translation in that language
 *
 * @author michotte
 * @see MultilingualString
 * @see Language
 */
@Entity
@Table(name = "MULTILINGUAL_STRING_ITEM")
public class MultilingualStringItem {

     /*
     * Fields
     */
    private Integer id;
    private MultilingualString multilingualString;
    private Language language;
    private String text;

    /*
     * Constructors
     */
    public MultilingualStringItem() {
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
    public Integer getId() {
        return id;
    }

    /**
     * Get the {@link MultilingualString} that owns this
     *
     * @return the {@link MultilingualString} that owns this
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "MULTILINGUAL_STRING_ID", referencedColumnName = "ID")
    public MultilingualString getMultilingualString() {
        return multilingualString;
    }

    /**
     * Get the {@link Language}
     *
     * @return the {@link Language}
     */
    @Column(name = "LANGUAGE", nullable = false, updatable = false)
    @Convert(converter = LanguageConverter.class)
	public Language getLanguage() {
        return language;
    }

    /**
     * Get the text
     *
     * @return the text
     */
    @Column(name = "TEXT")
    public String getText() {
        return text;
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
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Set the owning multilingualString
     *
     * @param multilingualString the owning multilingualString to set
     */
    public void setMultilingualString(MultilingualString multilingualString) {
        this.multilingualString = multilingualString;
    }

    /**
     * Set the language
     *
     * @param language the language to set
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * Set the text
     *
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /*
     * Utils
     */
    @Override
    public String toString() {
        return "MultilingualStringItem{" + "language=" + language + ", text='" + text + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MultilingualStringItem)) return false;

        MultilingualStringItem that = (MultilingualStringItem) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
