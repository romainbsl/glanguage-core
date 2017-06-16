package be.groups.glanguage.glanguage.api.entities.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * This class represents an item of a multilingual string.
 * It has a {@link Language} and a text representing the translation in that language
 * Created by michotte on 4/05/2017.
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
    @Id
    public Integer getId() {
        return id;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "MULTILINGUAL_STRING_ID", referencedColumnName = "ID")
    public MultilingualString getMultilingualString() {
        return multilingualString;
    }

    @Column(name = "LANGUAGE", nullable = false, updatable = false)
    @Convert(converter = LanguageConverter.class)
	public Language getLanguage() {
        return language;
    }

    @Column(name = "TEXT")
    public String getText() {
        return text;
    }

    /*
     * Setters
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public void setMultilingualString(MultilingualString multilingualString) {
        this.multilingualString = multilingualString;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

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
