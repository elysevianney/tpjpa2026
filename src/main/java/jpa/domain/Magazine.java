package jpa.domain;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class Magazine extends Element implements Serializable {

    private LocalDate datePublication;

    public Magazine() {
    }

    public Magazine(String media, String title, LocalDate datePublication) {
        super(media, title);
        this.datePublication = datePublication;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }
}

