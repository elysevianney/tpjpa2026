package jpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.lang.Object;

@Entity
@Table(name = "books")
public class Book extends LibraryItem {

    @Column(nullable = false)
    private String author;

    public Book() {
    }

    public Book(String media, String title, String author) {
        super(media, title);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

