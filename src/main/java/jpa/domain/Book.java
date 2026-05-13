package jpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.lang.Object;

@Entity
public class Book extends Element implements Serializable {


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

