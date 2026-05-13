package jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "elements")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Element implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String media;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private List<Borrow> borrowings = new ArrayList<>();

    @ManyToMany(mappedBy = "wishlist")
    @JsonIgnore
    private List<User> wishedBy = new ArrayList<>();

    public Element() {
    }

    public Element(String media, String title) {
        this.media = media;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Borrow> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(List<Borrow> borrowings) {
        this.borrowings = borrowings;
    }

    public List<User> getWishedBy() {
        return wishedBy;
    }

    public void setWishedBy(List<User> wishedBy) {
        this.wishedBy = wishedBy;
    }
}
