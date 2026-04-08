package jpa.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "magazines")
public class Magazine extends LibraryItem {

    @Column(nullable = false)
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

